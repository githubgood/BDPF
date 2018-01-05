package com.xunfang.utils.office.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;
import org.mozilla.universalchardet.UniversalDetector;
import org.springframework.stereotype.Service;

import com.aspose.pdf.Document;
import com.aspose.pdf.Font;
import com.aspose.pdf.FontRepository;
import com.aspose.pdf.Page;
import com.aspose.pdf.SaveFormat;
import com.aspose.pdf.TextFragment;
import com.aspose.pdf.text.FontTypes;
import com.xunfang.utils.office.domain.ConvertStatus;
import com.xunfang.utils.office.service.File2PdfService;
import com.xunfang.utils.office.util.AsposeLicenseUtil;

/**
 * 将TXT转换为PDF文件。
 * <br/>
 * 由于直接调用Aspose.pdf组件将TXT写入PDF存在乱码问题，所以按以下步骤完成。
 * <br/>
 * <ul>
 * <li>1. 将txt写入到word</li>
 * <li>2. 将word转成pdf</li>
 * </ul>
 *
 * @author wanglf
 * @version 1.0.0
 *          2017-7-3 21:17
 * @since Jdk1.7
 */
@Service
public class Txt2PdfServiceImpl implements File2PdfService {

    // private UniversalDetector detector = new UniversalDetector(null); // 用全局的可能还得加锁

    private Logger logger = Logger.getLogger(getClass());

    /**
     * simsun 宋体
     * simkai 楷体
     */
    private Font font = FontRepository.openFont(getClass().getClassLoader().getResourceAsStream("fonts/simsun.ttf"), FontTypes.TTF);

    @Override
    public ConvertStatus convert2Pdf(InputStream inputStream, OutputStream outputStream) {
        DetectorData detectorData = null;
        try {
            if (AsposeLicenseUtil.setPdfLicense()) {
                long start = System.currentTimeMillis();
                // 获取输入流的编码格式
                detectorData = detectEncoding(inputStream);
//                if (StringUtil.isEmpty(detectorData.getEncoding())) {
                    detectorData.setEncoding("UTF-8"); // set default encoding
//                }

                // 创建pdf文档
                Document pdfDoc = new Document();
                // pdfDoc.setLinearized(true); // 优化压缩pdf
                Page newPage = pdfDoc.getPages().add();

                // pdf写入至输出流
                copy2Page(detectorData.getInputStream(), detectorData.getEncoding(), newPage);
                pdfDoc.save(outputStream, SaveFormat.Pdf);

                long end = System.currentTimeMillis();
                logger.debug("convert doc2pdf completed, elapsed " + (end - start) / 1000.0 + " seconds!");
                return ConvertStatus.SUCCESS;
            } else {
                return ConvertStatus.LICENSE_ERROR;
            }
        } catch (UnsupportedEncodingException e) {
            logger.error("encoding " + detectorData.getEncoding() + " UnSupport", e);
            return ConvertStatus.CONVERT_ENCODING_ERROR;
        } catch (IOException e) {
            logger.error("convert2pdf error!", e);
            return ConvertStatus.CONVERT_TXT2PDF_ERROR;
        }
    }

    /**
     * 将输入流的数据添加到page中
     *
     * @param inputStream 输入流
     * @param encoding    输入流所采用的文件编码
     * @param newPage     page
     * @throws IOException
     */
    private void copy2Page(InputStream inputStream, String encoding, Page newPage) throws IOException {
        // 读取文本内容，按段落加入pdf文档中
        String line;
        int lineNum = 1;
        StringBuilder textBuilder = new StringBuilder();
        String lineSeparator = System.getProperty("line.separator");
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, encoding));

        while ((line = reader.readLine()) != null) {
            textBuilder.append(line + lineSeparator);

            if (lineNum % 200 == 0) {
                addNewParagraph(newPage, textBuilder);
                logger.debug("submit " + lineNum + " rows");
            }
            lineNum++;
        }
        if (textBuilder.length() > 0) {
            addNewParagraph(newPage, textBuilder);
            logger.debug("submit " + lineNum + " rows");
        }
    }

    /**
     * 将textBuilder中的内容添加到page中
     *
     * @param page
     * @param textBuilder
     */
    private void addNewParagraph(Page page, StringBuilder textBuilder) {
        TextFragment text = new TextFragment(textBuilder.toString());
        text.getTextState().setFont(font);
        page.getParagraphs().add(text);
        textBuilder.delete(0, textBuilder.length());
    }

    /**
     * 检测输入流的文件编码
     *
     * @param inputStream 输入流
     * @return 文件编码，如UTF-8
     * @throws IOException
     */
    private DetectorData detectEncoding(InputStream inputStream) {
        DetectorData detectorData = new DetectorData();

        int nread;
        byte[] buf = new byte[1024];
        UniversalDetector detector = new UniversalDetector(null);

        try {
//             使用BufferedInputStream，在读取了字节的编码后，用于重置输入流
            BufferedInputStream bis = new BufferedInputStream(inputStream, 1024);
            bis.mark(8192 * 2);

            while ((nread = bis.read(buf)) > 0 && !detector.isDone()) {
                detector.handleData(buf, 0, nread);
            }
            detector.dataEnd();

            bis.reset();
            detectorData.setInputStream(bis);
        } catch (IOException e) {
            logger.error("detect encoding error!", e);
            return null;
        }

        // 获取编码
        detectorData.setEncoding(detector.getDetectedCharset());
        return detectorData;

        // 重置detector
        // detector.reset();
        // return encoding;
    }

    class DetectorData {
        /**
         * 编码
         */
        private String encoding;

        /**
         * 读取完编码后，重置过的流
         */
        private InputStream inputStream;

        public DetectorData(String encoding, InputStream inputStream) {
            this.encoding = encoding;
            this.inputStream = inputStream;
        }

        public DetectorData() {
        }

        public String getEncoding() {
            return encoding;
        }

        public void setEncoding(String encoding) {
            this.encoding = encoding;
        }

        public InputStream getInputStream() {
            return inputStream;
        }

        public void setInputStream(InputStream inputStream) {
            this.inputStream = inputStream;
        }
    }
}

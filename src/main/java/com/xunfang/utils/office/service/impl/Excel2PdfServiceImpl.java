package com.xunfang.utils.office.service.impl;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.aspose.cells.Workbook;
import com.aspose.cells.Worksheet;
import com.xunfang.utils.office.domain.ConvertStatus;
import com.xunfang.utils.office.service.File2PdfService;
import com.xunfang.utils.office.util.AsposeLicenseUtil;

/**
 * 将Excel文件转换为pdf文件
 * @author wanglf
 * @version 1.0.0
 *          2017年7月3日 17:41
 * @since Jdk1.7
 */
@Service
public class Excel2PdfServiceImpl implements File2PdfService {

    private Logger logger = Logger.getLogger(getClass());


    @Override
    public ConvertStatus convert2Pdf(InputStream inputStream, OutputStream outputStream) {
        try {
            if (AsposeLicenseUtil.setCellsLicense()) {
                long start = System.currentTimeMillis();

                Workbook workbook = new Workbook(inputStream);
//                workbook.save(outputStream, SaveFormat.PDF);

                // TODO 当excel宽度太大时，在PDF中会拆断并分页。此处如何等比缩放。
                // 将不同的sheet单独保存为pdf
                //Get the count of the worksheets in the workbook
                int sheetCount = workbook.getWorksheets().getCount();

                //Make all sheets invisible except first worksheet
                for (int i = 1; i < workbook.getWorksheets().getCount(); i++)
                {
                    workbook.getWorksheets().get(i).setVisible(false);
                }
                // workbook.save(outputStream, SaveFormat.PDF);

                //Take Pdfs of each sheet
                for (int j = 0; j < workbook.getWorksheets().getCount(); j++)
                {
                    Worksheet ws = workbook.getWorksheets().get(j);
                    workbook.save("d:/temp_" + ws.getName() + ".pdf");

                    if (j < workbook.getWorksheets().getCount() - 1)
                    {
                        workbook.getWorksheets().get(j + 1).setVisible(true);
                        workbook.getWorksheets().get(j).setVisible(false);
                    }
                }
                
                long end = System.currentTimeMillis();
                logger.debug("convert excel2pdf completed, elapsed " + (end - start) / 1000.0 + " seconds!");
                return ConvertStatus.SUCCESS;
            } else {
                return ConvertStatus.LICENSE_ERROR;
            }
        } catch (Exception e) {
            logger.error("convert excel2pdf error!", e);
            return ConvertStatus.CONVERT_EXCEL2PDF_ERROR;
        }
    }
}

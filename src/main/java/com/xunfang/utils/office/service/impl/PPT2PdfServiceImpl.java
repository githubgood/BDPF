package com.xunfang.utils.office.service.impl;

import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.aspose.slides.Presentation;
import com.aspose.slides.SaveFormat;
import com.xunfang.utils.office.domain.ConvertStatus;
import com.xunfang.utils.office.service.File2PdfService;
import com.xunfang.utils.office.util.AsposeLicenseUtil;

/**
 * 将ppt文件转换为pdf文件
 * @author wanglf
 * @version 1.0.0
 *          2017年7月3日 17:41
 * @since Jdk1.7
 */
@Service
public class PPT2PdfServiceImpl implements File2PdfService {

    private Logger logger = Logger.getLogger(getClass());

    @Override
    public ConvertStatus convert2Pdf(InputStream inputStream, OutputStream outputStream) {
        try {
            if (AsposeLicenseUtil.setSlidesLicense()) {
                long start = System.currentTimeMillis();

                Presentation presentation = new Presentation(inputStream);
                presentation.save(outputStream, SaveFormat.Pdf);

                long end = System.currentTimeMillis();
                logger.debug("convert ppt2pdf completed, elapsed " + (end - start) / 1000.0 + " seconds!");
                return ConvertStatus.SUCCESS;
            } else {
                return ConvertStatus.LICENSE_ERROR;
            }
        } catch (Exception e) {
            logger.error("convert ppt2pdf error!", e);
            return ConvertStatus.CONVERT_PPT2PDF_ERROR;
        }
    }
}

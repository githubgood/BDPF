package com.xunfang.utils.office.service;

import java.io.InputStream;
import java.io.OutputStream;

import com.xunfang.utils.office.domain.ConvertStatus;

/**
 * 文件转换服务，将各类文档文件转换为PDF文件
 * @author wanglf
 * @version 1.0.0
 *          2017年7月3日 15:58
 * @since Jdk1.7
 */
public interface File2PdfService {

    /**
     * 将输入文件转换为PDF文件。
     * <br/>
     * <em>注意：输入及输出流在该方法中不会主动关闭，请调用完成后自己关闭！</em>
     * @param inputStream 输入流
     * @param outputStream 输出流
     * @return ConvertStatus 转换状态
     */
    public ConvertStatus convert2Pdf(InputStream inputStream, OutputStream outputStream);
}

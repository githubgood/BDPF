package com.xunfang.utils.office.service;

import java.io.InputStream;
import java.io.OutputStream;

import com.xunfang.utils.office.domain.ConvertStatus;

/**
 * PDF转换为SWF文件服务
 * @author wanglf
 * @version 1.0.0
 *         2017年7月3日 16:26
 * @since Jdk1.7
 */
public interface Pdf2SwfService {

    /**
     * 将pdf转换为swf文件并输出
     * <br/>
     * <em>注意：输入及输出流在该方法中不会主动关闭，请调用完成后自己关闭！</em>
     * @param inputStream
     * @param outputStream
     * @return
     * @throws Exception
     */
    public ConvertStatus pdf2swf(InputStream inputStream, OutputStream outputStream);
}

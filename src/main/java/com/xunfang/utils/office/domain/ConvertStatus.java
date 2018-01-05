package com.xunfang.utils.office.domain;

/**
 * @author wanglf
 * @version 1.0.0
 *          2017年7月3日 11:34
 * @since Jdk1.7
 */
public enum ConvertStatus {

    // 成功，均以0开头
    SUCCESS(000, "转换成功"),

    // License错误，均以1开头
    LICENSE_ERROR(101, "license无效"),

    // 转换异常，均以2开头
    CONVERT_ERROR(201, "转换失败"),

    // doc2pdf类型异常，以21开头
    CONVERT_DOC2PDF_ERROR(211, "DOC转PDF失败"),

    // excel2pdf类型异常，以22开头
    CONVERT_EXCEL2PDF_ERROR(221, "EXCEL转PDF失败"),

    // ppt2pdf类型异常，以23开头
    CONVERT_PPT2PDF_ERROR(231, "PPT转PDF失败"),

    // txt2pdf类型异常，以24开头
    CONVERT_TXT2PDF_ERROR(241, "TXT转PDF失败"),
    CONVERT_ENCODING_ERROR(242, "获取TXT编码失败，不支持的编码格式"),

    // pdf2swf类型异常，以25开头
    CONVERT_PDF2SWF_ERROR(251, "PDF转SWF错误"),
    CONVERT_PDF2SWF_COMMAND_ERROR(252, "PDF转SWF时执行pdf2swf命令错误"),
    CONVERT_PDF2SWF_IO_ERROR(253, "PDF转SWF时IO错误"),
    CONVERT_PDF2SWF_COMMAND_INTERRUPT_ERROR(254, "PDF转SWF时命令被中断错误"),

    // FTP转换类型异常，以26开头
    CONVERT_FTP_FILE_PATH_EMPTY(261, "FTP文件路径为空"),
    CONVERT_FTP_CONNECT_ERROR(262, "FTP连接失败"),
    CONVERT_FTP_FILE_EXT_ERROR(262, "FTP文件扩展名不存在"),
    CONVERT_FTP_UN_SUPPORT_FILE_ERROR(263, "转换失败，不支持的文件格式"),
    CONVERT_FTP_COMMAND_ERROR(264, "FTP命令执行失败或未完成");

    /**
     * 编码
     */
    private int code;

    /**
     * 编码描述
     */
    private String name;

    private ConvertStatus(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}

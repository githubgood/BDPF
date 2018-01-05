package com.xunfang.utils.office.domain;

/**
 * Swf配置信息
 * @author wanglf
 * @version 1.0.0
 *          2017年7月3日 17:01
 * @since Jdk1.7
 */
public class SwfConfig {

    /**
     * swf.exe或swf.sh文件的完整路径
     */
    private String filePath;

    /**
     * 中文语言目录
     */
    private String languageDir;

    /**
     * 转换质量，取值范围1-100
     */
    private Integer quality;

    /**
     * 获取swf.exe或swf.sh文件的完整路径
     * @return
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置swf.exe或swf.sh文件的完整路径
     * @param filePath
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 获取语言包所在路径
     * @return
     */
    public String getLanguageDir() {
        return languageDir;
    }

    /**
     * 设置语言包路径
     * @param languageDir
     */
    public void setLanguageDir(String languageDir) {
        this.languageDir = languageDir;
    }

    /**
     * 获取转换质量参数
     * @return
     */
    public Integer getQuality() {
        return quality;
    }

    /**
     * 设置转换质量参数
     * @param quality
     */
    public void setQuality(Integer quality) {
        this.quality = quality;
    }
}

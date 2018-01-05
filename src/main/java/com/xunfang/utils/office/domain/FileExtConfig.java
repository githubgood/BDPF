package com.xunfang.utils.office.domain;

import java.util.Map;

/**
 * 文件扩展名配置。基于扩展名选择不同的转pdf实现类。
 * @author wanglf
 * @version 1.0.0
 *          2017-7-3 14:42
 * @since Jdk1.7
 */
public class FileExtConfig {

    private Map<String, String> extConfigMap;

    /**
     * 设置ExtConfigMap，此处由spring注入
     * @param extConfigMap
     */
    public void setExtConfigMap(Map<String, String> extConfigMap) {
        this.extConfigMap = extConfigMap;
    }

    /**
     * 获取扩展名对应的转pdf服务的bean名称
     *
     * @param ext 文件扩展名，如xls,txt等
     * @return beanName
     */
    public String getExtServiceBeanName(String ext) {
        return extConfigMap.get(ext);
    }
}

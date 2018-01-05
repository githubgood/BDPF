package com.xunfang.bdpf.mllib.assembly.entity;

/**
 * 
 * @ClassName SqlAssembly
 * @Description: SQL脚本实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 下午2:32:29
 * @version V1.0
 */
public class SqlAssembly {
	//主键
    private String id;

    //组件主键
    private String bdpfMllibAssemblyId;

    //表1
    private String first;

    //表2
    private String second;

    //表3
    private String third;

    //表4
    private String four;

    //SQL
    private String sqlScript;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBdpfMllibAssemblyId() {
        return bdpfMllibAssemblyId;
    }

    public void setBdpfMllibAssemblyId(String bdpfMllibAssemblyId) {
        this.bdpfMllibAssemblyId = bdpfMllibAssemblyId == null ? null : bdpfMllibAssemblyId.trim();
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first == null ? null : first.trim();
    }

    public String getSecond() {
        return second;
    }

    public void setSecond(String second) {
        this.second = second == null ? null : second.trim();
    }

    public String getThird() {
        return third;
    }

    public void setThird(String third) {
        this.third = third == null ? null : third.trim();
    }

    public String getFour() {
        return four;
    }

    public void setFour(String four) {
        this.four = four == null ? null : four.trim();
    }

    public String getSqlScript() {
        return sqlScript;
    }

    public void setSqlScript(String sqlScript) {
        this.sqlScript = sqlScript == null ? null : sqlScript.trim();
    }
}
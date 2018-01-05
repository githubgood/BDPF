package com.xunfang.bdpf.mllib.assembly.entity;

/**
 * 
 * @ClassName ConvertAssembly
 * @Description: 类型转换实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 上午11:22:40
 * @version V1.0
 */
public class ConvertAssembly {
	//主键
    private String id;

    //组件主键
    private String bdpfMllibAssemblyId;

    //列名
    private String name;

    //数据类型
    private String dataType;

    //转换类型
    private Integer convertType;
    
    //序号
    private Integer xh;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    public Integer getConvertType() {
        return convertType;
    }

    public void setConvertType(Integer convertType) {
        this.convertType = convertType;
    }

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}
}
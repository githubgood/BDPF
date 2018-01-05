package com.xunfang.bdpf.mllib.experiment.entity;

/**
 * 
 * @ClassName TableChild
 * @Description: 创建子表实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 上午9:27:34
 * @version V1.0
 */
public class TableChild {
	//主键
    private String id;

    //创建表主键
    private String bdpfMllibCreateId;

    //列名
    private String name;

    //数据类型
    private String type;

    //数据长度
    private Integer length;
    
    //序号
    private Integer xh;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBdpfMllibCreateId() {
        return bdpfMllibCreateId;
    }

    public void setBdpfMllibCreateId(String bdpfMllibCreateId) {
        this.bdpfMllibCreateId = bdpfMllibCreateId == null ? null : bdpfMllibCreateId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}
    
}
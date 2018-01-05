package com.xunfang.bdpf.mllib.experiment.entity;

import java.util.Date;

/**
 * 
 * @ClassName Experiment
 * @Description: 实验实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 上午9:24:18
 * @version V1.0
 */
public class Experiment {
	//主键
    private String id;

    //实验文件夹表主键
    private String bdpfMllibFileId;

    //实验名称
    private String name;

    //实验描述
    private String description;

    //创建日期
    private Date createDate;
    
    //线条数组
    private String content;
    
    //组件数组
    private String mainarr;
    
    //创建帐号
    private String account;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBdpfMllibFileId() {
        return bdpfMllibFileId;
    }

    public void setBdpfMllibFileId(String bdpfMllibFileId) {
        this.bdpfMllibFileId = bdpfMllibFileId == null ? null : bdpfMllibFileId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

	public String getMainarr() {
		return mainarr;
	}

	public void setMainarr(String mainarr) {
		this.mainarr = mainarr == null ? null : mainarr.trim();
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account == null ? null : account.trim();
	}
    
}
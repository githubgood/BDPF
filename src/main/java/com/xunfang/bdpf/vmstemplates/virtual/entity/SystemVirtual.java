package com.xunfang.bdpf.vmstemplates.virtual.entity;

public class SystemVirtual {
    /**
     * 主键id
     */
    private String id;

    /**
     * 用户id
     */
    private String userId;

    /**
     * 主机ip
     */
    private String master;

    /**
     *从机ip 
     */
    private String slave;
    
    /**
     * 主机id
     */
    private String mid;

    /**
     *从机id 
     */
    private String sid;
    
    /**
     *已安装的环境  1=hadoop 2=HBase 3=HBaseHive 4=Spark
     */
    private String install;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master == null ? null : master.trim();
    }

    public String getSlave() {
        return slave;
    }

    public void setSlave(String slave) {
        this.slave = slave == null ? null : slave.trim();
    }

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getInstall() {
		return install;
	}

	public void setInstall(String install) {
		this.install = install;
	}
    
    
}
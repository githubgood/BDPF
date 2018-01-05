package com.xunfang.bdpf.mllib.assembly.entity;

/**
 * 
 * @ClassName StatisticalanalysisAssembly
 * @Description: 统计分析实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年11月7日 下午2:47:45
 * @version V1.0
 */
public class StatisticalanalysisAssembly {
	//主键ID
    private String id;

    //组件ID
    private String bdpfMllibAssemblyId;

    //特征列名称
    private String characteristicColumn;

    //组件库ID
    private String assemblyLibraryId;

    //特征列类型
    private String characteristicType;
    
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

    public String getCharacteristicColumn() {
        return characteristicColumn;
    }

    public void setCharacteristicColumn(String characteristicColumn) {
        this.characteristicColumn = characteristicColumn == null ? null : characteristicColumn.trim();
    }

    public String getAssemblyLibraryId() {
        return assemblyLibraryId;
    }

    public void setAssemblyLibraryId(String assemblyLibraryId) {
        this.assemblyLibraryId = assemblyLibraryId == null ? null : assemblyLibraryId.trim();
    }

    public String getCharacteristicType() {
        return characteristicType;
    }

    public void setCharacteristicType(String characteristicType) {
        this.characteristicType = characteristicType == null ? null : characteristicType.trim();
    }

	public Integer getXh() {
		return xh;
	}

	public void setXh(Integer xh) {
		this.xh = xh;
	}
    
}
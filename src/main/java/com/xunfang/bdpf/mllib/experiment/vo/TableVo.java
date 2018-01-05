package com.xunfang.bdpf.mllib.experiment.vo;

import com.xunfang.bdpf.mllib.experiment.entity.Table;

public class TableVo extends Table{

	private String[] columnName;
	private String[] columnType;
	
	public void setColumnName(String[] columnName) {
		this.columnName = columnName;
	}
	public String[] getColumnName() {
		return columnName;
	}
	public void setColumnType(String[] columnType) {
		this.columnType = columnType;
	}
	public String[] getColumnType() {
		return columnType;
	}
}

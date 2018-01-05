package com.xunfang.bdpf.mllib.assembly.entity;
/**
 * 
 * @ClassName Columns
 * @Description: 表的列数据
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author 
 * @date 
 * @version V1.0
 */
public class Columns {
	
	
	//列明
    private String column;

    //数据类型
    private String dataType;
    
    //表名
    private String  tableName;
    
    //表名
    private String  rownum;
    
	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getRownum() {
		return rownum;
	}

	public void setRownum(String rownum) {
		this.rownum = rownum;
	}


    
}

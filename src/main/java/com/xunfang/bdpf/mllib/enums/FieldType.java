package com.xunfang.bdpf.mllib.enums;
/**
 * 
 * @author yaolianhua
 *
 */
public enum FieldType {
	INT("int"),
	DOUBLE("double"),
	BIGINT("bigint"),
	DECIMAL("decimal"),
	STRING("string"),
	BOOLEAN("boolean"),
	DATETIME("datetime");
	
	private String name;
	
	FieldType(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}

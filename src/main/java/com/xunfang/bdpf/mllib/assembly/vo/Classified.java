package com.xunfang.bdpf.mllib.assembly.vo;

/**
 * 
 * @ClassName Classified
 * @Description: 二分类评估参数实体类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月27日 下午4:56:44
 * @version V1.0
 */
public class Classified {

	//模型：类标
	private String label;
	
	//准确率
	private String precision;
	
	//召回率
	private String recall;
	
	//F1值
	private String f1Score;
	
	//F2值
	private String f2Score;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPrecision() {
		return precision;
	}

	public void setPrecision(String precision) {
		this.precision = precision;
	}

	public String getRecall() {
		return recall;
	}

	public void setRecall(String recall) {
		this.recall = recall;
	}

	public String getF1Score() {
		return f1Score;
	}

	public void setF1Score(String f1Score) {
		this.f1Score = f1Score;
	}

	public String getF2Score() {
		return f2Score;
	}

	public void setF2Score(String f2Score) {
		this.f2Score = f2Score;
	}

}

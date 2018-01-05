	package com.xunfang.utils;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName PageResponse
 * @Description: 分页类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年6月5日 下午3:19:18
 * @version V1.0
 */
	public class PageResponse<T> implements Serializable {  
	    private static final long serialVersionUID = 768549219446295665L;  
	    private Integer total;  //总条数  
	    private List<T> records; //当前页显示数据  
	  
	    public Integer getTotal() {  
	        return total;  
	    }  
	  
	    public void setTotal(Integer total) {  
	        this.total = total;  
	    }  
	  
	    public List<T> getRecords() {  
	        return records;  
	    }  
	  
	    public void setRecords(List<T> records) {  
	        this.records = records;  
	    }  
	}

package com.xunfang.bdpf.mllib.assembly.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName ConvertAssemblyExample
 * @Description: 类型转换扩展类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 上午11:22:46
 * @version V1.0
 */
public class ConvertAssemblyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ConvertAssemblyExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdIsNull() {
            addCriterion("bdpf_mllib_assembly_id is null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdIsNotNull() {
            addCriterion("bdpf_mllib_assembly_id is not null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_id =", value, "bdpfMllibAssemblyId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdNotEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_id <>", value, "bdpfMllibAssemblyId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdGreaterThan(String value) {
            addCriterion("bdpf_mllib_assembly_id >", value, "bdpfMllibAssemblyId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdGreaterThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_id >=", value, "bdpfMllibAssemblyId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLessThan(String value) {
            addCriterion("bdpf_mllib_assembly_id <", value, "bdpfMllibAssemblyId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLessThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_id <=", value, "bdpfMllibAssemblyId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLike(String value) {
            addCriterion("bdpf_mllib_assembly_id like", value, "bdpfMllibAssemblyId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdNotLike(String value) {
            addCriterion("bdpf_mllib_assembly_id not like", value, "bdpfMllibAssemblyId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdIn(List<String> values) {
            addCriterion("bdpf_mllib_assembly_id in", values, "bdpfMllibAssemblyId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdNotIn(List<String> values) {
            addCriterion("bdpf_mllib_assembly_id not in", values, "bdpfMllibAssemblyId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_assembly_id between", value1, value2, "bdpfMllibAssemblyId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdNotBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_assembly_id not between", value1, value2, "bdpfMllibAssemblyId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNull() {
            addCriterion("data_type is null");
            return (Criteria) this;
        }

        public Criteria andDataTypeIsNotNull() {
            addCriterion("data_type is not null");
            return (Criteria) this;
        }

        public Criteria andDataTypeEqualTo(String value) {
            addCriterion("data_type =", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotEqualTo(String value) {
            addCriterion("data_type <>", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThan(String value) {
            addCriterion("data_type >", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeGreaterThanOrEqualTo(String value) {
            addCriterion("data_type >=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThan(String value) {
            addCriterion("data_type <", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLessThanOrEqualTo(String value) {
            addCriterion("data_type <=", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeLike(String value) {
            addCriterion("data_type like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotLike(String value) {
            addCriterion("data_type not like", value, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeIn(List<String> values) {
            addCriterion("data_type in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotIn(List<String> values) {
            addCriterion("data_type not in", values, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeBetween(String value1, String value2) {
            addCriterion("data_type between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andDataTypeNotBetween(String value1, String value2) {
            addCriterion("data_type not between", value1, value2, "dataType");
            return (Criteria) this;
        }

        public Criteria andConvertTypeIsNull() {
            addCriterion("convert_type is null");
            return (Criteria) this;
        }

        public Criteria andConvertTypeIsNotNull() {
            addCriterion("convert_type is not null");
            return (Criteria) this;
        }

        public Criteria andConvertTypeEqualTo(Integer value) {
            addCriterion("convert_type =", value, "convertType");
            return (Criteria) this;
        }

        public Criteria andConvertTypeNotEqualTo(Integer value) {
            addCriterion("convert_type <>", value, "convertType");
            return (Criteria) this;
        }

        public Criteria andConvertTypeGreaterThan(Integer value) {
            addCriterion("convert_type >", value, "convertType");
            return (Criteria) this;
        }

        public Criteria andConvertTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("convert_type >=", value, "convertType");
            return (Criteria) this;
        }

        public Criteria andConvertTypeLessThan(Integer value) {
            addCriterion("convert_type <", value, "convertType");
            return (Criteria) this;
        }

        public Criteria andConvertTypeLessThanOrEqualTo(Integer value) {
            addCriterion("convert_type <=", value, "convertType");
            return (Criteria) this;
        }

        public Criteria andConvertTypeIn(List<Integer> values) {
            addCriterion("convert_type in", values, "convertType");
            return (Criteria) this;
        }

        public Criteria andConvertTypeNotIn(List<Integer> values) {
            addCriterion("convert_type not in", values, "convertType");
            return (Criteria) this;
        }

        public Criteria andConvertTypeBetween(Integer value1, Integer value2) {
            addCriterion("convert_type between", value1, value2, "convertType");
            return (Criteria) this;
        }

        public Criteria andConvertTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("convert_type not between", value1, value2, "convertType");
            return (Criteria) this;
        }
        
        public Criteria andXhIsNull() {
            addCriterion("xh is null");
            return (Criteria) this;
        }

        public Criteria andXhIsNotNull() {
            addCriterion("xh is not null");
            return (Criteria) this;
        }

        public Criteria andXhEqualTo(Integer value) {
            addCriterion("xh =", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotEqualTo(Integer value) {
            addCriterion("xh <>", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThan(Integer value) {
            addCriterion("xh >", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThanOrEqualTo(Integer value) {
            addCriterion("xh >=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThan(Integer value) {
            addCriterion("xh <", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThanOrEqualTo(Integer value) {
            addCriterion("xh <=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhIn(List<Integer> values) {
            addCriterion("xh in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotIn(List<Integer> values) {
            addCriterion("xh not in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhBetween(Integer value1, Integer value2) {
            addCriterion("xh between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotBetween(Integer value1, Integer value2) {
            addCriterion("xh not between", value1, value2, "xh");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
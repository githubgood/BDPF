package com.xunfang.bdpf.mllib.assembly.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName FeatureengineeringAssemblyExample
 * @Description: 特征工程表扩展类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年10月31日 上午9:06:01
 * @version V1.0
 */
public class FeatureengineeringAssemblyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FeatureengineeringAssemblyExample() {
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

        public Criteria andProportionalityCoefficientIsNull() {
            addCriterion("proportionality_coefficient is null");
            return (Criteria) this;
        }

        public Criteria andProportionalityCoefficientIsNotNull() {
            addCriterion("proportionality_coefficient is not null");
            return (Criteria) this;
        }

        public Criteria andProportionalityCoefficientEqualTo(String value) {
            addCriterion("proportionality_coefficient =", value, "proportionalityCoefficient");
            return (Criteria) this;
        }

        public Criteria andProportionalityCoefficientNotEqualTo(String value) {
            addCriterion("proportionality_coefficient <>", value, "proportionalityCoefficient");
            return (Criteria) this;
        }

        public Criteria andProportionalityCoefficientGreaterThan(String value) {
            addCriterion("proportionality_coefficient >", value, "proportionalityCoefficient");
            return (Criteria) this;
        }

        public Criteria andProportionalityCoefficientGreaterThanOrEqualTo(String value) {
            addCriterion("proportionality_coefficient >=", value, "proportionalityCoefficient");
            return (Criteria) this;
        }

        public Criteria andProportionalityCoefficientLessThan(String value) {
            addCriterion("proportionality_coefficient <", value, "proportionalityCoefficient");
            return (Criteria) this;
        }

        public Criteria andProportionalityCoefficientLessThanOrEqualTo(String value) {
            addCriterion("proportionality_coefficient <=", value, "proportionalityCoefficient");
            return (Criteria) this;
        }

        public Criteria andProportionalityCoefficientLike(String value) {
            addCriterion("proportionality_coefficient like", value, "proportionalityCoefficient");
            return (Criteria) this;
        }

        public Criteria andProportionalityCoefficientNotLike(String value) {
            addCriterion("proportionality_coefficient not like", value, "proportionalityCoefficient");
            return (Criteria) this;
        }

        public Criteria andProportionalityCoefficientIn(List<String> values) {
            addCriterion("proportionality_coefficient in", values, "proportionalityCoefficient");
            return (Criteria) this;
        }

        public Criteria andProportionalityCoefficientNotIn(List<String> values) {
            addCriterion("proportionality_coefficient not in", values, "proportionalityCoefficient");
            return (Criteria) this;
        }

        public Criteria andProportionalityCoefficientBetween(String value1, String value2) {
            addCriterion("proportionality_coefficient between", value1, value2, "proportionalityCoefficient");
            return (Criteria) this;
        }

        public Criteria andProportionalityCoefficientNotBetween(String value1, String value2) {
            addCriterion("proportionality_coefficient not between", value1, value2, "proportionalityCoefficient");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIsNull() {
            addCriterion("equidistant_dispersion is null");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIsNotNull() {
            addCriterion("equidistant_dispersion is not null");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionEqualTo(String value) {
            addCriterion("equidistant_dispersion =", value, "equidistantDispersion");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionNotEqualTo(String value) {
            addCriterion("equidistant_dispersion <>", value, "equidistantDispersion");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionGreaterThan(String value) {
            addCriterion("equidistant_dispersion >", value, "equidistantDispersion");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionGreaterThanOrEqualTo(String value) {
            addCriterion("equidistant_dispersion >=", value, "equidistantDispersion");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionLessThan(String value) {
            addCriterion("equidistant_dispersion <", value, "equidistantDispersion");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionLessThanOrEqualTo(String value) {
            addCriterion("equidistant_dispersion <=", value, "equidistantDispersion");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionLike(String value) {
            addCriterion("equidistant_dispersion like", value, "equidistantDispersion");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionNotLike(String value) {
            addCriterion("equidistant_dispersion not like", value, "equidistantDispersion");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIn(List<String> values) {
            addCriterion("equidistant_dispersion in", values, "equidistantDispersion");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionNotIn(List<String> values) {
            addCriterion("equidistant_dispersion not in", values, "equidistantDispersion");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionBetween(String value1, String value2) {
            addCriterion("equidistant_dispersion between", value1, value2, "equidistantDispersion");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionNotBetween(String value1, String value2) {
            addCriterion("equidistant_dispersion not between", value1, value2, "equidistantDispersion");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIntervalIsNull() {
            addCriterion("equidistant_dispersion_interval is null");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIntervalIsNotNull() {
            addCriterion("equidistant_dispersion_interval is not null");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIntervalEqualTo(String value) {
            addCriterion("equidistant_dispersion_interval =", value, "equidistantDispersionInterval");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIntervalNotEqualTo(String value) {
            addCriterion("equidistant_dispersion_interval <>", value, "equidistantDispersionInterval");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIntervalGreaterThan(String value) {
            addCriterion("equidistant_dispersion_interval >", value, "equidistantDispersionInterval");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIntervalGreaterThanOrEqualTo(String value) {
            addCriterion("equidistant_dispersion_interval >=", value, "equidistantDispersionInterval");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIntervalLessThan(String value) {
            addCriterion("equidistant_dispersion_interval <", value, "equidistantDispersionInterval");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIntervalLessThanOrEqualTo(String value) {
            addCriterion("equidistant_dispersion_interval <=", value, "equidistantDispersionInterval");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIntervalLike(String value) {
            addCriterion("equidistant_dispersion_interval like", value, "equidistantDispersionInterval");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIntervalNotLike(String value) {
            addCriterion("equidistant_dispersion_interval not like", value, "equidistantDispersionInterval");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIntervalIn(List<String> values) {
            addCriterion("equidistant_dispersion_interval in", values, "equidistantDispersionInterval");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIntervalNotIn(List<String> values) {
            addCriterion("equidistant_dispersion_interval not in", values, "equidistantDispersionInterval");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIntervalBetween(String value1, String value2) {
            addCriterion("equidistant_dispersion_interval between", value1, value2, "equidistantDispersionInterval");
            return (Criteria) this;
        }

        public Criteria andEquidistantDispersionIntervalNotBetween(String value1, String value2) {
            addCriterion("equidistant_dispersion_interval not between", value1, value2, "equidistantDispersionInterval");
            return (Criteria) this;
        }

        public Criteria andKIsNull() {
            addCriterion("k is null");
            return (Criteria) this;
        }

        public Criteria andKIsNotNull() {
            addCriterion("k is not null");
            return (Criteria) this;
        }

        public Criteria andKEqualTo(String value) {
            addCriterion("k =", value, "k");
            return (Criteria) this;
        }

        public Criteria andKNotEqualTo(String value) {
            addCriterion("k <>", value, "k");
            return (Criteria) this;
        }

        public Criteria andKGreaterThan(String value) {
            addCriterion("k >", value, "k");
            return (Criteria) this;
        }

        public Criteria andKGreaterThanOrEqualTo(String value) {
            addCriterion("k >=", value, "k");
            return (Criteria) this;
        }

        public Criteria andKLessThan(String value) {
            addCriterion("k <", value, "k");
            return (Criteria) this;
        }

        public Criteria andKLessThanOrEqualTo(String value) {
            addCriterion("k <=", value, "k");
            return (Criteria) this;
        }

        public Criteria andKLike(String value) {
            addCriterion("k like", value, "k");
            return (Criteria) this;
        }

        public Criteria andKNotLike(String value) {
            addCriterion("k not like", value, "k");
            return (Criteria) this;
        }

        public Criteria andKIn(List<String> values) {
            addCriterion("k in", values, "k");
            return (Criteria) this;
        }

        public Criteria andKNotIn(List<String> values) {
            addCriterion("k not in", values, "k");
            return (Criteria) this;
        }

        public Criteria andKBetween(String value1, String value2) {
            addCriterion("k between", value1, value2, "k");
            return (Criteria) this;
        }

        public Criteria andKNotBetween(String value1, String value2) {
            addCriterion("k not between", value1, value2, "k");
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
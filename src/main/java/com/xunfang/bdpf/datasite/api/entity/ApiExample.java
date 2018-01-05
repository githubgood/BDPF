package com.xunfang.bdpf.datasite.api.entity;

import java.util.ArrayList;
import java.util.List;

public class ApiExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ApiExample() {
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

        public Criteria andDataAreaIdIsNull() {
            addCriterion("data_area_id is null");
            return (Criteria) this;
        }

        public Criteria andDataAreaIdIsNotNull() {
            addCriterion("data_area_id is not null");
            return (Criteria) this;
        }

        public Criteria andDataAreaIdEqualTo(String value) {
            addCriterion("data_area_id =", value, "dataAreaId");
            return (Criteria) this;
        }

        public Criteria andDataAreaIdNotEqualTo(String value) {
            addCriterion("data_area_id <>", value, "dataAreaId");
            return (Criteria) this;
        }

        public Criteria andDataAreaIdGreaterThan(String value) {
            addCriterion("data_area_id >", value, "dataAreaId");
            return (Criteria) this;
        }

        public Criteria andDataAreaIdGreaterThanOrEqualTo(String value) {
            addCriterion("data_area_id >=", value, "dataAreaId");
            return (Criteria) this;
        }

        public Criteria andDataAreaIdLessThan(String value) {
            addCriterion("data_area_id <", value, "dataAreaId");
            return (Criteria) this;
        }

        public Criteria andDataAreaIdLessThanOrEqualTo(String value) {
            addCriterion("data_area_id <=", value, "dataAreaId");
            return (Criteria) this;
        }

        public Criteria andDataAreaIdLike(String value) {
            addCriterion("data_area_id like", value, "dataAreaId");
            return (Criteria) this;
        }

        public Criteria andDataAreaIdNotLike(String value) {
            addCriterion("data_area_id not like", value, "dataAreaId");
            return (Criteria) this;
        }

        public Criteria andDataAreaIdIn(List<String> values) {
            addCriterion("data_area_id in", values, "dataAreaId");
            return (Criteria) this;
        }

        public Criteria andDataAreaIdNotIn(List<String> values) {
            addCriterion("data_area_id not in", values, "dataAreaId");
            return (Criteria) this;
        }

        public Criteria andDataAreaIdBetween(String value1, String value2) {
            addCriterion("data_area_id between", value1, value2, "dataAreaId");
            return (Criteria) this;
        }

        public Criteria andDataAreaIdNotBetween(String value1, String value2) {
            addCriterion("data_area_id not between", value1, value2, "dataAreaId");
            return (Criteria) this;
        }

        public Criteria andApiDescriptionIsNull() {
            addCriterion("api_description is null");
            return (Criteria) this;
        }

        public Criteria andApiDescriptionIsNotNull() {
            addCriterion("api_description is not null");
            return (Criteria) this;
        }

        public Criteria andApiDescriptionEqualTo(String value) {
            addCriterion("api_description =", value, "apiDescription");
            return (Criteria) this;
        }

        public Criteria andApiDescriptionNotEqualTo(String value) {
            addCriterion("api_description <>", value, "apiDescription");
            return (Criteria) this;
        }

        public Criteria andApiDescriptionGreaterThan(String value) {
            addCriterion("api_description >", value, "apiDescription");
            return (Criteria) this;
        }

        public Criteria andApiDescriptionGreaterThanOrEqualTo(String value) {
            addCriterion("api_description >=", value, "apiDescription");
            return (Criteria) this;
        }

        public Criteria andApiDescriptionLessThan(String value) {
            addCriterion("api_description <", value, "apiDescription");
            return (Criteria) this;
        }

        public Criteria andApiDescriptionLessThanOrEqualTo(String value) {
            addCriterion("api_description <=", value, "apiDescription");
            return (Criteria) this;
        }

        public Criteria andApiDescriptionLike(String value) {
            addCriterion("api_description like", value, "apiDescription");
            return (Criteria) this;
        }

        public Criteria andApiDescriptionNotLike(String value) {
            addCriterion("api_description not like", value, "apiDescription");
            return (Criteria) this;
        }

        public Criteria andApiDescriptionIn(List<String> values) {
            addCriterion("api_description in", values, "apiDescription");
            return (Criteria) this;
        }

        public Criteria andApiDescriptionNotIn(List<String> values) {
            addCriterion("api_description not in", values, "apiDescription");
            return (Criteria) this;
        }

        public Criteria andApiDescriptionBetween(String value1, String value2) {
            addCriterion("api_description between", value1, value2, "apiDescription");
            return (Criteria) this;
        }

        public Criteria andApiDescriptionNotBetween(String value1, String value2) {
            addCriterion("api_description not between", value1, value2, "apiDescription");
            return (Criteria) this;
        }

        public Criteria andApiAddressIsNull() {
            addCriterion("api_address is null");
            return (Criteria) this;
        }

        public Criteria andApiAddressIsNotNull() {
            addCriterion("api_address is not null");
            return (Criteria) this;
        }

        public Criteria andApiAddressEqualTo(String value) {
            addCriterion("api_address =", value, "apiAddress");
            return (Criteria) this;
        }

        public Criteria andApiAddressNotEqualTo(String value) {
            addCriterion("api_address <>", value, "apiAddress");
            return (Criteria) this;
        }

        public Criteria andApiAddressGreaterThan(String value) {
            addCriterion("api_address >", value, "apiAddress");
            return (Criteria) this;
        }

        public Criteria andApiAddressGreaterThanOrEqualTo(String value) {
            addCriterion("api_address >=", value, "apiAddress");
            return (Criteria) this;
        }

        public Criteria andApiAddressLessThan(String value) {
            addCriterion("api_address <", value, "apiAddress");
            return (Criteria) this;
        }

        public Criteria andApiAddressLessThanOrEqualTo(String value) {
            addCriterion("api_address <=", value, "apiAddress");
            return (Criteria) this;
        }

        public Criteria andApiAddressLike(String value) {
            addCriterion("api_address like", value, "apiAddress");
            return (Criteria) this;
        }

        public Criteria andApiAddressNotLike(String value) {
            addCriterion("api_address not like", value, "apiAddress");
            return (Criteria) this;
        }

        public Criteria andApiAddressIn(List<String> values) {
            addCriterion("api_address in", values, "apiAddress");
            return (Criteria) this;
        }

        public Criteria andApiAddressNotIn(List<String> values) {
            addCriterion("api_address not in", values, "apiAddress");
            return (Criteria) this;
        }

        public Criteria andApiAddressBetween(String value1, String value2) {
            addCriterion("api_address between", value1, value2, "apiAddress");
            return (Criteria) this;
        }

        public Criteria andApiAddressNotBetween(String value1, String value2) {
            addCriterion("api_address not between", value1, value2, "apiAddress");
            return (Criteria) this;
        }

        public Criteria andApiFormatIsNull() {
            addCriterion("api_format is null");
            return (Criteria) this;
        }

        public Criteria andApiFormatIsNotNull() {
            addCriterion("api_format is not null");
            return (Criteria) this;
        }

        public Criteria andApiFormatEqualTo(String value) {
            addCriterion("api_format =", value, "apiFormat");
            return (Criteria) this;
        }

        public Criteria andApiFormatNotEqualTo(String value) {
            addCriterion("api_format <>", value, "apiFormat");
            return (Criteria) this;
        }

        public Criteria andApiFormatGreaterThan(String value) {
            addCriterion("api_format >", value, "apiFormat");
            return (Criteria) this;
        }

        public Criteria andApiFormatGreaterThanOrEqualTo(String value) {
            addCriterion("api_format >=", value, "apiFormat");
            return (Criteria) this;
        }

        public Criteria andApiFormatLessThan(String value) {
            addCriterion("api_format <", value, "apiFormat");
            return (Criteria) this;
        }

        public Criteria andApiFormatLessThanOrEqualTo(String value) {
            addCriterion("api_format <=", value, "apiFormat");
            return (Criteria) this;
        }

        public Criteria andApiFormatLike(String value) {
            addCriterion("api_format like", value, "apiFormat");
            return (Criteria) this;
        }

        public Criteria andApiFormatNotLike(String value) {
            addCriterion("api_format not like", value, "apiFormat");
            return (Criteria) this;
        }

        public Criteria andApiFormatIn(List<String> values) {
            addCriterion("api_format in", values, "apiFormat");
            return (Criteria) this;
        }

        public Criteria andApiFormatNotIn(List<String> values) {
            addCriterion("api_format not in", values, "apiFormat");
            return (Criteria) this;
        }

        public Criteria andApiFormatBetween(String value1, String value2) {
            addCriterion("api_format between", value1, value2, "apiFormat");
            return (Criteria) this;
        }

        public Criteria andApiFormatNotBetween(String value1, String value2) {
            addCriterion("api_format not between", value1, value2, "apiFormat");
            return (Criteria) this;
        }

        public Criteria andApiExampleIsNull() {
            addCriterion("api_example is null");
            return (Criteria) this;
        }

        public Criteria andApiExampleIsNotNull() {
            addCriterion("api_example is not null");
            return (Criteria) this;
        }

        public Criteria andApiExampleEqualTo(String value) {
            addCriterion("api_example =", value, "apiExample");
            return (Criteria) this;
        }

        public Criteria andApiExampleNotEqualTo(String value) {
            addCriterion("api_example <>", value, "apiExample");
            return (Criteria) this;
        }

        public Criteria andApiExampleGreaterThan(String value) {
            addCriterion("api_example >", value, "apiExample");
            return (Criteria) this;
        }

        public Criteria andApiExampleGreaterThanOrEqualTo(String value) {
            addCriterion("api_example >=", value, "apiExample");
            return (Criteria) this;
        }

        public Criteria andApiExampleLessThan(String value) {
            addCriterion("api_example <", value, "apiExample");
            return (Criteria) this;
        }

        public Criteria andApiExampleLessThanOrEqualTo(String value) {
            addCriterion("api_example <=", value, "apiExample");
            return (Criteria) this;
        }

        public Criteria andApiExampleLike(String value) {
            addCriterion("api_example like", value, "apiExample");
            return (Criteria) this;
        }

        public Criteria andApiExampleNotLike(String value) {
            addCriterion("api_example not like", value, "apiExample");
            return (Criteria) this;
        }

        public Criteria andApiExampleIn(List<String> values) {
            addCriterion("api_example in", values, "apiExample");
            return (Criteria) this;
        }

        public Criteria andApiExampleNotIn(List<String> values) {
            addCriterion("api_example not in", values, "apiExample");
            return (Criteria) this;
        }

        public Criteria andApiExampleBetween(String value1, String value2) {
            addCriterion("api_example between", value1, value2, "apiExample");
            return (Criteria) this;
        }

        public Criteria andApiExampleNotBetween(String value1, String value2) {
            addCriterion("api_example not between", value1, value2, "apiExample");
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
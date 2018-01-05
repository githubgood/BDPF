package com.xunfang.bdpf.system.time.entity;

import java.util.ArrayList;
import java.util.List;

public class TimeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TimeExample() {
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

        public Criteria andMinTimeIsNull() {
            addCriterion("min_time is null");
            return (Criteria) this;
        }

        public Criteria andMinTimeIsNotNull() {
            addCriterion("min_time is not null");
            return (Criteria) this;
        }

        public Criteria andMinTimeEqualTo(String value) {
            addCriterion("min_time =", value, "minTime");
            return (Criteria) this;
        }

        public Criteria andMinTimeNotEqualTo(String value) {
            addCriterion("min_time <>", value, "minTime");
            return (Criteria) this;
        }

        public Criteria andMinTimeGreaterThan(String value) {
            addCriterion("min_time >", value, "minTime");
            return (Criteria) this;
        }

        public Criteria andMinTimeGreaterThanOrEqualTo(String value) {
            addCriterion("min_time >=", value, "minTime");
            return (Criteria) this;
        }

        public Criteria andMinTimeLessThan(String value) {
            addCriterion("min_time <", value, "minTime");
            return (Criteria) this;
        }

        public Criteria andMinTimeLessThanOrEqualTo(String value) {
            addCriterion("min_time <=", value, "minTime");
            return (Criteria) this;
        }

        public Criteria andMinTimeLike(String value) {
            addCriterion("min_time like", value, "minTime");
            return (Criteria) this;
        }

        public Criteria andMinTimeNotLike(String value) {
            addCriterion("min_time not like", value, "minTime");
            return (Criteria) this;
        }

        public Criteria andMinTimeIn(List<String> values) {
            addCriterion("min_time in", values, "minTime");
            return (Criteria) this;
        }

        public Criteria andMinTimeNotIn(List<String> values) {
            addCriterion("min_time not in", values, "minTime");
            return (Criteria) this;
        }

        public Criteria andMinTimeBetween(String value1, String value2) {
            addCriterion("min_time between", value1, value2, "minTime");
            return (Criteria) this;
        }

        public Criteria andMinTimeNotBetween(String value1, String value2) {
            addCriterion("min_time not between", value1, value2, "minTime");
            return (Criteria) this;
        }

        public Criteria andMaxTimeIsNull() {
            addCriterion("max_time is null");
            return (Criteria) this;
        }

        public Criteria andMaxTimeIsNotNull() {
            addCriterion("max_time is not null");
            return (Criteria) this;
        }

        public Criteria andMaxTimeEqualTo(String value) {
            addCriterion("max_time =", value, "maxTime");
            return (Criteria) this;
        }

        public Criteria andMaxTimeNotEqualTo(String value) {
            addCriterion("max_time <>", value, "maxTime");
            return (Criteria) this;
        }

        public Criteria andMaxTimeGreaterThan(String value) {
            addCriterion("max_time >", value, "maxTime");
            return (Criteria) this;
        }

        public Criteria andMaxTimeGreaterThanOrEqualTo(String value) {
            addCriterion("max_time >=", value, "maxTime");
            return (Criteria) this;
        }

        public Criteria andMaxTimeLessThan(String value) {
            addCriterion("max_time <", value, "maxTime");
            return (Criteria) this;
        }

        public Criteria andMaxTimeLessThanOrEqualTo(String value) {
            addCriterion("max_time <=", value, "maxTime");
            return (Criteria) this;
        }

        public Criteria andMaxTimeLike(String value) {
            addCriterion("max_time like", value, "maxTime");
            return (Criteria) this;
        }

        public Criteria andMaxTimeNotLike(String value) {
            addCriterion("max_time not like", value, "maxTime");
            return (Criteria) this;
        }

        public Criteria andMaxTimeIn(List<String> values) {
            addCriterion("max_time in", values, "maxTime");
            return (Criteria) this;
        }

        public Criteria andMaxTimeNotIn(List<String> values) {
            addCriterion("max_time not in", values, "maxTime");
            return (Criteria) this;
        }

        public Criteria andMaxTimeBetween(String value1, String value2) {
            addCriterion("max_time between", value1, value2, "maxTime");
            return (Criteria) this;
        }

        public Criteria andMaxTimeNotBetween(String value1, String value2) {
            addCriterion("max_time not between", value1, value2, "maxTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNull() {
            addCriterion("add_time is null");
            return (Criteria) this;
        }

        public Criteria andAddTimeIsNotNull() {
            addCriterion("add_time is not null");
            return (Criteria) this;
        }

        public Criteria andAddTimeEqualTo(String value) {
            addCriterion("add_time =", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotEqualTo(String value) {
            addCriterion("add_time <>", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThan(String value) {
            addCriterion("add_time >", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeGreaterThanOrEqualTo(String value) {
            addCriterion("add_time >=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThan(String value) {
            addCriterion("add_time <", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLessThanOrEqualTo(String value) {
            addCriterion("add_time <=", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeLike(String value) {
            addCriterion("add_time like", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotLike(String value) {
            addCriterion("add_time not like", value, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeIn(List<String> values) {
            addCriterion("add_time in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotIn(List<String> values) {
            addCriterion("add_time not in", values, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeBetween(String value1, String value2) {
            addCriterion("add_time between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andAddTimeNotBetween(String value1, String value2) {
            addCriterion("add_time not between", value1, value2, "addTime");
            return (Criteria) this;
        }

        public Criteria andMaxNumberIsNull() {
            addCriterion("max_number is null");
            return (Criteria) this;
        }

        public Criteria andMaxNumberIsNotNull() {
            addCriterion("max_number is not null");
            return (Criteria) this;
        }

        public Criteria andMaxNumberEqualTo(String value) {
            addCriterion("max_number =", value, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberNotEqualTo(String value) {
            addCriterion("max_number <>", value, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberGreaterThan(String value) {
            addCriterion("max_number >", value, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberGreaterThanOrEqualTo(String value) {
            addCriterion("max_number >=", value, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberLessThan(String value) {
            addCriterion("max_number <", value, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberLessThanOrEqualTo(String value) {
            addCriterion("max_number <=", value, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberLike(String value) {
            addCriterion("max_number like", value, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberNotLike(String value) {
            addCriterion("max_number not like", value, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberIn(List<String> values) {
            addCriterion("max_number in", values, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberNotIn(List<String> values) {
            addCriterion("max_number not in", values, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberBetween(String value1, String value2) {
            addCriterion("max_number between", value1, value2, "maxNumber");
            return (Criteria) this;
        }

        public Criteria andMaxNumberNotBetween(String value1, String value2) {
            addCriterion("max_number not between", value1, value2, "maxNumber");
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
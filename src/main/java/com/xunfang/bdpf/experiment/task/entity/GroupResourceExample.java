package com.xunfang.bdpf.experiment.task.entity;

import java.util.ArrayList;
import java.util.List;

public class GroupResourceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public GroupResourceExample() {
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

        public Criteria andExperimentGroupIdIsNull() {
            addCriterion("experiment_group_id is null");
            return (Criteria) this;
        }

        public Criteria andExperimentGroupIdIsNotNull() {
            addCriterion("experiment_group_id is not null");
            return (Criteria) this;
        }

        public Criteria andExperimentGroupIdEqualTo(String value) {
            addCriterion("experiment_group_id =", value, "experimentGroupId");
            return (Criteria) this;
        }

        public Criteria andExperimentGroupIdNotEqualTo(String value) {
            addCriterion("experiment_group_id <>", value, "experimentGroupId");
            return (Criteria) this;
        }

        public Criteria andExperimentGroupIdGreaterThan(String value) {
            addCriterion("experiment_group_id >", value, "experimentGroupId");
            return (Criteria) this;
        }

        public Criteria andExperimentGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("experiment_group_id >=", value, "experimentGroupId");
            return (Criteria) this;
        }

        public Criteria andExperimentGroupIdLessThan(String value) {
            addCriterion("experiment_group_id <", value, "experimentGroupId");
            return (Criteria) this;
        }

        public Criteria andExperimentGroupIdLessThanOrEqualTo(String value) {
            addCriterion("experiment_group_id <=", value, "experimentGroupId");
            return (Criteria) this;
        }

        public Criteria andExperimentGroupIdLike(String value) {
            addCriterion("experiment_group_id like", value, "experimentGroupId");
            return (Criteria) this;
        }

        public Criteria andExperimentGroupIdNotLike(String value) {
            addCriterion("experiment_group_id not like", value, "experimentGroupId");
            return (Criteria) this;
        }

        public Criteria andExperimentGroupIdIn(List<String> values) {
            addCriterion("experiment_group_id in", values, "experimentGroupId");
            return (Criteria) this;
        }

        public Criteria andExperimentGroupIdNotIn(List<String> values) {
            addCriterion("experiment_group_id not in", values, "experimentGroupId");
            return (Criteria) this;
        }

        public Criteria andExperimentGroupIdBetween(String value1, String value2) {
            addCriterion("experiment_group_id between", value1, value2, "experimentGroupId");
            return (Criteria) this;
        }

        public Criteria andExperimentGroupIdNotBetween(String value1, String value2) {
            addCriterion("experiment_group_id not between", value1, value2, "experimentGroupId");
            return (Criteria) this;
        }

        public Criteria andCourseResourceIdIsNull() {
            addCriterion("course_resource_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseResourceIdIsNotNull() {
            addCriterion("course_resource_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseResourceIdEqualTo(String value) {
            addCriterion("course_resource_id =", value, "courseResourceId");
            return (Criteria) this;
        }

        public Criteria andCourseResourceIdNotEqualTo(String value) {
            addCriterion("course_resource_id <>", value, "courseResourceId");
            return (Criteria) this;
        }

        public Criteria andCourseResourceIdGreaterThan(String value) {
            addCriterion("course_resource_id >", value, "courseResourceId");
            return (Criteria) this;
        }

        public Criteria andCourseResourceIdGreaterThanOrEqualTo(String value) {
            addCriterion("course_resource_id >=", value, "courseResourceId");
            return (Criteria) this;
        }

        public Criteria andCourseResourceIdLessThan(String value) {
            addCriterion("course_resource_id <", value, "courseResourceId");
            return (Criteria) this;
        }

        public Criteria andCourseResourceIdLessThanOrEqualTo(String value) {
            addCriterion("course_resource_id <=", value, "courseResourceId");
            return (Criteria) this;
        }

        public Criteria andCourseResourceIdLike(String value) {
            addCriterion("course_resource_id like", value, "courseResourceId");
            return (Criteria) this;
        }

        public Criteria andCourseResourceIdNotLike(String value) {
            addCriterion("course_resource_id not like", value, "courseResourceId");
            return (Criteria) this;
        }

        public Criteria andCourseResourceIdIn(List<String> values) {
            addCriterion("course_resource_id in", values, "courseResourceId");
            return (Criteria) this;
        }

        public Criteria andCourseResourceIdNotIn(List<String> values) {
            addCriterion("course_resource_id not in", values, "courseResourceId");
            return (Criteria) this;
        }

        public Criteria andCourseResourceIdBetween(String value1, String value2) {
            addCriterion("course_resource_id between", value1, value2, "courseResourceId");
            return (Criteria) this;
        }

        public Criteria andCourseResourceIdNotBetween(String value1, String value2) {
            addCriterion("course_resource_id not between", value1, value2, "courseResourceId");
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
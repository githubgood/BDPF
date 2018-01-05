package com.xunfang.bdpf.experiment.grade.entity;

import java.util.ArrayList;
import java.util.List;

public class StudentVirtualExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StudentVirtualExample() {
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

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andVirtualIdIsNull() {
            addCriterion("virtual_id is null");
            return (Criteria) this;
        }

        public Criteria andVirtualIdIsNotNull() {
            addCriterion("virtual_id is not null");
            return (Criteria) this;
        }

        public Criteria andVirtualIdEqualTo(String value) {
            addCriterion("virtual_id =", value, "virtualId");
            return (Criteria) this;
        }

        public Criteria andVirtualIdNotEqualTo(String value) {
            addCriterion("virtual_id <>", value, "virtualId");
            return (Criteria) this;
        }

        public Criteria andVirtualIdGreaterThan(String value) {
            addCriterion("virtual_id >", value, "virtualId");
            return (Criteria) this;
        }

        public Criteria andVirtualIdGreaterThanOrEqualTo(String value) {
            addCriterion("virtual_id >=", value, "virtualId");
            return (Criteria) this;
        }

        public Criteria andVirtualIdLessThan(String value) {
            addCriterion("virtual_id <", value, "virtualId");
            return (Criteria) this;
        }

        public Criteria andVirtualIdLessThanOrEqualTo(String value) {
            addCriterion("virtual_id <=", value, "virtualId");
            return (Criteria) this;
        }

        public Criteria andVirtualIdLike(String value) {
            addCriterion("virtual_id like", value, "virtualId");
            return (Criteria) this;
        }

        public Criteria andVirtualIdNotLike(String value) {
            addCriterion("virtual_id not like", value, "virtualId");
            return (Criteria) this;
        }

        public Criteria andVirtualIdIn(List<String> values) {
            addCriterion("virtual_id in", values, "virtualId");
            return (Criteria) this;
        }

        public Criteria andVirtualIdNotIn(List<String> values) {
            addCriterion("virtual_id not in", values, "virtualId");
            return (Criteria) this;
        }

        public Criteria andVirtualIdBetween(String value1, String value2) {
            addCriterion("virtual_id between", value1, value2, "virtualId");
            return (Criteria) this;
        }

        public Criteria andVirtualIdNotBetween(String value1, String value2) {
            addCriterion("virtual_id not between", value1, value2, "virtualId");
            return (Criteria) this;
        }

        public Criteria andVirtualNameIsNull() {
            addCriterion("virtual_name is null");
            return (Criteria) this;
        }

        public Criteria andVirtualNameIsNotNull() {
            addCriterion("virtual_name is not null");
            return (Criteria) this;
        }

        public Criteria andVirtualNameEqualTo(String value) {
            addCriterion("virtual_name =", value, "virtualName");
            return (Criteria) this;
        }

        public Criteria andVirtualNameNotEqualTo(String value) {
            addCriterion("virtual_name <>", value, "virtualName");
            return (Criteria) this;
        }

        public Criteria andVirtualNameGreaterThan(String value) {
            addCriterion("virtual_name >", value, "virtualName");
            return (Criteria) this;
        }

        public Criteria andVirtualNameGreaterThanOrEqualTo(String value) {
            addCriterion("virtual_name >=", value, "virtualName");
            return (Criteria) this;
        }

        public Criteria andVirtualNameLessThan(String value) {
            addCriterion("virtual_name <", value, "virtualName");
            return (Criteria) this;
        }

        public Criteria andVirtualNameLessThanOrEqualTo(String value) {
            addCriterion("virtual_name <=", value, "virtualName");
            return (Criteria) this;
        }

        public Criteria andVirtualNameLike(String value) {
            addCriterion("virtual_name like", value, "virtualName");
            return (Criteria) this;
        }

        public Criteria andVirtualNameNotLike(String value) {
            addCriterion("virtual_name not like", value, "virtualName");
            return (Criteria) this;
        }

        public Criteria andVirtualNameIn(List<String> values) {
            addCriterion("virtual_name in", values, "virtualName");
            return (Criteria) this;
        }

        public Criteria andVirtualNameNotIn(List<String> values) {
            addCriterion("virtual_name not in", values, "virtualName");
            return (Criteria) this;
        }

        public Criteria andVirtualNameBetween(String value1, String value2) {
            addCriterion("virtual_name between", value1, value2, "virtualName");
            return (Criteria) this;
        }

        public Criteria andVirtualNameNotBetween(String value1, String value2) {
            addCriterion("virtual_name not between", value1, value2, "virtualName");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNull() {
            addCriterion("class_id is null");
            return (Criteria) this;
        }

        public Criteria andClassIdIsNotNull() {
            addCriterion("class_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassIdEqualTo(String value) {
            addCriterion("class_id =", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotEqualTo(String value) {
            addCriterion("class_id <>", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThan(String value) {
            addCriterion("class_id >", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("class_id >=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThan(String value) {
            addCriterion("class_id <", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLessThanOrEqualTo(String value) {
            addCriterion("class_id <=", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdLike(String value) {
            addCriterion("class_id like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotLike(String value) {
            addCriterion("class_id not like", value, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdIn(List<String> values) {
            addCriterion("class_id in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotIn(List<String> values) {
            addCriterion("class_id not in", values, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdBetween(String value1, String value2) {
            addCriterion("class_id between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andClassIdNotBetween(String value1, String value2) {
            addCriterion("class_id not between", value1, value2, "classId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(String value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(String value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(String value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(String value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(String value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(String value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLike(String value) {
            addCriterion("group_id like", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotLike(String value) {
            addCriterion("group_id not like", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<String> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<String> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(String value1, String value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(String value1, String value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andCourseTaskIdIsNull() {
            addCriterion("course_task_id is null");
            return (Criteria) this;
        }

        public Criteria andCourseTaskIdIsNotNull() {
            addCriterion("course_task_id is not null");
            return (Criteria) this;
        }

        public Criteria andCourseTaskIdEqualTo(String value) {
            addCriterion("course_task_id =", value, "courseTaskId");
            return (Criteria) this;
        }

        public Criteria andCourseTaskIdNotEqualTo(String value) {
            addCriterion("course_task_id <>", value, "courseTaskId");
            return (Criteria) this;
        }

        public Criteria andCourseTaskIdGreaterThan(String value) {
            addCriterion("course_task_id >", value, "courseTaskId");
            return (Criteria) this;
        }

        public Criteria andCourseTaskIdGreaterThanOrEqualTo(String value) {
            addCriterion("course_task_id >=", value, "courseTaskId");
            return (Criteria) this;
        }

        public Criteria andCourseTaskIdLessThan(String value) {
            addCriterion("course_task_id <", value, "courseTaskId");
            return (Criteria) this;
        }

        public Criteria andCourseTaskIdLessThanOrEqualTo(String value) {
            addCriterion("course_task_id <=", value, "courseTaskId");
            return (Criteria) this;
        }

        public Criteria andCourseTaskIdLike(String value) {
            addCriterion("course_task_id like", value, "courseTaskId");
            return (Criteria) this;
        }

        public Criteria andCourseTaskIdNotLike(String value) {
            addCriterion("course_task_id not like", value, "courseTaskId");
            return (Criteria) this;
        }

        public Criteria andCourseTaskIdIn(List<String> values) {
            addCriterion("course_task_id in", values, "courseTaskId");
            return (Criteria) this;
        }

        public Criteria andCourseTaskIdNotIn(List<String> values) {
            addCriterion("course_task_id not in", values, "courseTaskId");
            return (Criteria) this;
        }

        public Criteria andCourseTaskIdBetween(String value1, String value2) {
            addCriterion("course_task_id between", value1, value2, "courseTaskId");
            return (Criteria) this;
        }

        public Criteria andCourseTaskIdNotBetween(String value1, String value2) {
            addCriterion("course_task_id not between", value1, value2, "courseTaskId");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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
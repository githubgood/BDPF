package com.xunfang.bdpf.mllib.assembly.entity;

import java.util.ArrayList;
import java.util.List;

public class JoinAssemblyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JoinAssemblyExample() {
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

        public Criteria andBdpfMllibAssemblyLeftIdIsNull() {
            addCriterion("bdpf_mllib_assembly_left_id is null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLeftIdIsNotNull() {
            addCriterion("bdpf_mllib_assembly_left_id is not null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLeftIdEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_left_id =", value, "bdpfMllibAssemblyLeftId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLeftIdNotEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_left_id <>", value, "bdpfMllibAssemblyLeftId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLeftIdGreaterThan(String value) {
            addCriterion("bdpf_mllib_assembly_left_id >", value, "bdpfMllibAssemblyLeftId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLeftIdGreaterThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_left_id >=", value, "bdpfMllibAssemblyLeftId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLeftIdLessThan(String value) {
            addCriterion("bdpf_mllib_assembly_left_id <", value, "bdpfMllibAssemblyLeftId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLeftIdLessThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_left_id <=", value, "bdpfMllibAssemblyLeftId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLeftIdLike(String value) {
            addCriterion("bdpf_mllib_assembly_left_id like", value, "bdpfMllibAssemblyLeftId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLeftIdNotLike(String value) {
            addCriterion("bdpf_mllib_assembly_left_id not like", value, "bdpfMllibAssemblyLeftId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLeftIdIn(List<String> values) {
            addCriterion("bdpf_mllib_assembly_left_id in", values, "bdpfMllibAssemblyLeftId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLeftIdNotIn(List<String> values) {
            addCriterion("bdpf_mllib_assembly_left_id not in", values, "bdpfMllibAssemblyLeftId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLeftIdBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_assembly_left_id between", value1, value2, "bdpfMllibAssemblyLeftId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLeftIdNotBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_assembly_left_id not between", value1, value2, "bdpfMllibAssemblyLeftId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyRightIdIsNull() {
            addCriterion("bdpf_mllib_assembly_right_id is null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyRightIdIsNotNull() {
            addCriterion("bdpf_mllib_assembly_right_id is not null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyRightIdEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_right_id =", value, "bdpfMllibAssemblyRightId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyRightIdNotEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_right_id <>", value, "bdpfMllibAssemblyRightId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyRightIdGreaterThan(String value) {
            addCriterion("bdpf_mllib_assembly_right_id >", value, "bdpfMllibAssemblyRightId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyRightIdGreaterThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_right_id >=", value, "bdpfMllibAssemblyRightId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyRightIdLessThan(String value) {
            addCriterion("bdpf_mllib_assembly_right_id <", value, "bdpfMllibAssemblyRightId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyRightIdLessThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_right_id <=", value, "bdpfMllibAssemblyRightId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyRightIdLike(String value) {
            addCriterion("bdpf_mllib_assembly_right_id like", value, "bdpfMllibAssemblyRightId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyRightIdNotLike(String value) {
            addCriterion("bdpf_mllib_assembly_right_id not like", value, "bdpfMllibAssemblyRightId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyRightIdIn(List<String> values) {
            addCriterion("bdpf_mllib_assembly_right_id in", values, "bdpfMllibAssemblyRightId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyRightIdNotIn(List<String> values) {
            addCriterion("bdpf_mllib_assembly_right_id not in", values, "bdpfMllibAssemblyRightId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyRightIdBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_assembly_right_id between", value1, value2, "bdpfMllibAssemblyRightId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyRightIdNotBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_assembly_right_id not between", value1, value2, "bdpfMllibAssemblyRightId");
            return (Criteria) this;
        }

        public Criteria andJoinTypeIsNull() {
            addCriterion("join_type is null");
            return (Criteria) this;
        }

        public Criteria andJoinTypeIsNotNull() {
            addCriterion("join_type is not null");
            return (Criteria) this;
        }

        public Criteria andJoinTypeEqualTo(Integer value) {
            addCriterion("join_type =", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeNotEqualTo(Integer value) {
            addCriterion("join_type <>", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeGreaterThan(Integer value) {
            addCriterion("join_type >", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("join_type >=", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeLessThan(Integer value) {
            addCriterion("join_type <", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeLessThanOrEqualTo(Integer value) {
            addCriterion("join_type <=", value, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeIn(List<Integer> values) {
            addCriterion("join_type in", values, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeNotIn(List<Integer> values) {
            addCriterion("join_type not in", values, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeBetween(Integer value1, Integer value2) {
            addCriterion("join_type between", value1, value2, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("join_type not between", value1, value2, "joinType");
            return (Criteria) this;
        }

        public Criteria andJoinConditionIsNull() {
            addCriterion("join_condition is null");
            return (Criteria) this;
        }

        public Criteria andJoinConditionIsNotNull() {
            addCriterion("join_condition is not null");
            return (Criteria) this;
        }

        public Criteria andJoinConditionEqualTo(String value) {
            addCriterion("join_condition =", value, "joinCondition");
            return (Criteria) this;
        }

        public Criteria andJoinConditionNotEqualTo(String value) {
            addCriterion("join_condition <>", value, "joinCondition");
            return (Criteria) this;
        }

        public Criteria andJoinConditionGreaterThan(String value) {
            addCriterion("join_condition >", value, "joinCondition");
            return (Criteria) this;
        }

        public Criteria andJoinConditionGreaterThanOrEqualTo(String value) {
            addCriterion("join_condition >=", value, "joinCondition");
            return (Criteria) this;
        }

        public Criteria andJoinConditionLessThan(String value) {
            addCriterion("join_condition <", value, "joinCondition");
            return (Criteria) this;
        }

        public Criteria andJoinConditionLessThanOrEqualTo(String value) {
            addCriterion("join_condition <=", value, "joinCondition");
            return (Criteria) this;
        }

        public Criteria andJoinConditionLike(String value) {
            addCriterion("join_condition like", value, "joinCondition");
            return (Criteria) this;
        }

        public Criteria andJoinConditionNotLike(String value) {
            addCriterion("join_condition not like", value, "joinCondition");
            return (Criteria) this;
        }

        public Criteria andJoinConditionIn(List<String> values) {
            addCriterion("join_condition in", values, "joinCondition");
            return (Criteria) this;
        }

        public Criteria andJoinConditionNotIn(List<String> values) {
            addCriterion("join_condition not in", values, "joinCondition");
            return (Criteria) this;
        }

        public Criteria andJoinConditionBetween(String value1, String value2) {
            addCriterion("join_condition between", value1, value2, "joinCondition");
            return (Criteria) this;
        }

        public Criteria andJoinConditionNotBetween(String value1, String value2) {
            addCriterion("join_condition not between", value1, value2, "joinCondition");
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
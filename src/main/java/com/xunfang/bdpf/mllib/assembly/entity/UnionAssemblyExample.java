package com.xunfang.bdpf.mllib.assembly.entity;

import java.util.ArrayList;
import java.util.List;

public class UnionAssemblyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UnionAssemblyExample() {
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

        public Criteria andBdpfMllibAssemblyIdLeftIsNull() {
            addCriterion("bdpf_mllib_assembly_id_left is null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLeftIsNotNull() {
            addCriterion("bdpf_mllib_assembly_id_left is not null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLeftEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_id_left =", value, "bdpfMllibAssemblyIdLeft");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLeftNotEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_id_left <>", value, "bdpfMllibAssemblyIdLeft");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLeftGreaterThan(String value) {
            addCriterion("bdpf_mllib_assembly_id_left >", value, "bdpfMllibAssemblyIdLeft");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLeftGreaterThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_id_left >=", value, "bdpfMllibAssemblyIdLeft");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLeftLessThan(String value) {
            addCriterion("bdpf_mllib_assembly_id_left <", value, "bdpfMllibAssemblyIdLeft");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLeftLessThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_id_left <=", value, "bdpfMllibAssemblyIdLeft");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLeftLike(String value) {
            addCriterion("bdpf_mllib_assembly_id_left like", value, "bdpfMllibAssemblyIdLeft");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLeftNotLike(String value) {
            addCriterion("bdpf_mllib_assembly_id_left not like", value, "bdpfMllibAssemblyIdLeft");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLeftIn(List<String> values) {
            addCriterion("bdpf_mllib_assembly_id_left in", values, "bdpfMllibAssemblyIdLeft");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLeftNotIn(List<String> values) {
            addCriterion("bdpf_mllib_assembly_id_left not in", values, "bdpfMllibAssemblyIdLeft");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLeftBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_assembly_id_left between", value1, value2, "bdpfMllibAssemblyIdLeft");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdLeftNotBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_assembly_id_left not between", value1, value2, "bdpfMllibAssemblyIdLeft");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdRightIsNull() {
            addCriterion("bdpf_mllib_assembly_id_right is null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdRightIsNotNull() {
            addCriterion("bdpf_mllib_assembly_id_right is not null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdRightEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_id_right =", value, "bdpfMllibAssemblyIdRight");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdRightNotEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_id_right <>", value, "bdpfMllibAssemblyIdRight");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdRightGreaterThan(String value) {
            addCriterion("bdpf_mllib_assembly_id_right >", value, "bdpfMllibAssemblyIdRight");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdRightGreaterThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_id_right >=", value, "bdpfMllibAssemblyIdRight");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdRightLessThan(String value) {
            addCriterion("bdpf_mllib_assembly_id_right <", value, "bdpfMllibAssemblyIdRight");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdRightLessThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_id_right <=", value, "bdpfMllibAssemblyIdRight");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdRightLike(String value) {
            addCriterion("bdpf_mllib_assembly_id_right like", value, "bdpfMllibAssemblyIdRight");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdRightNotLike(String value) {
            addCriterion("bdpf_mllib_assembly_id_right not like", value, "bdpfMllibAssemblyIdRight");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdRightIn(List<String> values) {
            addCriterion("bdpf_mllib_assembly_id_right in", values, "bdpfMllibAssemblyIdRight");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdRightNotIn(List<String> values) {
            addCriterion("bdpf_mllib_assembly_id_right not in", values, "bdpfMllibAssemblyIdRight");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdRightBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_assembly_id_right between", value1, value2, "bdpfMllibAssemblyIdRight");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyIdRightNotBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_assembly_id_right not between", value1, value2, "bdpfMllibAssemblyIdRight");
            return (Criteria) this;
        }

        public Criteria andWhereConditionLeftIsNull() {
            addCriterion("where_condition_left is null");
            return (Criteria) this;
        }

        public Criteria andWhereConditionLeftIsNotNull() {
            addCriterion("where_condition_left is not null");
            return (Criteria) this;
        }

        public Criteria andWhereConditionLeftEqualTo(String value) {
            addCriterion("where_condition_left =", value, "whereConditionLeft");
            return (Criteria) this;
        }

        public Criteria andWhereConditionLeftNotEqualTo(String value) {
            addCriterion("where_condition_left <>", value, "whereConditionLeft");
            return (Criteria) this;
        }

        public Criteria andWhereConditionLeftGreaterThan(String value) {
            addCriterion("where_condition_left >", value, "whereConditionLeft");
            return (Criteria) this;
        }

        public Criteria andWhereConditionLeftGreaterThanOrEqualTo(String value) {
            addCriterion("where_condition_left >=", value, "whereConditionLeft");
            return (Criteria) this;
        }

        public Criteria andWhereConditionLeftLessThan(String value) {
            addCriterion("where_condition_left <", value, "whereConditionLeft");
            return (Criteria) this;
        }

        public Criteria andWhereConditionLeftLessThanOrEqualTo(String value) {
            addCriterion("where_condition_left <=", value, "whereConditionLeft");
            return (Criteria) this;
        }

        public Criteria andWhereConditionLeftLike(String value) {
            addCriterion("where_condition_left like", value, "whereConditionLeft");
            return (Criteria) this;
        }

        public Criteria andWhereConditionLeftNotLike(String value) {
            addCriterion("where_condition_left not like", value, "whereConditionLeft");
            return (Criteria) this;
        }

        public Criteria andWhereConditionLeftIn(List<String> values) {
            addCriterion("where_condition_left in", values, "whereConditionLeft");
            return (Criteria) this;
        }

        public Criteria andWhereConditionLeftNotIn(List<String> values) {
            addCriterion("where_condition_left not in", values, "whereConditionLeft");
            return (Criteria) this;
        }

        public Criteria andWhereConditionLeftBetween(String value1, String value2) {
            addCriterion("where_condition_left between", value1, value2, "whereConditionLeft");
            return (Criteria) this;
        }

        public Criteria andWhereConditionLeftNotBetween(String value1, String value2) {
            addCriterion("where_condition_left not between", value1, value2, "whereConditionLeft");
            return (Criteria) this;
        }

        public Criteria andWhereConditionRightIsNull() {
            addCriterion("where_condition_right is null");
            return (Criteria) this;
        }

        public Criteria andWhereConditionRightIsNotNull() {
            addCriterion("where_condition_right is not null");
            return (Criteria) this;
        }

        public Criteria andWhereConditionRightEqualTo(String value) {
            addCriterion("where_condition_right =", value, "whereConditionRight");
            return (Criteria) this;
        }

        public Criteria andWhereConditionRightNotEqualTo(String value) {
            addCriterion("where_condition_right <>", value, "whereConditionRight");
            return (Criteria) this;
        }

        public Criteria andWhereConditionRightGreaterThan(String value) {
            addCriterion("where_condition_right >", value, "whereConditionRight");
            return (Criteria) this;
        }

        public Criteria andWhereConditionRightGreaterThanOrEqualTo(String value) {
            addCriterion("where_condition_right >=", value, "whereConditionRight");
            return (Criteria) this;
        }

        public Criteria andWhereConditionRightLessThan(String value) {
            addCriterion("where_condition_right <", value, "whereConditionRight");
            return (Criteria) this;
        }

        public Criteria andWhereConditionRightLessThanOrEqualTo(String value) {
            addCriterion("where_condition_right <=", value, "whereConditionRight");
            return (Criteria) this;
        }

        public Criteria andWhereConditionRightLike(String value) {
            addCriterion("where_condition_right like", value, "whereConditionRight");
            return (Criteria) this;
        }

        public Criteria andWhereConditionRightNotLike(String value) {
            addCriterion("where_condition_right not like", value, "whereConditionRight");
            return (Criteria) this;
        }

        public Criteria andWhereConditionRightIn(List<String> values) {
            addCriterion("where_condition_right in", values, "whereConditionRight");
            return (Criteria) this;
        }

        public Criteria andWhereConditionRightNotIn(List<String> values) {
            addCriterion("where_condition_right not in", values, "whereConditionRight");
            return (Criteria) this;
        }

        public Criteria andWhereConditionRightBetween(String value1, String value2) {
            addCriterion("where_condition_right between", value1, value2, "whereConditionRight");
            return (Criteria) this;
        }

        public Criteria andWhereConditionRightNotBetween(String value1, String value2) {
            addCriterion("where_condition_right not between", value1, value2, "whereConditionRight");
            return (Criteria) this;
        }

        public Criteria andDeduplicationIsNull() {
            addCriterion("deduplication is null");
            return (Criteria) this;
        }

        public Criteria andDeduplicationIsNotNull() {
            addCriterion("deduplication is not null");
            return (Criteria) this;
        }

        public Criteria andDeduplicationEqualTo(Integer value) {
            addCriterion("deduplication =", value, "deduplication");
            return (Criteria) this;
        }

        public Criteria andDeduplicationNotEqualTo(Integer value) {
            addCriterion("deduplication <>", value, "deduplication");
            return (Criteria) this;
        }

        public Criteria andDeduplicationGreaterThan(Integer value) {
            addCriterion("deduplication >", value, "deduplication");
            return (Criteria) this;
        }

        public Criteria andDeduplicationGreaterThanOrEqualTo(Integer value) {
            addCriterion("deduplication >=", value, "deduplication");
            return (Criteria) this;
        }

        public Criteria andDeduplicationLessThan(Integer value) {
            addCriterion("deduplication <", value, "deduplication");
            return (Criteria) this;
        }

        public Criteria andDeduplicationLessThanOrEqualTo(Integer value) {
            addCriterion("deduplication <=", value, "deduplication");
            return (Criteria) this;
        }

        public Criteria andDeduplicationIn(List<Integer> values) {
            addCriterion("deduplication in", values, "deduplication");
            return (Criteria) this;
        }

        public Criteria andDeduplicationNotIn(List<Integer> values) {
            addCriterion("deduplication not in", values, "deduplication");
            return (Criteria) this;
        }

        public Criteria andDeduplicationBetween(Integer value1, Integer value2) {
            addCriterion("deduplication between", value1, value2, "deduplication");
            return (Criteria) this;
        }

        public Criteria andDeduplicationNotBetween(Integer value1, Integer value2) {
            addCriterion("deduplication not between", value1, value2, "deduplication");
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
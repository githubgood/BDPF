package com.xunfang.bdpf.mllib.assembly.entity;

import java.util.ArrayList;
import java.util.List;

public class JoinChildAssemblyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public JoinChildAssemblyExample() {
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

        public Criteria andBdpfMllibAssemblyJoinIdIsNull() {
            addCriterion("bdpf_mllib_assembly_join_id is null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyJoinIdIsNotNull() {
            addCriterion("bdpf_mllib_assembly_join_id is not null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyJoinIdEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_join_id =", value, "bdpfMllibAssemblyJoinId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyJoinIdNotEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_join_id <>", value, "bdpfMllibAssemblyJoinId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyJoinIdGreaterThan(String value) {
            addCriterion("bdpf_mllib_assembly_join_id >", value, "bdpfMllibAssemblyJoinId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyJoinIdGreaterThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_join_id >=", value, "bdpfMllibAssemblyJoinId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyJoinIdLessThan(String value) {
            addCriterion("bdpf_mllib_assembly_join_id <", value, "bdpfMllibAssemblyJoinId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyJoinIdLessThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_join_id <=", value, "bdpfMllibAssemblyJoinId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyJoinIdLike(String value) {
            addCriterion("bdpf_mllib_assembly_join_id like", value, "bdpfMllibAssemblyJoinId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyJoinIdNotLike(String value) {
            addCriterion("bdpf_mllib_assembly_join_id not like", value, "bdpfMllibAssemblyJoinId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyJoinIdIn(List<String> values) {
            addCriterion("bdpf_mllib_assembly_join_id in", values, "bdpfMllibAssemblyJoinId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyJoinIdNotIn(List<String> values) {
            addCriterion("bdpf_mllib_assembly_join_id not in", values, "bdpfMllibAssemblyJoinId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyJoinIdBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_assembly_join_id between", value1, value2, "bdpfMllibAssemblyJoinId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyJoinIdNotBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_assembly_join_id not between", value1, value2, "bdpfMllibAssemblyJoinId");
            return (Criteria) this;
        }

        public Criteria andColumnInIsNull() {
            addCriterion("column_in is null");
            return (Criteria) this;
        }

        public Criteria andColumnInIsNotNull() {
            addCriterion("column_in is not null");
            return (Criteria) this;
        }

        public Criteria andColumnInEqualTo(String value) {
            addCriterion("column_in =", value, "columnIn");
            return (Criteria) this;
        }

        public Criteria andColumnInNotEqualTo(String value) {
            addCriterion("column_in <>", value, "columnIn");
            return (Criteria) this;
        }

        public Criteria andColumnInGreaterThan(String value) {
            addCriterion("column_in >", value, "columnIn");
            return (Criteria) this;
        }

        public Criteria andColumnInGreaterThanOrEqualTo(String value) {
            addCriterion("column_in >=", value, "columnIn");
            return (Criteria) this;
        }

        public Criteria andColumnInLessThan(String value) {
            addCriterion("column_in <", value, "columnIn");
            return (Criteria) this;
        }

        public Criteria andColumnInLessThanOrEqualTo(String value) {
            addCriterion("column_in <=", value, "columnIn");
            return (Criteria) this;
        }

        public Criteria andColumnInLike(String value) {
            addCriterion("column_in like", value, "columnIn");
            return (Criteria) this;
        }

        public Criteria andColumnInNotLike(String value) {
            addCriterion("column_in not like", value, "columnIn");
            return (Criteria) this;
        }

        public Criteria andColumnInIn(List<String> values) {
            addCriterion("column_in in", values, "columnIn");
            return (Criteria) this;
        }

        public Criteria andColumnInNotIn(List<String> values) {
            addCriterion("column_in not in", values, "columnIn");
            return (Criteria) this;
        }

        public Criteria andColumnInBetween(String value1, String value2) {
            addCriterion("column_in between", value1, value2, "columnIn");
            return (Criteria) this;
        }

        public Criteria andColumnInNotBetween(String value1, String value2) {
            addCriterion("column_in not between", value1, value2, "columnIn");
            return (Criteria) this;
        }

        public Criteria andColumnOutIsNull() {
            addCriterion("column_out is null");
            return (Criteria) this;
        }

        public Criteria andColumnOutIsNotNull() {
            addCriterion("column_out is not null");
            return (Criteria) this;
        }

        public Criteria andColumnOutEqualTo(String value) {
            addCriterion("column_out =", value, "columnOut");
            return (Criteria) this;
        }

        public Criteria andColumnOutNotEqualTo(String value) {
            addCriterion("column_out <>", value, "columnOut");
            return (Criteria) this;
        }

        public Criteria andColumnOutGreaterThan(String value) {
            addCriterion("column_out >", value, "columnOut");
            return (Criteria) this;
        }

        public Criteria andColumnOutGreaterThanOrEqualTo(String value) {
            addCriterion("column_out >=", value, "columnOut");
            return (Criteria) this;
        }

        public Criteria andColumnOutLessThan(String value) {
            addCriterion("column_out <", value, "columnOut");
            return (Criteria) this;
        }

        public Criteria andColumnOutLessThanOrEqualTo(String value) {
            addCriterion("column_out <=", value, "columnOut");
            return (Criteria) this;
        }

        public Criteria andColumnOutLike(String value) {
            addCriterion("column_out like", value, "columnOut");
            return (Criteria) this;
        }

        public Criteria andColumnOutNotLike(String value) {
            addCriterion("column_out not like", value, "columnOut");
            return (Criteria) this;
        }

        public Criteria andColumnOutIn(List<String> values) {
            addCriterion("column_out in", values, "columnOut");
            return (Criteria) this;
        }

        public Criteria andColumnOutNotIn(List<String> values) {
            addCriterion("column_out not in", values, "columnOut");
            return (Criteria) this;
        }

        public Criteria andColumnOutBetween(String value1, String value2) {
            addCriterion("column_out between", value1, value2, "columnOut");
            return (Criteria) this;
        }

        public Criteria andColumnOutNotBetween(String value1, String value2) {
            addCriterion("column_out not between", value1, value2, "columnOut");
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

        public Criteria andOperationTypeIsNull() {
            addCriterion("operation_type is null");
            return (Criteria) this;
        }

        public Criteria andOperationTypeIsNotNull() {
            addCriterion("operation_type is not null");
            return (Criteria) this;
        }

        public Criteria andOperationTypeEqualTo(Integer value) {
            addCriterion("operation_type =", value, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeNotEqualTo(Integer value) {
            addCriterion("operation_type <>", value, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeGreaterThan(Integer value) {
            addCriterion("operation_type >", value, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("operation_type >=", value, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeLessThan(Integer value) {
            addCriterion("operation_type <", value, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeLessThanOrEqualTo(Integer value) {
            addCriterion("operation_type <=", value, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeIn(List<Integer> values) {
            addCriterion("operation_type in", values, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeNotIn(List<Integer> values) {
            addCriterion("operation_type not in", values, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeBetween(Integer value1, Integer value2) {
            addCriterion("operation_type between", value1, value2, "operationType");
            return (Criteria) this;
        }

        public Criteria andOperationTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("operation_type not between", value1, value2, "operationType");
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
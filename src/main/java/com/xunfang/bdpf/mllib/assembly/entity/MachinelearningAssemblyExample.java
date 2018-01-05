package com.xunfang.bdpf.mllib.assembly.entity;

import java.util.ArrayList;
import java.util.List;

public class MachinelearningAssemblyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MachinelearningAssemblyExample() {
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

        public Criteria andNumClassesIsNull() {
            addCriterion("num_classes is null");
            return (Criteria) this;
        }

        public Criteria andNumClassesIsNotNull() {
            addCriterion("num_classes is not null");
            return (Criteria) this;
        }

        public Criteria andNumClassesEqualTo(Integer value) {
            addCriterion("num_classes =", value, "numClasses");
            return (Criteria) this;
        }

        public Criteria andNumClassesNotEqualTo(Integer value) {
            addCriterion("num_classes <>", value, "numClasses");
            return (Criteria) this;
        }

        public Criteria andNumClassesGreaterThan(Integer value) {
            addCriterion("num_classes >", value, "numClasses");
            return (Criteria) this;
        }

        public Criteria andNumClassesGreaterThanOrEqualTo(Integer value) {
            addCriterion("num_classes >=", value, "numClasses");
            return (Criteria) this;
        }

        public Criteria andNumClassesLessThan(Integer value) {
            addCriterion("num_classes <", value, "numClasses");
            return (Criteria) this;
        }

        public Criteria andNumClassesLessThanOrEqualTo(Integer value) {
            addCriterion("num_classes <=", value, "numClasses");
            return (Criteria) this;
        }

        public Criteria andNumClassesIn(List<Integer> values) {
            addCriterion("num_classes in", values, "numClasses");
            return (Criteria) this;
        }

        public Criteria andNumClassesNotIn(List<Integer> values) {
            addCriterion("num_classes not in", values, "numClasses");
            return (Criteria) this;
        }

        public Criteria andNumClassesBetween(Integer value1, Integer value2) {
            addCriterion("num_classes between", value1, value2, "numClasses");
            return (Criteria) this;
        }

        public Criteria andNumClassesNotBetween(Integer value1, Integer value2) {
            addCriterion("num_classes not between", value1, value2, "numClasses");
            return (Criteria) this;
        }

        public Criteria andMaxDepthIsNull() {
            addCriterion("max_depth is null");
            return (Criteria) this;
        }

        public Criteria andMaxDepthIsNotNull() {
            addCriterion("max_depth is not null");
            return (Criteria) this;
        }

        public Criteria andMaxDepthEqualTo(Integer value) {
            addCriterion("max_depth =", value, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthNotEqualTo(Integer value) {
            addCriterion("max_depth <>", value, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthGreaterThan(Integer value) {
            addCriterion("max_depth >", value, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_depth >=", value, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthLessThan(Integer value) {
            addCriterion("max_depth <", value, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthLessThanOrEqualTo(Integer value) {
            addCriterion("max_depth <=", value, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthIn(List<Integer> values) {
            addCriterion("max_depth in", values, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthNotIn(List<Integer> values) {
            addCriterion("max_depth not in", values, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthBetween(Integer value1, Integer value2) {
            addCriterion("max_depth between", value1, value2, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxDepthNotBetween(Integer value1, Integer value2) {
            addCriterion("max_depth not between", value1, value2, "maxDepth");
            return (Criteria) this;
        }

        public Criteria andMaxBinsIsNull() {
            addCriterion("max_Bins is null");
            return (Criteria) this;
        }

        public Criteria andMaxBinsIsNotNull() {
            addCriterion("max_Bins is not null");
            return (Criteria) this;
        }

        public Criteria andMaxBinsEqualTo(Integer value) {
            addCriterion("max_Bins =", value, "maxBins");
            return (Criteria) this;
        }

        public Criteria andMaxBinsNotEqualTo(Integer value) {
            addCriterion("max_Bins <>", value, "maxBins");
            return (Criteria) this;
        }

        public Criteria andMaxBinsGreaterThan(Integer value) {
            addCriterion("max_Bins >", value, "maxBins");
            return (Criteria) this;
        }

        public Criteria andMaxBinsGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_Bins >=", value, "maxBins");
            return (Criteria) this;
        }

        public Criteria andMaxBinsLessThan(Integer value) {
            addCriterion("max_Bins <", value, "maxBins");
            return (Criteria) this;
        }

        public Criteria andMaxBinsLessThanOrEqualTo(Integer value) {
            addCriterion("max_Bins <=", value, "maxBins");
            return (Criteria) this;
        }

        public Criteria andMaxBinsIn(List<Integer> values) {
            addCriterion("max_Bins in", values, "maxBins");
            return (Criteria) this;
        }

        public Criteria andMaxBinsNotIn(List<Integer> values) {
            addCriterion("max_Bins not in", values, "maxBins");
            return (Criteria) this;
        }

        public Criteria andMaxBinsBetween(Integer value1, Integer value2) {
            addCriterion("max_Bins between", value1, value2, "maxBins");
            return (Criteria) this;
        }

        public Criteria andMaxBinsNotBetween(Integer value1, Integer value2) {
            addCriterion("max_Bins not between", value1, value2, "maxBins");
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

        public Criteria andNumIterationsIsNull() {
            addCriterion("num_Iterations is null");
            return (Criteria) this;
        }

        public Criteria andNumIterationsIsNotNull() {
            addCriterion("num_Iterations is not null");
            return (Criteria) this;
        }

        public Criteria andNumIterationsEqualTo(Integer value) {
            addCriterion("num_Iterations =", value, "numIterations");
            return (Criteria) this;
        }

        public Criteria andNumIterationsNotEqualTo(Integer value) {
            addCriterion("num_Iterations <>", value, "numIterations");
            return (Criteria) this;
        }

        public Criteria andNumIterationsGreaterThan(Integer value) {
            addCriterion("num_Iterations >", value, "numIterations");
            return (Criteria) this;
        }

        public Criteria andNumIterationsGreaterThanOrEqualTo(Integer value) {
            addCriterion("num_Iterations >=", value, "numIterations");
            return (Criteria) this;
        }

        public Criteria andNumIterationsLessThan(Integer value) {
            addCriterion("num_Iterations <", value, "numIterations");
            return (Criteria) this;
        }

        public Criteria andNumIterationsLessThanOrEqualTo(Integer value) {
            addCriterion("num_Iterations <=", value, "numIterations");
            return (Criteria) this;
        }

        public Criteria andNumIterationsIn(List<Integer> values) {
            addCriterion("num_Iterations in", values, "numIterations");
            return (Criteria) this;
        }

        public Criteria andNumIterationsNotIn(List<Integer> values) {
            addCriterion("num_Iterations not in", values, "numIterations");
            return (Criteria) this;
        }

        public Criteria andNumIterationsBetween(Integer value1, Integer value2) {
            addCriterion("num_Iterations between", value1, value2, "numIterations");
            return (Criteria) this;
        }

        public Criteria andNumIterationsNotBetween(Integer value1, Integer value2) {
            addCriterion("num_Iterations not between", value1, value2, "numIterations");
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

        public Criteria andKEqualTo(Integer value) {
            addCriterion("k =", value, "k");
            return (Criteria) this;
        }

        public Criteria andKNotEqualTo(Integer value) {
            addCriterion("k <>", value, "k");
            return (Criteria) this;
        }

        public Criteria andKGreaterThan(Integer value) {
            addCriterion("k >", value, "k");
            return (Criteria) this;
        }

        public Criteria andKGreaterThanOrEqualTo(Integer value) {
            addCriterion("k >=", value, "k");
            return (Criteria) this;
        }

        public Criteria andKLessThan(Integer value) {
            addCriterion("k <", value, "k");
            return (Criteria) this;
        }

        public Criteria andKLessThanOrEqualTo(Integer value) {
            addCriterion("k <=", value, "k");
            return (Criteria) this;
        }

        public Criteria andKIn(List<Integer> values) {
            addCriterion("k in", values, "k");
            return (Criteria) this;
        }

        public Criteria andKNotIn(List<Integer> values) {
            addCriterion("k not in", values, "k");
            return (Criteria) this;
        }

        public Criteria andKBetween(Integer value1, Integer value2) {
            addCriterion("k between", value1, value2, "k");
            return (Criteria) this;
        }

        public Criteria andKNotBetween(Integer value1, Integer value2) {
            addCriterion("k not between", value1, value2, "k");
            return (Criteria) this;
        }

        public Criteria andNumTreesIsNull() {
            addCriterion("num_Trees is null");
            return (Criteria) this;
        }

        public Criteria andNumTreesIsNotNull() {
            addCriterion("num_Trees is not null");
            return (Criteria) this;
        }

        public Criteria andNumTreesEqualTo(Integer value) {
            addCriterion("num_Trees =", value, "numTrees");
            return (Criteria) this;
        }

        public Criteria andNumTreesNotEqualTo(Integer value) {
            addCriterion("num_Trees <>", value, "numTrees");
            return (Criteria) this;
        }

        public Criteria andNumTreesGreaterThan(Integer value) {
            addCriterion("num_Trees >", value, "numTrees");
            return (Criteria) this;
        }

        public Criteria andNumTreesGreaterThanOrEqualTo(Integer value) {
            addCriterion("num_Trees >=", value, "numTrees");
            return (Criteria) this;
        }

        public Criteria andNumTreesLessThan(Integer value) {
            addCriterion("num_Trees <", value, "numTrees");
            return (Criteria) this;
        }

        public Criteria andNumTreesLessThanOrEqualTo(Integer value) {
            addCriterion("num_Trees <=", value, "numTrees");
            return (Criteria) this;
        }

        public Criteria andNumTreesIn(List<Integer> values) {
            addCriterion("num_Trees in", values, "numTrees");
            return (Criteria) this;
        }

        public Criteria andNumTreesNotIn(List<Integer> values) {
            addCriterion("num_Trees not in", values, "numTrees");
            return (Criteria) this;
        }

        public Criteria andNumTreesBetween(Integer value1, Integer value2) {
            addCriterion("num_Trees between", value1, value2, "numTrees");
            return (Criteria) this;
        }

        public Criteria andNumTreesNotBetween(Integer value1, Integer value2) {
            addCriterion("num_Trees not between", value1, value2, "numTrees");
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
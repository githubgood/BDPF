package com.xunfang.bdpf.mllib.assembly.entity;

import java.util.ArrayList;
import java.util.List;

public class RandomSamplingAssemblyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RandomSamplingAssemblyExample() {
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

        public Criteria andSampleSizeIsNull() {
            addCriterion("sample_size is null");
            return (Criteria) this;
        }

        public Criteria andSampleSizeIsNotNull() {
            addCriterion("sample_size is not null");
            return (Criteria) this;
        }

        public Criteria andSampleSizeEqualTo(Integer value) {
            addCriterion("sample_size =", value, "sampleSize");
            return (Criteria) this;
        }

        public Criteria andSampleSizeNotEqualTo(Integer value) {
            addCriterion("sample_size <>", value, "sampleSize");
            return (Criteria) this;
        }

        public Criteria andSampleSizeGreaterThan(Integer value) {
            addCriterion("sample_size >", value, "sampleSize");
            return (Criteria) this;
        }

        public Criteria andSampleSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("sample_size >=", value, "sampleSize");
            return (Criteria) this;
        }

        public Criteria andSampleSizeLessThan(Integer value) {
            addCriterion("sample_size <", value, "sampleSize");
            return (Criteria) this;
        }

        public Criteria andSampleSizeLessThanOrEqualTo(Integer value) {
            addCriterion("sample_size <=", value, "sampleSize");
            return (Criteria) this;
        }

        public Criteria andSampleSizeIn(List<Integer> values) {
            addCriterion("sample_size in", values, "sampleSize");
            return (Criteria) this;
        }

        public Criteria andSampleSizeNotIn(List<Integer> values) {
            addCriterion("sample_size not in", values, "sampleSize");
            return (Criteria) this;
        }

        public Criteria andSampleSizeBetween(Integer value1, Integer value2) {
            addCriterion("sample_size between", value1, value2, "sampleSize");
            return (Criteria) this;
        }

        public Criteria andSampleSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("sample_size not between", value1, value2, "sampleSize");
            return (Criteria) this;
        }

        public Criteria andSampleRatioIsNull() {
            addCriterion("sample_ratio is null");
            return (Criteria) this;
        }

        public Criteria andSampleRatioIsNotNull() {
            addCriterion("sample_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andSampleRatioEqualTo(Double value) {
            addCriterion("sample_ratio =", value, "sampleRatio");
            return (Criteria) this;
        }

        public Criteria andSampleRatioNotEqualTo(Double value) {
            addCriterion("sample_ratio <>", value, "sampleRatio");
            return (Criteria) this;
        }

        public Criteria andSampleRatioGreaterThan(Double value) {
            addCriterion("sample_ratio >", value, "sampleRatio");
            return (Criteria) this;
        }

        public Criteria andSampleRatioGreaterThanOrEqualTo(Double value) {
            addCriterion("sample_ratio >=", value, "sampleRatio");
            return (Criteria) this;
        }

        public Criteria andSampleRatioLessThan(Double value) {
            addCriterion("sample_ratio <", value, "sampleRatio");
            return (Criteria) this;
        }

        public Criteria andSampleRatioLessThanOrEqualTo(Double value) {
            addCriterion("sample_ratio <=", value, "sampleRatio");
            return (Criteria) this;
        }

        public Criteria andSampleRatioIn(List<Double> values) {
            addCriterion("sample_ratio in", values, "sampleRatio");
            return (Criteria) this;
        }

        public Criteria andSampleRatioNotIn(List<Double> values) {
            addCriterion("sample_ratio not in", values, "sampleRatio");
            return (Criteria) this;
        }

        public Criteria andSampleRatioBetween(Double value1, Double value2) {
            addCriterion("sample_ratio between", value1, value2, "sampleRatio");
            return (Criteria) this;
        }

        public Criteria andSampleRatioNotBetween(Double value1, Double value2) {
            addCriterion("sample_ratio not between", value1, value2, "sampleRatio");
            return (Criteria) this;
        }

        public Criteria andSampleReplaceIsNull() {
            addCriterion("sample_replace is null");
            return (Criteria) this;
        }

        public Criteria andSampleReplaceIsNotNull() {
            addCriterion("sample_replace is not null");
            return (Criteria) this;
        }

        public Criteria andSampleReplaceEqualTo(Integer value) {
            addCriterion("sample_replace =", value, "sampleReplace");
            return (Criteria) this;
        }

        public Criteria andSampleReplaceNotEqualTo(Integer value) {
            addCriterion("sample_replace <>", value, "sampleReplace");
            return (Criteria) this;
        }

        public Criteria andSampleReplaceGreaterThan(Integer value) {
            addCriterion("sample_replace >", value, "sampleReplace");
            return (Criteria) this;
        }

        public Criteria andSampleReplaceGreaterThanOrEqualTo(Integer value) {
            addCriterion("sample_replace >=", value, "sampleReplace");
            return (Criteria) this;
        }

        public Criteria andSampleReplaceLessThan(Integer value) {
            addCriterion("sample_replace <", value, "sampleReplace");
            return (Criteria) this;
        }

        public Criteria andSampleReplaceLessThanOrEqualTo(Integer value) {
            addCriterion("sample_replace <=", value, "sampleReplace");
            return (Criteria) this;
        }

        public Criteria andSampleReplaceIn(List<Integer> values) {
            addCriterion("sample_replace in", values, "sampleReplace");
            return (Criteria) this;
        }

        public Criteria andSampleReplaceNotIn(List<Integer> values) {
            addCriterion("sample_replace not in", values, "sampleReplace");
            return (Criteria) this;
        }

        public Criteria andSampleReplaceBetween(Integer value1, Integer value2) {
            addCriterion("sample_replace between", value1, value2, "sampleReplace");
            return (Criteria) this;
        }

        public Criteria andSampleReplaceNotBetween(Integer value1, Integer value2) {
            addCriterion("sample_replace not between", value1, value2, "sampleReplace");
            return (Criteria) this;
        }

        public Criteria andRandomSeedIsNull() {
            addCriterion("random_seed is null");
            return (Criteria) this;
        }

        public Criteria andRandomSeedIsNotNull() {
            addCriterion("random_seed is not null");
            return (Criteria) this;
        }

        public Criteria andRandomSeedEqualTo(Integer value) {
            addCriterion("random_seed =", value, "randomSeed");
            return (Criteria) this;
        }

        public Criteria andRandomSeedNotEqualTo(Integer value) {
            addCriterion("random_seed <>", value, "randomSeed");
            return (Criteria) this;
        }

        public Criteria andRandomSeedGreaterThan(Integer value) {
            addCriterion("random_seed >", value, "randomSeed");
            return (Criteria) this;
        }

        public Criteria andRandomSeedGreaterThanOrEqualTo(Integer value) {
            addCriterion("random_seed >=", value, "randomSeed");
            return (Criteria) this;
        }

        public Criteria andRandomSeedLessThan(Integer value) {
            addCriterion("random_seed <", value, "randomSeed");
            return (Criteria) this;
        }

        public Criteria andRandomSeedLessThanOrEqualTo(Integer value) {
            addCriterion("random_seed <=", value, "randomSeed");
            return (Criteria) this;
        }

        public Criteria andRandomSeedIn(List<Integer> values) {
            addCriterion("random_seed in", values, "randomSeed");
            return (Criteria) this;
        }

        public Criteria andRandomSeedNotIn(List<Integer> values) {
            addCriterion("random_seed not in", values, "randomSeed");
            return (Criteria) this;
        }

        public Criteria andRandomSeedBetween(Integer value1, Integer value2) {
            addCriterion("random_seed between", value1, value2, "randomSeed");
            return (Criteria) this;
        }

        public Criteria andRandomSeedNotBetween(Integer value1, Integer value2) {
            addCriterion("random_seed not between", value1, value2, "randomSeed");
            return (Criteria) this;
        }

        public Criteria andCoreNumIsNull() {
            addCriterion("core_num is null");
            return (Criteria) this;
        }

        public Criteria andCoreNumIsNotNull() {
            addCriterion("core_num is not null");
            return (Criteria) this;
        }

        public Criteria andCoreNumEqualTo(Integer value) {
            addCriterion("core_num =", value, "coreNum");
            return (Criteria) this;
        }

        public Criteria andCoreNumNotEqualTo(Integer value) {
            addCriterion("core_num <>", value, "coreNum");
            return (Criteria) this;
        }

        public Criteria andCoreNumGreaterThan(Integer value) {
            addCriterion("core_num >", value, "coreNum");
            return (Criteria) this;
        }

        public Criteria andCoreNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("core_num >=", value, "coreNum");
            return (Criteria) this;
        }

        public Criteria andCoreNumLessThan(Integer value) {
            addCriterion("core_num <", value, "coreNum");
            return (Criteria) this;
        }

        public Criteria andCoreNumLessThanOrEqualTo(Integer value) {
            addCriterion("core_num <=", value, "coreNum");
            return (Criteria) this;
        }

        public Criteria andCoreNumIn(List<Integer> values) {
            addCriterion("core_num in", values, "coreNum");
            return (Criteria) this;
        }

        public Criteria andCoreNumNotIn(List<Integer> values) {
            addCriterion("core_num not in", values, "coreNum");
            return (Criteria) this;
        }

        public Criteria andCoreNumBetween(Integer value1, Integer value2) {
            addCriterion("core_num between", value1, value2, "coreNum");
            return (Criteria) this;
        }

        public Criteria andCoreNumNotBetween(Integer value1, Integer value2) {
            addCriterion("core_num not between", value1, value2, "coreNum");
            return (Criteria) this;
        }

        public Criteria andMemSizePercoreIsNull() {
            addCriterion("mem_size_percore is null");
            return (Criteria) this;
        }

        public Criteria andMemSizePercoreIsNotNull() {
            addCriterion("mem_size_percore is not null");
            return (Criteria) this;
        }

        public Criteria andMemSizePercoreEqualTo(Integer value) {
            addCriterion("mem_size_percore =", value, "memSizePercore");
            return (Criteria) this;
        }

        public Criteria andMemSizePercoreNotEqualTo(Integer value) {
            addCriterion("mem_size_percore <>", value, "memSizePercore");
            return (Criteria) this;
        }

        public Criteria andMemSizePercoreGreaterThan(Integer value) {
            addCriterion("mem_size_percore >", value, "memSizePercore");
            return (Criteria) this;
        }

        public Criteria andMemSizePercoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("mem_size_percore >=", value, "memSizePercore");
            return (Criteria) this;
        }

        public Criteria andMemSizePercoreLessThan(Integer value) {
            addCriterion("mem_size_percore <", value, "memSizePercore");
            return (Criteria) this;
        }

        public Criteria andMemSizePercoreLessThanOrEqualTo(Integer value) {
            addCriterion("mem_size_percore <=", value, "memSizePercore");
            return (Criteria) this;
        }

        public Criteria andMemSizePercoreIn(List<Integer> values) {
            addCriterion("mem_size_percore in", values, "memSizePercore");
            return (Criteria) this;
        }

        public Criteria andMemSizePercoreNotIn(List<Integer> values) {
            addCriterion("mem_size_percore not in", values, "memSizePercore");
            return (Criteria) this;
        }

        public Criteria andMemSizePercoreBetween(Integer value1, Integer value2) {
            addCriterion("mem_size_percore between", value1, value2, "memSizePercore");
            return (Criteria) this;
        }

        public Criteria andMemSizePercoreNotBetween(Integer value1, Integer value2) {
            addCriterion("mem_size_percore not between", value1, value2, "memSizePercore");
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
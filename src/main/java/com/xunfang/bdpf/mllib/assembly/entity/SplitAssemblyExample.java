package com.xunfang.bdpf.mllib.assembly.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName SplitAssemblyExample
 * @Description: 拆分扩展类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 下午2:32:22
 * @version V1.0
 */
public class SplitAssemblyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SplitAssemblyExample() {
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

        public Criteria andResolutionModeIsNull() {
            addCriterion("resolution_mode is null");
            return (Criteria) this;
        }

        public Criteria andResolutionModeIsNotNull() {
            addCriterion("resolution_mode is not null");
            return (Criteria) this;
        }

        public Criteria andResolutionModeEqualTo(Integer value) {
            addCriterion("resolution_mode =", value, "resolutionMode");
            return (Criteria) this;
        }

        public Criteria andResolutionModeNotEqualTo(Integer value) {
            addCriterion("resolution_mode <>", value, "resolutionMode");
            return (Criteria) this;
        }

        public Criteria andResolutionModeGreaterThan(Integer value) {
            addCriterion("resolution_mode >", value, "resolutionMode");
            return (Criteria) this;
        }

        public Criteria andResolutionModeGreaterThanOrEqualTo(Integer value) {
            addCriterion("resolution_mode >=", value, "resolutionMode");
            return (Criteria) this;
        }

        public Criteria andResolutionModeLessThan(Integer value) {
            addCriterion("resolution_mode <", value, "resolutionMode");
            return (Criteria) this;
        }

        public Criteria andResolutionModeLessThanOrEqualTo(Integer value) {
            addCriterion("resolution_mode <=", value, "resolutionMode");
            return (Criteria) this;
        }

        public Criteria andResolutionModeIn(List<Integer> values) {
            addCriterion("resolution_mode in", values, "resolutionMode");
            return (Criteria) this;
        }

        public Criteria andResolutionModeNotIn(List<Integer> values) {
            addCriterion("resolution_mode not in", values, "resolutionMode");
            return (Criteria) this;
        }

        public Criteria andResolutionModeBetween(Integer value1, Integer value2) {
            addCriterion("resolution_mode between", value1, value2, "resolutionMode");
            return (Criteria) this;
        }

        public Criteria andResolutionModeNotBetween(Integer value1, Integer value2) {
            addCriterion("resolution_mode not between", value1, value2, "resolutionMode");
            return (Criteria) this;
        }

        public Criteria andSegmentationRatioIsNull() {
            addCriterion("segmentation_ratio is null");
            return (Criteria) this;
        }

        public Criteria andSegmentationRatioIsNotNull() {
            addCriterion("segmentation_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andSegmentationRatioEqualTo(Double value) {
            addCriterion("segmentation_ratio =", value, "segmentationRatio");
            return (Criteria) this;
        }

        public Criteria andSegmentationRatioNotEqualTo(Double value) {
            addCriterion("segmentation_ratio <>", value, "segmentationRatio");
            return (Criteria) this;
        }

        public Criteria andSegmentationRatioGreaterThan(Double value) {
            addCriterion("segmentation_ratio >", value, "segmentationRatio");
            return (Criteria) this;
        }

        public Criteria andSegmentationRatioGreaterThanOrEqualTo(Double value) {
            addCriterion("segmentation_ratio >=", value, "segmentationRatio");
            return (Criteria) this;
        }

        public Criteria andSegmentationRatioLessThan(Double value) {
            addCriterion("segmentation_ratio <", value, "segmentationRatio");
            return (Criteria) this;
        }

        public Criteria andSegmentationRatioLessThanOrEqualTo(Double value) {
            addCriterion("segmentation_ratio <=", value, "segmentationRatio");
            return (Criteria) this;
        }

        public Criteria andSegmentationRatioIn(List<Double> values) {
            addCriterion("segmentation_ratio in", values, "segmentationRatio");
            return (Criteria) this;
        }

        public Criteria andSegmentationRatioNotIn(List<Double> values) {
            addCriterion("segmentation_ratio not in", values, "segmentationRatio");
            return (Criteria) this;
        }

        public Criteria andSegmentationRatioBetween(Double value1, Double value2) {
            addCriterion("segmentation_ratio between", value1, value2, "segmentationRatio");
            return (Criteria) this;
        }

        public Criteria andSegmentationRatioNotBetween(Double value1, Double value2) {
            addCriterion("segmentation_ratio not between", value1, value2, "segmentationRatio");
            return (Criteria) this;
        }

        public Criteria andRandomIsNull() {
            addCriterion("random is null");
            return (Criteria) this;
        }

        public Criteria andRandomIsNotNull() {
            addCriterion("random is not null");
            return (Criteria) this;
        }

        public Criteria andRandomEqualTo(Integer value) {
            addCriterion("random =", value, "random");
            return (Criteria) this;
        }

        public Criteria andRandomNotEqualTo(Integer value) {
            addCriterion("random <>", value, "random");
            return (Criteria) this;
        }

        public Criteria andRandomGreaterThan(Integer value) {
            addCriterion("random >", value, "random");
            return (Criteria) this;
        }

        public Criteria andRandomGreaterThanOrEqualTo(Integer value) {
            addCriterion("random >=", value, "random");
            return (Criteria) this;
        }

        public Criteria andRandomLessThan(Integer value) {
            addCriterion("random <", value, "random");
            return (Criteria) this;
        }

        public Criteria andRandomLessThanOrEqualTo(Integer value) {
            addCriterion("random <=", value, "random");
            return (Criteria) this;
        }

        public Criteria andRandomIn(List<Integer> values) {
            addCriterion("random in", values, "random");
            return (Criteria) this;
        }

        public Criteria andRandomNotIn(List<Integer> values) {
            addCriterion("random not in", values, "random");
            return (Criteria) this;
        }

        public Criteria andRandomBetween(Integer value1, Integer value2) {
            addCriterion("random between", value1, value2, "random");
            return (Criteria) this;
        }

        public Criteria andRandomNotBetween(Integer value1, Integer value2) {
            addCriterion("random not between", value1, value2, "random");
            return (Criteria) this;
        }

        public Criteria andCoreNumberIsNull() {
            addCriterion("core_number is null");
            return (Criteria) this;
        }

        public Criteria andCoreNumberIsNotNull() {
            addCriterion("core_number is not null");
            return (Criteria) this;
        }

        public Criteria andCoreNumberEqualTo(Integer value) {
            addCriterion("core_number =", value, "coreNumber");
            return (Criteria) this;
        }

        public Criteria andCoreNumberNotEqualTo(Integer value) {
            addCriterion("core_number <>", value, "coreNumber");
            return (Criteria) this;
        }

        public Criteria andCoreNumberGreaterThan(Integer value) {
            addCriterion("core_number >", value, "coreNumber");
            return (Criteria) this;
        }

        public Criteria andCoreNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("core_number >=", value, "coreNumber");
            return (Criteria) this;
        }

        public Criteria andCoreNumberLessThan(Integer value) {
            addCriterion("core_number <", value, "coreNumber");
            return (Criteria) this;
        }

        public Criteria andCoreNumberLessThanOrEqualTo(Integer value) {
            addCriterion("core_number <=", value, "coreNumber");
            return (Criteria) this;
        }

        public Criteria andCoreNumberIn(List<Integer> values) {
            addCriterion("core_number in", values, "coreNumber");
            return (Criteria) this;
        }

        public Criteria andCoreNumberNotIn(List<Integer> values) {
            addCriterion("core_number not in", values, "coreNumber");
            return (Criteria) this;
        }

        public Criteria andCoreNumberBetween(Integer value1, Integer value2) {
            addCriterion("core_number between", value1, value2, "coreNumber");
            return (Criteria) this;
        }

        public Criteria andCoreNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("core_number not between", value1, value2, "coreNumber");
            return (Criteria) this;
        }

        public Criteria andMemoryIsNull() {
            addCriterion("memory is null");
            return (Criteria) this;
        }

        public Criteria andMemoryIsNotNull() {
            addCriterion("memory is not null");
            return (Criteria) this;
        }

        public Criteria andMemoryEqualTo(Integer value) {
            addCriterion("memory =", value, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryNotEqualTo(Integer value) {
            addCriterion("memory <>", value, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryGreaterThan(Integer value) {
            addCriterion("memory >", value, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryGreaterThanOrEqualTo(Integer value) {
            addCriterion("memory >=", value, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryLessThan(Integer value) {
            addCriterion("memory <", value, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryLessThanOrEqualTo(Integer value) {
            addCriterion("memory <=", value, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryIn(List<Integer> values) {
            addCriterion("memory in", values, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryNotIn(List<Integer> values) {
            addCriterion("memory not in", values, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryBetween(Integer value1, Integer value2) {
            addCriterion("memory between", value1, value2, "memory");
            return (Criteria) this;
        }

        public Criteria andMemoryNotBetween(Integer value1, Integer value2) {
            addCriterion("memory not between", value1, value2, "memory");
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
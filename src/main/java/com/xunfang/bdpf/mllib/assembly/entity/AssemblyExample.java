package com.xunfang.bdpf.mllib.assembly.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @ClassName AssemblyExample
 * @Description: 组件扩展类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 上午10:49:23
 * @version V1.0
 */
public class AssemblyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AssemblyExample() {
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

        public Criteria andBdpfMllibExperimentIdIsNull() {
            addCriterion("bdpf_mllib_experiment_id is null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibExperimentIdIsNotNull() {
            addCriterion("bdpf_mllib_experiment_id is not null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibExperimentIdEqualTo(String value) {
            addCriterion("bdpf_mllib_experiment_id =", value, "bdpfMllibExperimentId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibExperimentIdNotEqualTo(String value) {
            addCriterion("bdpf_mllib_experiment_id <>", value, "bdpfMllibExperimentId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibExperimentIdGreaterThan(String value) {
            addCriterion("bdpf_mllib_experiment_id >", value, "bdpfMllibExperimentId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibExperimentIdGreaterThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_experiment_id >=", value, "bdpfMllibExperimentId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibExperimentIdLessThan(String value) {
            addCriterion("bdpf_mllib_experiment_id <", value, "bdpfMllibExperimentId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibExperimentIdLessThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_experiment_id <=", value, "bdpfMllibExperimentId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibExperimentIdLike(String value) {
            addCriterion("bdpf_mllib_experiment_id like", value, "bdpfMllibExperimentId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibExperimentIdNotLike(String value) {
            addCriterion("bdpf_mllib_experiment_id not like", value, "bdpfMllibExperimentId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibExperimentIdIn(List<String> values) {
            addCriterion("bdpf_mllib_experiment_id in", values, "bdpfMllibExperimentId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibExperimentIdNotIn(List<String> values) {
            addCriterion("bdpf_mllib_experiment_id not in", values, "bdpfMllibExperimentId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibExperimentIdBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_experiment_id between", value1, value2, "bdpfMllibExperimentId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibExperimentIdNotBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_experiment_id not between", value1, value2, "bdpfMllibExperimentId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLibraryIdIsNull() {
            addCriterion("bdpf_mllib_assembly_library_id is null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLibraryIdIsNotNull() {
            addCriterion("bdpf_mllib_assembly_library_id is not null");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLibraryIdEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_library_id =", value, "bdpfMllibAssemblyLibraryId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLibraryIdNotEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_library_id <>", value, "bdpfMllibAssemblyLibraryId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLibraryIdGreaterThan(String value) {
            addCriterion("bdpf_mllib_assembly_library_id >", value, "bdpfMllibAssemblyLibraryId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLibraryIdGreaterThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_library_id >=", value, "bdpfMllibAssemblyLibraryId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLibraryIdLessThan(String value) {
            addCriterion("bdpf_mllib_assembly_library_id <", value, "bdpfMllibAssemblyLibraryId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLibraryIdLessThanOrEqualTo(String value) {
            addCriterion("bdpf_mllib_assembly_library_id <=", value, "bdpfMllibAssemblyLibraryId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLibraryIdLike(String value) {
            addCriterion("bdpf_mllib_assembly_library_id like", value, "bdpfMllibAssemblyLibraryId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLibraryIdNotLike(String value) {
            addCriterion("bdpf_mllib_assembly_library_id not like", value, "bdpfMllibAssemblyLibraryId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLibraryIdIn(List<String> values) {
            addCriterion("bdpf_mllib_assembly_library_id in", values, "bdpfMllibAssemblyLibraryId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLibraryIdNotIn(List<String> values) {
            addCriterion("bdpf_mllib_assembly_library_id not in", values, "bdpfMllibAssemblyLibraryId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLibraryIdBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_assembly_library_id between", value1, value2, "bdpfMllibAssemblyLibraryId");
            return (Criteria) this;
        }

        public Criteria andBdpfMllibAssemblyLibraryIdNotBetween(String value1, String value2) {
            addCriterion("bdpf_mllib_assembly_library_id not between", value1, value2, "bdpfMllibAssemblyLibraryId");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNull() {
            addCriterion("parent_id is null");
            return (Criteria) this;
        }

        public Criteria andParentIdIsNotNull() {
            addCriterion("parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andParentIdEqualTo(String value) {
            addCriterion("parent_id =", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotEqualTo(String value) {
            addCriterion("parent_id <>", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThan(String value) {
            addCriterion("parent_id >", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("parent_id >=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThan(String value) {
            addCriterion("parent_id <", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdLessThanOrEqualTo(String value) {
            addCriterion("parent_id <=", value, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdIn(List<Integer> values) {
            addCriterion("parent_id in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotIn(List<String> values) {
            addCriterion("parent_id not in", values, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdBetween(String value1, String value2) {
            addCriterion("parent_id between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andParentIdNotBetween(String value1, String value2) {
            addCriterion("parent_id not between", value1, value2, "parentId");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
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

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andXhIsNull() {
            addCriterion("xh is null");
            return (Criteria) this;
        }

        public Criteria andXhIsNotNull() {
            addCriterion("xh is not null");
            return (Criteria) this;
        }

        public Criteria andXhEqualTo(Integer value) {
            addCriterion("xh =", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotEqualTo(Integer value) {
            addCriterion("xh <>", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThan(Integer value) {
            addCriterion("xh >", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhGreaterThanOrEqualTo(Integer value) {
            addCriterion("xh >=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThan(Integer value) {
            addCriterion("xh <", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhLessThanOrEqualTo(Integer value) {
            addCriterion("xh <=", value, "xh");
            return (Criteria) this;
        }

        public Criteria andXhIn(List<Integer> values) {
            addCriterion("xh in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotIn(List<Integer> values) {
            addCriterion("xh not in", values, "xh");
            return (Criteria) this;
        }

        public Criteria andXhBetween(Integer value1, Integer value2) {
            addCriterion("xh between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andXhNotBetween(Integer value1, Integer value2) {
            addCriterion("xh not between", value1, value2, "xh");
            return (Criteria) this;
        }

        public Criteria andIsAssemblyIsNull() {
            addCriterion("is_assembly is null");
            return (Criteria) this;
        }

        public Criteria andIsAssemblyIsNotNull() {
            addCriterion("is_assembly is not null");
            return (Criteria) this;
        }

        public Criteria andIsAssemblyEqualTo(Integer value) {
            addCriterion("is_assembly =", value, "isAssembly");
            return (Criteria) this;
        }

        public Criteria andIsAssemblyNotEqualTo(Integer value) {
            addCriterion("is_assembly <>", value, "isAssembly");
            return (Criteria) this;
        }

        public Criteria andIsAssemblyGreaterThan(Integer value) {
            addCriterion("is_assembly >", value, "isAssembly");
            return (Criteria) this;
        }

        public Criteria andIsAssemblyGreaterThanOrEqualTo(Integer value) {
            addCriterion("is_assembly >=", value, "isAssembly");
            return (Criteria) this;
        }

        public Criteria andIsAssemblyLessThan(Integer value) {
            addCriterion("is_assembly <", value, "isAssembly");
            return (Criteria) this;
        }

        public Criteria andIsAssemblyLessThanOrEqualTo(Integer value) {
            addCriterion("is_assembly <=", value, "isAssembly");
            return (Criteria) this;
        }

        public Criteria andIsAssemblyIn(List<Integer> values) {
            addCriterion("is_assembly in", values, "isAssembly");
            return (Criteria) this;
        }

        public Criteria andIsAssemblyNotIn(List<Integer> values) {
            addCriterion("is_assembly not in", values, "isAssembly");
            return (Criteria) this;
        }

        public Criteria andIsAssemblyBetween(Integer value1, Integer value2) {
            addCriterion("is_assembly between", value1, value2, "isAssembly");
            return (Criteria) this;
        }

        public Criteria andIsAssemblyNotBetween(Integer value1, Integer value2) {
            addCriterion("is_assembly not between", value1, value2, "isAssembly");
            return (Criteria) this;
        }

        public Criteria andMainarrIsNull() {
            addCriterion("mainArr is null");
            return (Criteria) this;
        }

        public Criteria andMainarrIsNotNull() {
            addCriterion("mainArr is not null");
            return (Criteria) this;
        }

        public Criteria andMainarrEqualTo(String value) {
            addCriterion("mainArr =", value, "mainarr");
            return (Criteria) this;
        }

        public Criteria andMainarrNotEqualTo(String value) {
            addCriterion("mainArr <>", value, "mainarr");
            return (Criteria) this;
        }

        public Criteria andMainarrGreaterThan(String value) {
            addCriterion("mainArr >", value, "mainarr");
            return (Criteria) this;
        }

        public Criteria andMainarrGreaterThanOrEqualTo(String value) {
            addCriterion("mainArr >=", value, "mainarr");
            return (Criteria) this;
        }

        public Criteria andMainarrLessThan(String value) {
            addCriterion("mainArr <", value, "mainarr");
            return (Criteria) this;
        }

        public Criteria andMainarrLessThanOrEqualTo(String value) {
            addCriterion("mainArr <=", value, "mainarr");
            return (Criteria) this;
        }

        public Criteria andMainarrLike(String value) {
            addCriterion("mainArr like", value, "mainarr");
            return (Criteria) this;
        }

        public Criteria andMainarrNotLike(String value) {
            addCriterion("mainArr not like", value, "mainarr");
            return (Criteria) this;
        }

        public Criteria andMainarrIn(List<String> values) {
            addCriterion("mainArr in", values, "mainarr");
            return (Criteria) this;
        }

        public Criteria andMainarrNotIn(List<String> values) {
            addCriterion("mainArr not in", values, "mainarr");
            return (Criteria) this;
        }

        public Criteria andMainarrBetween(String value1, String value2) {
            addCriterion("mainArr between", value1, value2, "mainarr");
            return (Criteria) this;
        }

        public Criteria andMainarrNotBetween(String value1, String value2) {
            addCriterion("mainArr not between", value1, value2, "mainarr");
            return (Criteria) this;
        }

        public Criteria andConnectsIsNull() {
            addCriterion("connects is null");
            return (Criteria) this;
        }

        public Criteria andConnectsIsNotNull() {
            addCriterion("connects is not null");
            return (Criteria) this;
        }

        public Criteria andConnectsEqualTo(String value) {
            addCriterion("connects =", value, "connects");
            return (Criteria) this;
        }

        public Criteria andConnectsNotEqualTo(String value) {
            addCriterion("connects <>", value, "connects");
            return (Criteria) this;
        }

        public Criteria andConnectsGreaterThan(String value) {
            addCriterion("connects >", value, "connects");
            return (Criteria) this;
        }

        public Criteria andConnectsGreaterThanOrEqualTo(String value) {
            addCriterion("connects >=", value, "connects");
            return (Criteria) this;
        }

        public Criteria andConnectsLessThan(String value) {
            addCriterion("connects <", value, "connects");
            return (Criteria) this;
        }

        public Criteria andConnectsLessThanOrEqualTo(String value) {
            addCriterion("connects <=", value, "connects");
            return (Criteria) this;
        }

        public Criteria andConnectsLike(String value) {
            addCriterion("connects like", value, "connects");
            return (Criteria) this;
        }

        public Criteria andConnectsNotLike(String value) {
            addCriterion("connects not like", value, "connects");
            return (Criteria) this;
        }

        public Criteria andConnectsIn(List<String> values) {
            addCriterion("connects in", values, "connects");
            return (Criteria) this;
        }

        public Criteria andConnectsNotIn(List<String> values) {
            addCriterion("connects not in", values, "connects");
            return (Criteria) this;
        }

        public Criteria andConnectsBetween(String value1, String value2) {
            addCriterion("connects between", value1, value2, "connects");
            return (Criteria) this;
        }

        public Criteria andConnectsNotBetween(String value1, String value2) {
            addCriterion("connects not between", value1, value2, "connects");
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
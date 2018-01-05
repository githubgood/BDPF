package com.xunfang.bdpf.mllib.assembly.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName SqlAssemblyExample
 * @Description: SQL脚本扩展类
 * Copyright: Copyright (c) 2017 
 * Company:深圳市讯方技术股份有限公司
 *
 * @author jm
 * @date 2017年9月19日 下午2:32:26
 * @version V1.0
 */
public class SqlAssemblyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SqlAssemblyExample() {
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

        public Criteria andFirstIsNull() {
            addCriterion("first is null");
            return (Criteria) this;
        }

        public Criteria andFirstIsNotNull() {
            addCriterion("first is not null");
            return (Criteria) this;
        }

        public Criteria andFirstEqualTo(String value) {
            addCriterion("first =", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotEqualTo(String value) {
            addCriterion("first <>", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstGreaterThan(String value) {
            addCriterion("first >", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstGreaterThanOrEqualTo(String value) {
            addCriterion("first >=", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstLessThan(String value) {
            addCriterion("first <", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstLessThanOrEqualTo(String value) {
            addCriterion("first <=", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstLike(String value) {
            addCriterion("first like", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotLike(String value) {
            addCriterion("first not like", value, "first");
            return (Criteria) this;
        }

        public Criteria andFirstIn(List<String> values) {
            addCriterion("first in", values, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotIn(List<String> values) {
            addCriterion("first not in", values, "first");
            return (Criteria) this;
        }

        public Criteria andFirstBetween(String value1, String value2) {
            addCriterion("first between", value1, value2, "first");
            return (Criteria) this;
        }

        public Criteria andFirstNotBetween(String value1, String value2) {
            addCriterion("first not between", value1, value2, "first");
            return (Criteria) this;
        }

        public Criteria andSecondIsNull() {
            addCriterion("second is null");
            return (Criteria) this;
        }

        public Criteria andSecondIsNotNull() {
            addCriterion("second is not null");
            return (Criteria) this;
        }

        public Criteria andSecondEqualTo(String value) {
            addCriterion("second =", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondNotEqualTo(String value) {
            addCriterion("second <>", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondGreaterThan(String value) {
            addCriterion("second >", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondGreaterThanOrEqualTo(String value) {
            addCriterion("second >=", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondLessThan(String value) {
            addCriterion("second <", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondLessThanOrEqualTo(String value) {
            addCriterion("second <=", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondLike(String value) {
            addCriterion("second like", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondNotLike(String value) {
            addCriterion("second not like", value, "second");
            return (Criteria) this;
        }

        public Criteria andSecondIn(List<String> values) {
            addCriterion("second in", values, "second");
            return (Criteria) this;
        }

        public Criteria andSecondNotIn(List<String> values) {
            addCriterion("second not in", values, "second");
            return (Criteria) this;
        }

        public Criteria andSecondBetween(String value1, String value2) {
            addCriterion("second between", value1, value2, "second");
            return (Criteria) this;
        }

        public Criteria andSecondNotBetween(String value1, String value2) {
            addCriterion("second not between", value1, value2, "second");
            return (Criteria) this;
        }

        public Criteria andThirdIsNull() {
            addCriterion("third is null");
            return (Criteria) this;
        }

        public Criteria andThirdIsNotNull() {
            addCriterion("third is not null");
            return (Criteria) this;
        }

        public Criteria andThirdEqualTo(String value) {
            addCriterion("third =", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdNotEqualTo(String value) {
            addCriterion("third <>", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdGreaterThan(String value) {
            addCriterion("third >", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdGreaterThanOrEqualTo(String value) {
            addCriterion("third >=", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdLessThan(String value) {
            addCriterion("third <", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdLessThanOrEqualTo(String value) {
            addCriterion("third <=", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdLike(String value) {
            addCriterion("third like", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdNotLike(String value) {
            addCriterion("third not like", value, "third");
            return (Criteria) this;
        }

        public Criteria andThirdIn(List<String> values) {
            addCriterion("third in", values, "third");
            return (Criteria) this;
        }

        public Criteria andThirdNotIn(List<String> values) {
            addCriterion("third not in", values, "third");
            return (Criteria) this;
        }

        public Criteria andThirdBetween(String value1, String value2) {
            addCriterion("third between", value1, value2, "third");
            return (Criteria) this;
        }

        public Criteria andThirdNotBetween(String value1, String value2) {
            addCriterion("third not between", value1, value2, "third");
            return (Criteria) this;
        }

        public Criteria andFourIsNull() {
            addCriterion("four is null");
            return (Criteria) this;
        }

        public Criteria andFourIsNotNull() {
            addCriterion("four is not null");
            return (Criteria) this;
        }

        public Criteria andFourEqualTo(String value) {
            addCriterion("four =", value, "four");
            return (Criteria) this;
        }

        public Criteria andFourNotEqualTo(String value) {
            addCriterion("four <>", value, "four");
            return (Criteria) this;
        }

        public Criteria andFourGreaterThan(String value) {
            addCriterion("four >", value, "four");
            return (Criteria) this;
        }

        public Criteria andFourGreaterThanOrEqualTo(String value) {
            addCriterion("four >=", value, "four");
            return (Criteria) this;
        }

        public Criteria andFourLessThan(String value) {
            addCriterion("four <", value, "four");
            return (Criteria) this;
        }

        public Criteria andFourLessThanOrEqualTo(String value) {
            addCriterion("four <=", value, "four");
            return (Criteria) this;
        }

        public Criteria andFourLike(String value) {
            addCriterion("four like", value, "four");
            return (Criteria) this;
        }

        public Criteria andFourNotLike(String value) {
            addCriterion("four not like", value, "four");
            return (Criteria) this;
        }

        public Criteria andFourIn(List<String> values) {
            addCriterion("four in", values, "four");
            return (Criteria) this;
        }

        public Criteria andFourNotIn(List<String> values) {
            addCriterion("four not in", values, "four");
            return (Criteria) this;
        }

        public Criteria andFourBetween(String value1, String value2) {
            addCriterion("four between", value1, value2, "four");
            return (Criteria) this;
        }

        public Criteria andFourNotBetween(String value1, String value2) {
            addCriterion("four not between", value1, value2, "four");
            return (Criteria) this;
        }

        public Criteria andSqlScriptIsNull() {
            addCriterion("sql_script is null");
            return (Criteria) this;
        }

        public Criteria andSqlScriptIsNotNull() {
            addCriterion("sql_script is not null");
            return (Criteria) this;
        }

        public Criteria andSqlScriptEqualTo(String value) {
            addCriterion("sql_script =", value, "sqlScript");
            return (Criteria) this;
        }

        public Criteria andSqlScriptNotEqualTo(String value) {
            addCriterion("sql_script <>", value, "sqlScript");
            return (Criteria) this;
        }

        public Criteria andSqlScriptGreaterThan(String value) {
            addCriterion("sql_script >", value, "sqlScript");
            return (Criteria) this;
        }

        public Criteria andSqlScriptGreaterThanOrEqualTo(String value) {
            addCriterion("sql_script >=", value, "sqlScript");
            return (Criteria) this;
        }

        public Criteria andSqlScriptLessThan(String value) {
            addCriterion("sql_script <", value, "sqlScript");
            return (Criteria) this;
        }

        public Criteria andSqlScriptLessThanOrEqualTo(String value) {
            addCriterion("sql_script <=", value, "sqlScript");
            return (Criteria) this;
        }

        public Criteria andSqlScriptLike(String value) {
            addCriterion("sql_script like", value, "sqlScript");
            return (Criteria) this;
        }

        public Criteria andSqlScriptNotLike(String value) {
            addCriterion("sql_script not like", value, "sqlScript");
            return (Criteria) this;
        }

        public Criteria andSqlScriptIn(List<String> values) {
            addCriterion("sql_script in", values, "sqlScript");
            return (Criteria) this;
        }

        public Criteria andSqlScriptNotIn(List<String> values) {
            addCriterion("sql_script not in", values, "sqlScript");
            return (Criteria) this;
        }

        public Criteria andSqlScriptBetween(String value1, String value2) {
            addCriterion("sql_script between", value1, value2, "sqlScript");
            return (Criteria) this;
        }

        public Criteria andSqlScriptNotBetween(String value1, String value2) {
            addCriterion("sql_script not between", value1, value2, "sqlScript");
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
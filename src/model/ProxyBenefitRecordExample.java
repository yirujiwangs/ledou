package model;

import model.divide.BaseDivideModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProxyBenefitRecordExample extends BaseDivideModel {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProxyBenefitRecordExample() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenIsNull() {
            addCriterion("proxy_token is null");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenIsNotNull() {
            addCriterion("proxy_token is not null");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenEqualTo(String value) {
            addCriterion("proxy_token =", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenNotEqualTo(String value) {
            addCriterion("proxy_token <>", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenGreaterThan(String value) {
            addCriterion("proxy_token >", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenGreaterThanOrEqualTo(String value) {
            addCriterion("proxy_token >=", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenLessThan(String value) {
            addCriterion("proxy_token <", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenLessThanOrEqualTo(String value) {
            addCriterion("proxy_token <=", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenLike(String value) {
            addCriterion("proxy_token like", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenNotLike(String value) {
            addCriterion("proxy_token not like", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenIn(List<String> values) {
            addCriterion("proxy_token in", values, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenNotIn(List<String> values) {
            addCriterion("proxy_token not in", values, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenBetween(String value1, String value2) {
            addCriterion("proxy_token between", value1, value2, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenNotBetween(String value1, String value2) {
            addCriterion("proxy_token not between", value1, value2, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andYear_service_benefitIsNull() {
            addCriterion("year_service_benefit is null");
            return (Criteria) this;
        }

        public Criteria andYear_service_benefitIsNotNull() {
            addCriterion("year_service_benefit is not null");
            return (Criteria) this;
        }

        public Criteria andYear_service_benefitEqualTo(Double value) {
            addCriterion("year_service_benefit =", value, "year_service_benefit");
            return (Criteria) this;
        }

        public Criteria andYear_service_benefitNotEqualTo(Double value) {
            addCriterion("year_service_benefit <>", value, "year_service_benefit");
            return (Criteria) this;
        }

        public Criteria andYear_service_benefitGreaterThan(Double value) {
            addCriterion("year_service_benefit >", value, "year_service_benefit");
            return (Criteria) this;
        }

        public Criteria andYear_service_benefitGreaterThanOrEqualTo(Double value) {
            addCriterion("year_service_benefit >=", value, "year_service_benefit");
            return (Criteria) this;
        }

        public Criteria andYear_service_benefitLessThan(Double value) {
            addCriterion("year_service_benefit <", value, "year_service_benefit");
            return (Criteria) this;
        }

        public Criteria andYear_service_benefitLessThanOrEqualTo(Double value) {
            addCriterion("year_service_benefit <=", value, "year_service_benefit");
            return (Criteria) this;
        }

        public Criteria andYear_service_benefitIn(List<Double> values) {
            addCriterion("year_service_benefit in", values, "year_service_benefit");
            return (Criteria) this;
        }

        public Criteria andYear_service_benefitNotIn(List<Double> values) {
            addCriterion("year_service_benefit not in", values, "year_service_benefit");
            return (Criteria) this;
        }

        public Criteria andYear_service_benefitBetween(Double value1, Double value2) {
            addCriterion("year_service_benefit between", value1, value2, "year_service_benefit");
            return (Criteria) this;
        }

        public Criteria andYear_service_benefitNotBetween(Double value1, Double value2) {
            addCriterion("year_service_benefit not between", value1, value2, "year_service_benefit");
            return (Criteria) this;
        }

        public Criteria andLedouke_benefitIsNull() {
            addCriterion("ledouke_benefit is null");
            return (Criteria) this;
        }

        public Criteria andLedouke_benefitIsNotNull() {
            addCriterion("ledouke_benefit is not null");
            return (Criteria) this;
        }

        public Criteria andLedouke_benefitEqualTo(Double value) {
            addCriterion("ledouke_benefit =", value, "ledouke_benefit");
            return (Criteria) this;
        }

        public Criteria andLedouke_benefitNotEqualTo(Double value) {
            addCriterion("ledouke_benefit <>", value, "ledouke_benefit");
            return (Criteria) this;
        }

        public Criteria andLedouke_benefitGreaterThan(Double value) {
            addCriterion("ledouke_benefit >", value, "ledouke_benefit");
            return (Criteria) this;
        }

        public Criteria andLedouke_benefitGreaterThanOrEqualTo(Double value) {
            addCriterion("ledouke_benefit >=", value, "ledouke_benefit");
            return (Criteria) this;
        }

        public Criteria andLedouke_benefitLessThan(Double value) {
            addCriterion("ledouke_benefit <", value, "ledouke_benefit");
            return (Criteria) this;
        }

        public Criteria andLedouke_benefitLessThanOrEqualTo(Double value) {
            addCriterion("ledouke_benefit <=", value, "ledouke_benefit");
            return (Criteria) this;
        }

        public Criteria andLedouke_benefitIn(List<Double> values) {
            addCriterion("ledouke_benefit in", values, "ledouke_benefit");
            return (Criteria) this;
        }

        public Criteria andLedouke_benefitNotIn(List<Double> values) {
            addCriterion("ledouke_benefit not in", values, "ledouke_benefit");
            return (Criteria) this;
        }

        public Criteria andLedouke_benefitBetween(Double value1, Double value2) {
            addCriterion("ledouke_benefit between", value1, value2, "ledouke_benefit");
            return (Criteria) this;
        }

        public Criteria andLedouke_benefitNotBetween(Double value1, Double value2) {
            addCriterion("ledouke_benefit not between", value1, value2, "ledouke_benefit");
            return (Criteria) this;
        }

        public Criteria andLive_circle_benefitIsNull() {
            addCriterion("live_circle_benefit is null");
            return (Criteria) this;
        }

        public Criteria andLive_circle_benefitIsNotNull() {
            addCriterion("live_circle_benefit is not null");
            return (Criteria) this;
        }

        public Criteria andLive_circle_benefitEqualTo(Double value) {
            addCriterion("live_circle_benefit =", value, "live_circle_benefit");
            return (Criteria) this;
        }

        public Criteria andLive_circle_benefitNotEqualTo(Double value) {
            addCriterion("live_circle_benefit <>", value, "live_circle_benefit");
            return (Criteria) this;
        }

        public Criteria andLive_circle_benefitGreaterThan(Double value) {
            addCriterion("live_circle_benefit >", value, "live_circle_benefit");
            return (Criteria) this;
        }

        public Criteria andLive_circle_benefitGreaterThanOrEqualTo(Double value) {
            addCriterion("live_circle_benefit >=", value, "live_circle_benefit");
            return (Criteria) this;
        }

        public Criteria andLive_circle_benefitLessThan(Double value) {
            addCriterion("live_circle_benefit <", value, "live_circle_benefit");
            return (Criteria) this;
        }

        public Criteria andLive_circle_benefitLessThanOrEqualTo(Double value) {
            addCriterion("live_circle_benefit <=", value, "live_circle_benefit");
            return (Criteria) this;
        }

        public Criteria andLive_circle_benefitIn(List<Double> values) {
            addCriterion("live_circle_benefit in", values, "live_circle_benefit");
            return (Criteria) this;
        }

        public Criteria andLive_circle_benefitNotIn(List<Double> values) {
            addCriterion("live_circle_benefit not in", values, "live_circle_benefit");
            return (Criteria) this;
        }

        public Criteria andLive_circle_benefitBetween(Double value1, Double value2) {
            addCriterion("live_circle_benefit between", value1, value2, "live_circle_benefit");
            return (Criteria) this;
        }

        public Criteria andLive_circle_benefitNotBetween(Double value1, Double value2) {
            addCriterion("live_circle_benefit not between", value1, value2, "live_circle_benefit");
            return (Criteria) this;
        }

        public Criteria andLescene_benefitIsNull() {
            addCriterion("lescene_benefit is null");
            return (Criteria) this;
        }

        public Criteria andLescene_benefitIsNotNull() {
            addCriterion("lescene_benefit is not null");
            return (Criteria) this;
        }

        public Criteria andLescene_benefitEqualTo(Double value) {
            addCriterion("lescene_benefit =", value, "lescene_benefit");
            return (Criteria) this;
        }

        public Criteria andLescene_benefitNotEqualTo(Double value) {
            addCriterion("lescene_benefit <>", value, "lescene_benefit");
            return (Criteria) this;
        }

        public Criteria andLescene_benefitGreaterThan(Double value) {
            addCriterion("lescene_benefit >", value, "lescene_benefit");
            return (Criteria) this;
        }

        public Criteria andLescene_benefitGreaterThanOrEqualTo(Double value) {
            addCriterion("lescene_benefit >=", value, "lescene_benefit");
            return (Criteria) this;
        }

        public Criteria andLescene_benefitLessThan(Double value) {
            addCriterion("lescene_benefit <", value, "lescene_benefit");
            return (Criteria) this;
        }

        public Criteria andLescene_benefitLessThanOrEqualTo(Double value) {
            addCriterion("lescene_benefit <=", value, "lescene_benefit");
            return (Criteria) this;
        }

        public Criteria andLescene_benefitIn(List<Double> values) {
            addCriterion("lescene_benefit in", values, "lescene_benefit");
            return (Criteria) this;
        }

        public Criteria andLescene_benefitNotIn(List<Double> values) {
            addCriterion("lescene_benefit not in", values, "lescene_benefit");
            return (Criteria) this;
        }

        public Criteria andLescene_benefitBetween(Double value1, Double value2) {
            addCriterion("lescene_benefit between", value1, value2, "lescene_benefit");
            return (Criteria) this;
        }

        public Criteria andLescene_benefitNotBetween(Double value1, Double value2) {
            addCriterion("lescene_benefit not between", value1, value2, "lescene_benefit");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createtime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createtime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createtime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createtime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createtime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createtime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createtime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createtime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createtime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createtime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createtime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createtime not between", value1, value2, "createtime");
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
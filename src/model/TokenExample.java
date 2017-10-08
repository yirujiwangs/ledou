package model;

import model.divide.Criterion;

import java.util.ArrayList;
import java.util.List;

public class TokenExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TokenExample() {
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

        public Criteria andAccesstokenIsNull() {
            addCriterion("accessToken is null");
            return (Criteria) this;
        }

        public Criteria andAccesstokenIsNotNull() {
            addCriterion("accessToken is not null");
            return (Criteria) this;
        }

        public Criteria andAccesstokenEqualTo(String value) {
            addCriterion("accessToken =", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenNotEqualTo(String value) {
            addCriterion("accessToken <>", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenGreaterThan(String value) {
            addCriterion("accessToken >", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenGreaterThanOrEqualTo(String value) {
            addCriterion("accessToken >=", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenLessThan(String value) {
            addCriterion("accessToken <", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenLessThanOrEqualTo(String value) {
            addCriterion("accessToken <=", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenLike(String value) {
            addCriterion("accessToken like", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenNotLike(String value) {
            addCriterion("accessToken not like", value, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenIn(List<String> values) {
            addCriterion("accessToken in", values, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenNotIn(List<String> values) {
            addCriterion("accessToken not in", values, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenBetween(String value1, String value2) {
            addCriterion("accessToken between", value1, value2, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andAccesstokenNotBetween(String value1, String value2) {
            addCriterion("accessToken not between", value1, value2, "accesstoken");
            return (Criteria) this;
        }

        public Criteria andExpiresinIsNull() {
            addCriterion("expiresIn is null");
            return (Criteria) this;
        }

        public Criteria andExpiresinIsNotNull() {
            addCriterion("expiresIn is not null");
            return (Criteria) this;
        }

        public Criteria andExpiresinEqualTo(Integer value) {
            addCriterion("expiresIn =", value, "expiresin");
            return (Criteria) this;
        }

        public Criteria andExpiresinNotEqualTo(Integer value) {
            addCriterion("expiresIn <>", value, "expiresin");
            return (Criteria) this;
        }

        public Criteria andExpiresinGreaterThan(Integer value) {
            addCriterion("expiresIn >", value, "expiresin");
            return (Criteria) this;
        }

        public Criteria andExpiresinGreaterThanOrEqualTo(Integer value) {
            addCriterion("expiresIn >=", value, "expiresin");
            return (Criteria) this;
        }

        public Criteria andExpiresinLessThan(Integer value) {
            addCriterion("expiresIn <", value, "expiresin");
            return (Criteria) this;
        }

        public Criteria andExpiresinLessThanOrEqualTo(Integer value) {
            addCriterion("expiresIn <=", value, "expiresin");
            return (Criteria) this;
        }

        public Criteria andExpiresinIn(List<Integer> values) {
            addCriterion("expiresIn in", values, "expiresin");
            return (Criteria) this;
        }

        public Criteria andExpiresinNotIn(List<Integer> values) {
            addCriterion("expiresIn not in", values, "expiresin");
            return (Criteria) this;
        }

        public Criteria andExpiresinBetween(Integer value1, Integer value2) {
            addCriterion("expiresIn between", value1, value2, "expiresin");
            return (Criteria) this;
        }

        public Criteria andExpiresinNotBetween(Integer value1, Integer value2) {
            addCriterion("expiresIn not between", value1, value2, "expiresin");
            return (Criteria) this;
        }

        public Criteria andFirstgettimeIsNull() {
            addCriterion("firstGetTime is null");
            return (Criteria) this;
        }

        public Criteria andFirstgettimeIsNotNull() {
            addCriterion("firstGetTime is not null");
            return (Criteria) this;
        }

        public Criteria andFirstgettimeEqualTo(Integer value) {
            addCriterion("firstGetTime =", value, "firstgettime");
            return (Criteria) this;
        }

        public Criteria andFirstgettimeNotEqualTo(Integer value) {
            addCriterion("firstGetTime <>", value, "firstgettime");
            return (Criteria) this;
        }

        public Criteria andFirstgettimeGreaterThan(Integer value) {
            addCriterion("firstGetTime >", value, "firstgettime");
            return (Criteria) this;
        }

        public Criteria andFirstgettimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("firstGetTime >=", value, "firstgettime");
            return (Criteria) this;
        }

        public Criteria andFirstgettimeLessThan(Integer value) {
            addCriterion("firstGetTime <", value, "firstgettime");
            return (Criteria) this;
        }

        public Criteria andFirstgettimeLessThanOrEqualTo(Integer value) {
            addCriterion("firstGetTime <=", value, "firstgettime");
            return (Criteria) this;
        }

        public Criteria andFirstgettimeIn(List<Integer> values) {
            addCriterion("firstGetTime in", values, "firstgettime");
            return (Criteria) this;
        }

        public Criteria andFirstgettimeNotIn(List<Integer> values) {
            addCriterion("firstGetTime not in", values, "firstgettime");
            return (Criteria) this;
        }

        public Criteria andFirstgettimeBetween(Integer value1, Integer value2) {
            addCriterion("firstGetTime between", value1, value2, "firstgettime");
            return (Criteria) this;
        }

        public Criteria andFirstgettimeNotBetween(Integer value1, Integer value2) {
            addCriterion("firstGetTime not between", value1, value2, "firstgettime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

}
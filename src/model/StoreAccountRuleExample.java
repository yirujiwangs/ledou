package model;

import model.divide.Criterion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StoreAccountRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StoreAccountRuleExample() {
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

        public Criteria andStore_typeIsNull() {
            addCriterion("store_type is null");
            return (Criteria) this;
        }

        public Criteria andStore_typeIsNotNull() {
            addCriterion("store_type is not null");
            return (Criteria) this;
        }

        public Criteria andStore_typeEqualTo(Integer value) {
            addCriterion("store_type =", value, "store_type");
            return (Criteria) this;
        }

        public Criteria andStore_typeNotEqualTo(Integer value) {
            addCriterion("store_type <>", value, "store_type");
            return (Criteria) this;
        }

        public Criteria andStore_typeGreaterThan(Integer value) {
            addCriterion("store_type >", value, "store_type");
            return (Criteria) this;
        }

        public Criteria andStore_typeGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_type >=", value, "store_type");
            return (Criteria) this;
        }

        public Criteria andStore_typeLessThan(Integer value) {
            addCriterion("store_type <", value, "store_type");
            return (Criteria) this;
        }

        public Criteria andStore_typeLessThanOrEqualTo(Integer value) {
            addCriterion("store_type <=", value, "store_type");
            return (Criteria) this;
        }

        public Criteria andStore_typeIn(List<Integer> values) {
            addCriterion("store_type in", values, "store_type");
            return (Criteria) this;
        }

        public Criteria andStore_typeNotIn(List<Integer> values) {
            addCriterion("store_type not in", values, "store_type");
            return (Criteria) this;
        }

        public Criteria andStore_typeBetween(Integer value1, Integer value2) {
            addCriterion("store_type between", value1, value2, "store_type");
            return (Criteria) this;
        }

        public Criteria andStore_typeNotBetween(Integer value1, Integer value2) {
            addCriterion("store_type not between", value1, value2, "store_type");
            return (Criteria) this;
        }

        public Criteria andAccount_feeIsNull() {
            addCriterion("account_fee is null");
            return (Criteria) this;
        }

        public Criteria andAccount_feeIsNotNull() {
            addCriterion("account_fee is not null");
            return (Criteria) this;
        }

        public Criteria andAccount_feeEqualTo(Double value) {
            addCriterion("account_fee =", value, "account_fee");
            return (Criteria) this;
        }

        public Criteria andAccount_feeNotEqualTo(Double value) {
            addCriterion("account_fee <>", value, "account_fee");
            return (Criteria) this;
        }

        public Criteria andAccount_feeGreaterThan(Double value) {
            addCriterion("account_fee >", value, "account_fee");
            return (Criteria) this;
        }

        public Criteria andAccount_feeGreaterThanOrEqualTo(Double value) {
            addCriterion("account_fee >=", value, "account_fee");
            return (Criteria) this;
        }

        public Criteria andAccount_feeLessThan(Double value) {
            addCriterion("account_fee <", value, "account_fee");
            return (Criteria) this;
        }

        public Criteria andAccount_feeLessThanOrEqualTo(Double value) {
            addCriterion("account_fee <=", value, "account_fee");
            return (Criteria) this;
        }

        public Criteria andAccount_feeIn(List<Double> values) {
            addCriterion("account_fee in", values, "account_fee");
            return (Criteria) this;
        }

        public Criteria andAccount_feeNotIn(List<Double> values) {
            addCriterion("account_fee not in", values, "account_fee");
            return (Criteria) this;
        }

        public Criteria andAccount_feeBetween(Double value1, Double value2) {
            addCriterion("account_fee between", value1, value2, "account_fee");
            return (Criteria) this;
        }

        public Criteria andAccount_feeNotBetween(Double value1, Double value2) {
            addCriterion("account_fee not between", value1, value2, "account_fee");
            return (Criteria) this;
        }

        public Criteria andMsgIsNull() {
            addCriterion("msg is null");
            return (Criteria) this;
        }

        public Criteria andMsgIsNotNull() {
            addCriterion("msg is not null");
            return (Criteria) this;
        }

        public Criteria andMsgEqualTo(String value) {
            addCriterion("msg =", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotEqualTo(String value) {
            addCriterion("msg <>", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThan(String value) {
            addCriterion("msg >", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgGreaterThanOrEqualTo(String value) {
            addCriterion("msg >=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThan(String value) {
            addCriterion("msg <", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLessThanOrEqualTo(String value) {
            addCriterion("msg <=", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgLike(String value) {
            addCriterion("msg like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotLike(String value) {
            addCriterion("msg not like", value, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgIn(List<String> values) {
            addCriterion("msg in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotIn(List<String> values) {
            addCriterion("msg not in", values, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgBetween(String value1, String value2) {
            addCriterion("msg between", value1, value2, "msg");
            return (Criteria) this;
        }

        public Criteria andMsgNotBetween(String value1, String value2) {
            addCriterion("msg not between", value1, value2, "msg");
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

}
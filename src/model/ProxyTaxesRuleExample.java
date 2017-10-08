package model;

import model.divide.Criterion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProxyTaxesRuleExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProxyTaxesRuleExample() {
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

        public Criteria andProxy_levelIsNull() {
            addCriterion("proxy_level is null");
            return (Criteria) this;
        }

        public Criteria andProxy_levelIsNotNull() {
            addCriterion("proxy_level is not null");
            return (Criteria) this;
        }

        public Criteria andProxy_levelEqualTo(Integer value) {
            addCriterion("proxy_level =", value, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelNotEqualTo(Integer value) {
            addCriterion("proxy_level <>", value, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelGreaterThan(Integer value) {
            addCriterion("proxy_level >", value, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelGreaterThanOrEqualTo(Integer value) {
            addCriterion("proxy_level >=", value, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelLessThan(Integer value) {
            addCriterion("proxy_level <", value, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelLessThanOrEqualTo(Integer value) {
            addCriterion("proxy_level <=", value, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelIn(List<Integer> values) {
            addCriterion("proxy_level in", values, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelNotIn(List<Integer> values) {
            addCriterion("proxy_level not in", values, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelBetween(Integer value1, Integer value2) {
            addCriterion("proxy_level between", value1, value2, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelNotBetween(Integer value1, Integer value2) {
            addCriterion("proxy_level not between", value1, value2, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andUserphoneIsNull() {
            addCriterion("userphone is null");
            return (Criteria) this;
        }

        public Criteria andUserphoneIsNotNull() {
            addCriterion("userphone is not null");
            return (Criteria) this;
        }

        public Criteria andUserphoneEqualTo(String value) {
            addCriterion("userphone =", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneNotEqualTo(String value) {
            addCriterion("userphone <>", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneGreaterThan(String value) {
            addCriterion("userphone >", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneGreaterThanOrEqualTo(String value) {
            addCriterion("userphone >=", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneLessThan(String value) {
            addCriterion("userphone <", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneLessThanOrEqualTo(String value) {
            addCriterion("userphone <=", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneLike(String value) {
            addCriterion("userphone like", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneNotLike(String value) {
            addCriterion("userphone not like", value, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneIn(List<String> values) {
            addCriterion("userphone in", values, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneNotIn(List<String> values) {
            addCriterion("userphone not in", values, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneBetween(String value1, String value2) {
            addCriterion("userphone between", value1, value2, "userphone");
            return (Criteria) this;
        }

        public Criteria andUserphoneNotBetween(String value1, String value2) {
            addCriterion("userphone not between", value1, value2, "userphone");
            return (Criteria) this;
        }

        public Criteria andTaxesidIsNull() {
            addCriterion("taxesid is null");
            return (Criteria) this;
        }

        public Criteria andTaxesidIsNotNull() {
            addCriterion("taxesid is not null");
            return (Criteria) this;
        }

        public Criteria andTaxesidEqualTo(Integer value) {
            addCriterion("taxesid =", value, "taxesid");
            return (Criteria) this;
        }

        public Criteria andTaxesidNotEqualTo(Integer value) {
            addCriterion("taxesid <>", value, "taxesid");
            return (Criteria) this;
        }

        public Criteria andTaxesidGreaterThan(Integer value) {
            addCriterion("taxesid >", value, "taxesid");
            return (Criteria) this;
        }

        public Criteria andTaxesidGreaterThanOrEqualTo(Integer value) {
            addCriterion("taxesid >=", value, "taxesid");
            return (Criteria) this;
        }

        public Criteria andTaxesidLessThan(Integer value) {
            addCriterion("taxesid <", value, "taxesid");
            return (Criteria) this;
        }

        public Criteria andTaxesidLessThanOrEqualTo(Integer value) {
            addCriterion("taxesid <=", value, "taxesid");
            return (Criteria) this;
        }

        public Criteria andTaxesidIn(List<Integer> values) {
            addCriterion("taxesid in", values, "taxesid");
            return (Criteria) this;
        }

        public Criteria andTaxesidNotIn(List<Integer> values) {
            addCriterion("taxesid not in", values, "taxesid");
            return (Criteria) this;
        }

        public Criteria andTaxesidBetween(Integer value1, Integer value2) {
            addCriterion("taxesid between", value1, value2, "taxesid");
            return (Criteria) this;
        }

        public Criteria andTaxesidNotBetween(Integer value1, Integer value2) {
            addCriterion("taxesid not between", value1, value2, "taxesid");
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
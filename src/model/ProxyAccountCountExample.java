package model;

import model.divide.Criterion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProxyAccountCountExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProxyAccountCountExample() {
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

    protected abstract static class GeneratedCriteria extends model.divide.GeneratedCriteria{

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

        public Criteria andProxyPhoneEqualTo(String value) {
            addCriterion("proxy_phone =", value, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneNotEqualTo(String value) {
            addCriterion("proxy_phone <>", value, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneIn(List<String> values) {
            addCriterion("proxy_phone in", values, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneNotIn(List<String> values) {
            addCriterion("proxy_phone not in", values, "proxy_phone");
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

        public Criteria andNormal_num_totalIsNull() {
            addCriterion("normal_num_total is null");
            return (Criteria) this;
        }

        public Criteria andNormal_num_totalIsNotNull() {
            addCriterion("normal_num_total is not null");
            return (Criteria) this;
        }

        public Criteria andNormal_num_totalEqualTo(Integer value) {
            addCriterion("normal_num_total =", value, "normal_num_total");
            return (Criteria) this;
        }

        public Criteria andNormal_num_totalNotEqualTo(Integer value) {
            addCriterion("normal_num_total <>", value, "normal_num_total");
            return (Criteria) this;
        }

        public Criteria andNormal_num_totalGreaterThan(Integer value) {
            addCriterion("normal_num_total >", value, "normal_num_total");
            return (Criteria) this;
        }

        public Criteria andNormal_num_totalGreaterThanOrEqualTo(Integer value) {
            addCriterion("normal_num_total >=", value, "normal_num_total");
            return (Criteria) this;
        }

        public Criteria andNormal_num_totalLessThan(Integer value) {
            addCriterion("normal_num_total <", value, "normal_num_total");
            return (Criteria) this;
        }

        public Criteria andNormal_num_totalLessThanOrEqualTo(Integer value) {
            addCriterion("normal_num_total <=", value, "normal_num_total");
            return (Criteria) this;
        }

        public Criteria andNormal_num_totalIn(List<Integer> values) {
            addCriterion("normal_num_total in", values, "normal_num_total");
            return (Criteria) this;
        }

        public Criteria andNormal_num_totalNotIn(List<Integer> values) {
            addCriterion("normal_num_total not in", values, "normal_num_total");
            return (Criteria) this;
        }

        public Criteria andNormal_num_totalBetween(Integer value1, Integer value2) {
            addCriterion("normal_num_total between", value1, value2, "normal_num_total");
            return (Criteria) this;
        }

        public Criteria andNormal_num_totalNotBetween(Integer value1, Integer value2) {
            addCriterion("normal_num_total not between", value1, value2, "normal_num_total");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_totalIsNull() {
            addCriterion("platform_num_total is null");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_totalIsNotNull() {
            addCriterion("platform_num_total is not null");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_totalEqualTo(Integer value) {
            addCriterion("platform_num_total =", value, "platform_num_total");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_totalNotEqualTo(Integer value) {
            addCriterion("platform_num_total <>", value, "platform_num_total");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_totalGreaterThan(Integer value) {
            addCriterion("platform_num_total >", value, "platform_num_total");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_totalGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform_num_total >=", value, "platform_num_total");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_totalLessThan(Integer value) {
            addCriterion("platform_num_total <", value, "platform_num_total");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_totalLessThanOrEqualTo(Integer value) {
            addCriterion("platform_num_total <=", value, "platform_num_total");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_totalIn(List<Integer> values) {
            addCriterion("platform_num_total in", values, "platform_num_total");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_totalNotIn(List<Integer> values) {
            addCriterion("platform_num_total not in", values, "platform_num_total");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_totalBetween(Integer value1, Integer value2) {
            addCriterion("platform_num_total between", value1, value2, "platform_num_total");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_totalNotBetween(Integer value1, Integer value2) {
            addCriterion("platform_num_total not between", value1, value2, "platform_num_total");
            return (Criteria) this;
        }

        public Criteria andNormal_num_usedIsNull() {
            addCriterion("normal_num_used is null");
            return (Criteria) this;
        }

        public Criteria andNormal_num_usedIsNotNull() {
            addCriterion("normal_num_used is not null");
            return (Criteria) this;
        }

        public Criteria andNormal_num_usedEqualTo(Integer value) {
            addCriterion("normal_num_used =", value, "normal_num_used");
            return (Criteria) this;
        }

        public Criteria andNormal_num_usedNotEqualTo(Integer value) {
            addCriterion("normal_num_used <>", value, "normal_num_used");
            return (Criteria) this;
        }

        public Criteria andNormal_num_usedGreaterThan(Integer value) {
            addCriterion("normal_num_used >", value, "normal_num_used");
            return (Criteria) this;
        }

        public Criteria andNormal_num_usedGreaterThanOrEqualTo(Integer value) {
            addCriterion("normal_num_used >=", value, "normal_num_used");
            return (Criteria) this;
        }

        public Criteria andNormal_num_usedLessThan(Integer value) {
            addCriterion("normal_num_used <", value, "normal_num_used");
            return (Criteria) this;
        }

        public Criteria andNormal_num_usedLessThanOrEqualTo(Integer value) {
            addCriterion("normal_num_used <=", value, "normal_num_used");
            return (Criteria) this;
        }

        public Criteria andNormal_num_usedIn(List<Integer> values) {
            addCriterion("normal_num_used in", values, "normal_num_used");
            return (Criteria) this;
        }

        public Criteria andNormal_num_usedNotIn(List<Integer> values) {
            addCriterion("normal_num_used not in", values, "normal_num_used");
            return (Criteria) this;
        }

        public Criteria andNormal_num_usedBetween(Integer value1, Integer value2) {
            addCriterion("normal_num_used between", value1, value2, "normal_num_used");
            return (Criteria) this;
        }

        public Criteria andNormal_num_usedNotBetween(Integer value1, Integer value2) {
            addCriterion("normal_num_used not between", value1, value2, "normal_num_used");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_usedIsNull() {
            addCriterion("platform_num_used is null");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_usedIsNotNull() {
            addCriterion("platform_num_used is not null");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_usedEqualTo(Integer value) {
            addCriterion("platform_num_used =", value, "platform_num_used");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_usedNotEqualTo(Integer value) {
            addCriterion("platform_num_used <>", value, "platform_num_used");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_usedGreaterThan(Integer value) {
            addCriterion("platform_num_used >", value, "platform_num_used");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_usedGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform_num_used >=", value, "platform_num_used");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_usedLessThan(Integer value) {
            addCriterion("platform_num_used <", value, "platform_num_used");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_usedLessThanOrEqualTo(Integer value) {
            addCriterion("platform_num_used <=", value, "platform_num_used");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_usedIn(List<Integer> values) {
            addCriterion("platform_num_used in", values, "platform_num_used");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_usedNotIn(List<Integer> values) {
            addCriterion("platform_num_used not in", values, "platform_num_used");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_usedBetween(Integer value1, Integer value2) {
            addCriterion("platform_num_used between", value1, value2, "platform_num_used");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_usedNotBetween(Integer value1, Integer value2) {
            addCriterion("platform_num_used not between", value1, value2, "platform_num_used");
            return (Criteria) this;
        }

        public Criteria andNormal_num_activeIsNull() {
            addCriterion("normal_num_active is null");
            return (Criteria) this;
        }

        public Criteria andNormal_num_activeIsNotNull() {
            addCriterion("normal_num_active is not null");
            return (Criteria) this;
        }

        public Criteria andNormal_num_activeEqualTo(Integer value) {
            addCriterion("normal_num_active =", value, "normal_num_active");
            return (Criteria) this;
        }

        public Criteria andNormal_num_activeNotEqualTo(Integer value) {
            addCriterion("normal_num_active <>", value, "normal_num_active");
            return (Criteria) this;
        }

        public Criteria andNormal_num_activeGreaterThan(Integer value) {
            addCriterion("normal_num_active >", value, "normal_num_active");
            return (Criteria) this;
        }

        public Criteria andNormal_num_activeGreaterThanOrEqualTo(Integer value) {
            addCriterion("normal_num_active >=", value, "normal_num_active");
            return (Criteria) this;
        }

        public Criteria andNormal_num_activeLessThan(Integer value) {
            addCriterion("normal_num_active <", value, "normal_num_active");
            return (Criteria) this;
        }

        public Criteria andNormal_num_activeLessThanOrEqualTo(Integer value) {
            addCriterion("normal_num_active <=", value, "normal_num_active");
            return (Criteria) this;
        }

        public Criteria andNormal_num_activeIn(List<Integer> values) {
            addCriterion("normal_num_active in", values, "normal_num_active");
            return (Criteria) this;
        }

        public Criteria andNormal_num_activeNotIn(List<Integer> values) {
            addCriterion("normal_num_active not in", values, "normal_num_active");
            return (Criteria) this;
        }

        public Criteria andNormal_num_activeBetween(Integer value1, Integer value2) {
            addCriterion("normal_num_active between", value1, value2, "normal_num_active");
            return (Criteria) this;
        }

        public Criteria andNormal_num_activeNotBetween(Integer value1, Integer value2) {
            addCriterion("normal_num_active not between", value1, value2, "normal_num_active");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_activeIsNull() {
            addCriterion("platform_num_active is null");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_activeIsNotNull() {
            addCriterion("platform_num_active is not null");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_activeEqualTo(Integer value) {
            addCriterion("platform_num_active =", value, "platform_num_active");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_activeNotEqualTo(Integer value) {
            addCriterion("platform_num_active <>", value, "platform_num_active");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_activeGreaterThan(Integer value) {
            addCriterion("platform_num_active >", value, "platform_num_active");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_activeGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform_num_active >=", value, "platform_num_active");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_activeLessThan(Integer value) {
            addCriterion("platform_num_active <", value, "platform_num_active");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_activeLessThanOrEqualTo(Integer value) {
            addCriterion("platform_num_active <=", value, "platform_num_active");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_activeIn(List<Integer> values) {
            addCriterion("platform_num_active in", values, "platform_num_active");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_activeNotIn(List<Integer> values) {
            addCriterion("platform_num_active not in", values, "platform_num_active");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_activeBetween(Integer value1, Integer value2) {
            addCriterion("platform_num_active between", value1, value2, "platform_num_active");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_activeNotBetween(Integer value1, Integer value2) {
            addCriterion("platform_num_active not between", value1, value2, "platform_num_active");
            return (Criteria) this;
        }

        public Criteria andNormal_num_freezeingIsNull() {
            addCriterion("normal_num_freezeing is null");
            return (Criteria) this;
        }

        public Criteria andNormal_num_freezeingIsNotNull() {
            addCriterion("normal_num_freezeing is not null");
            return (Criteria) this;
        }

        public Criteria andNormal_num_freezeingEqualTo(Integer value) {
            addCriterion("normal_num_freezeing =", value, "normal_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andNormal_num_freezeingNotEqualTo(Integer value) {
            addCriterion("normal_num_freezeing <>", value, "normal_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andNormal_num_freezeingGreaterThan(Integer value) {
            addCriterion("normal_num_freezeing >", value, "normal_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andNormal_num_freezeingGreaterThanOrEqualTo(Integer value) {
            addCriterion("normal_num_freezeing >=", value, "normal_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andNormal_num_freezeingLessThan(Integer value) {
            addCriterion("normal_num_freezeing <", value, "normal_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andNormal_num_freezeingLessThanOrEqualTo(Integer value) {
            addCriterion("normal_num_freezeing <=", value, "normal_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andNormal_num_freezeingIn(List<Integer> values) {
            addCriterion("normal_num_freezeing in", values, "normal_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andNormal_num_freezeingNotIn(List<Integer> values) {
            addCriterion("normal_num_freezeing not in", values, "normal_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andNormal_num_freezeingBetween(Integer value1, Integer value2) {
            addCriterion("normal_num_freezeing between", value1, value2, "normal_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andNormal_num_freezeingNotBetween(Integer value1, Integer value2) {
            addCriterion("normal_num_freezeing not between", value1, value2, "normal_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_freezeingIsNull() {
            addCriterion("platform_num_freezeing is null");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_freezeingIsNotNull() {
            addCriterion("platform_num_freezeing is not null");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_freezeingEqualTo(Integer value) {
            addCriterion("platform_num_freezeing =", value, "platform_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_freezeingNotEqualTo(Integer value) {
            addCriterion("platform_num_freezeing <>", value, "platform_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_freezeingGreaterThan(Integer value) {
            addCriterion("platform_num_freezeing >", value, "platform_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_freezeingGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform_num_freezeing >=", value, "platform_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_freezeingLessThan(Integer value) {
            addCriterion("platform_num_freezeing <", value, "platform_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_freezeingLessThanOrEqualTo(Integer value) {
            addCriterion("platform_num_freezeing <=", value, "platform_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_freezeingIn(List<Integer> values) {
            addCriterion("platform_num_freezeing in", values, "platform_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_freezeingNotIn(List<Integer> values) {
            addCriterion("platform_num_freezeing not in", values, "platform_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_freezeingBetween(Integer value1, Integer value2) {
            addCriterion("platform_num_freezeing between", value1, value2, "platform_num_freezeing");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_freezeingNotBetween(Integer value1, Integer value2) {
            addCriterion("platform_num_freezeing not between", value1, value2, "platform_num_freezeing");
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
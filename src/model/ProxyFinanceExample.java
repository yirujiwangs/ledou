package model;

import model.divide.BaseDivideModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProxyFinanceExample extends BaseDivideModel {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProxyFinanceExample() {
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

        public Criteria andPhoneNumIsNull() {
            addCriterion("phoneNum is null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIsNotNull() {
            addCriterion("phoneNum is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneNumEqualTo(String value) {
            addCriterion("phoneNum =", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotEqualTo(String value) {
            addCriterion("phoneNum <>", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThan(String value) {
            addCriterion("phoneNum >", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumGreaterThanOrEqualTo(String value) {
            addCriterion("phoneNum >=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThan(String value) {
            addCriterion("phoneNum <", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLessThanOrEqualTo(String value) {
            addCriterion("phoneNum <=", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumLike(String value) {
            addCriterion("phoneNum like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotLike(String value) {
            addCriterion("phoneNum not like", value, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumIn(List<String> values) {
            addCriterion("phoneNum in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotIn(List<String> values) {
            addCriterion("phoneNum not in", values, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumBetween(String value1, String value2) {
            addCriterion("phoneNum between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andPhoneNumNotBetween(String value1, String value2) {
            addCriterion("phoneNum not between", value1, value2, "phoneNum");
            return (Criteria) this;
        }

        public Criteria andSum_incomeIsNull() {
            addCriterion("sum_income is null");
            return (Criteria) this;
        }

        public Criteria andSum_incomeIsNotNull() {
            addCriterion("sum_income is not null");
            return (Criteria) this;
        }

        public Criteria andSum_incomeEqualTo(Double value) {
            addCriterion("sum_income =", value, "sum_income");
            return (Criteria) this;
        }

        public Criteria andSum_incomeNotEqualTo(Double value) {
            addCriterion("sum_income <>", value, "sum_income");
            return (Criteria) this;
        }

        public Criteria andSum_incomeGreaterThan(Double value) {
            addCriterion("sum_income >", value, "sum_income");
            return (Criteria) this;
        }

        public Criteria andSum_incomeGreaterThanOrEqualTo(Double value) {
            addCriterion("sum_income >=", value, "sum_income");
            return (Criteria) this;
        }

        public Criteria andSum_incomeLessThan(Double value) {
            addCriterion("sum_income <", value, "sum_income");
            return (Criteria) this;
        }

        public Criteria andSum_incomeLessThanOrEqualTo(Double value) {
            addCriterion("sum_income <=", value, "sum_income");
            return (Criteria) this;
        }

        public Criteria andSum_incomeIn(List<Double> values) {
            addCriterion("sum_income in", values, "sum_income");
            return (Criteria) this;
        }

        public Criteria andSum_incomeNotIn(List<Double> values) {
            addCriterion("sum_income not in", values, "sum_income");
            return (Criteria) this;
        }

        public Criteria andSum_incomeBetween(Double value1, Double value2) {
            addCriterion("sum_income between", value1, value2, "sum_income");
            return (Criteria) this;
        }

        public Criteria andSum_incomeNotBetween(Double value1, Double value2) {
            addCriterion("sum_income not between", value1, value2, "sum_income");
            return (Criteria) this;
        }

        public Criteria andAvaiableIsNull() {
            addCriterion("avaiable is null");
            return (Criteria) this;
        }

        public Criteria andAvaiableIsNotNull() {
            addCriterion("avaiable is not null");
            return (Criteria) this;
        }

        public Criteria andAvaiableEqualTo(Double value) {
            addCriterion("avaiable =", value, "avaiable");
            return (Criteria) this;
        }

        public Criteria andAvaiableNotEqualTo(Double value) {
            addCriterion("avaiable <>", value, "avaiable");
            return (Criteria) this;
        }

        public Criteria andAvaiableGreaterThan(Double value) {
            addCriterion("avaiable >", value, "avaiable");
            return (Criteria) this;
        }

        public Criteria andAvaiableGreaterThanOrEqualTo(Double value) {
            addCriterion("avaiable >=", value, "avaiable");
            return (Criteria) this;
        }

        public Criteria andAvaiableLessThan(Double value) {
            addCriterion("avaiable <", value, "avaiable");
            return (Criteria) this;
        }

        public Criteria andAvaiableLessThanOrEqualTo(Double value) {
            addCriterion("avaiable <=", value, "avaiable");
            return (Criteria) this;
        }

        public Criteria andAvaiableIn(List<Double> values) {
            addCriterion("avaiable in", values, "avaiable");
            return (Criteria) this;
        }

        public Criteria andAvaiableNotIn(List<Double> values) {
            addCriterion("avaiable not in", values, "avaiable");
            return (Criteria) this;
        }

        public Criteria andAvaiableBetween(Double value1, Double value2) {
            addCriterion("avaiable between", value1, value2, "avaiable");
            return (Criteria) this;
        }

        public Criteria andAvaiableNotBetween(Double value1, Double value2) {
            addCriterion("avaiable not between", value1, value2, "avaiable");
            return (Criteria) this;
        }

        public Criteria andBalancedIsNull() {
            addCriterion("balanced is null");
            return (Criteria) this;
        }

        public Criteria andBalancedIsNotNull() {
            addCriterion("balanced is not null");
            return (Criteria) this;
        }

        public Criteria andBalancedEqualTo(Double value) {
            addCriterion("balanced =", value, "balanced");
            return (Criteria) this;
        }

        public Criteria andBalancedNotEqualTo(Double value) {
            addCriterion("balanced <>", value, "balanced");
            return (Criteria) this;
        }

        public Criteria andBalancedGreaterThan(Double value) {
            addCriterion("balanced >", value, "balanced");
            return (Criteria) this;
        }

        public Criteria andBalancedGreaterThanOrEqualTo(Double value) {
            addCriterion("balanced >=", value, "balanced");
            return (Criteria) this;
        }

        public Criteria andBalancedLessThan(Double value) {
            addCriterion("balanced <", value, "balanced");
            return (Criteria) this;
        }

        public Criteria andBalancedLessThanOrEqualTo(Double value) {
            addCriterion("balanced <=", value, "balanced");
            return (Criteria) this;
        }

        public Criteria andBalancedIn(List<Double> values) {
            addCriterion("balanced in", values, "balanced");
            return (Criteria) this;
        }

        public Criteria andBalancedNotIn(List<Double> values) {
            addCriterion("balanced not in", values, "balanced");
            return (Criteria) this;
        }

        public Criteria andBalancedBetween(Double value1, Double value2) {
            addCriterion("balanced between", value1, value2, "balanced");
            return (Criteria) this;
        }

        public Criteria andBalancedNotBetween(Double value1, Double value2) {
            addCriterion("balanced not between", value1, value2, "balanced");
            return (Criteria) this;
        }

        public Criteria andBalancingIsNull() {
            addCriterion("balancing is null");
            return (Criteria) this;
        }

        public Criteria andBalancingIsNotNull() {
            addCriterion("balancing is not null");
            return (Criteria) this;
        }

        public Criteria andBalancingEqualTo(Double value) {
            addCriterion("balancing =", value, "balancing");
            return (Criteria) this;
        }

        public Criteria andBalancingNotEqualTo(Double value) {
            addCriterion("balancing <>", value, "balancing");
            return (Criteria) this;
        }

        public Criteria andBalancingGreaterThan(Double value) {
            addCriterion("balancing >", value, "balancing");
            return (Criteria) this;
        }

        public Criteria andBalancingGreaterThanOrEqualTo(Double value) {
            addCriterion("balancing >=", value, "balancing");
            return (Criteria) this;
        }

        public Criteria andBalancingLessThan(Double value) {
            addCriterion("balancing <", value, "balancing");
            return (Criteria) this;
        }

        public Criteria andBalancingLessThanOrEqualTo(Double value) {
            addCriterion("balancing <=", value, "balancing");
            return (Criteria) this;
        }

        public Criteria andBalancingIn(List<Double> values) {
            addCriterion("balancing in", values, "balancing");
            return (Criteria) this;
        }

        public Criteria andBalancingNotIn(List<Double> values) {
            addCriterion("balancing not in", values, "balancing");
            return (Criteria) this;
        }

        public Criteria andBalancingBetween(Double value1, Double value2) {
            addCriterion("balancing between", value1, value2, "balancing");
            return (Criteria) this;
        }

        public Criteria andBalancingNotBetween(Double value1, Double value2) {
            addCriterion("balancing not between", value1, value2, "balancing");
            return (Criteria) this;
        }

        public Criteria andModifytimeIsNull() {
            addCriterion("modifytime is null");
            return (Criteria) this;
        }

        public Criteria andModifytimeIsNotNull() {
            addCriterion("modifytime is not null");
            return (Criteria) this;
        }

        public Criteria andModifytimeEqualTo(Date value) {
            addCriterion("modifytime =", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotEqualTo(Date value) {
            addCriterion("modifytime <>", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeGreaterThan(Date value) {
            addCriterion("modifytime >", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modifytime >=", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeLessThan(Date value) {
            addCriterion("modifytime <", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeLessThanOrEqualTo(Date value) {
            addCriterion("modifytime <=", value, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeIn(List<Date> values) {
            addCriterion("modifytime in", values, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotIn(List<Date> values) {
            addCriterion("modifytime not in", values, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeBetween(Date value1, Date value2) {
            addCriterion("modifytime between", value1, value2, "modifytime");
            return (Criteria) this;
        }

        public Criteria andModifytimeNotBetween(Date value1, Date value2) {
            addCriterion("modifytime not between", value1, value2, "modifytime");
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
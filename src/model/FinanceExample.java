package model;

import java.util.ArrayList;
import java.util.List;

public class FinanceExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FinanceExample() {
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

        public Criteria andPhonenumIsNull() {
            addCriterion("phoneNum is null");
            return (Criteria) this;
        }

        public Criteria andPhonenumIsNotNull() {
            addCriterion("phoneNum is not null");
            return (Criteria) this;
        }

        public Criteria andPhonenumEqualTo(String value) {
            addCriterion("phoneNum =", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotEqualTo(String value) {
            addCriterion("phoneNum <>", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumGreaterThan(String value) {
            addCriterion("phoneNum >", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumGreaterThanOrEqualTo(String value) {
            addCriterion("phoneNum >=", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumLessThan(String value) {
            addCriterion("phoneNum <", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumLessThanOrEqualTo(String value) {
            addCriterion("phoneNum <=", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumLike(String value) {
            addCriterion("phoneNum like", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotLike(String value) {
            addCriterion("phoneNum not like", value, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumIn(List<String> values) {
            addCriterion("phoneNum in", values, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotIn(List<String> values) {
            addCriterion("phoneNum not in", values, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumBetween(String value1, String value2) {
            addCriterion("phoneNum between", value1, value2, "phonenum");
            return (Criteria) this;
        }

        public Criteria andPhonenumNotBetween(String value1, String value2) {
            addCriterion("phoneNum not between", value1, value2, "phonenum");
            return (Criteria) this;
        }

        public Criteria andSumIsNull() {
            addCriterion("sum is null");
            return (Criteria) this;
        }

        public Criteria andSumIsNotNull() {
            addCriterion("sum is not null");
            return (Criteria) this;
        }

        public Criteria andSumEqualTo(Double value) {
            addCriterion("sum =", value, "sum");
            return (Criteria) this;
        }

        public Criteria andSumNotEqualTo(Double value) {
            addCriterion("sum <>", value, "sum");
            return (Criteria) this;
        }

        public Criteria andSumGreaterThan(Double value) {
            addCriterion("sum >", value, "sum");
            return (Criteria) this;
        }

        public Criteria andSumGreaterThanOrEqualTo(Double value) {
            addCriterion("sum >=", value, "sum");
            return (Criteria) this;
        }

        public Criteria andSumLessThan(Double value) {
            addCriterion("sum <", value, "sum");
            return (Criteria) this;
        }

        public Criteria andSumLessThanOrEqualTo(Double value) {
            addCriterion("sum <=", value, "sum");
            return (Criteria) this;
        }

        public Criteria andSumIn(List<Double> values) {
            addCriterion("sum in", values, "sum");
            return (Criteria) this;
        }

        public Criteria andSumNotIn(List<Double> values) {
            addCriterion("sum not in", values, "sum");
            return (Criteria) this;
        }

        public Criteria andSumBetween(Double value1, Double value2) {
            addCriterion("sum between", value1, value2, "sum");
            return (Criteria) this;
        }

        public Criteria andSumNotBetween(Double value1, Double value2) {
            addCriterion("sum not between", value1, value2, "sum");
            return (Criteria) this;
        }

        public Criteria andAvailableIsNull() {
            addCriterion("available is null");
            return (Criteria) this;
        }

        public Criteria andAvailableIsNotNull() {
            addCriterion("available is not null");
            return (Criteria) this;
        }

        public Criteria andAvailableEqualTo(Double value) {
            addCriterion("available =", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotEqualTo(Double value) {
            addCriterion("available <>", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableGreaterThan(Double value) {
            addCriterion("available >", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableGreaterThanOrEqualTo(Double value) {
            addCriterion("available >=", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableLessThan(Double value) {
            addCriterion("available <", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableLessThanOrEqualTo(Double value) {
            addCriterion("available <=", value, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableIn(List<Double> values) {
            addCriterion("available in", values, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotIn(List<Double> values) {
            addCriterion("available not in", values, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableBetween(Double value1, Double value2) {
            addCriterion("available between", value1, value2, "available");
            return (Criteria) this;
        }

        public Criteria andAvailableNotBetween(Double value1, Double value2) {
            addCriterion("available not between", value1, value2, "available");
            return (Criteria) this;
        }

        public Criteria andFreezeIsNull() {
            addCriterion("freeze is null");
            return (Criteria) this;
        }

        public Criteria andFreezeIsNotNull() {
            addCriterion("freeze is not null");
            return (Criteria) this;
        }

        public Criteria andFreezeEqualTo(Double value) {
            addCriterion("freeze =", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeNotEqualTo(Double value) {
            addCriterion("freeze <>", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeGreaterThan(Double value) {
            addCriterion("freeze >", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeGreaterThanOrEqualTo(Double value) {
            addCriterion("freeze >=", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeLessThan(Double value) {
            addCriterion("freeze <", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeLessThanOrEqualTo(Double value) {
            addCriterion("freeze <=", value, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeIn(List<Double> values) {
            addCriterion("freeze in", values, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeNotIn(List<Double> values) {
            addCriterion("freeze not in", values, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeBetween(Double value1, Double value2) {
            addCriterion("freeze between", value1, value2, "freeze");
            return (Criteria) this;
        }

        public Criteria andFreezeNotBetween(Double value1, Double value2) {
            addCriterion("freeze not between", value1, value2, "freeze");
            return (Criteria) this;
        }

        public Criteria andSumexpenseIsNull() {
            addCriterion("sumExpense is null");
            return (Criteria) this;
        }

        public Criteria andSumexpenseIsNotNull() {
            addCriterion("sumExpense is not null");
            return (Criteria) this;
        }

        public Criteria andSumexpenseEqualTo(Double value) {
            addCriterion("sumExpense =", value, "sumexpense");
            return (Criteria) this;
        }

        public Criteria andSumexpenseNotEqualTo(Double value) {
            addCriterion("sumExpense <>", value, "sumexpense");
            return (Criteria) this;
        }

        public Criteria andSumexpenseGreaterThan(Double value) {
            addCriterion("sumExpense >", value, "sumexpense");
            return (Criteria) this;
        }

        public Criteria andSumexpenseGreaterThanOrEqualTo(Double value) {
            addCriterion("sumExpense >=", value, "sumexpense");
            return (Criteria) this;
        }

        public Criteria andSumexpenseLessThan(Double value) {
            addCriterion("sumExpense <", value, "sumexpense");
            return (Criteria) this;
        }

        public Criteria andSumexpenseLessThanOrEqualTo(Double value) {
            addCriterion("sumExpense <=", value, "sumexpense");
            return (Criteria) this;
        }

        public Criteria andSumexpenseIn(List<Double> values) {
            addCriterion("sumExpense in", values, "sumexpense");
            return (Criteria) this;
        }

        public Criteria andSumexpenseNotIn(List<Double> values) {
            addCriterion("sumExpense not in", values, "sumexpense");
            return (Criteria) this;
        }

        public Criteria andSumexpenseBetween(Double value1, Double value2) {
            addCriterion("sumExpense between", value1, value2, "sumexpense");
            return (Criteria) this;
        }

        public Criteria andSumexpenseNotBetween(Double value1, Double value2) {
            addCriterion("sumExpense not between", value1, value2, "sumexpense");
            return (Criteria) this;
        }

        public Criteria andSumdepositIsNull() {
            addCriterion("sumDeposit is null");
            return (Criteria) this;
        }

        public Criteria andSumdepositIsNotNull() {
            addCriterion("sumDeposit is not null");
            return (Criteria) this;
        }

        public Criteria andSumdepositEqualTo(Double value) {
            addCriterion("sumDeposit =", value, "sumdeposit");
            return (Criteria) this;
        }

        public Criteria andSumdepositNotEqualTo(Double value) {
            addCriterion("sumDeposit <>", value, "sumdeposit");
            return (Criteria) this;
        }

        public Criteria andSumdepositGreaterThan(Double value) {
            addCriterion("sumDeposit >", value, "sumdeposit");
            return (Criteria) this;
        }

        public Criteria andSumdepositGreaterThanOrEqualTo(Double value) {
            addCriterion("sumDeposit >=", value, "sumdeposit");
            return (Criteria) this;
        }

        public Criteria andSumdepositLessThan(Double value) {
            addCriterion("sumDeposit <", value, "sumdeposit");
            return (Criteria) this;
        }

        public Criteria andSumdepositLessThanOrEqualTo(Double value) {
            addCriterion("sumDeposit <=", value, "sumdeposit");
            return (Criteria) this;
        }

        public Criteria andSumdepositIn(List<Double> values) {
            addCriterion("sumDeposit in", values, "sumdeposit");
            return (Criteria) this;
        }

        public Criteria andSumdepositNotIn(List<Double> values) {
            addCriterion("sumDeposit not in", values, "sumdeposit");
            return (Criteria) this;
        }

        public Criteria andSumdepositBetween(Double value1, Double value2) {
            addCriterion("sumDeposit between", value1, value2, "sumdeposit");
            return (Criteria) this;
        }

        public Criteria andSumdepositNotBetween(Double value1, Double value2) {
            addCriterion("sumDeposit not between", value1, value2, "sumdeposit");
            return (Criteria) this;
        }

        public Criteria andSumrefundIsNull() {
            addCriterion("sumRefund is null");
            return (Criteria) this;
        }

        public Criteria andSumrefundIsNotNull() {
            addCriterion("sumRefund is not null");
            return (Criteria) this;
        }

        public Criteria andSumrefundEqualTo(Double value) {
            addCriterion("sumRefund =", value, "sumrefund");
            return (Criteria) this;
        }

        public Criteria andSumrefundNotEqualTo(Double value) {
            addCriterion("sumRefund <>", value, "sumrefund");
            return (Criteria) this;
        }

        public Criteria andSumrefundGreaterThan(Double value) {
            addCriterion("sumRefund >", value, "sumrefund");
            return (Criteria) this;
        }

        public Criteria andSumrefundGreaterThanOrEqualTo(Double value) {
            addCriterion("sumRefund >=", value, "sumrefund");
            return (Criteria) this;
        }

        public Criteria andSumrefundLessThan(Double value) {
            addCriterion("sumRefund <", value, "sumrefund");
            return (Criteria) this;
        }

        public Criteria andSumrefundLessThanOrEqualTo(Double value) {
            addCriterion("sumRefund <=", value, "sumrefund");
            return (Criteria) this;
        }

        public Criteria andSumrefundIn(List<Double> values) {
            addCriterion("sumRefund in", values, "sumrefund");
            return (Criteria) this;
        }

        public Criteria andSumrefundNotIn(List<Double> values) {
            addCriterion("sumRefund not in", values, "sumrefund");
            return (Criteria) this;
        }

        public Criteria andSumrefundBetween(Double value1, Double value2) {
            addCriterion("sumRefund between", value1, value2, "sumrefund");
            return (Criteria) this;
        }

        public Criteria andSumrefundNotBetween(Double value1, Double value2) {
            addCriterion("sumRefund not between", value1, value2, "sumrefund");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

}
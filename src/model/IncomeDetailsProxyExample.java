package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class IncomeDetailsProxyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public IncomeDetailsProxyExample() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andDevice_buy_amount_selfIsNull() {
            addCriterion("device_buy_amount_self is null");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_selfIsNotNull() {
            addCriterion("device_buy_amount_self is not null");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_selfEqualTo(Integer value) {
            addCriterion("device_buy_amount_self =", value, "device_buy_amount_self");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_selfNotEqualTo(Integer value) {
            addCriterion("device_buy_amount_self <>", value, "device_buy_amount_self");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_selfGreaterThan(Integer value) {
            addCriterion("device_buy_amount_self >", value, "device_buy_amount_self");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_selfGreaterThanOrEqualTo(Integer value) {
            addCriterion("device_buy_amount_self >=", value, "device_buy_amount_self");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_selfLessThan(Integer value) {
            addCriterion("device_buy_amount_self <", value, "device_buy_amount_self");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_selfLessThanOrEqualTo(Integer value) {
            addCriterion("device_buy_amount_self <=", value, "device_buy_amount_self");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_selfIn(List<Integer> values) {
            addCriterion("device_buy_amount_self in", values, "device_buy_amount_self");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_selfNotIn(List<Integer> values) {
            addCriterion("device_buy_amount_self not in", values, "device_buy_amount_self");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_selfBetween(Integer value1, Integer value2) {
            addCriterion("device_buy_amount_self between", value1, value2, "device_buy_amount_self");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_selfNotBetween(Integer value1, Integer value2) {
            addCriterion("device_buy_amount_self not between", value1, value2, "device_buy_amount_self");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_directIsNull() {
            addCriterion("device_buy_amount_dealer_direct is null");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_directIsNotNull() {
            addCriterion("device_buy_amount_dealer_direct is not null");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_directEqualTo(Integer value) {
            addCriterion("device_buy_amount_dealer_direct =", value, "device_buy_amount_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_directNotEqualTo(Integer value) {
            addCriterion("device_buy_amount_dealer_direct <>", value, "device_buy_amount_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_directGreaterThan(Integer value) {
            addCriterion("device_buy_amount_dealer_direct >", value, "device_buy_amount_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_directGreaterThanOrEqualTo(Integer value) {
            addCriterion("device_buy_amount_dealer_direct >=", value, "device_buy_amount_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_directLessThan(Integer value) {
            addCriterion("device_buy_amount_dealer_direct <", value, "device_buy_amount_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_directLessThanOrEqualTo(Integer value) {
            addCriterion("device_buy_amount_dealer_direct <=", value, "device_buy_amount_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_directIn(List<Integer> values) {
            addCriterion("device_buy_amount_dealer_direct in", values, "device_buy_amount_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_directNotIn(List<Integer> values) {
            addCriterion("device_buy_amount_dealer_direct not in", values, "device_buy_amount_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_directBetween(Integer value1, Integer value2) {
            addCriterion("device_buy_amount_dealer_direct between", value1, value2, "device_buy_amount_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_directNotBetween(Integer value1, Integer value2) {
            addCriterion("device_buy_amount_dealer_direct not between", value1, value2, "device_buy_amount_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_indirectIsNull() {
            addCriterion("device_buy_amount_dealer_indirect is null");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_indirectIsNotNull() {
            addCriterion("device_buy_amount_dealer_indirect is not null");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_indirectEqualTo(Integer value) {
            addCriterion("device_buy_amount_dealer_indirect =", value, "device_buy_amount_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_indirectNotEqualTo(Integer value) {
            addCriterion("device_buy_amount_dealer_indirect <>", value, "device_buy_amount_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_indirectGreaterThan(Integer value) {
            addCriterion("device_buy_amount_dealer_indirect >", value, "device_buy_amount_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_indirectGreaterThanOrEqualTo(Integer value) {
            addCriterion("device_buy_amount_dealer_indirect >=", value, "device_buy_amount_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_indirectLessThan(Integer value) {
            addCriterion("device_buy_amount_dealer_indirect <", value, "device_buy_amount_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_indirectLessThanOrEqualTo(Integer value) {
            addCriterion("device_buy_amount_dealer_indirect <=", value, "device_buy_amount_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_indirectIn(List<Integer> values) {
            addCriterion("device_buy_amount_dealer_indirect in", values, "device_buy_amount_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_indirectNotIn(List<Integer> values) {
            addCriterion("device_buy_amount_dealer_indirect not in", values, "device_buy_amount_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_indirectBetween(Integer value1, Integer value2) {
            addCriterion("device_buy_amount_dealer_indirect between", value1, value2, "device_buy_amount_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andDevice_buy_amount_dealer_indirectNotBetween(Integer value1, Integer value2) {
            addCriterion("device_buy_amount_dealer_indirect not between", value1, value2, "device_buy_amount_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_selfIsNull() {
            addCriterion("money_ad_normal_self is null");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_selfIsNotNull() {
            addCriterion("money_ad_normal_self is not null");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_selfEqualTo(Integer value) {
            addCriterion("money_ad_normal_self =", value, "money_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_selfNotEqualTo(Integer value) {
            addCriterion("money_ad_normal_self <>", value, "money_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_selfGreaterThan(Integer value) {
            addCriterion("money_ad_normal_self >", value, "money_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_selfGreaterThanOrEqualTo(Integer value) {
            addCriterion("money_ad_normal_self >=", value, "money_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_selfLessThan(Integer value) {
            addCriterion("money_ad_normal_self <", value, "money_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_selfLessThanOrEqualTo(Integer value) {
            addCriterion("money_ad_normal_self <=", value, "money_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_selfIn(List<Integer> values) {
            addCriterion("money_ad_normal_self in", values, "money_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_selfNotIn(List<Integer> values) {
            addCriterion("money_ad_normal_self not in", values, "money_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_selfBetween(Integer value1, Integer value2) {
            addCriterion("money_ad_normal_self between", value1, value2, "money_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_selfNotBetween(Integer value1, Integer value2) {
            addCriterion("money_ad_normal_self not between", value1, value2, "money_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_directIsNull() {
            addCriterion("money_ad_normal_dealer_direct is null");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_directIsNotNull() {
            addCriterion("money_ad_normal_dealer_direct is not null");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_directEqualTo(Integer value) {
            addCriterion("money_ad_normal_dealer_direct =", value, "money_ad_normal_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_directNotEqualTo(Integer value) {
            addCriterion("money_ad_normal_dealer_direct <>", value, "money_ad_normal_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_directGreaterThan(Integer value) {
            addCriterion("money_ad_normal_dealer_direct >", value, "money_ad_normal_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_directGreaterThanOrEqualTo(Integer value) {
            addCriterion("money_ad_normal_dealer_direct >=", value, "money_ad_normal_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_directLessThan(Integer value) {
            addCriterion("money_ad_normal_dealer_direct <", value, "money_ad_normal_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_directLessThanOrEqualTo(Integer value) {
            addCriterion("money_ad_normal_dealer_direct <=", value, "money_ad_normal_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_directIn(List<Integer> values) {
            addCriterion("money_ad_normal_dealer_direct in", values, "money_ad_normal_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_directNotIn(List<Integer> values) {
            addCriterion("money_ad_normal_dealer_direct not in", values, "money_ad_normal_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_directBetween(Integer value1, Integer value2) {
            addCriterion("money_ad_normal_dealer_direct between", value1, value2, "money_ad_normal_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_directNotBetween(Integer value1, Integer value2) {
            addCriterion("money_ad_normal_dealer_direct not between", value1, value2, "money_ad_normal_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_indirectIsNull() {
            addCriterion("money_ad_normal_dealer_indirect is null");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_indirectIsNotNull() {
            addCriterion("money_ad_normal_dealer_indirect is not null");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_indirectEqualTo(Integer value) {
            addCriterion("money_ad_normal_dealer_indirect =", value, "money_ad_normal_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_indirectNotEqualTo(Integer value) {
            addCriterion("money_ad_normal_dealer_indirect <>", value, "money_ad_normal_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_indirectGreaterThan(Integer value) {
            addCriterion("money_ad_normal_dealer_indirect >", value, "money_ad_normal_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_indirectGreaterThanOrEqualTo(Integer value) {
            addCriterion("money_ad_normal_dealer_indirect >=", value, "money_ad_normal_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_indirectLessThan(Integer value) {
            addCriterion("money_ad_normal_dealer_indirect <", value, "money_ad_normal_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_indirectLessThanOrEqualTo(Integer value) {
            addCriterion("money_ad_normal_dealer_indirect <=", value, "money_ad_normal_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_indirectIn(List<Integer> values) {
            addCriterion("money_ad_normal_dealer_indirect in", values, "money_ad_normal_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_indirectNotIn(List<Integer> values) {
            addCriterion("money_ad_normal_dealer_indirect not in", values, "money_ad_normal_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_indirectBetween(Integer value1, Integer value2) {
            addCriterion("money_ad_normal_dealer_indirect between", value1, value2, "money_ad_normal_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andMoney_ad_normal_dealer_indirectNotBetween(Integer value1, Integer value2) {
            addCriterion("money_ad_normal_dealer_indirect not between", value1, value2, "money_ad_normal_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andMoney_deposit_storeIsNull() {
            addCriterion("money_deposit_store is null");
            return (Criteria) this;
        }

        public Criteria andMoney_deposit_storeIsNotNull() {
            addCriterion("money_deposit_store is not null");
            return (Criteria) this;
        }

        public Criteria andMoney_deposit_storeEqualTo(Integer value) {
            addCriterion("money_deposit_store =", value, "money_deposit_store");
            return (Criteria) this;
        }

        public Criteria andMoney_deposit_storeNotEqualTo(Integer value) {
            addCriterion("money_deposit_store <>", value, "money_deposit_store");
            return (Criteria) this;
        }

        public Criteria andMoney_deposit_storeGreaterThan(Integer value) {
            addCriterion("money_deposit_store >", value, "money_deposit_store");
            return (Criteria) this;
        }

        public Criteria andMoney_deposit_storeGreaterThanOrEqualTo(Integer value) {
            addCriterion("money_deposit_store >=", value, "money_deposit_store");
            return (Criteria) this;
        }

        public Criteria andMoney_deposit_storeLessThan(Integer value) {
            addCriterion("money_deposit_store <", value, "money_deposit_store");
            return (Criteria) this;
        }

        public Criteria andMoney_deposit_storeLessThanOrEqualTo(Integer value) {
            addCriterion("money_deposit_store <=", value, "money_deposit_store");
            return (Criteria) this;
        }

        public Criteria andMoney_deposit_storeIn(List<Integer> values) {
            addCriterion("money_deposit_store in", values, "money_deposit_store");
            return (Criteria) this;
        }

        public Criteria andMoney_deposit_storeNotIn(List<Integer> values) {
            addCriterion("money_deposit_store not in", values, "money_deposit_store");
            return (Criteria) this;
        }

        public Criteria andMoney_deposit_storeBetween(Integer value1, Integer value2) {
            addCriterion("money_deposit_store between", value1, value2, "money_deposit_store");
            return (Criteria) this;
        }

        public Criteria andMoney_deposit_storeNotBetween(Integer value1, Integer value2) {
            addCriterion("money_deposit_store not between", value1, value2, "money_deposit_store");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_selfIsNull() {
            addCriterion("benefit_device_self is null");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_selfIsNotNull() {
            addCriterion("benefit_device_self is not null");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_selfEqualTo(Integer value) {
            addCriterion("benefit_device_self =", value, "benefit_device_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_selfNotEqualTo(Integer value) {
            addCriterion("benefit_device_self <>", value, "benefit_device_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_selfGreaterThan(Integer value) {
            addCriterion("benefit_device_self >", value, "benefit_device_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_selfGreaterThanOrEqualTo(Integer value) {
            addCriterion("benefit_device_self >=", value, "benefit_device_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_selfLessThan(Integer value) {
            addCriterion("benefit_device_self <", value, "benefit_device_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_selfLessThanOrEqualTo(Integer value) {
            addCriterion("benefit_device_self <=", value, "benefit_device_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_selfIn(List<Integer> values) {
            addCriterion("benefit_device_self in", values, "benefit_device_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_selfNotIn(List<Integer> values) {
            addCriterion("benefit_device_self not in", values, "benefit_device_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_selfBetween(Integer value1, Integer value2) {
            addCriterion("benefit_device_self between", value1, value2, "benefit_device_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_selfNotBetween(Integer value1, Integer value2) {
            addCriterion("benefit_device_self not between", value1, value2, "benefit_device_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_dealer_directIsNull() {
            addCriterion("benefit_device_dealer_direct is null");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_dealer_directIsNotNull() {
            addCriterion("benefit_device_dealer_direct is not null");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_dealer_directEqualTo(Integer value) {
            addCriterion("benefit_device_dealer_direct =", value, "benefit_device_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_dealer_directNotEqualTo(Integer value) {
            addCriterion("benefit_device_dealer_direct <>", value, "benefit_device_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_dealer_directGreaterThan(Integer value) {
            addCriterion("benefit_device_dealer_direct >", value, "benefit_device_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_dealer_directGreaterThanOrEqualTo(Integer value) {
            addCriterion("benefit_device_dealer_direct >=", value, "benefit_device_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_dealer_directLessThan(Integer value) {
            addCriterion("benefit_device_dealer_direct <", value, "benefit_device_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_dealer_directLessThanOrEqualTo(Integer value) {
            addCriterion("benefit_device_dealer_direct <=", value, "benefit_device_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_dealer_directIn(List<Integer> values) {
            addCriterion("benefit_device_dealer_direct in", values, "benefit_device_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_dealer_directNotIn(List<Integer> values) {
            addCriterion("benefit_device_dealer_direct not in", values, "benefit_device_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_dealer_directBetween(Integer value1, Integer value2) {
            addCriterion("benefit_device_dealer_direct between", value1, value2, "benefit_device_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andBenefit_device_dealer_directNotBetween(Integer value1, Integer value2) {
            addCriterion("benefit_device_dealer_direct not between", value1, value2, "benefit_device_dealer_direct");
            return (Criteria) this;
        }

        public Criteria andBenefit_deivce_dealer_indirectIsNull() {
            addCriterion("benefit_deivce_dealer_indirect is null");
            return (Criteria) this;
        }

        public Criteria andBenefit_deivce_dealer_indirectIsNotNull() {
            addCriterion("benefit_deivce_dealer_indirect is not null");
            return (Criteria) this;
        }

        public Criteria andBenefit_deivce_dealer_indirectEqualTo(Integer value) {
            addCriterion("benefit_deivce_dealer_indirect =", value, "benefit_deivce_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_deivce_dealer_indirectNotEqualTo(Integer value) {
            addCriterion("benefit_deivce_dealer_indirect <>", value, "benefit_deivce_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_deivce_dealer_indirectGreaterThan(Integer value) {
            addCriterion("benefit_deivce_dealer_indirect >", value, "benefit_deivce_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_deivce_dealer_indirectGreaterThanOrEqualTo(Integer value) {
            addCriterion("benefit_deivce_dealer_indirect >=", value, "benefit_deivce_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_deivce_dealer_indirectLessThan(Integer value) {
            addCriterion("benefit_deivce_dealer_indirect <", value, "benefit_deivce_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_deivce_dealer_indirectLessThanOrEqualTo(Integer value) {
            addCriterion("benefit_deivce_dealer_indirect <=", value, "benefit_deivce_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_deivce_dealer_indirectIn(List<Integer> values) {
            addCriterion("benefit_deivce_dealer_indirect in", values, "benefit_deivce_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_deivce_dealer_indirectNotIn(List<Integer> values) {
            addCriterion("benefit_deivce_dealer_indirect not in", values, "benefit_deivce_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_deivce_dealer_indirectBetween(Integer value1, Integer value2) {
            addCriterion("benefit_deivce_dealer_indirect between", value1, value2, "benefit_deivce_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_deivce_dealer_indirectNotBetween(Integer value1, Integer value2) {
            addCriterion("benefit_deivce_dealer_indirect not between", value1, value2, "benefit_deivce_dealer_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_selfIsNull() {
            addCriterion("benefit_ad_normal_self is null");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_selfIsNotNull() {
            addCriterion("benefit_ad_normal_self is not null");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_selfEqualTo(Integer value) {
            addCriterion("benefit_ad_normal_self =", value, "benefit_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_selfNotEqualTo(Integer value) {
            addCriterion("benefit_ad_normal_self <>", value, "benefit_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_selfGreaterThan(Integer value) {
            addCriterion("benefit_ad_normal_self >", value, "benefit_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_selfGreaterThanOrEqualTo(Integer value) {
            addCriterion("benefit_ad_normal_self >=", value, "benefit_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_selfLessThan(Integer value) {
            addCriterion("benefit_ad_normal_self <", value, "benefit_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_selfLessThanOrEqualTo(Integer value) {
            addCriterion("benefit_ad_normal_self <=", value, "benefit_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_selfIn(List<Integer> values) {
            addCriterion("benefit_ad_normal_self in", values, "benefit_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_selfNotIn(List<Integer> values) {
            addCriterion("benefit_ad_normal_self not in", values, "benefit_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_selfBetween(Integer value1, Integer value2) {
            addCriterion("benefit_ad_normal_self between", value1, value2, "benefit_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_selfNotBetween(Integer value1, Integer value2) {
            addCriterion("benefit_ad_normal_self not between", value1, value2, "benefit_ad_normal_self");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_indirectIsNull() {
            addCriterion("benefit_ad_normal_indirect is null");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_indirectIsNotNull() {
            addCriterion("benefit_ad_normal_indirect is not null");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_indirectEqualTo(Integer value) {
            addCriterion("benefit_ad_normal_indirect =", value, "benefit_ad_normal_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_indirectNotEqualTo(Integer value) {
            addCriterion("benefit_ad_normal_indirect <>", value, "benefit_ad_normal_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_indirectGreaterThan(Integer value) {
            addCriterion("benefit_ad_normal_indirect >", value, "benefit_ad_normal_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_indirectGreaterThanOrEqualTo(Integer value) {
            addCriterion("benefit_ad_normal_indirect >=", value, "benefit_ad_normal_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_indirectLessThan(Integer value) {
            addCriterion("benefit_ad_normal_indirect <", value, "benefit_ad_normal_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_indirectLessThanOrEqualTo(Integer value) {
            addCriterion("benefit_ad_normal_indirect <=", value, "benefit_ad_normal_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_indirectIn(List<Integer> values) {
            addCriterion("benefit_ad_normal_indirect in", values, "benefit_ad_normal_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_indirectNotIn(List<Integer> values) {
            addCriterion("benefit_ad_normal_indirect not in", values, "benefit_ad_normal_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_indirectBetween(Integer value1, Integer value2) {
            addCriterion("benefit_ad_normal_indirect between", value1, value2, "benefit_ad_normal_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_ad_normal_indirectNotBetween(Integer value1, Integer value2) {
            addCriterion("benefit_ad_normal_indirect not between", value1, value2, "benefit_ad_normal_indirect");
            return (Criteria) this;
        }

        public Criteria andBenefit_deposit_storeIsNull() {
            addCriterion("benefit_deposit_store is null");
            return (Criteria) this;
        }

        public Criteria andBenefit_deposit_storeIsNotNull() {
            addCriterion("benefit_deposit_store is not null");
            return (Criteria) this;
        }

        public Criteria andBenefit_deposit_storeEqualTo(Integer value) {
            addCriterion("benefit_deposit_store =", value, "benefit_deposit_store");
            return (Criteria) this;
        }

        public Criteria andBenefit_deposit_storeNotEqualTo(Integer value) {
            addCriterion("benefit_deposit_store <>", value, "benefit_deposit_store");
            return (Criteria) this;
        }

        public Criteria andBenefit_deposit_storeGreaterThan(Integer value) {
            addCriterion("benefit_deposit_store >", value, "benefit_deposit_store");
            return (Criteria) this;
        }

        public Criteria andBenefit_deposit_storeGreaterThanOrEqualTo(Integer value) {
            addCriterion("benefit_deposit_store >=", value, "benefit_deposit_store");
            return (Criteria) this;
        }

        public Criteria andBenefit_deposit_storeLessThan(Integer value) {
            addCriterion("benefit_deposit_store <", value, "benefit_deposit_store");
            return (Criteria) this;
        }

        public Criteria andBenefit_deposit_storeLessThanOrEqualTo(Integer value) {
            addCriterion("benefit_deposit_store <=", value, "benefit_deposit_store");
            return (Criteria) this;
        }

        public Criteria andBenefit_deposit_storeIn(List<Integer> values) {
            addCriterion("benefit_deposit_store in", values, "benefit_deposit_store");
            return (Criteria) this;
        }

        public Criteria andBenefit_deposit_storeNotIn(List<Integer> values) {
            addCriterion("benefit_deposit_store not in", values, "benefit_deposit_store");
            return (Criteria) this;
        }

        public Criteria andBenefit_deposit_storeBetween(Integer value1, Integer value2) {
            addCriterion("benefit_deposit_store between", value1, value2, "benefit_deposit_store");
            return (Criteria) this;
        }

        public Criteria andBenefit_deposit_storeNotBetween(Integer value1, Integer value2) {
            addCriterion("benefit_deposit_store not between", value1, value2, "benefit_deposit_store");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }





        public Criteria andUtokenEqualTo(String value) {
            addCriterion("utoken =", value, "utoken");
            return (Criteria) this;
        }

        public Criteria andUtokenNotEqualTo(String value) {
            addCriterion("utoken <>", value, "utoken");
            return (Criteria) this;
        }

        public Criteria andUtokenGreaterThan(String value) {
            addCriterion("utoken >", value, "utoken");
            return (Criteria) this;
        }

        public Criteria andUtokenGreaterThanOrEqualTo(String value) {
            addCriterion("utoken >=", value, "utoken");
            return (Criteria) this;
        }

        public Criteria andUtokenLessThan(String value) {
            addCriterion("utoken <", value, "utoken");
            return (Criteria) this;
        }

        public Criteria andUtokenLessThanOrEqualTo(String value) {
            addCriterion("utoken <=", value, "utoken");
            return (Criteria) this;
        }

        public Criteria andUtokenLike(String value) {
            addCriterion("utoken like", value, "utoken");
            return (Criteria) this;
        }

        public Criteria andUtokenNotLike(String value) {
            addCriterion("utoken not like", value, "utoken");
            return (Criteria) this;
        }

        public Criteria andUtokenIn(List<String> values) {
            addCriterion("utoken in", values, "utoken");
            return (Criteria) this;
        }

        public Criteria andUtokenNotIn(List<String> values) {
            addCriterion("utoken not in", values, "utoken");
            return (Criteria) this;
        }

        public Criteria andUtokenBetween(String value1, String value2) {
            addCriterion("utoken between", value1, value2, "utoken");
            return (Criteria) this;
        }

        public Criteria andUtokenNotBetween(String value1, String value2) {
            addCriterion("utoken not between", value1, value2, "utoken");
            return (Criteria) this;
        }



        public Criteria andRecord_timeIsNull() {
            addCriterion("record_time is null");
            return (Criteria) this;
        }

        public Criteria andRecord_timeIsNotNull() {
            addCriterion("record_time is not null");
            return (Criteria) this;
        }

        public Criteria andRecord_timeEqualTo(Date value) {
            addCriterionForJDBCDate("record_time =", value, "record_time");
            return (Criteria) this;
        }

        public Criteria andRecord_timeNotEqualTo(Date value) {
            addCriterionForJDBCDate("record_time <>", value, "record_time");
            return (Criteria) this;
        }

        public Criteria andRecord_timeGreaterThan(Date value) {
            addCriterionForJDBCDate("record_time >", value, "record_time");
            return (Criteria) this;
        }

        public Criteria andRecord_timeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("record_time >=", value, "record_time");
            return (Criteria) this;
        }

        public Criteria andRecord_timeLessThan(Date value) {
            addCriterionForJDBCDate("record_time <", value, "record_time");
            return (Criteria) this;
        }

        public Criteria andRecord_timeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("record_time <=", value, "record_time");
            return (Criteria) this;
        }

        public Criteria andRecord_timeIn(List<Date> values) {
            addCriterionForJDBCDate("record_time in", values, "record_time");
            return (Criteria) this;
        }

        public Criteria andRecord_timeNotIn(List<Date> values) {
            addCriterionForJDBCDate("record_time not in", values, "record_time");
            return (Criteria) this;
        }

        public Criteria andRecord_timeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("record_time between", value1, value2, "record_time");
            return (Criteria) this;
        }

        public Criteria andRecord_timeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("record_time not between", value1, value2, "record_time");
            return (Criteria) this;
        }

        public Criteria andTimeIsNull() {
            addCriterion("time is null");
            return (Criteria) this;
        }

        public Criteria andTimeIsNotNull() {
            addCriterion("time is not null");
            return (Criteria) this;
        }

        public Criteria andTimeEqualTo(Date value) {
            addCriterion("time =", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotEqualTo(Date value) {
            addCriterion("time <>", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThan(Date value) {
            addCriterion("time >", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("time >=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThan(Date value) {
            addCriterion("time <", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeLessThanOrEqualTo(Date value) {
            addCriterion("time <=", value, "time");
            return (Criteria) this;
        }

        public Criteria andTimeIn(List<Date> values) {
            addCriterion("time in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotIn(List<Date> values) {
            addCriterion("time not in", values, "time");
            return (Criteria) this;
        }

        public Criteria andTimeBetween(Date value1, Date value2) {
            addCriterion("time between", value1, value2, "time");
            return (Criteria) this;
        }

        public Criteria andTimeNotBetween(Date value1, Date value2) {
            addCriterion("time not between", value1, value2, "time");
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
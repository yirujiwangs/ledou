package model;

import model.divide.Criterion;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class DepositExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DepositExample() {
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

        public Criteria andOut_trade_noIsNull() {
            addCriterion("out_trade_no is null");
            return (Criteria) this;
        }

        public Criteria andOut_trade_noIsNotNull() {
            addCriterion("out_trade_no is not null");
            return (Criteria) this;
        }

        public Criteria andOut_trade_noEqualTo(String value) {
            addCriterion("out_trade_no =", value, "out_trade_no");
            return (Criteria) this;
        }

        public Criteria andOut_trade_noNotEqualTo(String value) {
            addCriterion("out_trade_no <>", value, "out_trade_no");
            return (Criteria) this;
        }

        public Criteria andOut_trade_noGreaterThan(String value) {
            addCriterion("out_trade_no >", value, "out_trade_no");
            return (Criteria) this;
        }

        public Criteria andOut_trade_noGreaterThanOrEqualTo(String value) {
            addCriterion("out_trade_no >=", value, "out_trade_no");
            return (Criteria) this;
        }

        public Criteria andOut_trade_noLessThan(String value) {
            addCriterion("out_trade_no <", value, "out_trade_no");
            return (Criteria) this;
        }

        public Criteria andOut_trade_noLessThanOrEqualTo(String value) {
            addCriterion("out_trade_no <=", value, "out_trade_no");
            return (Criteria) this;
        }

        public Criteria andOut_trade_noLike(String value) {
            addCriterion("out_trade_no like", value, "out_trade_no");
            return (Criteria) this;
        }

        public Criteria andOut_trade_noNotLike(String value) {
            addCriterion("out_trade_no not like", value, "out_trade_no");
            return (Criteria) this;
        }

        public Criteria andOut_trade_noIn(List<String> values) {
            addCriterion("out_trade_no in", values, "out_trade_no");
            return (Criteria) this;
        }

        public Criteria andOut_trade_noNotIn(List<String> values) {
            addCriterion("out_trade_no not in", values, "out_trade_no");
            return (Criteria) this;
        }

        public Criteria andOut_trade_noBetween(String value1, String value2) {
            addCriterion("out_trade_no between", value1, value2, "out_trade_no");
            return (Criteria) this;
        }

        public Criteria andOut_trade_noNotBetween(String value1, String value2) {
            addCriterion("out_trade_no not between", value1, value2, "out_trade_no");
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

        public Criteria andTrade_noIsNull() {
            addCriterion("trade_no is null");
            return (Criteria) this;
        }


        public Criteria andTrade_noIsNotNull() {
            addCriterion("trade_no is not null");
            return (Criteria) this;
        }

        public Criteria andTrade_noEqualTo(String value) {
            addCriterion("trade_no =", value, "trade_no");
            return (Criteria) this;
        }

        public Criteria andTrade_noNotEqualTo(String value) {
            addCriterion("trade_no <>", value, "trade_no");
            return (Criteria) this;
        }

        public Criteria andTrade_noGreaterThan(String value) {
            addCriterion("trade_no >", value, "trade_no");
            return (Criteria) this;
        }

        public Criteria andTrade_noGreaterThanOrEqualTo(String value) {
            addCriterion("trade_no >=", value, "trade_no");
            return (Criteria) this;
        }

        public Criteria andTrade_noLessThan(String value) {
            addCriterion("trade_no <", value, "trade_no");
            return (Criteria) this;
        }

        public Criteria andTrade_noLessThanOrEqualTo(String value) {
            addCriterion("trade_no <=", value, "trade_no");
            return (Criteria) this;
        }

        public Criteria andTrade_noLike(String value) {
            addCriterion("trade_no like", value, "trade_no");
            return (Criteria) this;
        }

        public Criteria andTrade_noNotLike(String value) {
            addCriterion("trade_no not like", value, "trade_no");
            return (Criteria) this;
        }

        public Criteria andTrade_noIn(List<String> values) {
            addCriterion("trade_no in", values, "trade_no");
            return (Criteria) this;
        }

        public Criteria andTrade_noNotIn(List<String> values) {
            addCriterion("trade_no not in", values, "trade_no");
            return (Criteria) this;
        }

        public Criteria andTrade_noBetween(String value1, String value2) {
            addCriterion("trade_no between", value1, value2, "trade_no");
            return (Criteria) this;
        }

        public Criteria andTrade_noNotBetween(String value1, String value2) {
            addCriterion("trade_no not between", value1, value2, "trade_no");
            return (Criteria) this;
        }

        public Criteria andFeeIsNull() {
            addCriterion("fee is null");
            return (Criteria) this;
        }

        public Criteria andFeeIsNotNull() {
            addCriterion("fee is not null");
            return (Criteria) this;
        }

        public Criteria andFeeEqualTo(Double value) {
            addCriterion("fee =", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotEqualTo(Double value) {
            addCriterion("fee <>", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThan(Double value) {
            addCriterion("fee >", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeGreaterThanOrEqualTo(Double value) {
            addCriterion("fee >=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThan(Double value) {
            addCriterion("fee <", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeLessThanOrEqualTo(Double value) {
            addCriterion("fee <=", value, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeIn(List<Double> values) {
            addCriterion("fee in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotIn(List<Double> values) {
            addCriterion("fee not in", values, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeBetween(Double value1, Double value2) {
            addCriterion("fee between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andFeeNotBetween(Double value1, Double value2) {
            addCriterion("fee not between", value1, value2, "fee");
            return (Criteria) this;
        }

        public Criteria andDateIsNull() {
            addCriterion("date is null");
            return (Criteria) this;
        }

        public Criteria andDateIsNotNull() {
            addCriterion("date is not null");
            return (Criteria) this;
        }

        public Criteria andDateEqualTo(Date value) {
            addCriterionForJDBCDate("date =", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("date <>", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThan(Date value) {
            addCriterionForJDBCDate("date >", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date >=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThan(Date value) {
            addCriterionForJDBCDate("date <", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("date <=", value, "date");
            return (Criteria) this;
        }

        public Criteria andDateIn(List<Date> values) {
            addCriterionForJDBCDate("date in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("date not in", values, "date");
            return (Criteria) this;
        }

        public Criteria andDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("date not between", value1, value2, "date");
            return (Criteria) this;
        }

        public Criteria andDate_timeIsNull() {
            addCriterion("date_time is null");
            return (Criteria) this;
        }

        public Criteria andDate_timeIsNotNull() {

            addCriterion("date_time is not null");
            return (Criteria) this;
        }

        public Criteria andDate_timeEqualTo(Date value) {
            addCriterion("date_time =", value, "date_time");
            return (Criteria) this;
        }

        public Criteria andDate_timeNotEqualTo(Date value) {
            addCriterion("date_time <>", value, "date_time");
            return (Criteria) this;
        }

        public Criteria andDate_timeGreaterThan(Date value) {
            addCriterion("date_time >", value, "date_time");
            return (Criteria) this;
        }

        public Criteria andDate_timeGreaterThanOrEqualTo(Date value) {
            addCriterion("date_time >=", value, "date_time");
            return (Criteria) this;
        }

        public Criteria andDate_timeLessThan(Date value) {
            addCriterion("date_time <", value, "date_time");
            return (Criteria) this;
        }

        public Criteria andDate_timeLessThanOrEqualTo(Date value) {
            addCriterion("date_time <=", value, "date_time");
            return (Criteria) this;
        }

        public Criteria andDate_timeIn(List<Date> values) {
            addCriterion("date_time in", values, "date_time");
            return (Criteria) this;
        }

        public Criteria andDate_timeNotIn(List<Date> values) {
            addCriterion("date_time not in", values, "date_time");
            return (Criteria) this;
        }

        public Criteria andDate_timeBetween(Date value1, Date value2) {
            addCriterion("date_time between", value1, value2, "date_time");
            return (Criteria) this;
        }

        public Criteria andDate_timeNotBetween(Date value1, Date value2) {
            addCriterion("date_time not between", value1, value2, "date_time");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andResultIsNull() {
            addCriterion("result is null");
            return (Criteria) this;
        }

        public Criteria andResultIsNotNull() {
            addCriterion("result is not null");
            return (Criteria) this;
        }

        public Criteria andResultEqualTo(String value) {
            addCriterion("result =", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotEqualTo(String value) {
            addCriterion("result <>", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThan(String value) {
            addCriterion("result >", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultGreaterThanOrEqualTo(String value) {
            addCriterion("result >=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThan(String value) {
            addCriterion("result <", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLessThanOrEqualTo(String value) {
            addCriterion("result <=", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultLike(String value) {
            addCriterion("result like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotLike(String value) {
            addCriterion("result not like", value, "result");
            return (Criteria) this;
        }

        public Criteria andResultIn(List<String> values) {
            addCriterion("result in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotIn(List<String> values) {
            addCriterion("result not in", values, "result");
            return (Criteria) this;
        }

        public Criteria andResultBetween(String value1, String value2) {
            addCriterion("result between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andResultNotBetween(String value1, String value2) {
            addCriterion("result not between", value1, value2, "result");
            return (Criteria) this;
        }

        public Criteria andCauseIsNull() {
            addCriterion("cause is null");
            return (Criteria) this;
        }

        public Criteria andCauseIsNotNull() {
            addCriterion("cause is not null");
            return (Criteria) this;
        }

        public Criteria andCauseEqualTo(String value) {
            addCriterion("cause =", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotEqualTo(String value) {
            addCriterion("cause <>", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseGreaterThan(String value) {
            addCriterion("cause >", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseGreaterThanOrEqualTo(String value) {
            addCriterion("cause >=", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLessThan(String value) {
            addCriterion("cause <", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLessThanOrEqualTo(String value) {
            addCriterion("cause <=", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseLike(String value) {
            addCriterion("cause like", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotLike(String value) {
            addCriterion("cause not like", value, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseIn(List<String> values) {
            addCriterion("cause in", values, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotIn(List<String> values) {
            addCriterion("cause not in", values, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseBetween(String value1, String value2) {
            addCriterion("cause between", value1, value2, "cause");
            return (Criteria) this;
        }

        public Criteria andCauseNotBetween(String value1, String value2) {
            addCriterion("cause not between", value1, value2, "cause");
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

        public Criteria andProxy_taxesidIsNull() {
            addCriterion("proxy_taxesid is null");
            return (Criteria) this;
        }

        public Criteria andProxy_taxesidIsNotNull() {
            addCriterion("proxy_taxesid is not null");
            return (Criteria) this;
        }

        public Criteria andProxy_taxesidEqualTo(Integer value) {
            addCriterion("proxy_taxesid =", value, "proxy_taxesid");
            return (Criteria) this;
        }

        public Criteria andProxy_taxesidNotEqualTo(Integer value) {
            addCriterion("proxy_taxesid <>", value, "proxy_taxesid");
            return (Criteria) this;
        }

        public Criteria andProxy_taxesidGreaterThan(Integer value) {
            addCriterion("proxy_taxesid >", value, "proxy_taxesid");
            return (Criteria) this;
        }

        public Criteria andProxy_taxesidGreaterThanOrEqualTo(Integer value) {
            addCriterion("proxy_taxesid >=", value, "proxy_taxesid");
            return (Criteria) this;
        }

        public Criteria andProxy_taxesidLessThan(Integer value) {
            addCriterion("proxy_taxesid <", value, "proxy_taxesid");
            return (Criteria) this;
        }

        public Criteria andProxy_taxesidLessThanOrEqualTo(Integer value) {
            addCriterion("proxy_taxesid <=", value, "proxy_taxesid");
            return (Criteria) this;
        }

        public Criteria andProxy_taxesidIn(List<Integer> values) {
            addCriterion("proxy_taxesid in", values, "proxy_taxesid");
            return (Criteria) this;
        }

        public Criteria andProxy_taxesidNotIn(List<Integer> values) {
            addCriterion("proxy_taxesid not in", values, "proxy_taxesid");
            return (Criteria) this;
        }

        public Criteria andProxy_taxesidBetween(Integer value1, Integer value2) {
            addCriterion("proxy_taxesid between", value1, value2, "proxy_taxesid");
            return (Criteria) this;
        }

        public Criteria andProxy_taxesidNotBetween(Integer value1, Integer value2) {
            addCriterion("proxy_taxesid not between", value1, value2, "proxy_taxesid");
            return (Criteria) this;
        }

    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}
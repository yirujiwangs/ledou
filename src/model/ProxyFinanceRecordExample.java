package model;

import model.divide.BaseDivideModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProxyFinanceRecordExample extends BaseDivideModel{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProxyFinanceRecordExample() {
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andProxy_phoneIsNull() {
            addCriterion("proxy_phone is null");
            return (Criteria) this;
        }

        public Criteria andProxy_phoneIsNotNull() {
            addCriterion("proxy_phone is not null");
            return (Criteria) this;
        }

        public Criteria andProxy_phoneEqualTo(String value) {
            addCriterion("proxy_phone =", value, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andProxy_phoneNotEqualTo(String value) {
            addCriterion("proxy_phone <>", value, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andProxy_phoneGreaterThan(String value) {
            addCriterion("proxy_phone >", value, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andProxy_phoneGreaterThanOrEqualTo(String value) {
            addCriterion("proxy_phone >=", value, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andProxy_phoneLessThan(String value) {
            addCriterion("proxy_phone <", value, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andProxy_phoneLessThanOrEqualTo(String value) {
            addCriterion("proxy_phone <=", value, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andProxy_phoneLike(String value) {
            addCriterion("proxy_phone like", value, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andProxy_phoneNotLike(String value) {
            addCriterion("proxy_phone not like", value, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andProxy_phoneIn(List<String> values) {
            addCriterion("proxy_phone in", values, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andProxy_phoneNotIn(List<String> values) {
            addCriterion("proxy_phone not in", values, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andProxy_phoneBetween(String value1, String value2) {
            addCriterion("proxy_phone between", value1, value2, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andProxy_phoneNotBetween(String value1, String value2) {
            addCriterion("proxy_phone not between", value1, value2, "proxy_phone");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("state is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("state is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("state =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("state <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("state >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("state >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("state <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("state <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("state in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("state not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("state between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("state not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andDeposit_out_trade_noIsNull() {
            addCriterion("deposit_out_trade_no is null");
            return (Criteria) this;
        }

        public Criteria andDeposit_out_trade_noIsNotNull() {
            addCriterion("deposit_out_trade_no is not null");
            return (Criteria) this;
        }

        public Criteria andDeposit_out_trade_noEqualTo(String value) {
            addCriterion("deposit_out_trade_no =", value, "deposit_out_trade_no");
            return (Criteria) this;
        }

        public Criteria andDeposit_out_trade_noNotEqualTo(String value) {
            addCriterion("deposit_out_trade_no <>", value, "deposit_out_trade_no");
            return (Criteria) this;
        }

        public Criteria andDeposit_out_trade_noGreaterThan(String value) {
            addCriterion("deposit_out_trade_no >", value, "deposit_out_trade_no");
            return (Criteria) this;
        }

        public Criteria andDeposit_out_trade_noGreaterThanOrEqualTo(String value) {
            addCriterion("deposit_out_trade_no >=", value, "deposit_out_trade_no");
            return (Criteria) this;
        }

        public Criteria andDeposit_out_trade_noLessThan(String value) {
            addCriterion("deposit_out_trade_no <", value, "deposit_out_trade_no");
            return (Criteria) this;
        }

        public Criteria andDeposit_out_trade_noLessThanOrEqualTo(String value) {
            addCriterion("deposit_out_trade_no <=", value, "deposit_out_trade_no");
            return (Criteria) this;
        }

        public Criteria andDeposit_out_trade_noLike(String value) {
            addCriterion("deposit_out_trade_no like", value, "deposit_out_trade_no");
            return (Criteria) this;
        }

        public Criteria andDeposit_out_trade_noNotLike(String value) {
            addCriterion("deposit_out_trade_no not like", value, "deposit_out_trade_no");
            return (Criteria) this;
        }

        public Criteria andDeposit_out_trade_noIn(List<String> values) {
            addCriterion("deposit_out_trade_no in", values, "deposit_out_trade_no");
            return (Criteria) this;
        }

        public Criteria andDeposit_out_trade_noNotIn(List<String> values) {
            addCriterion("deposit_out_trade_no not in", values, "deposit_out_trade_no");
            return (Criteria) this;
        }

        public Criteria andDeposit_out_trade_noBetween(String value1, String value2) {
            addCriterion("deposit_out_trade_no between", value1, value2, "deposit_out_trade_no");
            return (Criteria) this;
        }

        public Criteria andDeposit_out_trade_noNotBetween(String value1, String value2) {
            addCriterion("deposit_out_trade_no not between", value1, value2, "deposit_out_trade_no");
            return (Criteria) this;
        }

        public Criteria andProxyUserPhoneIsNull() {
            addCriterion("proxy_userphone is null");
            return (Criteria) this;
        }

        public Criteria andProxyUserPhoneIsNotNull() {
            addCriterion("proxy_userphone is not null");
            return (Criteria) this;
        }

        public Criteria andProxyUserPhoneEqualTo(String value) {
            addCriterion("proxy_userphone =", value, "proxy_userphone");
            return (Criteria) this;
        }

        public Criteria andProxyUserPhoneNotEqualTo(String value) {
            addCriterion("proxy_userphone <>", value, "proxy_userphone");
            return (Criteria) this;
        }

        public Criteria andProxyUserPhoneGreaterThan(String value) {
            addCriterion("proxy_userphone >", value, "proxy_userphone");
            return (Criteria) this;
        }

        public Criteria andProxyUserPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("proxy_userphone >=", value, "proxy_userphone");
            return (Criteria) this;
        }

        public Criteria andProxyUserPhoneLessThan(String value) {
            addCriterion("proxy_userphone <", value, "proxy_userphone");
            return (Criteria) this;
        }

        public Criteria andProxyUserPhoneLessThanOrEqualTo(String value) {
            addCriterion("proxy_userphone <=", value, "proxy_userphone");
            return (Criteria) this;
        }

        public Criteria andProxyUserPhoneIn(List<String> values) {
            addCriterion("proxy_userphone in", values, "proxy_userphone");
            return (Criteria) this;
        }

        public Criteria andProxyUserPhoneNotIn(List<String> values) {
            addCriterion("proxy_userphone not in", values, "proxy_userphone");
            return (Criteria) this;
        }

        public Criteria andProxyUserPhoneBetween(String value1, String value2) {
            addCriterion("proxy_userphone between", value1, value2, "proxy_userphone");
            return (Criteria) this;
        }

        public Criteria andProxyUserPhoneNotBetween(String value1, String value2) {
            addCriterion("proxy_userphone not between", value1, value2, "proxy_userphone");
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
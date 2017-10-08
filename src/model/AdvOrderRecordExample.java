package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdvOrderRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdvOrderRecordExample() {
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

        public Criteria andProxyPhoneIsNull() {
            addCriterion("proxy_phone is null");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneIsNotNull() {
            addCriterion("proxy_phone is not null");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneEqualTo(String value) {
            addCriterion("proxy_phone =", value, "proxyPhone");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneNotEqualTo(String value) {
            addCriterion("proxy_phone <>", value, "proxyPhone");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneGreaterThan(String value) {
            addCriterion("proxy_phone >", value, "proxyPhone");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("proxy_phone >=", value, "proxyPhone");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneLessThan(String value) {
            addCriterion("proxy_phone <", value, "proxyPhone");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneLessThanOrEqualTo(String value) {
            addCriterion("proxy_phone <=", value, "proxyPhone");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneLike(String value) {
            addCriterion("proxy_phone like", value, "proxyPhone");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneNotLike(String value) {
            addCriterion("proxy_phone not like", value, "proxyPhone");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneIn(List<String> values) {
            addCriterion("proxy_phone in", values, "proxyPhone");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneNotIn(List<String> values) {
            addCriterion("proxy_phone not in", values, "proxyPhone");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneBetween(String value1, String value2) {
            addCriterion("proxy_phone between", value1, value2, "proxyPhone");
            return (Criteria) this;
        }

        public Criteria andProxyPhoneNotBetween(String value1, String value2) {
            addCriterion("proxy_phone not between", value1, value2, "proxyPhone");
            return (Criteria) this;
        }

        public Criteria andProxyTokenIsNull() {
            addCriterion("proxy_token is null");
            return (Criteria) this;
        }

        public Criteria andProxyTokenIsNotNull() {
            addCriterion("proxy_token is not null");
            return (Criteria) this;
        }

        public Criteria andProxyTokenEqualTo(String value) {
            addCriterion("proxy_token =", value, "proxyToken");
            return (Criteria) this;
        }

        public Criteria andProxyTokenNotEqualTo(String value) {
            addCriterion("proxy_token <>", value, "proxyToken");
            return (Criteria) this;
        }

        public Criteria andProxyTokenGreaterThan(String value) {
            addCriterion("proxy_token >", value, "proxyToken");
            return (Criteria) this;
        }

        public Criteria andProxyTokenGreaterThanOrEqualTo(String value) {
            addCriterion("proxy_token >=", value, "proxyToken");
            return (Criteria) this;
        }

        public Criteria andProxyTokenLessThan(String value) {
            addCriterion("proxy_token <", value, "proxyToken");
            return (Criteria) this;
        }

        public Criteria andProxyTokenLessThanOrEqualTo(String value) {
            addCriterion("proxy_token <=", value, "proxyToken");
            return (Criteria) this;
        }

        public Criteria andProxyTokenLike(String value) {
            addCriterion("proxy_token like", value, "proxyToken");
            return (Criteria) this;
        }

        public Criteria andProxyTokenNotLike(String value) {
            addCriterion("proxy_token not like", value, "proxyToken");
            return (Criteria) this;
        }

        public Criteria andProxyTokenIn(List<String> values) {
            addCriterion("proxy_token in", values, "proxyToken");
            return (Criteria) this;
        }

        public Criteria andProxyTokenNotIn(List<String> values) {
            addCriterion("proxy_token not in", values, "proxyToken");
            return (Criteria) this;
        }

        public Criteria andProxyTokenBetween(String value1, String value2) {
            addCriterion("proxy_token between", value1, value2, "proxyToken");
            return (Criteria) this;
        }

        public Criteria andProxyTokenNotBetween(String value1, String value2) {
            addCriterion("proxy_token not between", value1, value2, "proxyToken");
            return (Criteria) this;
        }

        public Criteria andAdvUuidIsNull() {
            addCriterion("adv_uuid is null");
            return (Criteria) this;
        }

        public Criteria andAdvUuidIsNotNull() {
            addCriterion("adv_uuid is not null");
            return (Criteria) this;
        }

        public Criteria andAdvUuidEqualTo(String value) {
            addCriterion("adv_uuid =", value, "advUuid");
            return (Criteria) this;
        }

        public Criteria andAdvUuidNotEqualTo(String value) {
            addCriterion("adv_uuid <>", value, "advUuid");
            return (Criteria) this;
        }

        public Criteria andAdvUuidGreaterThan(String value) {
            addCriterion("adv_uuid >", value, "advUuid");
            return (Criteria) this;
        }

        public Criteria andAdvUuidGreaterThanOrEqualTo(String value) {
            addCriterion("adv_uuid >=", value, "advUuid");
            return (Criteria) this;
        }

        public Criteria andAdvUuidLessThan(String value) {
            addCriterion("adv_uuid <", value, "advUuid");
            return (Criteria) this;
        }

        public Criteria andAdvUuidLessThanOrEqualTo(String value) {
            addCriterion("adv_uuid <=", value, "advUuid");
            return (Criteria) this;
        }

        public Criteria andAdvUuidLike(String value) {
            addCriterion("adv_uuid like", value, "advUuid");
            return (Criteria) this;
        }

        public Criteria andAdvUuidNotLike(String value) {
            addCriterion("adv_uuid not like", value, "advUuid");
            return (Criteria) this;
        }

        public Criteria andAdvUuidIn(List<String> values) {
            addCriterion("adv_uuid in", values, "advUuid");
            return (Criteria) this;
        }

        public Criteria andAdvUuidNotIn(List<String> values) {
            addCriterion("adv_uuid not in", values, "advUuid");
            return (Criteria) this;
        }

        public Criteria andAdvUuidBetween(String value1, String value2) {
            addCriterion("adv_uuid between", value1, value2, "advUuid");
            return (Criteria) this;
        }

        public Criteria andAdvUuidNotBetween(String value1, String value2) {
            addCriterion("adv_uuid not between", value1, value2, "advUuid");
            return (Criteria) this;
        }

        public Criteria andAdvFeeIsNull() {
            addCriterion("adv_fee is null");
            return (Criteria) this;
        }

        public Criteria andAdvFeeIsNotNull() {
            addCriterion("adv_fee is not null");
            return (Criteria) this;
        }

        public Criteria andAdvFeeEqualTo(Double value) {
            addCriterion("adv_fee =", value, "advFee");
            return (Criteria) this;
        }

        public Criteria andAdvFeeNotEqualTo(Double value) {
            addCriterion("adv_fee <>", value, "advFee");
            return (Criteria) this;
        }

        public Criteria andAdvFeeGreaterThan(Double value) {
            addCriterion("adv_fee >", value, "advFee");
            return (Criteria) this;
        }

        public Criteria andAdvFeeGreaterThanOrEqualTo(Double value) {
            addCriterion("adv_fee >=", value, "advFee");
            return (Criteria) this;
        }

        public Criteria andAdvFeeLessThan(Double value) {
            addCriterion("adv_fee <", value, "advFee");
            return (Criteria) this;
        }

        public Criteria andAdvFeeLessThanOrEqualTo(Double value) {
            addCriterion("adv_fee <=", value, "advFee");
            return (Criteria) this;
        }

        public Criteria andAdvFeeIn(List<Double> values) {
            addCriterion("adv_fee in", values, "advFee");
            return (Criteria) this;
        }

        public Criteria andAdvFeeNotIn(List<Double> values) {
            addCriterion("adv_fee not in", values, "advFee");
            return (Criteria) this;
        }

        public Criteria andAdvFeeBetween(Double value1, Double value2) {
            addCriterion("adv_fee between", value1, value2, "advFee");
            return (Criteria) this;
        }

        public Criteria andAdvFeeNotBetween(Double value1, Double value2) {
            addCriterion("adv_fee not between", value1, value2, "advFee");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIsNull() {
            addCriterion("adv_type is null");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIsNotNull() {
            addCriterion("adv_type is not null");
            return (Criteria) this;
        }

        public Criteria andAdvTypeEqualTo(Integer value) {
            addCriterion("adv_type =", value, "advType");
            return (Criteria) this;
        }

        public Criteria andAdvTypeNotEqualTo(Integer value) {
            addCriterion("adv_type <>", value, "advType");
            return (Criteria) this;
        }

        public Criteria andAdvTypeGreaterThan(Integer value) {
            addCriterion("adv_type >", value, "advType");
            return (Criteria) this;
        }

        public Criteria andAdvTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("adv_type >=", value, "advType");
            return (Criteria) this;
        }

        public Criteria andAdvTypeLessThan(Integer value) {
            addCriterion("adv_type <", value, "advType");
            return (Criteria) this;
        }

        public Criteria andAdvTypeLessThanOrEqualTo(Integer value) {
            addCriterion("adv_type <=", value, "advType");
            return (Criteria) this;
        }

        public Criteria andAdvTypeIn(List<Integer> values) {
            addCriterion("adv_type in", values, "advType");
            return (Criteria) this;
        }

        public Criteria andAdvTypeNotIn(List<Integer> values) {
            addCriterion("adv_type not in", values, "advType");
            return (Criteria) this;
        }

        public Criteria andAdvTypeBetween(Integer value1, Integer value2) {
            addCriterion("adv_type between", value1, value2, "advType");
            return (Criteria) this;
        }

        public Criteria andAdvTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("adv_type not between", value1, value2, "advType");
            return (Criteria) this;
        }

        public Criteria andOurTradeNoIsNull() {
            addCriterion("our_trade_no is null");
            return (Criteria) this;
        }

        public Criteria andOurTradeNoIsNotNull() {
            addCriterion("our_trade_no is not null");
            return (Criteria) this;
        }

        public Criteria andOurTradeNoEqualTo(String value) {
            addCriterion("our_trade_no =", value, "ourTradeNo");
            return (Criteria) this;
        }

        public Criteria andOurTradeNoNotEqualTo(String value) {
            addCriterion("our_trade_no <>", value, "ourTradeNo");
            return (Criteria) this;
        }

        public Criteria andOurTradeNoGreaterThan(String value) {
            addCriterion("our_trade_no >", value, "ourTradeNo");
            return (Criteria) this;
        }

        public Criteria andOurTradeNoGreaterThanOrEqualTo(String value) {
            addCriterion("our_trade_no >=", value, "ourTradeNo");
            return (Criteria) this;
        }

        public Criteria andOurTradeNoLessThan(String value) {
            addCriterion("our_trade_no <", value, "ourTradeNo");
            return (Criteria) this;
        }

        public Criteria andOurTradeNoLessThanOrEqualTo(String value) {
            addCriterion("our_trade_no <=", value, "ourTradeNo");
            return (Criteria) this;
        }

        public Criteria andOurTradeNoLike(String value) {
            addCriterion("our_trade_no like", value, "ourTradeNo");
            return (Criteria) this;
        }

        public Criteria andOurTradeNoNotLike(String value) {
            addCriterion("our_trade_no not like", value, "ourTradeNo");
            return (Criteria) this;
        }

        public Criteria andOurTradeNoIn(List<String> values) {
            addCriterion("our_trade_no in", values, "ourTradeNo");
            return (Criteria) this;
        }

        public Criteria andOurTradeNoNotIn(List<String> values) {
            addCriterion("our_trade_no not in", values, "ourTradeNo");
            return (Criteria) this;
        }

        public Criteria andOurTradeNoBetween(String value1, String value2) {
            addCriterion("our_trade_no between", value1, value2, "ourTradeNo");
            return (Criteria) this;
        }

        public Criteria andOurTradeNoNotBetween(String value1, String value2) {
            addCriterion("our_trade_no not between", value1, value2, "ourTradeNo");
            return (Criteria) this;
        }

        public Criteria andAlipayTradeNoIsNull() {
            addCriterion("alipay_trade_no is null");
            return (Criteria) this;
        }

        public Criteria andAlipayTradeNoIsNotNull() {
            addCriterion("alipay_trade_no is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayTradeNoEqualTo(String value) {
            addCriterion("alipay_trade_no =", value, "alipayTradeNo");
            return (Criteria) this;
        }

        public Criteria andAlipayTradeNoNotEqualTo(String value) {
            addCriterion("alipay_trade_no <>", value, "alipayTradeNo");
            return (Criteria) this;
        }

        public Criteria andAlipayTradeNoGreaterThan(String value) {
            addCriterion("alipay_trade_no >", value, "alipayTradeNo");
            return (Criteria) this;
        }

        public Criteria andAlipayTradeNoGreaterThanOrEqualTo(String value) {
            addCriterion("alipay_trade_no >=", value, "alipayTradeNo");
            return (Criteria) this;
        }

        public Criteria andAlipayTradeNoLessThan(String value) {
            addCriterion("alipay_trade_no <", value, "alipayTradeNo");
            return (Criteria) this;
        }

        public Criteria andAlipayTradeNoLessThanOrEqualTo(String value) {
            addCriterion("alipay_trade_no <=", value, "alipayTradeNo");
            return (Criteria) this;
        }

        public Criteria andAlipayTradeNoLike(String value) {
            addCriterion("alipay_trade_no like", value, "alipayTradeNo");
            return (Criteria) this;
        }

        public Criteria andAlipayTradeNoNotLike(String value) {
            addCriterion("alipay_trade_no not like", value, "alipayTradeNo");
            return (Criteria) this;
        }

        public Criteria andAlipayTradeNoIn(List<String> values) {
            addCriterion("alipay_trade_no in", values, "alipayTradeNo");
            return (Criteria) this;
        }

        public Criteria andAlipayTradeNoNotIn(List<String> values) {
            addCriterion("alipay_trade_no not in", values, "alipayTradeNo");
            return (Criteria) this;
        }

        public Criteria andAlipayTradeNoBetween(String value1, String value2) {
            addCriterion("alipay_trade_no between", value1, value2, "alipayTradeNo");
            return (Criteria) this;
        }

        public Criteria andAlipayTradeNoNotBetween(String value1, String value2) {
            addCriterion("alipay_trade_no not between", value1, value2, "alipayTradeNo");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNameIsNull() {
            addCriterion("alipay_account_name is null");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNameIsNotNull() {
            addCriterion("alipay_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNameEqualTo(String value) {
            addCriterion("alipay_account_name =", value, "alipayAccountName");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNameNotEqualTo(String value) {
            addCriterion("alipay_account_name <>", value, "alipayAccountName");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNameGreaterThan(String value) {
            addCriterion("alipay_account_name >", value, "alipayAccountName");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("alipay_account_name >=", value, "alipayAccountName");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNameLessThan(String value) {
            addCriterion("alipay_account_name <", value, "alipayAccountName");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNameLessThanOrEqualTo(String value) {
            addCriterion("alipay_account_name <=", value, "alipayAccountName");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNameLike(String value) {
            addCriterion("alipay_account_name like", value, "alipayAccountName");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNameNotLike(String value) {
            addCriterion("alipay_account_name not like", value, "alipayAccountName");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNameIn(List<String> values) {
            addCriterion("alipay_account_name in", values, "alipayAccountName");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNameNotIn(List<String> values) {
            addCriterion("alipay_account_name not in", values, "alipayAccountName");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNameBetween(String value1, String value2) {
            addCriterion("alipay_account_name between", value1, value2, "alipayAccountName");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNameNotBetween(String value1, String value2) {
            addCriterion("alipay_account_name not between", value1, value2, "alipayAccountName");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNumIsNull() {
            addCriterion("alipay_account_num is null");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNumIsNotNull() {
            addCriterion("alipay_account_num is not null");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNumEqualTo(String value) {
            addCriterion("alipay_account_num =", value, "alipayAccountNum");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNumNotEqualTo(String value) {
            addCriterion("alipay_account_num <>", value, "alipayAccountNum");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNumGreaterThan(String value) {
            addCriterion("alipay_account_num >", value, "alipayAccountNum");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNumGreaterThanOrEqualTo(String value) {
            addCriterion("alipay_account_num >=", value, "alipayAccountNum");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNumLessThan(String value) {
            addCriterion("alipay_account_num <", value, "alipayAccountNum");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNumLessThanOrEqualTo(String value) {
            addCriterion("alipay_account_num <=", value, "alipayAccountNum");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNumLike(String value) {
            addCriterion("alipay_account_num like", value, "alipayAccountNum");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNumNotLike(String value) {
            addCriterion("alipay_account_num not like", value, "alipayAccountNum");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNumIn(List<String> values) {
            addCriterion("alipay_account_num in", values, "alipayAccountNum");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNumNotIn(List<String> values) {
            addCriterion("alipay_account_num not in", values, "alipayAccountNum");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNumBetween(String value1, String value2) {
            addCriterion("alipay_account_num between", value1, value2, "alipayAccountNum");
            return (Criteria) this;
        }

        public Criteria andAlipayAccountNumNotBetween(String value1, String value2) {
            addCriterion("alipay_account_num not between", value1, value2, "alipayAccountNum");
            return (Criteria) this;
        }

        public Criteria andTradeStatusIsNull() {
            addCriterion("trade_status is null");
            return (Criteria) this;
        }

        public Criteria andTradeStatusIsNotNull() {
            addCriterion("trade_status is not null");
            return (Criteria) this;
        }

        public Criteria andTradeStatusEqualTo(Integer value) {
            addCriterion("trade_status =", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNotEqualTo(Integer value) {
            addCriterion("trade_status <>", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusGreaterThan(Integer value) {
            addCriterion("trade_status >", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("trade_status >=", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusLessThan(Integer value) {
            addCriterion("trade_status <", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusLessThanOrEqualTo(Integer value) {
            addCriterion("trade_status <=", value, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusIn(List<Integer> values) {
            addCriterion("trade_status in", values, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNotIn(List<Integer> values) {
            addCriterion("trade_status not in", values, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusBetween(Integer value1, Integer value2) {
            addCriterion("trade_status between", value1, value2, "tradeStatus");
            return (Criteria) this;
        }

        public Criteria andTradeStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("trade_status not between", value1, value2, "tradeStatus");
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

        public Criteria andTradetimeIsNull() {
            addCriterion("tradetime is null");
            return (Criteria) this;
        }

        public Criteria andTradetimeIsNotNull() {
            addCriterion("tradetime is not null");
            return (Criteria) this;
        }

        public Criteria andTradetimeEqualTo(Date value) {
            addCriterion("tradetime =", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeNotEqualTo(Date value) {
            addCriterion("tradetime <>", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeGreaterThan(Date value) {
            addCriterion("tradetime >", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("tradetime >=", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeLessThan(Date value) {
            addCriterion("tradetime <", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeLessThanOrEqualTo(Date value) {
            addCriterion("tradetime <=", value, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeIn(List<Date> values) {
            addCriterion("tradetime in", values, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeNotIn(List<Date> values) {
            addCriterion("tradetime not in", values, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeBetween(Date value1, Date value2) {
            addCriterion("tradetime between", value1, value2, "tradetime");
            return (Criteria) this;
        }

        public Criteria andTradetimeNotBetween(Date value1, Date value2) {
            addCriterion("tradetime not between", value1, value2, "tradetime");
            return (Criteria) this;
        }

        public Criteria andCreattimeIsNull() {
            addCriterion("creattime is null");
            return (Criteria) this;
        }

        public Criteria andCreattimeIsNotNull() {
            addCriterion("creattime is not null");
            return (Criteria) this;
        }

        public Criteria andCreattimeEqualTo(Date value) {
            addCriterion("creattime =", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotEqualTo(Date value) {
            addCriterion("creattime <>", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeGreaterThan(Date value) {
            addCriterion("creattime >", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeGreaterThanOrEqualTo(Date value) {
            addCriterion("creattime >=", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeLessThan(Date value) {
            addCriterion("creattime <", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeLessThanOrEqualTo(Date value) {
            addCriterion("creattime <=", value, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeIn(List<Date> values) {
            addCriterion("creattime in", values, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotIn(List<Date> values) {
            addCriterion("creattime not in", values, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeBetween(Date value1, Date value2) {
            addCriterion("creattime between", value1, value2, "creattime");
            return (Criteria) this;
        }

        public Criteria andCreattimeNotBetween(Date value1, Date value2) {
            addCriterion("creattime not between", value1, value2, "creattime");
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
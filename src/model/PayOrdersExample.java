package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PayOrdersExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PayOrdersExample() {
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

        public Criteria andSignEqualTo(String value) {
            addCriterion("sign =", value, "sign");
            return (Criteria) this;
        }


        public Criteria andAppidIsNull() {
            addCriterion("appid is null");
            return (Criteria) this;
        }

        public Criteria andAppidIsNotNull() {
            addCriterion("appid is not null");
            return (Criteria) this;
        }

        public Criteria andAppidEqualTo(String value) {
            addCriterion("appid =", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotEqualTo(String value) {
            addCriterion("appid <>", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThan(String value) {
            addCriterion("appid >", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidGreaterThanOrEqualTo(String value) {
            addCriterion("appid >=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThan(String value) {
            addCriterion("appid <", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLessThanOrEqualTo(String value) {
            addCriterion("appid <=", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidLike(String value) {
            addCriterion("appid like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotLike(String value) {
            addCriterion("appid not like", value, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidIn(List<String> values) {
            addCriterion("appid in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotIn(List<String> values) {
            addCriterion("appid not in", values, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidBetween(String value1, String value2) {
            addCriterion("appid between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andAppidNotBetween(String value1, String value2) {
            addCriterion("appid not between", value1, value2, "appid");
            return (Criteria) this;
        }

        public Criteria andMch_idIsNull() {
            addCriterion("mch_id is null");
            return (Criteria) this;
        }

        public Criteria andMch_idIsNotNull() {
            addCriterion("mch_id is not null");
            return (Criteria) this;
        }

        public Criteria andMch_idEqualTo(Integer value) {
            addCriterion("mch_id =", value, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idNotEqualTo(Integer value) {
            addCriterion("mch_id <>", value, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idGreaterThan(Integer value) {
            addCriterion("mch_id >", value, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("mch_id >=", value, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idLessThan(Integer value) {
            addCriterion("mch_id <", value, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idLessThanOrEqualTo(Integer value) {
            addCriterion("mch_id <=", value, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idIn(List<Integer> values) {
            addCriterion("mch_id in", values, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idNotIn(List<Integer> values) {
            addCriterion("mch_id not in", values, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idBetween(Integer value1, Integer value2) {
            addCriterion("mch_id between", value1, value2, "mch_id");
            return (Criteria) this;
        }

        public Criteria andMch_idNotBetween(Integer value1, Integer value2) {
            addCriterion("mch_id not between", value1, value2, "mch_id");
            return (Criteria) this;
        }

        public Criteria andNonce_strIsNull() {
            addCriterion("nonce_str is null");
            return (Criteria) this;
        }

        public Criteria andNonce_strIsNotNull() {
            addCriterion("nonce_str is not null");
            return (Criteria) this;
        }

        public Criteria andNonce_strEqualTo(String value) {
            addCriterion("nonce_str =", value, "nonce_str");
            return (Criteria) this;
        }

        public Criteria andNonce_strNotEqualTo(String value) {
            addCriterion("nonce_str <>", value, "nonce_str");
            return (Criteria) this;
        }

        public Criteria andNonce_strGreaterThan(String value) {
            addCriterion("nonce_str >", value, "nonce_str");
            return (Criteria) this;
        }

        public Criteria andNonce_strGreaterThanOrEqualTo(String value) {
            addCriterion("nonce_str >=", value, "nonce_str");
            return (Criteria) this;
        }

        public Criteria andNonce_strLessThan(String value) {
            addCriterion("nonce_str <", value, "nonce_str");
            return (Criteria) this;
        }

        public Criteria andNonce_strLessThanOrEqualTo(String value) {
            addCriterion("nonce_str <=", value, "nonce_str");
            return (Criteria) this;
        }

        public Criteria andNonce_strLike(String value) {
            addCriterion("nonce_str like", value, "nonce_str");
            return (Criteria) this;
        }

        public Criteria andNonce_strNotLike(String value) {
            addCriterion("nonce_str not like", value, "nonce_str");
            return (Criteria) this;
        }

        public Criteria andNonce_strIn(List<String> values) {
            addCriterion("nonce_str in", values, "nonce_str");
            return (Criteria) this;
        }

        public Criteria andNonce_strNotIn(List<String> values) {
            addCriterion("nonce_str not in", values, "nonce_str");
            return (Criteria) this;
        }

        public Criteria andNonce_strBetween(String value1, String value2) {
            addCriterion("nonce_str between", value1, value2, "nonce_str");
            return (Criteria) this;
        }

        public Criteria andNonce_strNotBetween(String value1, String value2) {
            addCriterion("nonce_str not between", value1, value2, "nonce_str");
            return (Criteria) this;
        }

        public Criteria andBodyIsNull() {
            addCriterion("body is null");
            return (Criteria) this;
        }

        public Criteria andBodyIsNotNull() {
            addCriterion("body is not null");
            return (Criteria) this;
        }

        public Criteria andBodyEqualTo(String value) {
            addCriterion("body =", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotEqualTo(String value) {
            addCriterion("body <>", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyGreaterThan(String value) {
            addCriterion("body >", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyGreaterThanOrEqualTo(String value) {
            addCriterion("body >=", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyLessThan(String value) {
            addCriterion("body <", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyLessThanOrEqualTo(String value) {
            addCriterion("body <=", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyLike(String value) {
            addCriterion("body like", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotLike(String value) {
            addCriterion("body not like", value, "body");
            return (Criteria) this;
        }

        public Criteria andBodyIn(List<String> values) {
            addCriterion("body in", values, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotIn(List<String> values) {
            addCriterion("body not in", values, "body");
            return (Criteria) this;
        }

        public Criteria andBodyBetween(String value1, String value2) {
            addCriterion("body between", value1, value2, "body");
            return (Criteria) this;
        }

        public Criteria andBodyNotBetween(String value1, String value2) {
            addCriterion("body not between", value1, value2, "body");
            return (Criteria) this;
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

        public Criteria andTotal_feeIsNull() {
            addCriterion("total_fee is null");
            return (Criteria) this;
        }

        public Criteria andTotal_feeIsNotNull() {
            addCriterion("total_fee is not null");
            return (Criteria) this;
        }

        public Criteria andTotal_feeEqualTo(Integer value) {
            addCriterion("total_fee =", value, "total_fee");
            return (Criteria) this;
        }

        public Criteria andTotal_feeNotEqualTo(Integer value) {
            addCriterion("total_fee <>", value, "total_fee");
            return (Criteria) this;
        }

        public Criteria andTotal_feeGreaterThan(Integer value) {
            addCriterion("total_fee >", value, "total_fee");
            return (Criteria) this;
        }

        public Criteria andTotal_feeGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_fee >=", value, "total_fee");
            return (Criteria) this;
        }

        public Criteria andTotal_feeLessThan(Integer value) {
            addCriterion("total_fee <", value, "total_fee");
            return (Criteria) this;
        }

        public Criteria andTotal_feeLessThanOrEqualTo(Integer value) {
            addCriterion("total_fee <=", value, "total_fee");
            return (Criteria) this;
        }

        public Criteria andTotal_feeIn(List<Integer> values) {
            addCriterion("total_fee in", values, "total_fee");
            return (Criteria) this;
        }

        public Criteria andTotal_feeNotIn(List<Integer> values) {
            addCriterion("total_fee not in", values, "total_fee");
            return (Criteria) this;
        }

        public Criteria andTotal_feeBetween(Integer value1, Integer value2) {
            addCriterion("total_fee between", value1, value2, "total_fee");
            return (Criteria) this;
        }

        public Criteria andTotal_feeNotBetween(Integer value1, Integer value2) {
            addCriterion("total_fee not between", value1, value2, "total_fee");
            return (Criteria) this;
        }

        public Criteria andSpbill_create_ipIsNull() {
            addCriterion("spbill_create_ip is null");
            return (Criteria) this;
        }

        public Criteria andSpbill_create_ipIsNotNull() {
            addCriterion("spbill_create_ip is not null");
            return (Criteria) this;
        }

        public Criteria andSpbill_create_ipEqualTo(String value) {
            addCriterion("spbill_create_ip =", value, "spbill_create_ip");
            return (Criteria) this;
        }

        public Criteria andSpbill_create_ipNotEqualTo(String value) {
            addCriterion("spbill_create_ip <>", value, "spbill_create_ip");
            return (Criteria) this;
        }

        public Criteria andSpbill_create_ipGreaterThan(String value) {
            addCriterion("spbill_create_ip >", value, "spbill_create_ip");
            return (Criteria) this;
        }

        public Criteria andSpbill_create_ipGreaterThanOrEqualTo(String value) {
            addCriterion("spbill_create_ip >=", value, "spbill_create_ip");
            return (Criteria) this;
        }

        public Criteria andSpbill_create_ipLessThan(String value) {
            addCriterion("spbill_create_ip <", value, "spbill_create_ip");
            return (Criteria) this;
        }

        public Criteria andSpbill_create_ipLessThanOrEqualTo(String value) {
            addCriterion("spbill_create_ip <=", value, "spbill_create_ip");
            return (Criteria) this;
        }

        public Criteria andSpbill_create_ipLike(String value) {
            addCriterion("spbill_create_ip like", value, "spbill_create_ip");
            return (Criteria) this;
        }

        public Criteria andSpbill_create_ipNotLike(String value) {
            addCriterion("spbill_create_ip not like", value, "spbill_create_ip");
            return (Criteria) this;
        }

        public Criteria andSpbill_create_ipIn(List<String> values) {
            addCriterion("spbill_create_ip in", values, "spbill_create_ip");
            return (Criteria) this;
        }

        public Criteria andSpbill_create_ipNotIn(List<String> values) {
            addCriterion("spbill_create_ip not in", values, "spbill_create_ip");
            return (Criteria) this;
        }

        public Criteria andSpbill_create_ipBetween(String value1, String value2) {
            addCriterion("spbill_create_ip between", value1, value2, "spbill_create_ip");
            return (Criteria) this;
        }

        public Criteria andSpbill_create_ipNotBetween(String value1, String value2) {
            addCriterion("spbill_create_ip not between", value1, value2, "spbill_create_ip");
            return (Criteria) this;
        }

        public Criteria andNotify_urlIsNull() {
            addCriterion("notify_url is null");
            return (Criteria) this;
        }

        public Criteria andNotify_urlIsNotNull() {
            addCriterion("notify_url is not null");
            return (Criteria) this;
        }

        public Criteria andNotify_urlEqualTo(String value) {
            addCriterion("notify_url =", value, "notify_url");
            return (Criteria) this;
        }

        public Criteria andNotify_urlNotEqualTo(String value) {
            addCriterion("notify_url <>", value, "notify_url");
            return (Criteria) this;
        }

        public Criteria andNotify_urlGreaterThan(String value) {
            addCriterion("notify_url >", value, "notify_url");
            return (Criteria) this;
        }

        public Criteria andNotify_urlGreaterThanOrEqualTo(String value) {
            addCriterion("notify_url >=", value, "notify_url");
            return (Criteria) this;
        }

        public Criteria andNotify_urlLessThan(String value) {
            addCriterion("notify_url <", value, "notify_url");
            return (Criteria) this;
        }

        public Criteria andNotify_urlLessThanOrEqualTo(String value) {
            addCriterion("notify_url <=", value, "notify_url");
            return (Criteria) this;
        }

        public Criteria andNotify_urlLike(String value) {
            addCriterion("notify_url like", value, "notify_url");
            return (Criteria) this;
        }

        public Criteria andNotify_urlNotLike(String value) {
            addCriterion("notify_url not like", value, "notify_url");
            return (Criteria) this;
        }

        public Criteria andNotify_urlIn(List<String> values) {
            addCriterion("notify_url in", values, "notify_url");
            return (Criteria) this;
        }

        public Criteria andNotify_urlNotIn(List<String> values) {
            addCriterion("notify_url not in", values, "notify_url");
            return (Criteria) this;
        }

        public Criteria andNotify_urlBetween(String value1, String value2) {
            addCriterion("notify_url between", value1, value2, "notify_url");
            return (Criteria) this;
        }

        public Criteria andNotify_urlNotBetween(String value1, String value2) {
            addCriterion("notify_url not between", value1, value2, "notify_url");
            return (Criteria) this;
        }

        public Criteria andTrade_typeIsNull() {
            addCriterion("trade_type is null");
            return (Criteria) this;
        }

        public Criteria andTrade_typeIsNotNull() {
            addCriterion("trade_type is not null");
            return (Criteria) this;
        }

        public Criteria andTrade_typeEqualTo(String value) {
            addCriterion("trade_type =", value, "trade_type");
            return (Criteria) this;
        }

        public Criteria andTrade_typeNotEqualTo(String value) {
            addCriterion("trade_type <>", value, "trade_type");
            return (Criteria) this;
        }

        public Criteria andTrade_typeGreaterThan(String value) {
            addCriterion("trade_type >", value, "trade_type");
            return (Criteria) this;
        }

        public Criteria andTrade_typeGreaterThanOrEqualTo(String value) {
            addCriterion("trade_type >=", value, "trade_type");
            return (Criteria) this;
        }

        public Criteria andTrade_typeLessThan(String value) {
            addCriterion("trade_type <", value, "trade_type");
            return (Criteria) this;
        }

        public Criteria andTrade_typeLessThanOrEqualTo(String value) {
            addCriterion("trade_type <=", value, "trade_type");
            return (Criteria) this;
        }

        public Criteria andTrade_typeLike(String value) {
            addCriterion("trade_type like", value, "trade_type");
            return (Criteria) this;
        }

        public Criteria andTrade_typeNotLike(String value) {
            addCriterion("trade_type not like", value, "trade_type");
            return (Criteria) this;
        }

        public Criteria andTrade_typeIn(List<String> values) {
            addCriterion("trade_type in", values, "trade_type");
            return (Criteria) this;
        }

        public Criteria andTrade_typeNotIn(List<String> values) {
            addCriterion("trade_type not in", values, "trade_type");
            return (Criteria) this;
        }

        public Criteria andTrade_typeBetween(String value1, String value2) {
            addCriterion("trade_type between", value1, value2, "trade_type");
            return (Criteria) this;
        }

        public Criteria andTrade_typeNotBetween(String value1, String value2) {
            addCriterion("trade_type not between", value1, value2, "trade_type");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNull() {
            addCriterion("openid is null");
            return (Criteria) this;
        }

        public Criteria andOpenidIsNotNull() {
            addCriterion("openid is not null");
            return (Criteria) this;
        }

        public Criteria andOpenidEqualTo(String value) {
            addCriterion("openid =", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotEqualTo(String value) {
            addCriterion("openid <>", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThan(String value) {
            addCriterion("openid >", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("openid >=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThan(String value) {
            addCriterion("openid <", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLessThanOrEqualTo(String value) {
            addCriterion("openid <=", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidLike(String value) {
            addCriterion("openid like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotLike(String value) {
            addCriterion("openid not like", value, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidIn(List<String> values) {
            addCriterion("openid in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotIn(List<String> values) {
            addCriterion("openid not in", values, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidBetween(String value1, String value2) {
            addCriterion("openid between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andOpenidNotBetween(String value1, String value2) {
            addCriterion("openid not between", value1, value2, "openid");
            return (Criteria) this;
        }

        public Criteria andPrepay_idIsNull() {
            addCriterion("prepay_id is null");
            return (Criteria) this;
        }

        public Criteria andPrepay_idIsNotNull() {
            addCriterion("prepay_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrepay_idEqualTo(String value) {
            addCriterion("prepay_id =", value, "prepay_id");
            return (Criteria) this;
        }

        public Criteria andPrepay_idNotEqualTo(String value) {
            addCriterion("prepay_id <>", value, "prepay_id");
            return (Criteria) this;
        }

        public Criteria andPrepay_idGreaterThan(String value) {
            addCriterion("prepay_id >", value, "prepay_id");
            return (Criteria) this;
        }

        public Criteria andPrepay_idGreaterThanOrEqualTo(String value) {
            addCriterion("prepay_id >=", value, "prepay_id");
            return (Criteria) this;
        }

        public Criteria andPrepay_idLessThan(String value) {
            addCriterion("prepay_id <", value, "prepay_id");
            return (Criteria) this;
        }

        public Criteria andPrepay_idLessThanOrEqualTo(String value) {
            addCriterion("prepay_id <=", value, "prepay_id");
            return (Criteria) this;
        }

        public Criteria andPrepay_idLike(String value) {
            addCriterion("prepay_id like", value, "prepay_id");
            return (Criteria) this;
        }

        public Criteria andPrepay_idNotLike(String value) {
            addCriterion("prepay_id not like", value, "prepay_id");
            return (Criteria) this;
        }

        public Criteria andPrepay_idIn(List<String> values) {
            addCriterion("prepay_id in", values, "prepay_id");
            return (Criteria) this;
        }

        public Criteria andPrepay_idNotIn(List<String> values) {
            addCriterion("prepay_id not in", values, "prepay_id");
            return (Criteria) this;
        }

        public Criteria andPrepay_idBetween(String value1, String value2) {
            addCriterion("prepay_id between", value1, value2, "prepay_id");
            return (Criteria) this;
        }

        public Criteria andPrepay_idNotBetween(String value1, String value2) {
            addCriterion("prepay_id not between", value1, value2, "prepay_id");
            return (Criteria) this;
        }

        public Criteria andUtokenIsNull() {
            addCriterion("utoken is null");
            return (Criteria) this;
        }

        public Criteria andUtokenIsNotNull() {
            addCriterion("utoken is not null");
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
package model;

import model.divide.BaseDivideModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeviceBuyRecordExample extends BaseDivideModel {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeviceBuyRecordExample() {
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

    protected abstract static class GeneratedCriteria extends model.divide.GeneratedCriteria {


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

        public Criteria andPartner_trade_noIsNull() {
            addCriterion("partner_trade_no is null");
            return (Criteria) this;
        }

        public Criteria andPartner_trade_noIsNotNull() {
            addCriterion("partner_trade_no is not null");
            return (Criteria) this;
        }

        public Criteria andPartner_trade_noEqualTo(String value) {
            addCriterion("partner_trade_no =", value, "partner_trade_no");
            return (Criteria) this;
        }

        public Criteria andPartner_trade_noNotEqualTo(String value) {
            addCriterion("partner_trade_no <>", value, "partner_trade_no");
            return (Criteria) this;
        }

        public Criteria andPartner_trade_noGreaterThan(String value) {
            addCriterion("partner_trade_no >", value, "partner_trade_no");
            return (Criteria) this;
        }

        public Criteria andPartner_trade_noGreaterThanOrEqualTo(String value) {
            addCriterion("partner_trade_no >=", value, "partner_trade_no");
            return (Criteria) this;
        }

        public Criteria andPartner_trade_noLessThan(String value) {
            addCriterion("partner_trade_no <", value, "partner_trade_no");
            return (Criteria) this;
        }

        public Criteria andPartner_trade_noLessThanOrEqualTo(String value) {
            addCriterion("partner_trade_no <=", value, "partner_trade_no");
            return (Criteria) this;
        }

        public Criteria andPartner_trade_noLike(String value) {
            addCriterion("partner_trade_no like", value, "partner_trade_no");
            return (Criteria) this;
        }

        public Criteria andPartner_trade_noNotLike(String value) {
            addCriterion("partner_trade_no not like", value, "partner_trade_no");
            return (Criteria) this;
        }

        public Criteria andPartner_trade_noIn(List<String> values) {
            addCriterion("partner_trade_no in", values, "partner_trade_no");
            return (Criteria) this;
        }

        public Criteria andPartner_trade_noNotIn(List<String> values) {
            addCriterion("partner_trade_no not in", values, "partner_trade_no");
            return (Criteria) this;
        }

        public Criteria andPartner_trade_noBetween(String value1, String value2) {
            addCriterion("partner_trade_no between", value1, value2, "partner_trade_no");
            return (Criteria) this;
        }

        public Criteria andPartner_trade_noNotBetween(String value1, String value2) {
            addCriterion("partner_trade_no not between", value1, value2, "partner_trade_no");
            return (Criteria) this;
        }

        public Criteria andDevice_typeIsNull() {
            addCriterion("device_type is null");
            return (Criteria) this;
        }

        public Criteria andDevice_typeIsNotNull() {
            addCriterion("device_type is not null");
            return (Criteria) this;
        }

        public Criteria andDevice_typeEqualTo(String value) {
            addCriterion("device_type =", value, "device_type");
            return (Criteria) this;
        }

        public Criteria andDevice_typeNotEqualTo(String value) {
            addCriterion("device_type <>", value, "device_type");
            return (Criteria) this;
        }

        public Criteria andDevice_typeGreaterThan(String value) {
            addCriterion("device_type >", value, "device_type");
            return (Criteria) this;
        }

        public Criteria andDevice_typeGreaterThanOrEqualTo(String value) {
            addCriterion("device_type >=", value, "device_type");
            return (Criteria) this;
        }

        public Criteria andDevice_typeLessThan(String value) {
            addCriterion("device_type <", value, "device_type");
            return (Criteria) this;
        }

        public Criteria andDevice_typeLessThanOrEqualTo(String value) {
            addCriterion("device_type <=", value, "device_type");
            return (Criteria) this;
        }

        public Criteria andDevice_typeLike(String value) {
            addCriterion("device_type like", value, "device_type");
            return (Criteria) this;
        }

        public Criteria andDevice_typeNotLike(String value) {
            addCriterion("device_type not like", value, "device_type");
            return (Criteria) this;
        }

        public Criteria andDevice_typeIn(List<String> values) {
            addCriterion("device_type in", values, "device_type");
            return (Criteria) this;
        }

        public Criteria andDevice_typeNotIn(List<String> values) {
            addCriterion("device_type not in", values, "device_type");
            return (Criteria) this;
        }

        public Criteria andDevice_typeBetween(String value1, String value2) {
            addCriterion("device_type between", value1, value2, "device_type");
            return (Criteria) this;
        }

        public Criteria andDevice_typeNotBetween(String value1, String value2) {
            addCriterion("device_type not between", value1, value2, "device_type");
            return (Criteria) this;
        }

        public Criteria andAmountIsNull() {
            addCriterion("amount is null");
            return (Criteria) this;
        }

        public Criteria andAmountIsNotNull() {
            addCriterion("amount is not null");
            return (Criteria) this;
        }

        public Criteria andAmountEqualTo(Integer value) {
            addCriterion("amount =", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotEqualTo(Integer value) {
            addCriterion("amount <>", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThan(Integer value) {
            addCriterion("amount >", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("amount >=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThan(Integer value) {
            addCriterion("amount <", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountLessThanOrEqualTo(Integer value) {
            addCriterion("amount <=", value, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountIn(List<Integer> values) {
            addCriterion("amount in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotIn(List<Integer> values) {
            addCriterion("amount not in", values, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountBetween(Integer value1, Integer value2) {
            addCriterion("amount between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("amount not between", value1, value2, "amount");
            return (Criteria) this;
        }

        public Criteria andRule_idIsNull() {
            addCriterion("rule_id is null");
            return (Criteria) this;
        }

        public Criteria andRule_idIsNotNull() {
            addCriterion("rule_id is not null");
            return (Criteria) this;
        }

        public Criteria andRule_idEqualTo(Integer value) {
            addCriterion("rule_id =", value, "rule_id");
            return (Criteria) this;
        }

        public Criteria andRule_idNotEqualTo(Integer value) {
            addCriterion("rule_id <>", value, "rule_id");
            return (Criteria) this;
        }

        public Criteria andRule_idGreaterThan(Integer value) {
            addCriterion("rule_id >", value, "rule_id");
            return (Criteria) this;
        }

        public Criteria andRule_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("rule_id >=", value, "rule_id");
            return (Criteria) this;
        }

        public Criteria andRule_idLessThan(Integer value) {
            addCriterion("rule_id <", value, "rule_id");
            return (Criteria) this;
        }

        public Criteria andRule_idLessThanOrEqualTo(Integer value) {
            addCriterion("rule_id <=", value, "rule_id");
            return (Criteria) this;
        }

        public Criteria andRule_idIn(List<Integer> values) {
            addCriterion("rule_id in", values, "rule_id");
            return (Criteria) this;
        }

        public Criteria andRule_idNotIn(List<Integer> values) {
            addCriterion("rule_id not in", values, "rule_id");
            return (Criteria) this;
        }

        public Criteria andRule_idBetween(Integer value1, Integer value2) {
            addCriterion("rule_id between", value1, value2, "rule_id");
            return (Criteria) this;
        }

        public Criteria andRule_idNotBetween(Integer value1, Integer value2) {
            addCriterion("rule_id not between", value1, value2, "rule_id");
            return (Criteria) this;
        }

        public Criteria andUnit_priceIsNull() {
            addCriterion("unit_price is null");
            return (Criteria) this;
        }

        public Criteria andUnit_priceIsNotNull() {
            addCriterion("unit_price is not null");
            return (Criteria) this;
        }

        public Criteria andUnit_priceEqualTo(Integer value) {
            addCriterion("unit_price =", value, "unit_price");
            return (Criteria) this;
        }

        public Criteria andUnit_priceNotEqualTo(Integer value) {
            addCriterion("unit_price <>", value, "unit_price");
            return (Criteria) this;
        }

        public Criteria andUnit_priceGreaterThan(Integer value) {
            addCriterion("unit_price >", value, "unit_price");
            return (Criteria) this;
        }

        public Criteria andUnit_priceGreaterThanOrEqualTo(Integer value) {
            addCriterion("unit_price >=", value, "unit_price");
            return (Criteria) this;
        }

        public Criteria andUnit_priceLessThan(Integer value) {
            addCriterion("unit_price <", value, "unit_price");
            return (Criteria) this;
        }

        public Criteria andUnit_priceLessThanOrEqualTo(Integer value) {
            addCriterion("unit_price <=", value, "unit_price");
            return (Criteria) this;
        }

        public Criteria andUnit_priceIn(List<Integer> values) {
            addCriterion("unit_price in", values, "unit_price");
            return (Criteria) this;
        }

        public Criteria andUnit_priceNotIn(List<Integer> values) {
            addCriterion("unit_price not in", values, "unit_price");
            return (Criteria) this;
        }

        public Criteria andUnit_priceBetween(Integer value1, Integer value2) {
            addCriterion("unit_price between", value1, value2, "unit_price");
            return (Criteria) this;
        }

        public Criteria andUnit_priceNotBetween(Integer value1, Integer value2) {
            addCriterion("unit_price not between", value1, value2, "unit_price");
            return (Criteria) this;
        }

        public Criteria andOriginal_priceIsNull() {
            addCriterion("original_price is null");
            return (Criteria) this;
        }

        public Criteria andOriginal_priceIsNotNull() {
            addCriterion("original_price is not null");
            return (Criteria) this;
        }

        public Criteria andOriginal_priceEqualTo(Integer value) {
            addCriterion("original_price =", value, "original_price");
            return (Criteria) this;
        }

        public Criteria andOriginal_priceNotEqualTo(Integer value) {
            addCriterion("original_price <>", value, "original_price");
            return (Criteria) this;
        }

        public Criteria andOriginal_priceGreaterThan(Integer value) {
            addCriterion("original_price >", value, "original_price");
            return (Criteria) this;
        }

        public Criteria andOriginal_priceGreaterThanOrEqualTo(Integer value) {
            addCriterion("original_price >=", value, "original_price");
            return (Criteria) this;
        }

        public Criteria andOriginal_priceLessThan(Integer value) {
            addCriterion("original_price <", value, "original_price");
            return (Criteria) this;
        }

        public Criteria andOriginal_priceLessThanOrEqualTo(Integer value) {
            addCriterion("original_price <=", value, "original_price");
            return (Criteria) this;
        }

        public Criteria andOriginal_priceIn(List<Integer> values) {
            addCriterion("original_price in", values, "original_price");
            return (Criteria) this;
        }

        public Criteria andOriginal_priceNotIn(List<Integer> values) {
            addCriterion("original_price not in", values, "original_price");
            return (Criteria) this;
        }

        public Criteria andOriginal_priceBetween(Integer value1, Integer value2) {
            addCriterion("original_price between", value1, value2, "original_price");
            return (Criteria) this;
        }

        public Criteria andOriginal_priceNotBetween(Integer value1, Integer value2) {
            addCriterion("original_price not between", value1, value2, "original_price");
            return (Criteria) this;
        }

        public Criteria andPolicy_reductIsNull() {
            addCriterion("policy_reduct is null");
            return (Criteria) this;
        }

        public Criteria andPolicy_reductIsNotNull() {
            addCriterion("policy_reduct is not null");
            return (Criteria) this;
        }

        public Criteria andPolicy_reductEqualTo(Integer value) {
            addCriterion("policy_reduct =", value, "policy_reduct");
            return (Criteria) this;
        }

        public Criteria andPolicy_reductNotEqualTo(Integer value) {
            addCriterion("policy_reduct <>", value, "policy_reduct");
            return (Criteria) this;
        }

        public Criteria andPolicy_reductGreaterThan(Integer value) {
            addCriterion("policy_reduct >", value, "policy_reduct");
            return (Criteria) this;
        }

        public Criteria andPolicy_reductGreaterThanOrEqualTo(Integer value) {
            addCriterion("policy_reduct >=", value, "policy_reduct");
            return (Criteria) this;
        }

        public Criteria andPolicy_reductLessThan(Integer value) {
            addCriterion("policy_reduct <", value, "policy_reduct");
            return (Criteria) this;
        }

        public Criteria andPolicy_reductLessThanOrEqualTo(Integer value) {
            addCriterion("policy_reduct <=", value, "policy_reduct");
            return (Criteria) this;
        }

        public Criteria andPolicy_reductIn(List<Integer> values) {
            addCriterion("policy_reduct in", values, "policy_reduct");
            return (Criteria) this;
        }

        public Criteria andPolicy_reductNotIn(List<Integer> values) {
            addCriterion("policy_reduct not in", values, "policy_reduct");
            return (Criteria) this;
        }

        public Criteria andPolicy_reductBetween(Integer value1, Integer value2) {
            addCriterion("policy_reduct between", value1, value2, "policy_reduct");
            return (Criteria) this;
        }

        public Criteria andPolicy_reductNotBetween(Integer value1, Integer value2) {
            addCriterion("policy_reduct not between", value1, value2, "policy_reduct");
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

        public Criteria andType_secIsNull() {
            addCriterion("type_sec is null");
            return (Criteria) this;
        }

        public Criteria andType_secIsNotNull() {
            addCriterion("type_sec is not null");
            return (Criteria) this;
        }

        public Criteria andType_secEqualTo(String value) {
            addCriterion("type_sec =", value, "type_sec");
            return (Criteria) this;
        }

        public Criteria andType_secNotEqualTo(String value) {
            addCriterion("type_sec <>", value, "type_sec");
            return (Criteria) this;
        }

        public Criteria andType_secGreaterThan(String value) {
            addCriterion("type_sec >", value, "type_sec");
            return (Criteria) this;
        }

        public Criteria andType_secGreaterThanOrEqualTo(String value) {
            addCriterion("type_sec >=", value, "type_sec");
            return (Criteria) this;
        }

        public Criteria andType_secLessThan(String value) {
            addCriterion("type_sec <", value, "type_sec");
            return (Criteria) this;
        }

        public Criteria andType_secLessThanOrEqualTo(String value) {
            addCriterion("type_sec <=", value, "type_sec");
            return (Criteria) this;
        }

        public Criteria andType_secLike(String value) {
            addCriterion("type_sec like", value, "type_sec");
            return (Criteria) this;
        }

        public Criteria andType_secNotLike(String value) {
            addCriterion("type_sec not like", value, "type_sec");
            return (Criteria) this;
        }

        public Criteria andType_secIn(List<String> values) {
            addCriterion("type_sec in", values, "type_sec");
            return (Criteria) this;
        }

        public Criteria andType_secNotIn(List<String> values) {
            addCriterion("type_sec not in", values, "type_sec");
            return (Criteria) this;
        }

        public Criteria andType_secBetween(String value1, String value2) {
            addCriterion("type_sec between", value1, value2, "type_sec");
            return (Criteria) this;
        }

        public Criteria andType_secNotBetween(String value1, String value2) {
            addCriterion("type_sec not between", value1, value2, "type_sec");
            return (Criteria) this;
        }

        public Criteria andCommentIsNull() {
            addCriterion("comment is null");
            return (Criteria) this;
        }

        public Criteria andCommentIsNotNull() {
            addCriterion("comment is not null");
            return (Criteria) this;
        }

        public Criteria andCommentEqualTo(String value) {
            addCriterion("comment =", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotEqualTo(String value) {
            addCriterion("comment <>", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThan(String value) {
            addCriterion("comment >", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentGreaterThanOrEqualTo(String value) {
            addCriterion("comment >=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThan(String value) {
            addCriterion("comment <", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLessThanOrEqualTo(String value) {
            addCriterion("comment <=", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentLike(String value) {
            addCriterion("comment like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotLike(String value) {
            addCriterion("comment not like", value, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentIn(List<String> values) {
            addCriterion("comment in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotIn(List<String> values) {
            addCriterion("comment not in", values, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentBetween(String value1, String value2) {
            addCriterion("comment between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andCommentNotBetween(String value1, String value2) {
            addCriterion("comment not between", value1, value2, "comment");
            return (Criteria) this;
        }

        public Criteria andLogistic_noIsNull() {
            addCriterion("logistic_no is null");
            return (Criteria) this;
        }

        public Criteria andLogistic_noIsNotNull() {
            addCriterion("logistic_no is not null");
            return (Criteria) this;
        }

        public Criteria andLogistic_noEqualTo(String value) {
            addCriterion("logistic_no =", value, "logistic_no");
            return (Criteria) this;
        }

        public Criteria andLogistic_noNotEqualTo(String value) {
            addCriterion("logistic_no <>", value, "logistic_no");
            return (Criteria) this;
        }

        public Criteria andLogistic_noGreaterThan(String value) {
            addCriterion("logistic_no >", value, "logistic_no");
            return (Criteria) this;
        }

        public Criteria andLogistic_noGreaterThanOrEqualTo(String value) {
            addCriterion("logistic_no >=", value, "logistic_no");
            return (Criteria) this;
        }

        public Criteria andLogistic_noLessThan(String value) {
            addCriterion("logistic_no <", value, "logistic_no");
            return (Criteria) this;
        }

        public Criteria andLogistic_noLessThanOrEqualTo(String value) {
            addCriterion("logistic_no <=", value, "logistic_no");
            return (Criteria) this;
        }

        public Criteria andLogistic_noLike(String value) {
            addCriterion("logistic_no like", value, "logistic_no");
            return (Criteria) this;
        }

        public Criteria andLogistic_noNotLike(String value) {
            addCriterion("logistic_no not like", value, "logistic_no");
            return (Criteria) this;
        }

        public Criteria andLogistic_noIn(List<String> values) {
            addCriterion("logistic_no in", values, "logistic_no");
            return (Criteria) this;
        }

        public Criteria andLogistic_noNotIn(List<String> values) {
            addCriterion("logistic_no not in", values, "logistic_no");
            return (Criteria) this;
        }

        public Criteria andLogistic_noBetween(String value1, String value2) {
            addCriterion("logistic_no between", value1, value2, "logistic_no");
            return (Criteria) this;
        }

        public Criteria andLogistic_noNotBetween(String value1, String value2) {
            addCriterion("logistic_no not between", value1, value2, "logistic_no");
            return (Criteria) this;
        }

        public Criteria andConsigneeIsNull() {
            addCriterion("consignee is null");
            return (Criteria) this;
        }

        public Criteria andConsigneeIsNotNull() {
            addCriterion("consignee is not null");
            return (Criteria) this;
        }

        public Criteria andConsigneeEqualTo(String value) {
            addCriterion("consignee =", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotEqualTo(String value) {
            addCriterion("consignee <>", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeGreaterThan(String value) {
            addCriterion("consignee >", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeGreaterThanOrEqualTo(String value) {
            addCriterion("consignee >=", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLessThan(String value) {
            addCriterion("consignee <", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLessThanOrEqualTo(String value) {
            addCriterion("consignee <=", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeLike(String value) {
            addCriterion("consignee like", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotLike(String value) {
            addCriterion("consignee not like", value, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeIn(List<String> values) {
            addCriterion("consignee in", values, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotIn(List<String> values) {
            addCriterion("consignee not in", values, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeBetween(String value1, String value2) {
            addCriterion("consignee between", value1, value2, "consignee");
            return (Criteria) this;
        }

        public Criteria andConsigneeNotBetween(String value1, String value2) {
            addCriterion("consignee not between", value1, value2, "consignee");
            return (Criteria) this;
        }

        public Criteria andContact_wayIsNull() {
            addCriterion("contact_way is null");
            return (Criteria) this;
        }

        public Criteria andContact_wayIsNotNull() {
            addCriterion("contact_way is not null");
            return (Criteria) this;
        }

        public Criteria andContact_wayEqualTo(String value) {
            addCriterion("contact_way =", value, "contact_way");
            return (Criteria) this;
        }

        public Criteria andContact_wayNotEqualTo(String value) {
            addCriterion("contact_way <>", value, "contact_way");
            return (Criteria) this;
        }

        public Criteria andContact_wayGreaterThan(String value) {
            addCriterion("contact_way >", value, "contact_way");
            return (Criteria) this;
        }

        public Criteria andContact_wayGreaterThanOrEqualTo(String value) {
            addCriterion("contact_way >=", value, "contact_way");
            return (Criteria) this;
        }

        public Criteria andContact_wayLessThan(String value) {
            addCriterion("contact_way <", value, "contact_way");
            return (Criteria) this;
        }

        public Criteria andContact_wayLessThanOrEqualTo(String value) {
            addCriterion("contact_way <=", value, "contact_way");
            return (Criteria) this;
        }

        public Criteria andContact_wayLike(String value) {
            addCriterion("contact_way like", value, "contact_way");
            return (Criteria) this;
        }

        public Criteria andContact_wayNotLike(String value) {
            addCriterion("contact_way not like", value, "contact_way");
            return (Criteria) this;
        }

        public Criteria andContact_wayIn(List<String> values) {
            addCriterion("contact_way in", values, "contact_way");
            return (Criteria) this;
        }

        public Criteria andContact_wayNotIn(List<String> values) {
            addCriterion("contact_way not in", values, "contact_way");
            return (Criteria) this;
        }

        public Criteria andContact_wayBetween(String value1, String value2) {
            addCriterion("contact_way between", value1, value2, "contact_way");
            return (Criteria) this;
        }

        public Criteria andContact_wayNotBetween(String value1, String value2) {
            addCriterion("contact_way not between", value1, value2, "contact_way");
            return (Criteria) this;
        }

        public Criteria andDelivery_areaIsNull() {
            addCriterion("delivery_area is null");
            return (Criteria) this;
        }

        public Criteria andDelivery_areaIsNotNull() {
            addCriterion("delivery_area is not null");
            return (Criteria) this;
        }

        public Criteria andDelivery_areaEqualTo(String value) {
            addCriterion("delivery_area =", value, "delivery_area");
            return (Criteria) this;
        }

        public Criteria andDelivery_areaNotEqualTo(String value) {
            addCriterion("delivery_area <>", value, "delivery_area");
            return (Criteria) this;
        }

        public Criteria andDelivery_areaGreaterThan(String value) {
            addCriterion("delivery_area >", value, "delivery_area");
            return (Criteria) this;
        }

        public Criteria andDelivery_areaGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_area >=", value, "delivery_area");
            return (Criteria) this;
        }

        public Criteria andDelivery_areaLessThan(String value) {
            addCriterion("delivery_area <", value, "delivery_area");
            return (Criteria) this;
        }

        public Criteria andDelivery_areaLessThanOrEqualTo(String value) {
            addCriterion("delivery_area <=", value, "delivery_area");
            return (Criteria) this;
        }

        public Criteria andDelivery_areaLike(String value) {
            addCriterion("delivery_area like", value, "delivery_area");
            return (Criteria) this;
        }

        public Criteria andDelivery_areaNotLike(String value) {
            addCriterion("delivery_area not like", value, "delivery_area");
            return (Criteria) this;
        }

        public Criteria andDelivery_areaIn(List<String> values) {
            addCriterion("delivery_area in", values, "delivery_area");
            return (Criteria) this;
        }

        public Criteria andDelivery_areaNotIn(List<String> values) {
            addCriterion("delivery_area not in", values, "delivery_area");
            return (Criteria) this;
        }

        public Criteria andDelivery_areaBetween(String value1, String value2) {
            addCriterion("delivery_area between", value1, value2, "delivery_area");
            return (Criteria) this;
        }

        public Criteria andDelivery_areaNotBetween(String value1, String value2) {
            addCriterion("delivery_area not between", value1, value2, "delivery_area");
            return (Criteria) this;
        }

        public Criteria andDelivery_addressIsNull() {
            addCriterion("delivery_address is null");
            return (Criteria) this;
        }

        public Criteria andDelivery_addressIsNotNull() {
            addCriterion("delivery_address is not null");
            return (Criteria) this;
        }

        public Criteria andDelivery_addressEqualTo(String value) {
            addCriterion("delivery_address =", value, "delivery_address");
            return (Criteria) this;
        }

        public Criteria andDelivery_addressNotEqualTo(String value) {
            addCriterion("delivery_address <>", value, "delivery_address");
            return (Criteria) this;
        }

        public Criteria andDelivery_addressGreaterThan(String value) {
            addCriterion("delivery_address >", value, "delivery_address");
            return (Criteria) this;
        }

        public Criteria andDelivery_addressGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_address >=", value, "delivery_address");
            return (Criteria) this;
        }

        public Criteria andDelivery_addressLessThan(String value) {
            addCriterion("delivery_address <", value, "delivery_address");
            return (Criteria) this;
        }

        public Criteria andDelivery_addressLessThanOrEqualTo(String value) {
            addCriterion("delivery_address <=", value, "delivery_address");
            return (Criteria) this;
        }

        public Criteria andDelivery_addressLike(String value) {
            addCriterion("delivery_address like", value, "delivery_address");
            return (Criteria) this;
        }

        public Criteria andDelivery_addressNotLike(String value) {
            addCriterion("delivery_address not like", value, "delivery_address");
            return (Criteria) this;
        }

        public Criteria andDelivery_addressIn(List<String> values) {
            addCriterion("delivery_address in", values, "delivery_address");
            return (Criteria) this;
        }

        public Criteria andDelivery_addressNotIn(List<String> values) {
            addCriterion("delivery_address not in", values, "delivery_address");
            return (Criteria) this;
        }

        public Criteria andDelivery_addressBetween(String value1, String value2) {
            addCriterion("delivery_address between", value1, value2, "delivery_address");
            return (Criteria) this;
        }

        public Criteria andDelivery_addressNotBetween(String value1, String value2) {
            addCriterion("delivery_address not between", value1, value2, "delivery_address");
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

        public Criteria andReasonIsNull() {
            addCriterion("reason is null");
            return (Criteria) this;
        }

        public Criteria andReasonIsNotNull() {
            addCriterion("reason is not null");
            return (Criteria) this;
        }

        public Criteria andReasonEqualTo(String value) {
            addCriterion("reason =", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasontNotEqualTo(String value) {
            addCriterion("reason <>", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThan(String value) {
            addCriterion("reason >", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonGreaterThanOrEqualTo(String value) {
            addCriterion("reason >=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThan(String value) {
            addCriterion("reason <", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLessThanOrEqualTo(String value) {
            addCriterion("reason <=", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonLike(String value) {
            addCriterion("reason like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotLike(String value) {
            addCriterion("reason not like", value, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonIn(List<String> values) {
            addCriterion("reason in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotIn(List<String> values) {
            addCriterion("reason not in", values, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonBetween(String value1, String value2) {
            addCriterion("reason between", value1, value2, "reason");
            return (Criteria) this;
        }

        public Criteria andReasonNotBetween(String value1, String value2) {
            addCriterion("reason not between", value1, value2, "reason");
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
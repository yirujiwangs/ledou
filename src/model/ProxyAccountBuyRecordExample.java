package model;
import model.divide.BaseDivideModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class ProxyAccountBuyRecordExample extends BaseDivideModel{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProxyAccountBuyRecordExample() {
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

        public Criteria andNormal_num_payIsNull() {
            addCriterion("normal_num_pay is null");
            return (Criteria) this;
        }

        public Criteria andNormal_num_payIsNotNull() {
            addCriterion("normal_num_pay is not null");
            return (Criteria) this;
        }

        public Criteria andNormal_num_payEqualTo(Integer value) {
            addCriterion("normal_num_pay =", value, "normal_num_pay");
            return (Criteria) this;
        }

        public Criteria andNormal_num_payNotEqualTo(Integer value) {
            addCriterion("normal_num_pay <>", value, "normal_num_pay");
            return (Criteria) this;
        }

        public Criteria andNormal_num_payGreaterThan(Integer value) {
            addCriterion("normal_num_pay >", value, "normal_num_pay");
            return (Criteria) this;
        }

        public Criteria andNormal_num_payGreaterThanOrEqualTo(Integer value) {
            addCriterion("normal_num_pay >=", value, "normal_num_pay");
            return (Criteria) this;
        }

        public Criteria andNormal_num_payLessThan(Integer value) {
            addCriterion("normal_num_pay <", value, "normal_num_pay");
            return (Criteria) this;
        }

        public Criteria andNormal_num_payLessThanOrEqualTo(Integer value) {
            addCriterion("normal_num_pay <=", value, "normal_num_pay");
            return (Criteria) this;
        }

        public Criteria andNormal_num_payIn(List<Integer> values) {
            addCriterion("normal_num_pay in", values, "normal_num_pay");
            return (Criteria) this;
        }

        public Criteria andNormal_num_payNotIn(List<Integer> values) {
            addCriterion("normal_num_pay not in", values, "normal_num_pay");
            return (Criteria) this;
        }

        public Criteria andNormal_num_payBetween(Integer value1, Integer value2) {
            addCriterion("normal_num_pay between", value1, value2, "normal_num_pay");
            return (Criteria) this;
        }

        public Criteria andNormal_num_payNotBetween(Integer value1, Integer value2) {
            addCriterion("normal_num_pay not between", value1, value2, "normal_num_pay");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_payIsNull() {
            addCriterion("platform_num_pay is null");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_payIsNotNull() {
            addCriterion("platform_num_pay is not null");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_payEqualTo(Integer value) {
            addCriterion("platform_num_pay =", value, "platform_num_pay");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_payNotEqualTo(Integer value) {
            addCriterion("platform_num_pay <>", value, "platform_num_pay");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_payGreaterThan(Integer value) {
            addCriterion("platform_num_pay >", value, "platform_num_pay");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_payGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform_num_pay >=", value, "platform_num_pay");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_payLessThan(Integer value) {
            addCriterion("platform_num_pay <", value, "platform_num_pay");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_payLessThanOrEqualTo(Integer value) {
            addCriterion("platform_num_pay <=", value, "platform_num_pay");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_payIn(List<Integer> values) {
            addCriterion("platform_num_pay in", values, "platform_num_pay");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_payNotIn(List<Integer> values) {
            addCriterion("platform_num_pay not in", values, "platform_num_pay");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_payBetween(Integer value1, Integer value2) {
            addCriterion("platform_num_pay between", value1, value2, "platform_num_pay");
            return (Criteria) this;
        }

        public Criteria andPlatform_num_payNotBetween(Integer value1, Integer value2) {
            addCriterion("platform_num_pay not between", value1, value2, "platform_num_pay");
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

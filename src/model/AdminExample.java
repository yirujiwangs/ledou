package model;

import model.divide.BaseDivideModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AdminExample extends BaseDivideModel {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AdminExample() {
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

        public Criteria andAccountIsNull() {
            addCriterion("account is null");
            return (Criteria) this;
        }

        public Criteria andAccountIsNotNull() {
            addCriterion("account is not null");
            return (Criteria) this;
        }

        public Criteria andAccountEqualTo(String value) {
            addCriterion("account =", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotEqualTo(String value) {
            addCriterion("account <>", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThan(String value) {
            addCriterion("account >", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountGreaterThanOrEqualTo(String value) {
            addCriterion("account >=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThan(String value) {
            addCriterion("account <", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLessThanOrEqualTo(String value) {
            addCriterion("account <=", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountLike(String value) {
            addCriterion("account like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotLike(String value) {
            addCriterion("account not like", value, "account");
            return (Criteria) this;
        }

        public Criteria andAccountIn(List<String> values) {
            addCriterion("account in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotIn(List<String> values) {
            addCriterion("account not in", values, "account");
            return (Criteria) this;
        }

        public Criteria andAccountBetween(String value1, String value2) {
            addCriterion("account between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andAccountNotBetween(String value1, String value2) {
            addCriterion("account not between", value1, value2, "account");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNull() {
            addCriterion("password is null");
            return (Criteria) this;
        }

        public Criteria andPasswordIsNotNull() {
            addCriterion("password is not null");
            return (Criteria) this;
        }

        public Criteria andPasswordEqualTo(String value) {
            addCriterion("password =", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotEqualTo(String value) {
            addCriterion("password <>", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThan(String value) {
            addCriterion("password >", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("password >=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThan(String value) {
            addCriterion("password <", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLessThanOrEqualTo(String value) {
            addCriterion("password <=", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordLike(String value) {
            addCriterion("password like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotLike(String value) {
            addCriterion("password not like", value, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordIn(List<String> values) {
            addCriterion("password in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotIn(List<String> values) {
            addCriterion("password not in", values, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordBetween(String value1, String value2) {
            addCriterion("password between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andPasswordNotBetween(String value1, String value2) {
            addCriterion("password not between", value1, value2, "password");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("username is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("username is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("username =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("username <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("username >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("username >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("username <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("username <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("username like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("username not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("username in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("username not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("username between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("username not between", value1, value2, "username");
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

        public Criteria andLevelIsNull() {
            addCriterion("level is null");
            return (Criteria) this;
        }

        public Criteria andLevelIsNotNull() {
            addCriterion("level is not null");
            return (Criteria) this;
        }

        public Criteria andLevelEqualTo(Integer value) {
            addCriterion("level =", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotEqualTo(Integer value) {
            addCriterion("level <>", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThan(Integer value) {
            addCriterion("level >", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelGreaterThanOrEqualTo(Integer value) {
            addCriterion("level >=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThan(Integer value) {
            addCriterion("level <", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelLessThanOrEqualTo(Integer value) {
            addCriterion("level <=", value, "level");
            return (Criteria) this;
        }

        public Criteria andLevelIn(List<Integer> values) {
            addCriterion("level in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotIn(List<Integer> values) {
            addCriterion("level not in", values, "level");
            return (Criteria) this;
        }

        public Criteria andLevelBetween(Integer value1, Integer value2) {
            addCriterion("level between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andLevelNotBetween(Integer value1, Integer value2) {
            addCriterion("level not between", value1, value2, "level");
            return (Criteria) this;
        }

        public Criteria andDescrIsNull() {
            addCriterion("descr is null");
            return (Criteria) this;
        }

        public Criteria andDescrIsNotNull() {
            addCriterion("descr is not null");
            return (Criteria) this;
        }

        public Criteria andDescrEqualTo(String value) {
            addCriterion("descr =", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotEqualTo(String value) {
            addCriterion("descr <>", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrGreaterThan(String value) {
            addCriterion("descr >", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrGreaterThanOrEqualTo(String value) {
            addCriterion("descr >=", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLessThan(String value) {
            addCriterion("descr <", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLessThanOrEqualTo(String value) {
            addCriterion("descr <=", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrLike(String value) {
            addCriterion("descr like", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotLike(String value) {
            addCriterion("descr not like", value, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrIn(List<String> values) {
            addCriterion("descr in", values, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotIn(List<String> values) {
            addCriterion("descr not in", values, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrBetween(String value1, String value2) {
            addCriterion("descr between", value1, value2, "descr");
            return (Criteria) this;
        }

        public Criteria andDescrNotBetween(String value1, String value2) {
            addCriterion("descr not between", value1, value2, "descr");
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

        public Criteria andProxy_tokenIsNull() {
            addCriterion("proxy_token is null");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenIsNotNull() {
            addCriterion("proxy_token is not null");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenEqualTo(String value) {
            addCriterion("proxy_token =", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenNotEqualTo(String value) {
            addCriterion("proxy_token <>", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenGreaterThan(String value) {
            addCriterion("proxy_token >", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenGreaterThanOrEqualTo(String value) {
            addCriterion("proxy_token >=", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenLessThan(String value) {
            addCriterion("proxy_token <", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenLessThanOrEqualTo(String value) {
            addCriterion("proxy_token <=", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenLike(String value) {
            addCriterion("proxy_token like", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenNotLike(String value) {
            addCriterion("proxy_token not like", value, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenIn(List<String> values) {
            addCriterion("proxy_token in", values, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenNotIn(List<String> values) {
            addCriterion("proxy_token not in", values, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenBetween(String value1, String value2) {
            addCriterion("proxy_token between", value1, value2, "proxy_token");
            return (Criteria) this;
        }

        public Criteria andProxy_tokenNotBetween(String value1, String value2) {
            addCriterion("proxy_token not between", value1, value2, "proxy_token");
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

        public Criteria andPtokenIsNull() {
            addCriterion("ptoken is null");
            return (Criteria) this;
        }

        public Criteria andPtokenIsNotNull() {
            addCriterion("ptoken is not null");
            return (Criteria) this;
        }

        public Criteria andPtokenEqualTo(String value) {
            addCriterion("ptoken =", value, "ptoken");
            return (Criteria) this;
        }

        public Criteria andPtokenNotEqualTo(String value) {
            addCriterion("ptoken <>", value, "ptoken");
            return (Criteria) this;
        }

        public Criteria andPtokenGreaterThan(String value) {
            addCriterion("ptoken >", value, "ptoken");
            return (Criteria) this;
        }

        public Criteria andPtokenGreaterThanOrEqualTo(String value) {
            addCriterion("ptoken >=", value, "ptoken");
            return (Criteria) this;
        }

        public Criteria andPtokenLessThan(String value) {
            addCriterion("ptoken <", value, "ptoken");
            return (Criteria) this;
        }

        public Criteria andPtokenLessThanOrEqualTo(String value) {
            addCriterion("ptoken <=", value, "ptoken");
            return (Criteria) this;
        }

        public Criteria andPtokenLike(String value) {
            addCriterion("ptoken like", value, "ptoken");
            return (Criteria) this;
        }

        public Criteria andPtokenNotLike(String value) {
            addCriterion("ptoken not like", value, "ptoken");
            return (Criteria) this;
        }

        public Criteria andPtokenIn(List<String> values) {
            addCriterion("ptoken in", values, "ptoken");
            return (Criteria) this;
        }

        public Criteria andPtokenNotIn(List<String> values) {
            addCriterion("ptoken not in", values, "ptoken");
            return (Criteria) this;
        }

        public Criteria andPtokenBetween(String value1, String value2) {
            addCriterion("ptoken between", value1, value2, "ptoken");
            return (Criteria) this;
        }

        public Criteria andPtokenNotBetween(String value1, String value2) {
            addCriterion("ptoken not between", value1, value2, "ptoken");
            return (Criteria) this;
        }

        public Criteria andRecommend_phoneIsNull() {
            addCriterion("recommend_phone is null");
            return (Criteria) this;
        }

        public Criteria andRecommend_phoneIsNotNull() {
            addCriterion("recommend_phone is not null");
            return (Criteria) this;
        }

        public Criteria andRecommend_phoneEqualTo(String value) {
            addCriterion("recommend_phone =", value, "recommend_phone");
            return (Criteria) this;
        }

        public Criteria andRecommend_phoneNotEqualTo(String value) {
            addCriterion("recommend_phone <>", value, "recommend_phone");
            return (Criteria) this;
        }

        public Criteria andRecommend_phoneGreaterThan(String value) {
            addCriterion("recommend_phone >", value, "recommend_phone");
            return (Criteria) this;
        }

        public Criteria andRecommend_phoneGreaterThanOrEqualTo(String value) {
            addCriterion("recommend_phone >=", value, "recommend_phone");
            return (Criteria) this;
        }

        public Criteria andRecommend_phoneLessThan(String value) {
            addCriterion("recommend_phone <", value, "recommend_phone");
            return (Criteria) this;
        }

        public Criteria andRecommend_phoneLessThanOrEqualTo(String value) {
            addCriterion("recommend_phone <=", value, "recommend_phone");
            return (Criteria) this;
        }

        public Criteria andRecommend_phoneLike(String value) {
            addCriterion("recommend_phone like", value, "recommend_phone");
            return (Criteria) this;
        }

        public Criteria andRecommend_phoneNotLike(String value) {
            addCriterion("recommend_phone not like", value, "recommend_phone");
            return (Criteria) this;
        }

        public Criteria andRecommend_phoneIn(List<String> values) {
            addCriterion("recommend_phone in", values, "recommend_phone");
            return (Criteria) this;
        }

        public Criteria andRecommend_phoneNotIn(List<String> values) {
            addCriterion("recommend_phone not in", values, "recommend_phone");
            return (Criteria) this;
        }

        public Criteria andRecommend_phoneBetween(String value1, String value2) {
            addCriterion("recommend_phone between", value1, value2, "recommend_phone");
            return (Criteria) this;
        }

        public Criteria andRecommend_phoneNotBetween(String value1, String value2) {
            addCriterion("recommend_phone not between", value1, value2, "recommend_phone");
            return (Criteria) this;
        }

        public Criteria andRecommend_tokenIsNull() {
            addCriterion("recommend_token is null");
            return (Criteria) this;
        }

        public Criteria andRecommend_tokenIsNotNull() {
            addCriterion("recommend_token is not null");
            return (Criteria) this;
        }

        public Criteria andRecommend_tokenEqualTo(String value) {
            addCriterion("recommend_token =", value, "recommend_token");
            return (Criteria) this;
        }

        public Criteria andRecommend_tokenNotEqualTo(String value) {
            addCriterion("recommend_token <>", value, "recommend_token");
            return (Criteria) this;
        }

        public Criteria andRecommend_tokenGreaterThan(String value) {
            addCriterion("recommend_token >", value, "recommend_token");
            return (Criteria) this;
        }

        public Criteria andRecommend_tokenGreaterThanOrEqualTo(String value) {
            addCriterion("recommend_token >=", value, "recommend_token");
            return (Criteria) this;
        }

        public Criteria andRecommend_tokenLessThan(String value) {
            addCriterion("recommend_token <", value, "recommend_token");
            return (Criteria) this;
        }

        public Criteria andRecommend_tokenLessThanOrEqualTo(String value) {
            addCriterion("recommend_token <=", value, "recommend_token");
            return (Criteria) this;
        }

        public Criteria andRecommend_tokenLike(String value) {
            addCriterion("recommend_token like", value, "recommend_token");
            return (Criteria) this;
        }

        public Criteria andRecommend_tokenNotLike(String value) {
            addCriterion("recommend_token not like", value, "recommend_token");
            return (Criteria) this;
        }

        public Criteria andRecommend_tokenIn(List<String> values) {
            addCriterion("recommend_token in", values, "recommend_token");
            return (Criteria) this;
        }

        public Criteria andRecommend_tokenNotIn(List<String> values) {
            addCriterion("recommend_token not in", values, "recommend_token");
            return (Criteria) this;
        }

        public Criteria andRecommend_tokenBetween(String value1, String value2) {
            addCriterion("recommend_token between", value1, value2, "recommend_token");
            return (Criteria) this;
        }

        public Criteria andRecommend_tokenNotBetween(String value1, String value2) {
            addCriterion("recommend_token not between", value1, value2, "recommend_token");
            return (Criteria) this;
        }

        public Criteria andSecretkeyIsNull() {
            addCriterion("secretkey is null");
            return (Criteria) this;
        }

        public Criteria andSecretkeyIsNotNull() {
            addCriterion("secretkey is not null");
            return (Criteria) this;
        }

        public Criteria andSecretkeyEqualTo(String value) {
            addCriterion("secretkey =", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyNotEqualTo(String value) {
            addCriterion("secretkey <>", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyGreaterThan(String value) {
            addCriterion("secretkey >", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyGreaterThanOrEqualTo(String value) {
            addCriterion("secretkey >=", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyLessThan(String value) {
            addCriterion("secretkey <", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyLessThanOrEqualTo(String value) {
            addCriterion("secretkey <=", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyLike(String value) {
            addCriterion("secretkey like", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyNotLike(String value) {
            addCriterion("secretkey not like", value, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyIn(List<String> values) {
            addCriterion("secretkey in", values, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyNotIn(List<String> values) {
            addCriterion("secretkey not in", values, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyBetween(String value1, String value2) {
            addCriterion("secretkey between", value1, value2, "secretkey");
            return (Criteria) this;
        }

        public Criteria andSecretkeyNotBetween(String value1, String value2) {
            addCriterion("secretkey not between", value1, value2, "secretkey");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("createTime <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andSignwayIsNull() {
            addCriterion("signway is null");
            return (Criteria) this;
        }

        public Criteria andSignwayIsNotNull() {
            addCriterion("signway is not null");
            return (Criteria) this;
        }

        public Criteria andSignwayEqualTo(Integer value) {
            addCriterion("signway =", value, "signway");
            return (Criteria) this;
        }

        public Criteria andSignwayNotEqualTo(Integer value) {
            addCriterion("signway <>", value, "signway");
            return (Criteria) this;
        }

        public Criteria andSignwayGreaterThan(Integer value) {
            addCriterion("signway >", value, "signway");
            return (Criteria) this;
        }

        public Criteria andSignwayGreaterThanOrEqualTo(Integer value) {
            addCriterion("signway >=", value, "signway");
            return (Criteria) this;
        }

        public Criteria andSignwayLessThan(Integer value) {
            addCriterion("signway <", value, "signway");
            return (Criteria) this;
        }

        public Criteria andSignwayLessThanOrEqualTo(Integer value) {
            addCriterion("signway <=", value, "signway");
            return (Criteria) this;
        }

        public Criteria andSignwayIn(List<Integer> values) {
            addCriterion("signway in", values, "signway");
            return (Criteria) this;
        }

        public Criteria andSignwayNotIn(List<Integer> values) {
            addCriterion("signway not in", values, "signway");
            return (Criteria) this;
        }

        public Criteria andSignwayBetween(Integer value1, Integer value2) {
            addCriterion("signway between", value1, value2, "signway");
            return (Criteria) this;
        }

        public Criteria andSignwayNotBetween(Integer value1, Integer value2) {
            addCriterion("signway not between", value1, value2, "signway");
            return (Criteria) this;
        }

        public Criteria andFranchise_feeIsNull() {
            addCriterion("franchise_fee is null");
            return (Criteria) this;
        }

        public Criteria andFranchise_feeIsNotNull() {
            addCriterion("franchise_fee is not null");
            return (Criteria) this;
        }

        public Criteria andFranchise_feeEqualTo(Integer value) {
            addCriterion("franchise_fee =", value, "franchise_fee");
            return (Criteria) this;
        }

        public Criteria andFranchise_feeNotEqualTo(Integer value) {
            addCriterion("franchise_fee <>", value, "franchise_fee");
            return (Criteria) this;
        }

        public Criteria andFranchise_feeGreaterThan(Integer value) {
            addCriterion("franchise_fee >", value, "franchise_fee");
            return (Criteria) this;
        }

        public Criteria andFranchise_feeGreaterThanOrEqualTo(Integer value) {
            addCriterion("franchise_fee >=", value, "franchise_fee");
            return (Criteria) this;
        }

        public Criteria andFranchise_feeLessThan(Integer value) {
            addCriterion("franchise_fee <", value, "franchise_fee");
            return (Criteria) this;
        }

        public Criteria andFranchise_feeLessThanOrEqualTo(Integer value) {
            addCriterion("franchise_fee <=", value, "franchise_fee");
            return (Criteria) this;
        }

        public Criteria andFranchise_feeIn(List<Integer> values) {
            addCriterion("franchise_fee in", values, "franchise_fee");
            return (Criteria) this;
        }

        public Criteria andFranchise_feeNotIn(List<Integer> values) {
            addCriterion("franchise_fee not in", values, "franchise_fee");
            return (Criteria) this;
        }

        public Criteria andFranchise_feeBetween(Integer value1, Integer value2) {
            addCriterion("franchise_fee between", value1, value2, "franchise_fee");
            return (Criteria) this;
        }

        public Criteria andFranchise_feeNotBetween(Integer value1, Integer value2) {
            addCriterion("franchise_fee not between", value1, value2, "franchise_fee");
            return (Criteria) this;
        }

        public Criteria andPolicyIsNull() {
            addCriterion("policy is null");
            return (Criteria) this;
        }

        public Criteria andPolicyIsNotNull() {
            addCriterion("policy is not null");
            return (Criteria) this;
        }

        public Criteria andPolicyEqualTo(Integer value) {
            addCriterion("policy =", value, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyNotEqualTo(Integer value) {
            addCriterion("policy <>", value, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyGreaterThan(Integer value) {
            addCriterion("policy >", value, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyGreaterThanOrEqualTo(Integer value) {
            addCriterion("policy >=", value, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyLessThan(Integer value) {
            addCriterion("policy <", value, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyLessThanOrEqualTo(Integer value) {
            addCriterion("policy <=", value, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyIn(List<Integer> values) {
            addCriterion("policy in", values, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyNotIn(List<Integer> values) {
            addCriterion("policy not in", values, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyBetween(Integer value1, Integer value2) {
            addCriterion("policy between", value1, value2, "policy");
            return (Criteria) this;
        }

        public Criteria andPolicyNotBetween(Integer value1, Integer value2) {
            addCriterion("policy not between", value1, value2, "policy");
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
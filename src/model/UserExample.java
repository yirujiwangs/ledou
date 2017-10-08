package model;

import model.divide.BaseDivideModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample extends BaseDivideModel{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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

        public Criteria andStorenameIsNull() {
            addCriterion("storeName is null");
            return (Criteria) this;
        }

        public Criteria andStorenameIsNotNull() {
            addCriterion("storeName is not null");
            return (Criteria) this;
        }

        public Criteria andStorenameEqualTo(String value) {
            addCriterion("storeName =", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotEqualTo(String value) {
            addCriterion("storeName <>", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameGreaterThan(String value) {
            addCriterion("storeName >", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameGreaterThanOrEqualTo(String value) {
            addCriterion("storeName >=", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameLessThan(String value) {
            addCriterion("storeName <", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameLessThanOrEqualTo(String value) {
            addCriterion("storeName <=", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameLike(String value) {
            addCriterion("storeName like", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotLike(String value) {
            addCriterion("storeName not like", value, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameIn(List<String> values) {
            addCriterion("storeName in", values, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotIn(List<String> values) {
            addCriterion("storeName not in", values, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameBetween(String value1, String value2) {
            addCriterion("storeName between", value1, value2, "storename");
            return (Criteria) this;
        }

        public Criteria andStorenameNotBetween(String value1, String value2) {
            addCriterion("storeName not between", value1, value2, "storename");
            return (Criteria) this;
        }

        public Criteria andPasswdIsNull() {
            addCriterion("passwd is null");
            return (Criteria) this;
        }

        public Criteria andPasswdIsNotNull() {
            addCriterion("passwd is not null");
            return (Criteria) this;
        }

        public Criteria andPasswdEqualTo(String value) {
            addCriterion("passwd =", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotEqualTo(String value) {
            addCriterion("passwd <>", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdGreaterThan(String value) {
            addCriterion("passwd >", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdGreaterThanOrEqualTo(String value) {
            addCriterion("passwd >=", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLessThan(String value) {
            addCriterion("passwd <", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLessThanOrEqualTo(String value) {
            addCriterion("passwd <=", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdLike(String value) {
            addCriterion("passwd like", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotLike(String value) {
            addCriterion("passwd not like", value, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdIn(List<String> values) {
            addCriterion("passwd in", values, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotIn(List<String> values) {
            addCriterion("passwd not in", values, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdBetween(String value1, String value2) {
            addCriterion("passwd between", value1, value2, "passwd");
            return (Criteria) this;
        }

        public Criteria andPasswdNotBetween(String value1, String value2) {
            addCriterion("passwd not between", value1, value2, "passwd");
            return (Criteria) this;
        }

        public Criteria andStoretypeIsNull() {
            addCriterion("storeType is null");
            return (Criteria) this;
        }

        public Criteria andStoretypeIsNotNull() {
            addCriterion("storeType is not null");
            return (Criteria) this;
        }

        public Criteria andStoretypeEqualTo(String value) {
            addCriterion("storeType =", value, "storetype");
            return (Criteria) this;
        }

        public Criteria andStoretypeNotEqualTo(String value) {
            addCriterion("storeType <>", value, "storetype");
            return (Criteria) this;
        }

        public Criteria andStoretypeGreaterThan(String value) {
            addCriterion("storeType >", value, "storetype");
            return (Criteria) this;
        }

        public Criteria andStoretypeGreaterThanOrEqualTo(String value) {
            addCriterion("storeType >=", value, "storetype");
            return (Criteria) this;
        }

        public Criteria andStoretypeLessThan(String value) {
            addCriterion("storeType <", value, "storetype");
            return (Criteria) this;
        }

        public Criteria andStoretypeLessThanOrEqualTo(String value) {
            addCriterion("storeType <=", value, "storetype");
            return (Criteria) this;
        }

        public Criteria andStoretypeLike(String value) {
            addCriterion("storeType like", value, "storetype");
            return (Criteria) this;
        }

        public Criteria andStoretypeNotLike(String value) {
            addCriterion("storeType not like", value, "storetype");
            return (Criteria) this;
        }

        public Criteria andStoretypeIn(List<String> values) {
            addCriterion("storeType in", values, "storetype");
            return (Criteria) this;
        }

        public Criteria andStoretypeNotIn(List<String> values) {
            addCriterion("storeType not in", values, "storetype");
            return (Criteria) this;
        }

        public Criteria andStoretypeBetween(String value1, String value2) {
            addCriterion("storeType between", value1, value2, "storetype");
            return (Criteria) this;
        }

        public Criteria andStoretypeNotBetween(String value1, String value2) {
            addCriterion("storeType not between", value1, value2, "storetype");
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

        public Criteria andWeixinstatusIsNull() {
            addCriterion("weiXinStatus is null");
            return (Criteria) this;
        }

        public Criteria andWeixinstatusIsNotNull() {
            addCriterion("weiXinStatus is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinstatusEqualTo(String value) {
            addCriterion("weiXinStatus =", value, "weixinstatus");
            return (Criteria) this;
        }

        public Criteria andWeixinstatusNotEqualTo(String value) {
            addCriterion("weiXinStatus <>", value, "weixinstatus");
            return (Criteria) this;
        }

        public Criteria andWeixinstatusGreaterThan(String value) {
            addCriterion("weiXinStatus >", value, "weixinstatus");
            return (Criteria) this;
        }

        public Criteria andWeixinstatusGreaterThanOrEqualTo(String value) {
            addCriterion("weiXinStatus >=", value, "weixinstatus");
            return (Criteria) this;
        }

        public Criteria andWeixinstatusLessThan(String value) {
            addCriterion("weiXinStatus <", value, "weixinstatus");
            return (Criteria) this;
        }

        public Criteria andWeixinstatusLessThanOrEqualTo(String value) {
            addCriterion("weiXinStatus <=", value, "weixinstatus");
            return (Criteria) this;
        }

        public Criteria andWeixinstatusLike(String value) {
            addCriterion("weiXinStatus like", value, "weixinstatus");
            return (Criteria) this;
        }

        public Criteria andWeixinstatusNotLike(String value) {
            addCriterion("weiXinStatus not like", value, "weixinstatus");
            return (Criteria) this;
        }

        public Criteria andWeixinstatusIn(List<String> values) {
            addCriterion("weiXinStatus in", values, "weixinstatus");
            return (Criteria) this;
        }

        public Criteria andWeixinstatusNotIn(List<String> values) {
            addCriterion("weiXinStatus not in", values, "weixinstatus");
            return (Criteria) this;
        }

        public Criteria andWeixinstatusBetween(String value1, String value2) {
            addCriterion("weiXinStatus between", value1, value2, "weixinstatus");
            return (Criteria) this;
        }

        public Criteria andWeixinstatusNotBetween(String value1, String value2) {
            addCriterion("weiXinStatus not between", value1, value2, "weixinstatus");
            return (Criteria) this;
        }

        public Criteria andMaxgroupnumIsNull() {
            addCriterion("maxGroupNum is null");
            return (Criteria) this;
        }

        public Criteria andMaxgroupnumIsNotNull() {
            addCriterion("maxGroupNum is not null");
            return (Criteria) this;
        }

        public Criteria andMaxgroupnumEqualTo(Integer value) {
            addCriterion("maxGroupNum =", value, "maxgroupnum");
            return (Criteria) this;
        }

        public Criteria andMaxgroupnumNotEqualTo(Integer value) {
            addCriterion("maxGroupNum <>", value, "maxgroupnum");
            return (Criteria) this;
        }

        public Criteria andMaxgroupnumGreaterThan(Integer value) {
            addCriterion("maxGroupNum >", value, "maxgroupnum");
            return (Criteria) this;
        }

        public Criteria andMaxgroupnumGreaterThanOrEqualTo(Integer value) {
            addCriterion("maxGroupNum >=", value, "maxgroupnum");
            return (Criteria) this;
        }

        public Criteria andMaxgroupnumLessThan(Integer value) {
            addCriterion("maxGroupNum <", value, "maxgroupnum");
            return (Criteria) this;
        }

        public Criteria andMaxgroupnumLessThanOrEqualTo(Integer value) {
            addCriterion("maxGroupNum <=", value, "maxgroupnum");
            return (Criteria) this;
        }

        public Criteria andMaxgroupnumIn(List<Integer> values) {
            addCriterion("maxGroupNum in", values, "maxgroupnum");
            return (Criteria) this;
        }

        public Criteria andMaxgroupnumNotIn(List<Integer> values) {
            addCriterion("maxGroupNum not in", values, "maxgroupnum");
            return (Criteria) this;
        }

        public Criteria andMaxgroupnumBetween(Integer value1, Integer value2) {
            addCriterion("maxGroupNum between", value1, value2, "maxgroupnum");
            return (Criteria) this;
        }

        public Criteria andMaxgroupnumNotBetween(Integer value1, Integer value2) {
            addCriterion("maxGroupNum not between", value1, value2, "maxgroupnum");
            return (Criteria) this;
        }

        public Criteria andMaxstorenumIsNull() {
            addCriterion("maxStoreNum is null");
            return (Criteria) this;
        }

        public Criteria andMaxstorenumIsNotNull() {
            addCriterion("maxStoreNum is not null");
            return (Criteria) this;
        }

        public Criteria andMaxstorenumEqualTo(Integer value) {
            addCriterion("maxStoreNum =", value, "maxstorenum");
            return (Criteria) this;
        }

        public Criteria andMaxstorenumNotEqualTo(Integer value) {
            addCriterion("maxStoreNum <>", value, "maxstorenum");
            return (Criteria) this;
        }

        public Criteria andMaxstorenumGreaterThan(Integer value) {
            addCriterion("maxStoreNum >", value, "maxstorenum");
            return (Criteria) this;
        }

        public Criteria andMaxstorenumGreaterThanOrEqualTo(Integer value) {
            addCriterion("maxStoreNum >=", value, "maxstorenum");
            return (Criteria) this;
        }

        public Criteria andMaxstorenumLessThan(Integer value) {
            addCriterion("maxStoreNum <", value, "maxstorenum");
            return (Criteria) this;
        }

        public Criteria andMaxstorenumLessThanOrEqualTo(Integer value) {
            addCriterion("maxStoreNum <=", value, "maxstorenum");
            return (Criteria) this;
        }

        public Criteria andMaxstorenumIn(List<Integer> values) {
            addCriterion("maxStoreNum in", values, "maxstorenum");
            return (Criteria) this;
        }

        public Criteria andMaxstorenumNotIn(List<Integer> values) {
            addCriterion("maxStoreNum not in", values, "maxstorenum");
            return (Criteria) this;
        }

        public Criteria andMaxstorenumBetween(Integer value1, Integer value2) {
            addCriterion("maxStoreNum between", value1, value2, "maxstorenum");
            return (Criteria) this;
        }

        public Criteria andMaxstorenumNotBetween(Integer value1, Integer value2) {
            addCriterion("maxStoreNum not between", value1, value2, "maxstorenum");
            return (Criteria) this;
        }

        public Criteria andPlatformidIsNull() {
            addCriterion("platformId is null");
            return (Criteria) this;
        }

        public Criteria andPlatformidIsNotNull() {
            addCriterion("platformId is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformidEqualTo(Integer value) {
            addCriterion("platformId =", value, "platformid");
            return (Criteria) this;
        }

        public Criteria andPlatformidNotEqualTo(Integer value) {
            addCriterion("platformId <>", value, "platformid");
            return (Criteria) this;
        }

        public Criteria andPlatformidGreaterThan(Integer value) {
            addCriterion("platformId >", value, "platformid");
            return (Criteria) this;
        }

        public Criteria andPlatformidGreaterThanOrEqualTo(Integer value) {
            addCriterion("platformId >=", value, "platformid");
            return (Criteria) this;
        }

        public Criteria andPlatformidLessThan(Integer value) {
            addCriterion("platformId <", value, "platformid");
            return (Criteria) this;
        }

        public Criteria andPlatformidLessThanOrEqualTo(Integer value) {
            addCriterion("platformId <=", value, "platformid");
            return (Criteria) this;
        }

        public Criteria andPlatformidIn(List<Integer> values) {
            addCriterion("platformId in", values, "platformid");
            return (Criteria) this;
        }

        public Criteria andPlatformidNotIn(List<Integer> values) {
            addCriterion("platformId not in", values, "platformid");
            return (Criteria) this;
        }

        public Criteria andPlatformidBetween(Integer value1, Integer value2) {
            addCriterion("platformId between", value1, value2, "platformid");
            return (Criteria) this;
        }

        public Criteria andPlatformidNotBetween(Integer value1, Integer value2) {
            addCriterion("platformId not between", value1, value2, "platformid");
            return (Criteria) this;
        }

        public Criteria andUserinfoidIsNull() {
            addCriterion("userInfoId is null");
            return (Criteria) this;
        }

        public Criteria andUserinfoidIsNotNull() {
            addCriterion("userInfoId is not null");
            return (Criteria) this;
        }

        public Criteria andUserinfoidEqualTo(Integer value) {
            addCriterion("userInfoId =", value, "userinfoid");
            return (Criteria) this;
        }

        public Criteria andUserinfoidNotEqualTo(Integer value) {
            addCriterion("userInfoId <>", value, "userinfoid");
            return (Criteria) this;
        }

        public Criteria andUserinfoidGreaterThan(Integer value) {
            addCriterion("userInfoId >", value, "userinfoid");
            return (Criteria) this;
        }

        public Criteria andUserinfoidGreaterThanOrEqualTo(Integer value) {
            addCriterion("userInfoId >=", value, "userinfoid");
            return (Criteria) this;
        }

        public Criteria andUserinfoidLessThan(Integer value) {
            addCriterion("userInfoId <", value, "userinfoid");
            return (Criteria) this;
        }

        public Criteria andUserinfoidLessThanOrEqualTo(Integer value) {
            addCriterion("userInfoId <=", value, "userinfoid");
            return (Criteria) this;
        }

        public Criteria andUserinfoidIn(List<Integer> values) {
            addCriterion("userInfoId in", values, "userinfoid");
            return (Criteria) this;
        }

        public Criteria andUserinfoidNotIn(List<Integer> values) {
            addCriterion("userInfoId not in", values, "userinfoid");
            return (Criteria) this;
        }

        public Criteria andUserinfoidBetween(Integer value1, Integer value2) {
            addCriterion("userInfoId between", value1, value2, "userinfoid");
            return (Criteria) this;
        }

        public Criteria andUserinfoidNotBetween(Integer value1, Integer value2) {
            addCriterion("userInfoId not between", value1, value2, "userinfoid");
            return (Criteria) this;
        }

        public Criteria andWxAppidIsNull() {
            addCriterion("wx_appid is null");
            return (Criteria) this;
        }

        public Criteria andWxAppidIsNotNull() {
            addCriterion("wx_appid is not null");
            return (Criteria) this;
        }

        public Criteria andWxAppidEqualTo(String value) {
            addCriterion("wx_appid =", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidNotEqualTo(String value) {
            addCriterion("wx_appid <>", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidGreaterThan(String value) {
            addCriterion("wx_appid >", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidGreaterThanOrEqualTo(String value) {
            addCriterion("wx_appid >=", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidLessThan(String value) {
            addCriterion("wx_appid <", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidLessThanOrEqualTo(String value) {
            addCriterion("wx_appid <=", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidLike(String value) {
            addCriterion("wx_appid like", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidNotLike(String value) {
            addCriterion("wx_appid not like", value, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidIn(List<String> values) {
            addCriterion("wx_appid in", values, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidNotIn(List<String> values) {
            addCriterion("wx_appid not in", values, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidBetween(String value1, String value2) {
            addCriterion("wx_appid between", value1, value2, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andWxAppidNotBetween(String value1, String value2) {
            addCriterion("wx_appid not between", value1, value2, "wxAppid");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("createTime is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("createTime is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("createTime =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("createTime <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("createTime >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("createTime >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("createTime <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("createTime <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("createTime in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("createTime not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("createTime between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("createTime not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andLastmodifytimeIsNull() {
            addCriterion("lastModifyTime is null");
            return (Criteria) this;
        }

        public Criteria andLastmodifytimeIsNotNull() {
            addCriterion("lastModifyTime is not null");
            return (Criteria) this;
        }

        public Criteria andLastmodifytimeEqualTo(Date value) {
            addCriterion("lastModifyTime =", value, "lastmodifytime");
            return (Criteria) this;
        }

        public Criteria andLastmodifytimeNotEqualTo(Date value) {
            addCriterion("lastModifyTime <>", value, "lastmodifytime");
            return (Criteria) this;
        }

        public Criteria andLastmodifytimeGreaterThan(Date value) {
            addCriterion("lastModifyTime >", value, "lastmodifytime");
            return (Criteria) this;
        }

        public Criteria andLastmodifytimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lastModifyTime >=", value, "lastmodifytime");
            return (Criteria) this;
        }

        public Criteria andLastmodifytimeLessThan(Date value) {
            addCriterion("lastModifyTime <", value, "lastmodifytime");
            return (Criteria) this;
        }

        public Criteria andLastmodifytimeLessThanOrEqualTo(Date value) {
            addCriterion("lastModifyTime <=", value, "lastmodifytime");
            return (Criteria) this;
        }

        public Criteria andLastmodifytimeIn(List<Date> values) {
            addCriterion("lastModifyTime in", values, "lastmodifytime");
            return (Criteria) this;
        }

        public Criteria andLastmodifytimeNotIn(List<Date> values) {
            addCriterion("lastModifyTime not in", values, "lastmodifytime");
            return (Criteria) this;
        }

        public Criteria andLastmodifytimeBetween(Date value1, Date value2) {
            addCriterion("lastModifyTime between", value1, value2, "lastmodifytime");
            return (Criteria) this;
        }

        public Criteria andLastmodifytimeNotBetween(Date value1, Date value2) {
            addCriterion("lastModifyTime not between", value1, value2, "lastmodifytime");
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

        public Criteria andCorporationidIsNull() {
            addCriterion("corporationId is null");
            return (Criteria) this;
        }

        public Criteria andCorporationidIsNotNull() {
            addCriterion("corporationId is not null");
            return (Criteria) this;
        }

        public Criteria andCorporationidEqualTo(Integer value) {
            addCriterion("corporationId =", value, "corporationid");
            return (Criteria) this;
        }

        public Criteria andCorporationidNotEqualTo(Integer value) {
            addCriterion("corporationId <>", value, "corporationid");
            return (Criteria) this;
        }

        public Criteria andCorporationidGreaterThan(Integer value) {
            addCriterion("corporationId >", value, "corporationid");
            return (Criteria) this;
        }

        public Criteria andCorporationidGreaterThanOrEqualTo(Integer value) {
            addCriterion("corporationId >=", value, "corporationid");
            return (Criteria) this;
        }

        public Criteria andCorporationidLessThan(Integer value) {
            addCriterion("corporationId <", value, "corporationid");
            return (Criteria) this;
        }

        public Criteria andCorporationidLessThanOrEqualTo(Integer value) {
            addCriterion("corporationId <=", value, "corporationid");
            return (Criteria) this;
        }

        public Criteria andCorporationidIn(List<Integer> values) {
            addCriterion("corporationId in", values, "corporationid");
            return (Criteria) this;
        }

        public Criteria andCorporationidNotIn(List<Integer> values) {
            addCriterion("corporationId not in", values, "corporationid");
            return (Criteria) this;
        }

        public Criteria andCorporationidBetween(Integer value1, Integer value2) {
            addCriterion("corporationId between", value1, value2, "corporationid");
            return (Criteria) this;
        }

        public Criteria andCorporationidNotBetween(Integer value1, Integer value2) {
            addCriterion("corporationId not between", value1, value2, "corporationid");
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
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}
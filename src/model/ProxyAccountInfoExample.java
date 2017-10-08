package model;

import model.divide.BaseDivideModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProxyAccountInfoExample extends BaseDivideModel {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProxyAccountInfoExample() {
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

        public Criteria andAccount_idIsNull() {
            addCriterion("account_id is null");
            return (Criteria) this;
        }

        public Criteria andAccount_idIsNotNull() {
            addCriterion("account_id is not null");
            return (Criteria) this;
        }

        public Criteria andAccount_idEqualTo(Integer value) {
            addCriterion("account_id =", value, "account_id");
            return (Criteria) this;
        }

        public Criteria andAccount_idNotEqualTo(Integer value) {
            addCriterion("account_id <>", value, "account_id");
            return (Criteria) this;
        }

        public Criteria andAccount_idGreaterThan(Integer value) {
            addCriterion("account_id >", value, "account_id");
            return (Criteria) this;
        }

        public Criteria andAccount_idGreaterThanOrEqualTo(Integer value) {
            addCriterion("account_id >=", value, "account_id");
            return (Criteria) this;
        }

        public Criteria andAccount_idLessThan(Integer value) {
            addCriterion("account_id <", value, "account_id");
            return (Criteria) this;
        }

        public Criteria andAccount_idLessThanOrEqualTo(Integer value) {
            addCriterion("account_id <=", value, "account_id");
            return (Criteria) this;
        }

        public Criteria andAccount_idIn(List<Integer> values) {
            addCriterion("account_id in", values, "account_id");
            return (Criteria) this;
        }

        public Criteria andAccount_idNotIn(List<Integer> values) {
            addCriterion("account_id not in", values, "account_id");
            return (Criteria) this;
        }

        public Criteria andAccount_idBetween(Integer value1, Integer value2) {
            addCriterion("account_id between", value1, value2, "account_id");
            return (Criteria) this;
        }

        public Criteria andAccount_idNotBetween(Integer value1, Integer value2) {
            addCriterion("account_id not between", value1, value2, "account_id");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNull() {
            addCriterion("nickname is null");
            return (Criteria) this;
        }

        public Criteria andNicknameIsNotNull() {
            addCriterion("nickname is not null");
            return (Criteria) this;
        }

        public Criteria andNicknameEqualTo(String value) {
            addCriterion("nickname =", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotEqualTo(String value) {
            addCriterion("nickname <>", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThan(String value) {
            addCriterion("nickname >", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameGreaterThanOrEqualTo(String value) {
            addCriterion("nickname >=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThan(String value) {
            addCriterion("nickname <", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLessThanOrEqualTo(String value) {
            addCriterion("nickname <=", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameLike(String value) {
            addCriterion("nickname like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotLike(String value) {
            addCriterion("nickname not like", value, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameIn(List<String> values) {
            addCriterion("nickname in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotIn(List<String> values) {
            addCriterion("nickname not in", values, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameBetween(String value1, String value2) {
            addCriterion("nickname between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andNicknameNotBetween(String value1, String value2) {
            addCriterion("nickname not between", value1, value2, "nickname");
            return (Criteria) this;
        }

        public Criteria andCompany_nameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompany_nameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompany_nameEqualTo(String value) {
            addCriterion("company_name =", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameGreaterThan(String value) {
            addCriterion("company_name >", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameLessThan(String value) {
            addCriterion("company_name <", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameLike(String value) {
            addCriterion("company_name like", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameNotLike(String value) {
            addCriterion("company_name not like", value, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameIn(List<String> values) {
            addCriterion("company_name in", values, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_nameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "company_name");
            return (Criteria) this;
        }

        public Criteria andCompany_numIsNull() {
            addCriterion("company_num is null");
            return (Criteria) this;
        }

        public Criteria andCompany_numIsNotNull() {
            addCriterion("company_num is not null");
            return (Criteria) this;
        }

        public Criteria andCompany_numEqualTo(String value) {
            addCriterion("company_num =", value, "company_num");
            return (Criteria) this;
        }

        public Criteria andCompany_numNotEqualTo(String value) {
            addCriterion("company_num <>", value, "company_num");
            return (Criteria) this;
        }

        public Criteria andCompany_numGreaterThan(String value) {
            addCriterion("company_num >", value, "company_num");
            return (Criteria) this;
        }

        public Criteria andCompany_numGreaterThanOrEqualTo(String value) {
            addCriterion("company_num >=", value, "company_num");
            return (Criteria) this;
        }

        public Criteria andCompany_numLessThan(String value) {
            addCriterion("company_num <", value, "company_num");
            return (Criteria) this;
        }

        public Criteria andCompany_numLessThanOrEqualTo(String value) {
            addCriterion("company_num <=", value, "company_num");
            return (Criteria) this;
        }

        public Criteria andCompany_numLike(String value) {
            addCriterion("company_num like", value, "company_num");
            return (Criteria) this;
        }

        public Criteria andCompany_numNotLike(String value) {
            addCriterion("company_num not like", value, "company_num");
            return (Criteria) this;
        }

        public Criteria andCompany_numIn(List<String> values) {
            addCriterion("company_num in", values, "company_num");
            return (Criteria) this;
        }

        public Criteria andCompany_numNotIn(List<String> values) {
            addCriterion("company_num not in", values, "company_num");
            return (Criteria) this;
        }

        public Criteria andCompany_numBetween(String value1, String value2) {
            addCriterion("company_num between", value1, value2, "company_num");
            return (Criteria) this;
        }

        public Criteria andCompany_numNotBetween(String value1, String value2) {
            addCriterion("company_num not between", value1, value2, "company_num");
            return (Criteria) this;
        }

        public Criteria andId_numIsNull() {
            addCriterion("id_num is null");
            return (Criteria) this;
        }

        public Criteria andId_numIsNotNull() {
            addCriterion("id_num is not null");
            return (Criteria) this;
        }

        public Criteria andId_numEqualTo(String value) {
            addCriterion("id_num =", value, "id_num");
            return (Criteria) this;
        }

        public Criteria andId_numNotEqualTo(String value) {
            addCriterion("id_num <>", value, "id_num");
            return (Criteria) this;
        }

        public Criteria andId_numGreaterThan(String value) {
            addCriterion("id_num >", value, "id_num");
            return (Criteria) this;
        }

        public Criteria andId_numGreaterThanOrEqualTo(String value) {
            addCriterion("id_num >=", value, "id_num");
            return (Criteria) this;
        }

        public Criteria andId_numLessThan(String value) {
            addCriterion("id_num <", value, "id_num");
            return (Criteria) this;
        }

        public Criteria andId_numLessThanOrEqualTo(String value) {
            addCriterion("id_num <=", value, "id_num");
            return (Criteria) this;
        }

        public Criteria andId_numLike(String value) {
            addCriterion("id_num like", value, "id_num");
            return (Criteria) this;
        }

        public Criteria andId_numNotLike(String value) {
            addCriterion("id_num not like", value, "id_num");
            return (Criteria) this;
        }

        public Criteria andId_numIn(List<String> values) {
            addCriterion("id_num in", values, "id_num");
            return (Criteria) this;
        }

        public Criteria andId_numNotIn(List<String> values) {
            addCriterion("id_num not in", values, "id_num");
            return (Criteria) this;
        }

        public Criteria andId_numBetween(String value1, String value2) {
            addCriterion("id_num between", value1, value2, "id_num");
            return (Criteria) this;
        }

        public Criteria andId_numNotBetween(String value1, String value2) {
            addCriterion("id_num not between", value1, value2, "id_num");
            return (Criteria) this;
        }

        public Criteria andLogourlIsNull() {
            addCriterion("logourl is null");
            return (Criteria) this;
        }

        public Criteria andLogourlIsNotNull() {
            addCriterion("logourl is not null");
            return (Criteria) this;
        }

        public Criteria andLogourlEqualTo(String value) {
            addCriterion("logourl =", value, "logourl");
            return (Criteria) this;
        }

        public Criteria andLogourlNotEqualTo(String value) {
            addCriterion("logourl <>", value, "logourl");
            return (Criteria) this;
        }

        public Criteria andLogourlGreaterThan(String value) {
            addCriterion("logourl >", value, "logourl");
            return (Criteria) this;
        }

        public Criteria andLogourlGreaterThanOrEqualTo(String value) {
            addCriterion("logourl >=", value, "logourl");
            return (Criteria) this;
        }

        public Criteria andLogourlLessThan(String value) {
            addCriterion("logourl <", value, "logourl");
            return (Criteria) this;
        }

        public Criteria andLogourlLessThanOrEqualTo(String value) {
            addCriterion("logourl <=", value, "logourl");
            return (Criteria) this;
        }

        public Criteria andLogourlLike(String value) {
            addCriterion("logourl like", value, "logourl");
            return (Criteria) this;
        }

        public Criteria andLogourlNotLike(String value) {
            addCriterion("logourl not like", value, "logourl");
            return (Criteria) this;
        }

        public Criteria andLogourlIn(List<String> values) {
            addCriterion("logourl in", values, "logourl");
            return (Criteria) this;
        }

        public Criteria andLogourlNotIn(List<String> values) {
            addCriterion("logourl not in", values, "logourl");
            return (Criteria) this;
        }

        public Criteria andLogourlBetween(String value1, String value2) {
            addCriterion("logourl between", value1, value2, "logourl");
            return (Criteria) this;
        }

        public Criteria andLogourlNotBetween(String value1, String value2) {
            addCriterion("logourl not between", value1, value2, "logourl");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andContract_nameIsNull() {
            addCriterion("contract_name is null");
            return (Criteria) this;
        }

        public Criteria andContract_nameIsNotNull() {
            addCriterion("contract_name is not null");
            return (Criteria) this;
        }

        public Criteria andContract_nameEqualTo(String value) {
            addCriterion("contract_name =", value, "contract_name");
            return (Criteria) this;
        }

        public Criteria andContract_nameNotEqualTo(String value) {
            addCriterion("contract_name <>", value, "contract_name");
            return (Criteria) this;
        }

        public Criteria andContract_nameGreaterThan(String value) {
            addCriterion("contract_name >", value, "contract_name");
            return (Criteria) this;
        }

        public Criteria andContract_nameGreaterThanOrEqualTo(String value) {
            addCriterion("contract_name >=", value, "contract_name");
            return (Criteria) this;
        }

        public Criteria andContract_nameLessThan(String value) {
            addCriterion("contract_name <", value, "contract_name");
            return (Criteria) this;
        }

        public Criteria andContract_nameLessThanOrEqualTo(String value) {
            addCriterion("contract_name <=", value, "contract_name");
            return (Criteria) this;
        }

        public Criteria andContract_nameLike(String value) {
            addCriterion("contract_name like", value, "contract_name");
            return (Criteria) this;
        }

        public Criteria andContract_nameNotLike(String value) {
            addCriterion("contract_name not like", value, "contract_name");
            return (Criteria) this;
        }

        public Criteria andContract_nameIn(List<String> values) {
            addCriterion("contract_name in", values, "contract_name");
            return (Criteria) this;
        }

        public Criteria andContract_nameNotIn(List<String> values) {
            addCriterion("contract_name not in", values, "contract_name");
            return (Criteria) this;
        }

        public Criteria andContract_nameBetween(String value1, String value2) {
            addCriterion("contract_name between", value1, value2, "contract_name");
            return (Criteria) this;
        }

        public Criteria andContract_nameNotBetween(String value1, String value2) {
            addCriterion("contract_name not between", value1, value2, "contract_name");
            return (Criteria) this;
        }

        public Criteria andContract_phoneIsNull() {
            addCriterion("contract_phone is null");
            return (Criteria) this;
        }

        public Criteria andContract_phoneIsNotNull() {
            addCriterion("contract_phone is not null");
            return (Criteria) this;
        }

        public Criteria andContract_phoneEqualTo(String value) {
            addCriterion("contract_phone =", value, "contract_phone");
            return (Criteria) this;
        }

        public Criteria andContract_phoneNotEqualTo(String value) {
            addCriterion("contract_phone <>", value, "contract_phone");
            return (Criteria) this;
        }

        public Criteria andContract_phoneGreaterThan(String value) {
            addCriterion("contract_phone >", value, "contract_phone");
            return (Criteria) this;
        }

        public Criteria andContract_phoneGreaterThanOrEqualTo(String value) {
            addCriterion("contract_phone >=", value, "contract_phone");
            return (Criteria) this;
        }

        public Criteria andContract_phoneLessThan(String value) {
            addCriterion("contract_phone <", value, "contract_phone");
            return (Criteria) this;
        }

        public Criteria andContract_phoneLessThanOrEqualTo(String value) {
            addCriterion("contract_phone <=", value, "contract_phone");
            return (Criteria) this;
        }

        public Criteria andContract_phoneLike(String value) {
            addCriterion("contract_phone like", value, "contract_phone");
            return (Criteria) this;
        }

        public Criteria andContract_phoneNotLike(String value) {
            addCriterion("contract_phone not like", value, "contract_phone");
            return (Criteria) this;
        }

        public Criteria andContract_phoneIn(List<String> values) {
            addCriterion("contract_phone in", values, "contract_phone");
            return (Criteria) this;
        }

        public Criteria andContract_phoneNotIn(List<String> values) {
            addCriterion("contract_phone not in", values, "contract_phone");
            return (Criteria) this;
        }

        public Criteria andContract_phoneBetween(String value1, String value2) {
            addCriterion("contract_phone between", value1, value2, "contract_phone");
            return (Criteria) this;
        }

        public Criteria andContract_phoneNotBetween(String value1, String value2) {
            addCriterion("contract_phone not between", value1, value2, "contract_phone");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andWx_nameIsNull() {
            addCriterion("wx_name is null");
            return (Criteria) this;
        }

        public Criteria andWx_nameIsNotNull() {
            addCriterion("wx_name is not null");
            return (Criteria) this;
        }

        public Criteria andWx_nameEqualTo(String value) {
            addCriterion("wx_name =", value, "wx_name");
            return (Criteria) this;
        }

        public Criteria andWx_nameNotEqualTo(String value) {
            addCriterion("wx_name <>", value, "wx_name");
            return (Criteria) this;
        }

        public Criteria andWx_nameGreaterThan(String value) {
            addCriterion("wx_name >", value, "wx_name");
            return (Criteria) this;
        }

        public Criteria andWx_nameGreaterThanOrEqualTo(String value) {
            addCriterion("wx_name >=", value, "wx_name");
            return (Criteria) this;
        }

        public Criteria andWx_nameLessThan(String value) {
            addCriterion("wx_name <", value, "wx_name");
            return (Criteria) this;
        }

        public Criteria andWx_nameLessThanOrEqualTo(String value) {
            addCriterion("wx_name <=", value, "wx_name");
            return (Criteria) this;
        }

        public Criteria andWx_nameLike(String value) {
            addCriterion("wx_name like", value, "wx_name");
            return (Criteria) this;
        }

        public Criteria andWx_nameNotLike(String value) {
            addCriterion("wx_name not like", value, "wx_name");
            return (Criteria) this;
        }

        public Criteria andWx_nameIn(List<String> values) {
            addCriterion("wx_name in", values, "wx_name");
            return (Criteria) this;
        }

        public Criteria andWx_nameNotIn(List<String> values) {
            addCriterion("wx_name not in", values, "wx_name");
            return (Criteria) this;
        }

        public Criteria andWx_nameBetween(String value1, String value2) {
            addCriterion("wx_name between", value1, value2, "wx_name");
            return (Criteria) this;
        }

        public Criteria andWx_nameNotBetween(String value1, String value2) {
            addCriterion("wx_name not between", value1, value2, "wx_name");
            return (Criteria) this;
        }

        public Criteria andWx_qr_urlIsNull() {
            addCriterion("wx_qr_url is null");
            return (Criteria) this;
        }

        public Criteria andWx_qr_urlIsNotNull() {
            addCriterion("wx_qr_url is not null");
            return (Criteria) this;
        }

        public Criteria andWx_qr_urlEqualTo(String value) {
            addCriterion("wx_qr_url =", value, "wx_qr_url");
            return (Criteria) this;
        }

        public Criteria andWx_qr_urlNotEqualTo(String value) {
            addCriterion("wx_qr_url <>", value, "wx_qr_url");
            return (Criteria) this;
        }

        public Criteria andWx_qr_urlGreaterThan(String value) {
            addCriterion("wx_qr_url >", value, "wx_qr_url");
            return (Criteria) this;
        }

        public Criteria andWx_qr_urlGreaterThanOrEqualTo(String value) {
            addCriterion("wx_qr_url >=", value, "wx_qr_url");
            return (Criteria) this;
        }

        public Criteria andWx_qr_urlLessThan(String value) {
            addCriterion("wx_qr_url <", value, "wx_qr_url");
            return (Criteria) this;
        }

        public Criteria andWx_qr_urlLessThanOrEqualTo(String value) {
            addCriterion("wx_qr_url <=", value, "wx_qr_url");
            return (Criteria) this;
        }

        public Criteria andWx_qr_urlLike(String value) {
            addCriterion("wx_qr_url like", value, "wx_qr_url");
            return (Criteria) this;
        }

        public Criteria andWx_qr_urlNotLike(String value) {
            addCriterion("wx_qr_url not like", value, "wx_qr_url");
            return (Criteria) this;
        }

        public Criteria andWx_qr_urlIn(List<String> values) {
            addCriterion("wx_qr_url in", values, "wx_qr_url");
            return (Criteria) this;
        }

        public Criteria andWx_qr_urlNotIn(List<String> values) {
            addCriterion("wx_qr_url not in", values, "wx_qr_url");
            return (Criteria) this;
        }

        public Criteria andWx_qr_urlBetween(String value1, String value2) {
            addCriterion("wx_qr_url between", value1, value2, "wx_qr_url");
            return (Criteria) this;
        }

        public Criteria andWx_qr_urlNotBetween(String value1, String value2) {
            addCriterion("wx_qr_url not between", value1, value2, "wx_qr_url");
            return (Criteria) this;
        }

        public Criteria andBank_account_nameIsNull() {
            addCriterion("bank_account_name is null");
            return (Criteria) this;
        }

        public Criteria andBank_account_nameIsNotNull() {
            addCriterion("bank_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andBank_account_nameEqualTo(String value) {
            addCriterion("bank_account_name =", value, "bank_account_name");
            return (Criteria) this;
        }

        public Criteria andBank_account_nameNotEqualTo(String value) {
            addCriterion("bank_account_name <>", value, "bank_account_name");
            return (Criteria) this;
        }

        public Criteria andBank_account_nameGreaterThan(String value) {
            addCriterion("bank_account_name >", value, "bank_account_name");
            return (Criteria) this;
        }

        public Criteria andBank_account_nameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account_name >=", value, "bank_account_name");
            return (Criteria) this;
        }

        public Criteria andBank_account_nameLessThan(String value) {
            addCriterion("bank_account_name <", value, "bank_account_name");
            return (Criteria) this;
        }

        public Criteria andBank_account_nameLessThanOrEqualTo(String value) {
            addCriterion("bank_account_name <=", value, "bank_account_name");
            return (Criteria) this;
        }

        public Criteria andBank_account_nameLike(String value) {
            addCriterion("bank_account_name like", value, "bank_account_name");
            return (Criteria) this;
        }

        public Criteria andBank_account_nameNotLike(String value) {
            addCriterion("bank_account_name not like", value, "bank_account_name");
            return (Criteria) this;
        }

        public Criteria andBank_account_nameIn(List<String> values) {
            addCriterion("bank_account_name in", values, "bank_account_name");
            return (Criteria) this;
        }

        public Criteria andBank_account_nameNotIn(List<String> values) {
            addCriterion("bank_account_name not in", values, "bank_account_name");
            return (Criteria) this;
        }

        public Criteria andBank_account_nameBetween(String value1, String value2) {
            addCriterion("bank_account_name between", value1, value2, "bank_account_name");
            return (Criteria) this;
        }

        public Criteria andBank_account_nameNotBetween(String value1, String value2) {
            addCriterion("bank_account_name not between", value1, value2, "bank_account_name");
            return (Criteria) this;
        }

        public Criteria andBank_account_numIsNull() {
            addCriterion("bank_account_num is null");
            return (Criteria) this;
        }

        public Criteria andBank_account_numIsNotNull() {
            addCriterion("bank_account_num is not null");
            return (Criteria) this;
        }

        public Criteria andBank_account_numEqualTo(String value) {
            addCriterion("bank_account_num =", value, "bank_account_num");
            return (Criteria) this;
        }

        public Criteria andBank_account_numNotEqualTo(String value) {
            addCriterion("bank_account_num <>", value, "bank_account_num");
            return (Criteria) this;
        }

        public Criteria andBank_account_numGreaterThan(String value) {
            addCriterion("bank_account_num >", value, "bank_account_num");
            return (Criteria) this;
        }

        public Criteria andBank_account_numGreaterThanOrEqualTo(String value) {
            addCriterion("bank_account_num >=", value, "bank_account_num");
            return (Criteria) this;
        }

        public Criteria andBank_account_numLessThan(String value) {
            addCriterion("bank_account_num <", value, "bank_account_num");
            return (Criteria) this;
        }

        public Criteria andBank_account_numLessThanOrEqualTo(String value) {
            addCriterion("bank_account_num <=", value, "bank_account_num");
            return (Criteria) this;
        }

        public Criteria andBank_account_numLike(String value) {
            addCriterion("bank_account_num like", value, "bank_account_num");
            return (Criteria) this;
        }

        public Criteria andBank_account_numNotLike(String value) {
            addCriterion("bank_account_num not like", value, "bank_account_num");
            return (Criteria) this;
        }

        public Criteria andBank_account_numIn(List<String> values) {
            addCriterion("bank_account_num in", values, "bank_account_num");
            return (Criteria) this;
        }

        public Criteria andBank_account_numNotIn(List<String> values) {
            addCriterion("bank_account_num not in", values, "bank_account_num");
            return (Criteria) this;
        }

        public Criteria andBank_account_numBetween(String value1, String value2) {
            addCriterion("bank_account_num between", value1, value2, "bank_account_num");
            return (Criteria) this;
        }

        public Criteria andBank_account_numNotBetween(String value1, String value2) {
            addCriterion("bank_account_num not between", value1, value2, "bank_account_num");
            return (Criteria) this;
        }

        public Criteria andBank_nameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBank_nameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBank_nameEqualTo(String value) {
            addCriterion("bank_name =", value, "bank_name");
            return (Criteria) this;
        }

        public Criteria andBank_nameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bank_name");
            return (Criteria) this;
        }

        public Criteria andBank_nameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bank_name");
            return (Criteria) this;
        }

        public Criteria andBank_nameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bank_name");
            return (Criteria) this;
        }

        public Criteria andBank_nameLessThan(String value) {
            addCriterion("bank_name <", value, "bank_name");
            return (Criteria) this;
        }

        public Criteria andBank_nameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bank_name");
            return (Criteria) this;
        }

        public Criteria andBank_nameLike(String value) {
            addCriterion("bank_name like", value, "bank_name");
            return (Criteria) this;
        }

        public Criteria andBank_nameNotLike(String value) {
            addCriterion("bank_name not like", value, "bank_name");
            return (Criteria) this;
        }

        public Criteria andBank_nameIn(List<String> values) {
            addCriterion("bank_name in", values, "bank_name");
            return (Criteria) this;
        }

        public Criteria andBank_nameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bank_name");
            return (Criteria) this;
        }

        public Criteria andBank_nameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bank_name");
            return (Criteria) this;
        }

        public Criteria andBank_nameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bank_name");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_nameIsNull() {
            addCriterion("alipay_account_name is null");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_nameIsNotNull() {
            addCriterion("alipay_account_name is not null");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_nameEqualTo(String value) {
            addCriterion("alipay_account_name =", value, "alipay_account_name");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_nameNotEqualTo(String value) {
            addCriterion("alipay_account_name <>", value, "alipay_account_name");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_nameGreaterThan(String value) {
            addCriterion("alipay_account_name >", value, "alipay_account_name");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_nameGreaterThanOrEqualTo(String value) {
            addCriterion("alipay_account_name >=", value, "alipay_account_name");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_nameLessThan(String value) {
            addCriterion("alipay_account_name <", value, "alipay_account_name");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_nameLessThanOrEqualTo(String value) {
            addCriterion("alipay_account_name <=", value, "alipay_account_name");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_nameLike(String value) {
            addCriterion("alipay_account_name like", value, "alipay_account_name");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_nameNotLike(String value) {
            addCriterion("alipay_account_name not like", value, "alipay_account_name");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_nameIn(List<String> values) {
            addCriterion("alipay_account_name in", values, "alipay_account_name");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_nameNotIn(List<String> values) {
            addCriterion("alipay_account_name not in", values, "alipay_account_name");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_nameBetween(String value1, String value2) {
            addCriterion("alipay_account_name between", value1, value2, "alipay_account_name");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_nameNotBetween(String value1, String value2) {
            addCriterion("alipay_account_name not between", value1, value2, "alipay_account_name");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_numIsNull() {
            addCriterion("alipay_account_num is null");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_numIsNotNull() {
            addCriterion("alipay_account_num is not null");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_numEqualTo(String value) {
            addCriterion("alipay_account_num =", value, "alipay_account_num");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_numNotEqualTo(String value) {
            addCriterion("alipay_account_num <>", value, "alipay_account_num");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_numGreaterThan(String value) {
            addCriterion("alipay_account_num >", value, "alipay_account_num");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_numGreaterThanOrEqualTo(String value) {
            addCriterion("alipay_account_num >=", value, "alipay_account_num");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_numLessThan(String value) {
            addCriterion("alipay_account_num <", value, "alipay_account_num");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_numLessThanOrEqualTo(String value) {
            addCriterion("alipay_account_num <=", value, "alipay_account_num");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_numLike(String value) {
            addCriterion("alipay_account_num like", value, "alipay_account_num");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_numNotLike(String value) {
            addCriterion("alipay_account_num not like", value, "alipay_account_num");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_numIn(List<String> values) {
            addCriterion("alipay_account_num in", values, "alipay_account_num");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_numNotIn(List<String> values) {
            addCriterion("alipay_account_num not in", values, "alipay_account_num");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_numBetween(String value1, String value2) {
            addCriterion("alipay_account_num between", value1, value2, "alipay_account_num");
            return (Criteria) this;
        }

        public Criteria andAlipay_account_numNotBetween(String value1, String value2) {
            addCriterion("alipay_account_num not between", value1, value2, "alipay_account_num");
            return (Criteria) this;
        }

        public Criteria andLabel_idsIsNull() {
            addCriterion("label_ids is null");
            return (Criteria) this;
        }

        public Criteria andLabel_idsIsNotNull() {
            addCriterion("label_ids is not null");
            return (Criteria) this;
        }

        public Criteria andLabel_idsEqualTo(String value) {
            addCriterion("label_ids =", value, "label_ids");
            return (Criteria) this;
        }

        public Criteria andLabel_idsNotEqualTo(String value) {
            addCriterion("label_ids <>", value, "label_ids");
            return (Criteria) this;
        }

        public Criteria andLabel_idsGreaterThan(String value) {
            addCriterion("label_ids >", value, "label_ids");
            return (Criteria) this;
        }

        public Criteria andLabel_idsGreaterThanOrEqualTo(String value) {
            addCriterion("label_ids >=", value, "label_ids");
            return (Criteria) this;
        }

        public Criteria andLabel_idsLessThan(String value) {
            addCriterion("label_ids <", value, "label_ids");
            return (Criteria) this;
        }

        public Criteria andLabel_idsLessThanOrEqualTo(String value) {
            addCriterion("label_ids <=", value, "label_ids");
            return (Criteria) this;
        }

        public Criteria andLabel_idsLike(String value) {
            addCriterion("label_ids like", value, "label_ids");
            return (Criteria) this;
        }

        public Criteria andLabel_idsNotLike(String value) {
            addCriterion("label_ids not like", value, "label_ids");
            return (Criteria) this;
        }

        public Criteria andLabel_idsIn(List<String> values) {
            addCriterion("label_ids in", values, "label_ids");
            return (Criteria) this;
        }

        public Criteria andLabel_idsNotIn(List<String> values) {
            addCriterion("label_ids not in", values, "label_ids");
            return (Criteria) this;
        }

        public Criteria andLabel_idsBetween(String value1, String value2) {
            addCriterion("label_ids between", value1, value2, "label_ids");
            return (Criteria) this;
        }

        public Criteria andLabel_idsNotBetween(String value1, String value2) {
            addCriterion("label_ids not between", value1, value2, "label_ids");
            return (Criteria) this;
        }

        public Criteria andFile_pathIsNull() {
            addCriterion("file_path is null");
            return (Criteria) this;
        }

        public Criteria andFile_pathIsNotNull() {
            addCriterion("file_path is not null");
            return (Criteria) this;
        }

        public Criteria andFile_pathEqualTo(String value) {
            addCriterion("file_path =", value, "file_path");
            return (Criteria) this;
        }

        public Criteria andFile_pathNotEqualTo(String value) {
            addCriterion("file_path <>", value, "file_path");
            return (Criteria) this;
        }

        public Criteria andFile_pathGreaterThan(String value) {
            addCriterion("file_path >", value, "file_path");
            return (Criteria) this;
        }

        public Criteria andFile_pathGreaterThanOrEqualTo(String value) {
            addCriterion("file_path >=", value, "file_path");
            return (Criteria) this;
        }

        public Criteria andFile_pathLessThan(String value) {
            addCriterion("file_path <", value, "file_path");
            return (Criteria) this;
        }

        public Criteria andFile_pathLessThanOrEqualTo(String value) {
            addCriterion("file_path <=", value, "file_path");
            return (Criteria) this;
        }

        public Criteria andFile_pathLike(String value) {
            addCriterion("file_path like", value, "file_path");
            return (Criteria) this;
        }

        public Criteria andFile_pathNotLike(String value) {
            addCriterion("file_path not like", value, "file_path");
            return (Criteria) this;
        }

        public Criteria andFile_pathIn(List<String> values) {
            addCriterion("file_path in", values, "file_path");
            return (Criteria) this;
        }

        public Criteria andFile_pathNotIn(List<String> values) {
            addCriterion("file_path not in", values, "file_path");
            return (Criteria) this;
        }

        public Criteria andFile_pathBetween(String value1, String value2) {
            addCriterion("file_path between", value1, value2, "file_path");
            return (Criteria) this;
        }

        public Criteria andFile_pathNotBetween(String value1, String value2) {
            addCriterion("file_path not between", value1, value2, "file_path");
            return (Criteria) this;
        }

        public Criteria andContract_pathIsNull() {
            addCriterion("contract_path is null");
            return (Criteria) this;
        }

        public Criteria andContract_pathIsNotNull() {
            addCriterion("contract_path is not null");
            return (Criteria) this;
        }

        public Criteria andContract_pathEqualTo(String value) {
            addCriterion("contract_path =", value, "contract_path");
            return (Criteria) this;
        }

        public Criteria andContract_pathNotEqualTo(String value) {
            addCriterion("contract_path <>", value, "contract_path");
            return (Criteria) this;
        }

        public Criteria andContract_pathGreaterThan(String value) {
            addCriterion("contract_path >", value, "contract_path");
            return (Criteria) this;
        }

        public Criteria andContract_pathGreaterThanOrEqualTo(String value) {
            addCriterion("contract_path >=", value, "contract_path");
            return (Criteria) this;
        }

        public Criteria andContract_pathLessThan(String value) {
            addCriterion("contract_path <", value, "contract_path");
            return (Criteria) this;
        }

        public Criteria andContract_pathLessThanOrEqualTo(String value) {
            addCriterion("contract_path <=", value, "contract_path");
            return (Criteria) this;
        }

        public Criteria andContract_pathLike(String value) {
            addCriterion("contract_path like", value, "contract_path");
            return (Criteria) this;
        }

        public Criteria andContract_pathNotLike(String value) {
            addCriterion("contract_path not like", value, "contract_path");
            return (Criteria) this;
        }

        public Criteria andContract_pathIn(List<String> values) {
            addCriterion("contract_path in", values, "contract_path");
            return (Criteria) this;
        }

        public Criteria andContract_pathNotIn(List<String> values) {
            addCriterion("contract_path not in", values, "contract_path");
            return (Criteria) this;
        }

        public Criteria andContract_pathBetween(String value1, String value2) {
            addCriterion("contract_path between", value1, value2, "contract_path");
            return (Criteria) this;
        }

        public Criteria andContract_pathNotBetween(String value1, String value2) {
            addCriterion("contract_path not between", value1, value2, "contract_path");
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
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}
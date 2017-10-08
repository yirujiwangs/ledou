package model;

import model.divide.BaseDivideModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProxyAreaReportLogExample extends BaseDivideModel{
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProxyAreaReportLogExample() {
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

        public Criteria andArea_ridIsNull() {
            addCriterion("area_rid is null");
            return (Criteria) this;
        }

        public Criteria andArea_ridIsNotNull() {
            addCriterion("area_rid is not null");
            return (Criteria) this;
        }

        public Criteria andArea_ridEqualTo(String value) {
            addCriterion("area_rid =", value, "area_rid");
            return (Criteria) this;
        }

        public Criteria andArea_ridNotEqualTo(String value) {
            addCriterion("area_rid <>", value, "area_rid");
            return (Criteria) this;
        }

        public Criteria andArea_ridGreaterThan(String value) {
            addCriterion("area_rid >", value, "area_rid");
            return (Criteria) this;
        }

        public Criteria andArea_ridGreaterThanOrEqualTo(String value) {
            addCriterion("area_rid >=", value, "area_rid");
            return (Criteria) this;
        }

        public Criteria andArea_ridLessThan(String value) {
            addCriterion("area_rid <", value, "area_rid");
            return (Criteria) this;
        }

        public Criteria andArea_ridLessThanOrEqualTo(String value) {
            addCriterion("area_rid <=", value, "area_rid");
            return (Criteria) this;
        }

        public Criteria andArea_ridLike(String value) {
            addCriterion("area_rid like", value, "area_rid");
            return (Criteria) this;
        }

        public Criteria andArea_ridNotLike(String value) {
            addCriterion("area_rid not like", value, "area_rid");
            return (Criteria) this;
        }

        public Criteria andArea_ridIn(List<String> values) {
            addCriterion("area_rid in", values, "area_rid");
            return (Criteria) this;
        }

        public Criteria andArea_ridNotIn(List<String> values) {
            addCriterion("area_rid not in", values, "area_rid");
            return (Criteria) this;
        }

        public Criteria andArea_ridBetween(String value1, String value2) {
            addCriterion("area_rid between", value1, value2, "area_rid");
            return (Criteria) this;
        }

        public Criteria andArea_ridNotBetween(String value1, String value2) {
            addCriterion("area_rid not between", value1, value2, "area_rid");
            return (Criteria) this;
        }

        public Criteria andArea_nameIsNull() {
            addCriterion("area_name is null");
            return (Criteria) this;
        }

        public Criteria andArea_nameIsNotNull() {
            addCriterion("area_name is not null");
            return (Criteria) this;
        }

        public Criteria andArea_nameEqualTo(String value) {
            addCriterion("area_name =", value, "area_name");
            return (Criteria) this;
        }

        public Criteria andArea_nameNotEqualTo(String value) {
            addCriterion("area_name <>", value, "area_name");
            return (Criteria) this;
        }

        public Criteria andArea_nameGreaterThan(String value) {
            addCriterion("area_name >", value, "area_name");
            return (Criteria) this;
        }

        public Criteria andArea_nameGreaterThanOrEqualTo(String value) {
            addCriterion("area_name >=", value, "area_name");
            return (Criteria) this;
        }

        public Criteria andArea_nameLessThan(String value) {
            addCriterion("area_name <", value, "area_name");
            return (Criteria) this;
        }

        public Criteria andArea_nameLessThanOrEqualTo(String value) {
            addCriterion("area_name <=", value, "area_name");
            return (Criteria) this;
        }

        public Criteria andArea_nameLike(String value) {
            addCriterion("area_name like", value, "area_name");
            return (Criteria) this;
        }

        public Criteria andArea_nameNotLike(String value) {
            addCriterion("area_name not like", value, "area_name");
            return (Criteria) this;
        }

        public Criteria andArea_nameIn(List<String> values) {
            addCriterion("area_name in", values, "area_name");
            return (Criteria) this;
        }

        public Criteria andArea_nameNotIn(List<String> values) {
            addCriterion("area_name not in", values, "area_name");
            return (Criteria) this;
        }

        public Criteria andArea_nameBetween(String value1, String value2) {
            addCriterion("area_name between", value1, value2, "area_name");
            return (Criteria) this;
        }

        public Criteria andArea_nameNotBetween(String value1, String value2) {
            addCriterion("area_name not between", value1, value2, "area_name");
            return (Criteria) this;
        }

        public Criteria andStart_dateIsNull() {
            addCriterion("start_date is null");
            return (Criteria) this;
        }

        public Criteria andStart_dateIsNotNull() {
            addCriterion("start_date is not null");
            return (Criteria) this;
        }

        public Criteria andStart_dateEqualTo(Date value) {
            addCriterionForJDBCDate("start_date =", value, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateNotEqualTo(Date value) {
            addCriterionForJDBCDate("start_date <>", value, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateGreaterThan(Date value) {
            addCriterionForJDBCDate("start_date >", value, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_date >=", value, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateLessThan(Date value) {
            addCriterionForJDBCDate("start_date <", value, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("start_date <=", value, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateIn(List<Date> values) {
            addCriterionForJDBCDate("start_date in", values, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateNotIn(List<Date> values) {
            addCriterionForJDBCDate("start_date not in", values, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_date between", value1, value2, "start_date");
            return (Criteria) this;
        }

        public Criteria andStart_dateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("start_date not between", value1, value2, "start_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateIsNull() {
            addCriterion("end_date is null");
            return (Criteria) this;
        }

        public Criteria andEnd_dateIsNotNull() {
            addCriterion("end_date is not null");
            return (Criteria) this;
        }

        public Criteria andEnd_dateEqualTo(Date value) {
            addCriterionForJDBCDate("end_date =", value, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateNotEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <>", value, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateGreaterThan(Date value) {
            addCriterionForJDBCDate("end_date >", value, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date >=", value, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateLessThan(Date value) {
            addCriterionForJDBCDate("end_date <", value, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("end_date <=", value, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateIn(List<Date> values) {
            addCriterionForJDBCDate("end_date in", values, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateNotIn(List<Date> values) {
            addCriterionForJDBCDate("end_date not in", values, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date between", value1, value2, "end_date");
            return (Criteria) this;
        }

        public Criteria andEnd_dateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("end_date not between", value1, value2, "end_date");
            return (Criteria) this;
        }

        public Criteria andProxy_levelIsNull() {
            addCriterion("proxy_level is null");
            return (Criteria) this;
        }

        public Criteria andProxy_levelIsNotNull() {
            addCriterion("proxy_level is not null");
            return (Criteria) this;
        }

        public Criteria andProxy_levelEqualTo(Integer value) {
            addCriterion("proxy_level =", value, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelNotEqualTo(Integer value) {
            addCriterion("proxy_level <>", value, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelGreaterThan(Integer value) {
            addCriterion("proxy_level >", value, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelGreaterThanOrEqualTo(Integer value) {
            addCriterion("proxy_level >=", value, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelLessThan(Integer value) {
            addCriterion("proxy_level <", value, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelLessThanOrEqualTo(Integer value) {
            addCriterion("proxy_level <=", value, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelIn(List<Integer> values) {
            addCriterion("proxy_level in", values, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelNotIn(List<Integer> values) {
            addCriterion("proxy_level not in", values, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelBetween(Integer value1, Integer value2) {
            addCriterion("proxy_level between", value1, value2, "proxy_level");
            return (Criteria) this;
        }

        public Criteria andProxy_levelNotBetween(Integer value1, Integer value2) {
            addCriterion("proxy_level not between", value1, value2, "proxy_level");
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

        public Criteria andCompanyIsNull() {
            addCriterion("company is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIsNotNull() {
            addCriterion("company is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyEqualTo(String value) {
            addCriterion("company =", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotEqualTo(String value) {
            addCriterion("company <>", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThan(String value) {
            addCriterion("company >", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("company >=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThan(String value) {
            addCriterion("company <", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLessThanOrEqualTo(String value) {
            addCriterion("company <=", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyLike(String value) {
            addCriterion("company like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotLike(String value) {
            addCriterion("company not like", value, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyIn(List<String> values) {
            addCriterion("company in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotIn(List<String> values) {
            addCriterion("company not in", values, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyBetween(String value1, String value2) {
            addCriterion("company between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andCompanyNotBetween(String value1, String value2) {
            addCriterion("company not between", value1, value2, "company");
            return (Criteria) this;
        }

        public Criteria andContact_nameIsNull() {
            addCriterion("contact_name is null");
            return (Criteria) this;
        }

        public Criteria andContact_nameIsNotNull() {
            addCriterion("contact_name is not null");
            return (Criteria) this;
        }

        public Criteria andContact_nameEqualTo(String value) {
            addCriterion("contact_name =", value, "contact_name");
            return (Criteria) this;
        }

        public Criteria andContact_nameNotEqualTo(String value) {
            addCriterion("contact_name <>", value, "contact_name");
            return (Criteria) this;
        }

        public Criteria andContact_nameGreaterThan(String value) {
            addCriterion("contact_name >", value, "contact_name");
            return (Criteria) this;
        }

        public Criteria andContact_nameGreaterThanOrEqualTo(String value) {
            addCriterion("contact_name >=", value, "contact_name");
            return (Criteria) this;
        }

        public Criteria andContact_nameLessThan(String value) {
            addCriterion("contact_name <", value, "contact_name");
            return (Criteria) this;
        }

        public Criteria andContact_nameLessThanOrEqualTo(String value) {
            addCriterion("contact_name <=", value, "contact_name");
            return (Criteria) this;
        }

        public Criteria andContact_nameLike(String value) {
            addCriterion("contact_name like", value, "contact_name");
            return (Criteria) this;
        }

        public Criteria andContact_nameNotLike(String value) {
            addCriterion("contact_name not like", value, "contact_name");
            return (Criteria) this;
        }

        public Criteria andContact_nameIn(List<String> values) {
            addCriterion("contact_name in", values, "contact_name");
            return (Criteria) this;
        }

        public Criteria andContact_nameNotIn(List<String> values) {
            addCriterion("contact_name not in", values, "contact_name");
            return (Criteria) this;
        }

        public Criteria andContact_nameBetween(String value1, String value2) {
            addCriterion("contact_name between", value1, value2, "contact_name");
            return (Criteria) this;
        }

        public Criteria andContact_nameNotBetween(String value1, String value2) {
            addCriterion("contact_name not between", value1, value2, "contact_name");
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
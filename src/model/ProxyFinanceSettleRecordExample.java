package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ProxyFinanceSettleRecordExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProxyFinanceSettleRecordExample() {
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

        public Criteria andStimeIsNull() {
            addCriterion("stime is null");
            return (Criteria) this;
        }

        public Criteria andStimeIsNotNull() {
            addCriterion("stime is not null");
            return (Criteria) this;
        }

        public Criteria andStimeEqualTo(Date value) {
            addCriterionForJDBCDate("stime =", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("stime <>", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeGreaterThan(Date value) {
            addCriterionForJDBCDate("stime >", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("stime >=", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeLessThan(Date value) {
            addCriterionForJDBCDate("stime <", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("stime <=", value, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeIn(List<Date> values) {
            addCriterionForJDBCDate("stime in", values, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("stime not in", values, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("stime between", value1, value2, "stime");
            return (Criteria) this;
        }

        public Criteria andStimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("stime not between", value1, value2, "stime");
            return (Criteria) this;
        }

        public Criteria andEtimeIsNull() {
            addCriterion("etime is null");
            return (Criteria) this;
        }

        public Criteria andEtimeIsNotNull() {
            addCriterion("etime is not null");
            return (Criteria) this;
        }

        public Criteria andEtimeEqualTo(Date value) {
            addCriterionForJDBCDate("etime =", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("etime <>", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeGreaterThan(Date value) {
            addCriterionForJDBCDate("etime >", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("etime >=", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeLessThan(Date value) {
            addCriterionForJDBCDate("etime <", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("etime <=", value, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeIn(List<Date> values) {
            addCriterionForJDBCDate("etime in", values, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("etime not in", values, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("etime between", value1, value2, "etime");
            return (Criteria) this;
        }

        public Criteria andEtimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("etime not between", value1, value2, "etime");
            return (Criteria) this;
        }

        public Criteria andJob_idIsNull() {
            addCriterion("job_id is null");
            return (Criteria) this;
        }

        public Criteria andJob_idIsNotNull() {
            addCriterion("job_id is not null");
            return (Criteria) this;
        }

        public Criteria andJob_idEqualTo(String value) {
            addCriterion("job_id =", value, "job_id");
            return (Criteria) this;
        }

        public Criteria andJob_idNotEqualTo(String value) {
            addCriterion("job_id <>", value, "job_id");
            return (Criteria) this;
        }

        public Criteria andJob_idGreaterThan(String value) {
            addCriterion("job_id >", value, "job_id");
            return (Criteria) this;
        }

        public Criteria andJob_idGreaterThanOrEqualTo(String value) {
            addCriterion("job_id >=", value, "job_id");
            return (Criteria) this;
        }

        public Criteria andJob_idLessThan(String value) {
            addCriterion("job_id <", value, "job_id");
            return (Criteria) this;
        }

        public Criteria andJob_idLessThanOrEqualTo(String value) {
            addCriterion("job_id <=", value, "job_id");
            return (Criteria) this;
        }

        public Criteria andJob_idLike(String value) {
            addCriterion("job_id like", value, "job_id");
            return (Criteria) this;
        }

        public Criteria andJob_idNotLike(String value) {
            addCriterion("job_id not like", value, "job_id");
            return (Criteria) this;
        }

        public Criteria andJob_idIn(List<String> values) {
            addCriterion("job_id in", values, "job_id");
            return (Criteria) this;
        }

        public Criteria andJob_idNotIn(List<String> values) {
            addCriterion("job_id not in", values, "job_id");
            return (Criteria) this;
        }

        public Criteria andJob_idBetween(String value1, String value2) {
            addCriterion("job_id between", value1, value2, "job_id");
            return (Criteria) this;
        }

        public Criteria andJob_idNotBetween(String value1, String value2) {
            addCriterion("job_id not between", value1, value2, "job_id");
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

        public Criteria andProxy_amountIsNull() {
            addCriterion("proxy_amount is null");
            return (Criteria) this;
        }

        public Criteria andProxy_amountIsNotNull() {
            addCriterion("proxy_amount is not null");
            return (Criteria) this;
        }

        public Criteria andProxy_amountEqualTo(Integer value) {
            addCriterion("proxy_amount =", value, "proxy_amount");
            return (Criteria) this;
        }

        public Criteria andProxy_amountNotEqualTo(Integer value) {
            addCriterion("proxy_amount <>", value, "proxy_amount");
            return (Criteria) this;
        }

        public Criteria andProxy_amountGreaterThan(Integer value) {
            addCriterion("proxy_amount >", value, "proxy_amount");
            return (Criteria) this;
        }

        public Criteria andProxy_amountGreaterThanOrEqualTo(Integer value) {
            addCriterion("proxy_amount >=", value, "proxy_amount");
            return (Criteria) this;
        }

        public Criteria andProxy_amountLessThan(Integer value) {
            addCriterion("proxy_amount <", value, "proxy_amount");
            return (Criteria) this;
        }

        public Criteria andProxy_amountLessThanOrEqualTo(Integer value) {
            addCriterion("proxy_amount <=", value, "proxy_amount");
            return (Criteria) this;
        }

        public Criteria andProxy_amountIn(List<Integer> values) {
            addCriterion("proxy_amount in", values, "proxy_amount");
            return (Criteria) this;
        }

        public Criteria andProxy_amountNotIn(List<Integer> values) {
            addCriterion("proxy_amount not in", values, "proxy_amount");
            return (Criteria) this;
        }

        public Criteria andProxy_amountBetween(Integer value1, Integer value2) {
            addCriterion("proxy_amount between", value1, value2, "proxy_amount");
            return (Criteria) this;
        }

        public Criteria andProxy_amountNotBetween(Integer value1, Integer value2) {
            addCriterion("proxy_amount not between", value1, value2, "proxy_amount");
            return (Criteria) this;
        }

        public Criteria andProxy_price_totalIsNull() {
            addCriterion("proxy_price_total is null");
            return (Criteria) this;
        }

        public Criteria andProxy_price_totalIsNotNull() {
            addCriterion("proxy_price_total is not null");
            return (Criteria) this;
        }

        public Criteria andProxy_price_totalEqualTo(Integer value) {
            addCriterion("proxy_price_total =", value, "proxy_price_total");
            return (Criteria) this;
        }

        public Criteria andProxy_price_totalNotEqualTo(Integer value) {
            addCriterion("proxy_price_total <>", value, "proxy_price_total");
            return (Criteria) this;
        }

        public Criteria andProxy_price_totalGreaterThan(Integer value) {
            addCriterion("proxy_price_total >", value, "proxy_price_total");
            return (Criteria) this;
        }

        public Criteria andProxy_price_totalGreaterThanOrEqualTo(Integer value) {
            addCriterion("proxy_price_total >=", value, "proxy_price_total");
            return (Criteria) this;
        }

        public Criteria andProxy_price_totalLessThan(Integer value) {
            addCriterion("proxy_price_total <", value, "proxy_price_total");
            return (Criteria) this;
        }

        public Criteria andProxy_price_totalLessThanOrEqualTo(Integer value) {
            addCriterion("proxy_price_total <=", value, "proxy_price_total");
            return (Criteria) this;
        }

        public Criteria andProxy_price_totalIn(List<Integer> values) {
            addCriterion("proxy_price_total in", values, "proxy_price_total");
            return (Criteria) this;
        }

        public Criteria andProxy_price_totalNotIn(List<Integer> values) {
            addCriterion("proxy_price_total not in", values, "proxy_price_total");
            return (Criteria) this;
        }

        public Criteria andProxy_price_totalBetween(Integer value1, Integer value2) {
            addCriterion("proxy_price_total between", value1, value2, "proxy_price_total");
            return (Criteria) this;
        }

        public Criteria andProxy_price_totalNotBetween(Integer value1, Integer value2) {
            addCriterion("proxy_price_total not between", value1, value2, "proxy_price_total");
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
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class LogisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public LogisticsExample() {
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

        public Criteria andLogistic_companyIsNull() {
            addCriterion("logistic_company is null");
            return (Criteria) this;
        }

        public Criteria andLogistic_companyIsNotNull() {
            addCriterion("logistic_company is not null");
            return (Criteria) this;
        }

        public Criteria andLogistic_companyEqualTo(String value) {
            addCriterion("logistic_company =", value, "logistic_company");
            return (Criteria) this;
        }

        public Criteria andLogistic_companyNotEqualTo(String value) {
            addCriterion("logistic_company <>", value, "logistic_company");
            return (Criteria) this;
        }

        public Criteria andLogistic_companyGreaterThan(String value) {
            addCriterion("logistic_company >", value, "logistic_company");
            return (Criteria) this;
        }

        public Criteria andLogistic_companyGreaterThanOrEqualTo(String value) {
            addCriterion("logistic_company >=", value, "logistic_company");
            return (Criteria) this;
        }

        public Criteria andLogistic_companyLessThan(String value) {
            addCriterion("logistic_company <", value, "logistic_company");
            return (Criteria) this;
        }

        public Criteria andLogistic_companyLessThanOrEqualTo(String value) {
            addCriterion("logistic_company <=", value, "logistic_company");
            return (Criteria) this;
        }

        public Criteria andLogistic_companyLike(String value) {
            addCriterion("logistic_company like", value, "logistic_company");
            return (Criteria) this;
        }

        public Criteria andLogistic_companyNotLike(String value) {
            addCriterion("logistic_company not like", value, "logistic_company");
            return (Criteria) this;
        }

        public Criteria andLogistic_companyIn(List<String> values) {
            addCriterion("logistic_company in", values, "logistic_company");
            return (Criteria) this;
        }

        public Criteria andLogistic_companyNotIn(List<String> values) {
            addCriterion("logistic_company not in", values, "logistic_company");
            return (Criteria) this;
        }

        public Criteria andLogistic_companyBetween(String value1, String value2) {
            addCriterion("logistic_company between", value1, value2, "logistic_company");
            return (Criteria) this;
        }

        public Criteria andLogistic_companyNotBetween(String value1, String value2) {
            addCriterion("logistic_company not between", value1, value2, "logistic_company");
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
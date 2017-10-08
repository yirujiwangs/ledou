package model.divide;

import java.util.List;

/**
 * Created by yeran on 2016/8/12.
 */
public class Criterion {
    private String condition;
    private String condition2;

    private Object value;

    private Object secondValue;

    private boolean noValue;

    private boolean singleValue;

    private boolean betweenValue;

    private boolean listValue;

    private boolean likeValue;

    private String typeHandler;

    public String getCondition() {
        return condition;
    }
    public String getCondition2() {
        return condition2;
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

    public boolean isLikeValue() {
        return likeValue;
    }

    public String getTypeHandler() {
        return typeHandler;
    }

    public Criterion(String condition) {
        super();
        this.condition = condition;
        this.typeHandler = null;
        this.noValue = true;
    }

    public Criterion(String condition, Object value, String typeHandler) {
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

    public Criterion(String condition, Object value) {
        this(condition, value, null);
    }

    public Criterion(String condition, String condition2, Object value, String typeHandler) {
        super();
        this.condition = condition;
        this.condition2 = condition2;
        this.value = value;
        this.typeHandler = typeHandler;

        if ("like".equals(typeHandler)) {
            this.likeValue = true;
        }
    }

    public Criterion(String condition, Object value, Object secondValue, String typeHandler) {
        super();
        this.condition = condition;
        this.value = value;
        this.secondValue = secondValue;
        this.typeHandler = typeHandler;
        this.betweenValue = true;
    }

    public Criterion(String condition, Object value, Object secondValue) {
        this(condition, value, secondValue, null);
    }
}
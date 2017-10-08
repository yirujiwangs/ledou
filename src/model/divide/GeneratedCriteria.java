package model.divide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeran on 2016/8/12.
 */
public class GeneratedCriteria {

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
        criteria.add(new Criterion(condition, value1, value2, property));
    }

    protected void addCriterion(String condition, String property) {
        if (condition == null || property == null) {
            throw new RuntimeException("condition  for " + property + " cannot be null");
        }
        criteria.add(new Criterion(condition, null, property));
    }

    protected void addCriterion(String condition,String condition2,Object value, String property) {
        if (condition == null || property == null) {
            throw new RuntimeException("condition  for " + property + " cannot be null");
        }
        criteria.add(new Criterion(condition,condition2, value, property));
    }

    public GeneratedCriteria andLike(String[] strs, String keyword) {
        StringBuilder stringBuilder = new StringBuilder();
        if (strs != null && strs.length > 0) {
            stringBuilder.append("concat_ws(','");
            for (int i = 0; i < strs.length; i++) {
                String str = strs[i];
                stringBuilder.append("," + str);
            }
            stringBuilder.append(")");
        }
        addCriterion("locate(", "," + stringBuilder.toString() + ")>0",keyword, "like");
        return this;
    }

}




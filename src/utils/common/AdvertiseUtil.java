package utils.common;

/**
 * Created by Administrator on 2016/10/7.
 */
public class AdvertiseUtil {
    public static Integer calculateAdvBudget(String withdralType, int onceMoney, int touNum){
        int advBudget = 0;
        if("O".equals(withdralType)){
            advBudget = onceMoney * touNum;
        }
        else if("Q".equals(withdralType)){
            advBudget = onceMoney/1000 * touNum;
        }

        return advBudget;
    }
}

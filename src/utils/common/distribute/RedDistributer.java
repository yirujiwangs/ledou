package utils.common.distribute;

import java.security.SecureRandom;

/**
 * Created by yeran on 2016/11/5.
 * <p/>
 * 红包分配算法
 * 根据红包总的可摇次数、用户已经摇动次数、用户已经摇中红包次数、红包剩余次数等参数
 * 进行判断，用户本次摇动是否摇中红包
 * 0：未摇中
 * 1：摇中红包
 * -1：错误
 */
public class RedDistributer {

    /**
     * 命中红包
     */
    public static final Integer RED_HIT = 0X1;
    /**
     * 未抽取到红包
     */
    public static final Integer RED_MISS = 0X0;


    public static Integer distribute(Integer totalTimes, Integer redCanTimes, Integer shakedTimes, Integer redGetTimes) {
        return distribute(totalTimes, redCanTimes, shakedTimes, redGetTimes, null);
    }

    /**
     * @param totalTimes  总的可摇动次数
     * @param redCanTimes 红包可以摇中的总次数
     * @param shakedTimes 用户已经摇动的次数
     * @param redGetTimes 用户已经摇中的红包次数
     * @return 抽取结果，1代表抽中，0代表未抽中
     * @since 1.0
     */
    public static Integer distribute(Integer totalTimes, Integer redCanTimes, Integer shakedTimes, Integer redGetTimes, DistributeHandler distributeHandler) {
        if (totalTimes <= 0 || redCanTimes <= 0 || redCanTimes > totalTimes) {
            if (distributeHandler != null)
                distributeHandler.handleDistributeResult(RED_MISS);
            return RED_MISS;
        }
        Integer leftShakeTimes = totalTimes - shakedTimes;//剩余摇动次数
        Integer leftRedTimes = redCanTimes - redGetTimes;//剩余红包可以领取次数
        if (leftShakeTimes <= 0 || leftRedTimes <= 0) {
            if (distributeHandler != null)
                distributeHandler.handleDistributeResult(RED_MISS);
            return RED_MISS;
        }
        if (leftRedTimes >= leftShakeTimes) {
            //红包剩余可摇次数>=剩余摇动次数，则必定此次摇中红包，返回1
            if (distributeHandler != null)
                distributeHandler.handleDistributeResult(RED_HIT);
            return RED_HIT;
        } else {
            Integer result = random();
            if (distributeHandler != null)
                distributeHandler.handleDistributeResult(result);
            return result;
        }

    }


    public static Integer random() {
    //    int a = (int) (Math.random() * 2);
        return new SecureRandom().nextInt(2);

    }
}

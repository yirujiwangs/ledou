package utils.common.distribute;

import model.base.BaseResult;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by yeran on 2016/11/5.
 */

public class RedDistributerTest {
    @Test
    public void testDistribute() throws Exception {
        Integer totalTimes = 5;
        final Integer[] redCanTimes = {3};
        final Integer[] shakedTimes = {0};
        final Integer[] redGetTimes = {0};
        for (int i = 0; i < 5; i++) {
            Integer result = RedDistributer.distribute(totalTimes, redCanTimes[0], shakedTimes[0], redGetTimes[0], new DistributeHandler() {
                @Override
                public void handleDistributeResult(Integer result) {
                    System.out.println("distribute result = " + (result == 1 ? "抽中" : "未抽中"));
                    shakedTimes[0]++;
                    System.out.println("shakedTimes=" + shakedTimes[0]);
                    if (RedDistributer.RED_HIT.equals(result)) {
                        //增加红包抽取记录，增加摇动次数记录
                        redGetTimes[0]++;
                        System.out.println("redGetTimes==" + redGetTimes[0]);
                    } else {
                    }
                }
            });
        }
    }

    @Test
    public void testDistribute1() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println( RedDistributer.random());
        }
    }
}
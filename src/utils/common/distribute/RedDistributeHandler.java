package utils.common.distribute;

import model.base.BaseResult;

/**
 * Created by yeran on 2016/11/5.
 *
 * default：红包分发抽取处理机制
 */
public class RedDistributeHandler implements DistributeHandler {

    private BaseResult baseResult;

    @Override
    public void handleDistributeResult(Integer result) {
        this.baseResult = new BaseResult(BaseResult.RESULT_SUCCESS,"distribute result = "+result);
    }

    public BaseResult getBaseResult(){
        return baseResult;
    }
}

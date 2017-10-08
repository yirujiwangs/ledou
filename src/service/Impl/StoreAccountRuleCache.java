package service.Impl;

import dao.StoreAccountRuleMapper;
import model.StoreAccountRule;
import model.StoreAccountRuleExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yeran on 2016/8/11.
 */
@Service(value = "storeAccountRuleCache")
public class StoreAccountRuleCache {

    private static Map<Integer, StoreAccountRule> storeAccountRule;

    @Autowired
    private StoreAccountRuleMapper storeAccountRuleMapper;

    public StoreAccountRule getStoreAccountRuleByType(int type) {
        if (storeAccountRule == null) {
            storeAccountRule = new HashMap<>();
        }

      //  if (!storeAccountRule.containsKey(type))
        {
            //System.out.println("init storeAccountRule from db");
            StoreAccountRuleExample storeAccountRuleExample = new StoreAccountRuleExample();
            storeAccountRuleExample.createCriteria().andStore_typeEqualTo(type);
            List<StoreAccountRule> storeAccountRules = storeAccountRuleMapper.selectByExample(storeAccountRuleExample);

           if (storeAccountRules != null && storeAccountRules.size() > 0)
               return  storeAccountRules.get(0);
//                storeAccountRule.put(type, storeAccountRules.get(0));
        }

        return null;
    }

}

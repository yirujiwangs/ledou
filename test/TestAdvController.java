import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.xpath.internal.SourceTree;
import controller.AdvertiseController;
import model.base.BaseResult;
import org.junit.Test;
import utils.api.AdControlClient;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/5.
 */
public class TestAdvController {



    @Test
    public void TestpicAdvPost(){

        List<String> utokens = new ArrayList<>();
        utokens.add("d6077adb440649fe94e6891343c14868");

       BaseResult baseResult =  AdControlClient.adBenefit(null, utokens);

        System.out.println(JSONObject.toJSONString(baseResult));
    }
}

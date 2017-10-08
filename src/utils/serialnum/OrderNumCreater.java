package utils.serialnum;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yeran on 2017/4/28.
 */
public class OrderNumCreater extends BaseSerialNumCreater {

    @Override
    public String create() {

        String tradeNo = ""+System.currentTimeMillis()+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
                            + (10000 + new SecureRandom().nextInt(89999));
        return tradeNo;
    }
}

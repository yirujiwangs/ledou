package utils.secret;

import org.junit.Test;
import utils.serialnum.OrderNumCreater;
import utils.serialnum.SerialNumCreater;
import weixin.popular.util.PayUtil;

/**
 * Created by yeran on 2016/10/27.
 */
public class SecretCoderTest {


    @Test
    public void testDecode() throws Exception {
      String psw = "123456";
       Secret secret = SecretCoder.encode(psw, SecretCoder.ENCODE_TYPE.PRIVATE);
        if (secret!=null){
            System.out.println(secret.getKey());
            System.out.println(secret.getResult());
        }
        String password = SecretCoder.decode(secret.getResult(), SecretCoder.ENCODE_TYPE.PRIVATE,secret.getKey());
        System.out.println(password);
//        ledouya892887400
//        UuUwRTMOISMFZ+esapjRYw==
//        abcde9231
    }

    @Test
    public void testEncoder_normal() throws Exception {
         String encodeStr = "wSCNL8/caQeJjRBTrFWxEQ==";
        String key = "ledouya868457301";
        String password = SecretCoder.decode(encodeStr, SecretCoder.ENCODE_TYPE.PRIVATE,key);
        System.out.println(password);
        //abcde
    }

    @Test
    public void tradeNoTest(){
       // System.out.println(SerialNumCreater.serialNum(OrderNumCreater.class));
//       String  url =  PayUtil.generateMchPayNativeRequestURL("wxdas", "dadada", "qweqeq", "dadsada");
//        System.out.println(url);
        String body="乐豆呀红包盒";
        System.out.println(body);
    }
}
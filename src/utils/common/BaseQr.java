package utils.common;

/**
 * Created by yeran on 2016/10/5.
 */
public class BaseQr {

    public static final String baseUrl = "http://qr.liantu.com/api.php?&w=200&text=";

    /**
     * 二维码内容
     * @param text
     * @return
     */
    public static String qrUrl(String text){
        if (text!=null)
            return baseUrl.concat(text);
        return null;
    }
}

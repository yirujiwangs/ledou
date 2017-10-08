package utils.secret;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

/**
 * Created by yeran on 2016/10/27.
 * <p/>
 * 绿豆芽私有加密工具_1:长文本加密
 */
public class PrivateSecretUtil {

    /**
     * 种子
     */
    protected static String seed = "ledouya";
    /**
     * CBC模式的侧向量
     */
    protected static String ivParameter = "yeranabezyzswzzj";

    /**
     * 创建加密Key值
     *
     * @return
     */
    protected static String seedCreate() {
        SecureRandom secureRandom = new SecureRandom();
      //  secureRandom.setSeed(System.currentTimeMillis());
        return seed.concat(String.format("%09d", secureRandom.nextInt(999999999)));
    }

    /**
     * 私有加密，使用AES加密，用于加密长文本
     *
     * @param str
     * @return
     */
    public static Secret encode(String str) {
        String key = seedCreate();
        String result = encode_aes(str, key);
        Secret secret = new Secret(key, result);
        return secret;
    }

    /**
     * @param str
     * @param key
     * @return
     * @descr: AES加密(对称加密)
     */
    public static String encode_aes(String str, String key) {

        String result = str;
        try {
            Cipher cipher;
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] raw = key.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            // 使用CBC模式，需要一个向量iv，可增加加密算法的强度
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(str.getBytes("utf-8"));
            result = new BASE64Encoder().encode(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解密，key是加密时候用的key，动态更新，需要从数据库获取
     *
     * @param encodedStr
     * @param key
     * @return
     */
    public static String decode(String encodedStr, String key) {
        return decrypt_aes(encodedStr, key);
    }

    // 解密
    protected static String decrypt_aes(String sSrc, String sKey) {
        try {
            byte[] raw = sKey.getBytes();
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
            byte[] original = cipher.doFinal(encrypted1);
            return new String(original, "utf-8");

        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


}

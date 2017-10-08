package utils.secret;

import com.alibaba.druid.util.Base64;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by yeran on 2016/10/27.
 * <p/>
 * 密码加密工具类
 */
public class SecretCoder {
   public enum ENCODE_TYPE {
        MD5, SHA1, RSA, PRIVATE
    }


    /**
     * 加密
     *
     * @param password
     * @param encode_type
     * @return
     */
    public static Secret encode(String password, ENCODE_TYPE encode_type) {
        if (password == null || password.trim().isEmpty()) {
            throw new NullPointerException("加密内容不能为null或者空字符串");
        }
        String encodePassword = password;
        Secret result = new Secret(null, encodePassword);
        switch (encode_type) {
            case MD5:
                encodePassword = encoder_normal(encode_type.toString(), password);
                result.setResult(encodePassword);
                break;
            case SHA1:
                encodePassword = encoder_normal("SHA", password);
                result.setResult(encodePassword);
                break;
            case RSA:
                break;
            case PRIVATE:
                result = PrivateSecretUtil.encode(password);
                break;
            default:
                break;
        }
        return result;
    }

    /**
     * 解密
     *
     * @param str
     * @param encode_type
     * @return
     */
    public static String decode(String str, ENCODE_TYPE encode_type) {
        return decode(str, encode_type, null);
    }

    /**
     * 解密--只支持PRIVATE类型解析
     *
     * @param str
     * @param encode_type
     * @param key         密钥
     * @return
     */
    public static String decode(String str, ENCODE_TYPE encode_type, String key) {
        if (str == null || str.trim().isEmpty()) {
            throw new NullPointerException("加密内容不能为null或者空字符串");
        }
        String result = str;
        switch (encode_type) {
            case PRIVATE:
                result = PrivateSecretUtil.decode(str, key);
                break;
            default:
                break;
        }

        return result;
    }


    /**
     * 基础加密
     *
     * @param encode_type
     * @param str
     * @return
     */
    protected static String encoder_normal(String encode_type, String str) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(encode_type);
            messageDigest.update(str.getBytes());
            byte[] encryptStr = messageDigest.digest();
            return Base64.byteArrayToBase64(encryptStr);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encode_type;
    }


}

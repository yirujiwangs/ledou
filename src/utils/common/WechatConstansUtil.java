package utils.common;

/**
 * @author lzl
 * @version 创建时间:2015年9月29日 下午5:32:27
 * @desc 控制服务器和本地测试代码需要改动的地方，如果需要更改环境，只需要更改这个文件即可 无需再更改其他文件
 */
public class WechatConstansUtil {
    /**
     * AppID和AppSecret
     */
    //乐豆互动
//		public static final String AppID = "wxdb09cd21946b6279";
//		public static final String AppSecret = "28d127efc910b0978b07e9640e290f34";

    //乐豆呀
    public static final String AppID = "wxd8f65d8da47e5173";
    public static final String AppSecret = "1fbc5dd4d4d0003314a279419981e739";


    //西北运营中心
//    public static final String AppID = "wx443a9ff308224df5";
//    public static final String AppSecret = "b6a40b8714c9db5089ca19aa577129d3";

    /**
     * 商户号
     */
    //乐豆互动
//	public static final String mchID = "1244066002";
    //乐豆呀
    public static final String mchID = "1271403501";


    //字符串，固定值，无需改动
    public static final String noncestr = "5K8264ILTKCH16CQ2502SI8ZNMTM67VS";


    /**
     * 签名秘钥选择
     */
    //乐豆互动
//	public static final String key="7f00badc51b937abcdcfb66a955a68f3";
    //乐豆呀
    public static final String key = "lvdouyatechwansuiwansuiwanwansui";

    /**
     * 公众号消息校验Token
     */
    public static final String PUBLIC_TOKEN = "CMbWqxB7bLidf6TnSfTg23Is3mXbpVo8";

    /**
     * 公众号消息加解密Key
     */
    public static final String PUBLIC_EncodingAESKey = "Rq3x5enmbQrHhn8gxRRsQ4Duqld9CX7Osu5UAsnMmLs";

    /**
     * 第三方公众平台AppID
     */
    public static final String PUBLIC_APPID = "wx3af82f7435f91b5a";

    /**
     * 第三方公众平台AppSecret
     */
    public static final String PUBLIC_APPSECRET = "bc06be8901de4f8c3e86061c7b3d2d2c";


    public static final String WX_LOGIN_APPID= "wxebf0860568383f8a";
    public static final String WX_LOGIN_SECRET= "9a347af53b1ae62d95966995d921dd7d";
    /**
     * 手机端页面跳转域名选择
     * 支付宝跳转域名选择
     */
    //服务器路径//
//		public static final String baseUrl="http://www.ledouya.com/ledou/";
//		public static final String baseDomain="www.ledouya.com";
    //本地路径，如果在本地测试，请每个人将下面两个域名修改为自己的域名
//		public static final String baseUrl="http://dahongzao2012.imwork.net/ledoya/";
//		public static final String baseDomain="dahongzao2012.imwork.net";


    public static final String PTOKEN_LEDOUYA = "3822494e693a838d31249b6d00969b8f";

   // public static final String PTOKEN_LEDOUYA = "6a1a8ef737c2309f2e52486dedfe58ff";


    /**
     * 摇一摇初始次数
     */
    public static final int shakingTimes = 20;
    public static final int countdownTimes = 60;
    //linux  /opt/software/tomcat7/webapps/cert/apiclient_cert.p12


    /**
     * 商户支付证书存储路径
     */
    public static final String certLocation = "/opt/software/tomcat7/webapps/cert/apiclient_cert.p12";


    public static final String urlTestString = "http://vesper.zicp.net/ledoya/shaking.do?phoneNum=18700450598&page_id=54";
}

package utils.api;

import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;

/**
 * Created by Administrator on 2016/10/3.
 */
public class BaseAPI {

     public static final String BASE_URL = "http://15g890j247.iask.in";
  //  public static final String BASE_URL = "http://www.ledouya.com";

    public static final String BASE_URL_AD_TEST = "http://lutianfei.vicp.io";
    public static final String baseUrl = BASE_URL + "/ledou";
    //public static final String baseUrl_test = "http://lutianfei.vicp.io/ledou";
   public static final String baseUrl_test = "http://15g890j247.iask.in/ledou";


    public static final String baseDomain = "15g890j247.iask.in";
    public static final String ALI_PAY_DEPOSIT = baseUrl + "/proxyFinance/toDeposit.do";

    protected static final String BASE_URL_LEDOUYA = "http://www.ledouya.com/wxAuth";
    protected static final String BASE_URL_LEDOUYA_TEST = "http://15g890j247.iask.in/wxAuth";
    protected static final String BASE_URL_ADPLATFORM_TEST1 = "http://158q765n35.imwork.net/dou/Iapi/";

    protected static final String ALI_PAY_URL_AD = baseUrl + "/advertise/leAdvPayNotify.do";


   /*############# PHP  ####################*/
    protected static final String BASE_URL_ADPLATFORM_TEST = "http://shaketest.ledouya.com/Agents/";

    protected static final String GET_ADV_COMI_URL = "";
    protected static final String GET_ADV_LIST_URL = "";
    protected static final String GET_ADV_WIDL_URL = "";


    protected static final String ADV_CONTROL_DELETE_URL = "/advertise/updateAdvStatus";

    /**
     * 代理商广告收益
     */
    protected static final String FINANCE_AD_BENEFIT = "finance/benefit";//红包收益
    protected static final String FINANCE_STORE_AD_DEPOSIT = "finance/storeAdDepositRecord";//商户广告金充值记录
    //protected static final String FINANCE_STORE_AD_DEPOSIT = "finance/storeAdDepositMonthRecord";//商户广告金充值记录

    protected static final String FINANCE_STORE_AD_DEPOSIT_SUM = "finance/storeAdDepositMoney";//商户广告金充值金额

    protected static final String DEVICE_ACTIVATE_BENEFIT_PROXY = "finance/deviceActivatedMoney";

    //public static final String DEVICE_ACTIVATE_RECORD = "finance/deviceActivatedMonthRecord";//设备激活记录（单位：月）

    public static final String DEVICE_ACTIVATE_RECORD = "finance/deviceActivatedDayRecord";//设备激活记录（单位：）

    public static final String DEVICE_ACTIVATE_RECORD_DAY = "finance/dateDeviceActivatedRecord";

    public static final String DEVICE_ACTIVATE_INDEX_DAY = "finance/dateDeviceActivatedIndex";

    protected static final String DEVICE_STATISTIC = "device/deviceIndex2";

    /**
     * 控制中心设备指标
     */
    protected static final String DEVICE_STATISTIC_TOTAL = "device/deviceIndexTotal";

    protected static final  String DEVICE_STATICTIC_PROXY = "device/deviceManage";



    public static final  String DEVICE_ACTIVATE_SETTING = "device/saveRebateRule";

    public static final  String DEVICE_ACTIVATE_INFO = "device/rebateRuleInfo";



    protected static Header jsonHeader = new BasicHeader(
            HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
    protected static Header xmlHeader = new BasicHeader(
            HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_XML.toString());
}

package utils.finance;


import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 金融工具类
 * 
 * */
public class FinanceUtil {

	protected static final String appId = "ledouya";
	protected static final String appKey="yeranabe";
	//protected static String KEY_MD5 =  AlipayMD5.sign(appKey,appId,"UTF-8");


	public static double Yuan2Fen(Double yuan) {
		if (yuan==null)
			return 0;
		BigDecimal d1 = new BigDecimal(100 * yuan);
		return d1.setScale(0, RoundingMode.HALF_UP).doubleValue();
	}

	public static double Fen2Yuan(Double fen) {
		if (fen==null)
			return 0;
		BigDecimal d1 = new BigDecimal(fen);
		return d1.divide(new BigDecimal(100), 2, RoundingMode.HALF_UP)
				.doubleValue();
	}

	public static double Yuan2Yuan(Double yuan){
		if(yuan==null)
			return  0;
		BigDecimal d1 = new BigDecimal(yuan);
		return d1.setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

	public static double Fen2Fen(Double fen){
		if(fen==null)
			return  0;
		BigDecimal d1 = new BigDecimal(fen);
		return d1.setScale(0,RoundingMode.HALF_UP).doubleValue();
	}


	/**
	 * 支付权限判断/密钥判断
	 *
	 * @param key
	 * @param time
	 * @return
	 */
	public static boolean havaPayRight(String key,Integer time){
		if (key!=null&&time!=null){
			String KEY_MD5 = AlipayMD5.sign(appId+appKey,""+time,"UTF-8");
			if(KEY_MD5.equals(key)){
				return true;
			}
		}
		return false;
	}

	/**
	 * 产生唯一订单号
	 * @return
	 */
	public static String outTradeNo(){
		UtilDate utilDate = new UtilDate();
		String out_trade_no = utilDate.getOrderNum()
				+ Thread.currentThread().getId() + utilDate.getThree();
		return out_trade_no;
	}
}

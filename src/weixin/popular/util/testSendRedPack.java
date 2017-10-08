package weixin.popular.util;

import weixin.popular.bean.paymch.Sendredpack;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class testSendRedPack {
	private void mian() throws UnknownHostException {

		Sendredpack sredpack = new Sendredpack();
		sredpack.setAct_name("1");
		sredpack.setClient_ip(Inet4Address.getLocalHost().toString());
		sredpack.setMax_value(500);
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyymmdd");
		String time = df.format(date);
		String strNow = System.currentTimeMillis()+"";
		String str = strNow.substring(strNow.length()-10,strNow.length());
		String mch_billno = "1244066002@1244066002"+time+str;
		sredpack.setMch_billno(mch_billno);
		sredpack.setMch_id("1244066002@1244066002");
		sredpack.setMin_value(500);
		sredpack.setNick_name("涔愯眴浜掑姩");
		sredpack.setNonce_str("5K8264ILTKCH16CQ2502SI8ZNMTM67VS");
		sredpack.setRe_openid("oi4KPswtKHFiXfCkCqyFRyXZP5uE");
		sredpack.setRemark("鎭枩鍙戣储");

		sredpack.setSend_name("test");
		sredpack.setTotal_amount(500);

		sredpack.setWxappid("wxdb09cd21946b6279");
		sredpack.setWishing("鎭枩鍙戣储");
		sredpack.setTotal_num(1);

		//PayMchAPI.mmpaymkttransfersSendredpack();
	}
}

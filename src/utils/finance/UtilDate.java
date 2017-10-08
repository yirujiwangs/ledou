package utils.finance;

import java.util.Date;
import java.util.Random;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

/*
 *类名：UtilDate
 *功能：自定义订单类 提供唯一订单号
 */

public class UtilDate {

    public static final String dtLong  = "yyyyMMddHHmmss";
    public static final String simple  = "yyyy-MM-dd HH:mm:ss";
    public static final String dtShort = "yyyyMMdd";
	
    /*
     * 返回系统当前时间(精确到毫秒),作为一个唯一的订单编号
     * 以yyyyMMddHHmmss为格式的当前系统时间
     */
	public String getOrderNum(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtLong);
		return df.format(date);
	}
	
	
	/*
	 * 获取系统当前日期(精确到毫秒)，格式：yyyy-MM-dd HH:mm:ss
	 */
	public String getDateFormatter(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(simple);
		return df.format(date);
	}
	
	/*
	 * 获取系统当期年月日(精确到天)，格式：yyyyMMdd
	 */
	public String getDate(){
		Date date=new Date();
		DateFormat df=new SimpleDateFormat(dtShort);
		return df.format(date);
	}
	
	/*
	 * 产生随机的四位数，方法名不改变
	 */
	public String getThree(){
		Random rad=new Random();
		return rad.nextInt(10000)+"";
	}
	
}

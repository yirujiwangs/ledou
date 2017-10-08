package utils.net;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	
	public static final Integer TIMEOUT=7*60*60;
	
	/*添加一个cookie*/
	public static void add_cookie(String cookie_name,String cookie_value,HttpServletResponse res,Integer time){
		Cookie ph_cookie  = new Cookie(cookie_name,cookie_value);
		ph_cookie.setMaxAge(time==null?TIMEOUT:time);
		res.addCookie(ph_cookie);
	}
	
	/*删除一个cookie*/
	public static void del_cookie(String cookie_name,HttpServletRequest req,HttpServletResponse res)
	{
		Cookie myCookie[] = req.getCookies();
		if (myCookie != null) {
			for (Cookie cookie : myCookie) {
				if (cookie_name.equals(cookie.getName())) {
					cookie.setMaxAge(0);
					res.addCookie(cookie);
				}
			}
		}
	}

	public static boolean updateCookie(String cookie_name,HttpServletRequest req,HttpServletResponse res,
									   String cookie_value,Integer time){
		Cookie myCookie[] = req.getCookies();
		if (myCookie != null) {
			for (Cookie cookie : myCookie) {
				if (cookie_name.equals(cookie.getName())) {
					cookie.setValue(cookie_value);
					if (time!=null)
					cookie.setMaxAge(time);
					res.addCookie(cookie);
					return true;
				}
			}
		}
		add_cookie(cookie_name,cookie_value,res,time);
		return true;
	}
	
	public static String get_cookie(String cookie_name,HttpServletRequest req)
	{
		Cookie myCookie[] = req.getCookies();
		if (myCookie != null) 
		{
			for (Cookie cookie : myCookie) 
			{
				System.out.println(cookie.getName()+"--"+cookie.getValue());
				if (cookie_name.equals(cookie.getName())) 
				{
					return cookie.getValue();
				}
			}
		
		}
		return null;
	}

}

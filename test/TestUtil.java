import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;
import utils.api.wxUtil;
import utils.finance.FinanceUtil;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2017/7/26.
 */
public class TestUtil {
    @Test
    public void testCalender(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date stime = calendar.getTime();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(format.format(stime));
        //System.out.println(format.format(etime));

        calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //�·�-1
        calendar.add(Calendar.DATE, -1);
        Date edate = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date sdate = calendar.getTime();
        //SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(sdate));
        System.out.println(format.format(edate));
    }

    @Test
    public void testFinance(){
        System.out.println(FinanceUtil.Fen2Yuan(200.611));
        System.out.println(FinanceUtil.Yuan2Fen(200.6152));
        System.out.println(FinanceUtil.Yuan2Yuan(200.6122));
        System.out.println((int)FinanceUtil.Fen2Fen(200.6122));

    }

    @Test
    public void testDate(){
        long stime=0,etime=0;
        Calendar calendar = Calendar.getInstance();
        String startMonth = "2017-06";
        String endMonth = "2017-08";
        try {
            stime = new SimpleDateFormat("yyyy-MM").parse(startMonth).getTime();
            etime = new SimpleDateFormat("yyyy-MM").parse(endMonth).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        calendar.setTimeInMillis(etime);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE,-1);
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        etime= calendar.getTimeInMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(stime));
        System.out.println(format.format(etime));
    }

    @Test
    public void testGetwxStatistics(){
        String accessToken = wxUtil.getAccessToken();
        long datetime = System.currentTimeMillis() -(long) 86400000*89;
        System.out.println("��ǰtoken -- " + accessToken);
        String url = "https://api.weixin.qq.com/shakearound/statistics/devicelist?access_token="+accessToken;

        JSONObject json = new JSONObject();
        json.put("date", datetime / 1000);
        json.put("page_index", 1);
        System.out.println("request json : " + json);
        JSONObject jsonTmp = json;
        json = new JSONObject();
        json = wxUtil.post(url,jsonTmp);
        System.out.println(json);
    }

    @Test
    public void test(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //�·�-1
        calendar.add(Calendar.DATE, -1);
        Date edate = calendar.getTime();

        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        Date sdate = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        System.out.println(format.format(sdate));
        System.out.println(format.format(edate));

    }

    @Test
    public void testRetainAll(){
        List<String> aList = new ArrayList<String>();
        List<String> bList = new ArrayList<String>();
        aList.add("a");
        aList.add("b");
        aList.add("c");
        bList.add("b");
        bList.add("c");
        bList.add("d");
        aList.retainAll(bList);
        System.out.println(aList.toString());
    }

    @Test
    public void testDecoder(){
        String s= "logo=http%3A%2F%2F7xovjx.com2.z0.glb.qiniucdn.com%2Fbb.jpg%3FimageView2%2F2%2Fw%2F120%2Fh%2F120%2Finterlace%2F0%2Fq%2F100&media_type=P&storeName=12345&advStrategyList=%5B%7B%22groupName%22%3A%22%E6%B5%8B%E8%AF%95%E5%88%86%E7%BB%841%22%2C%22proxyName%22%3A%22%E9%83%AD%E6%B5%A9%E6%B5%8B%E8%AF%95%22%2C%22groupId%22%3A158%2C%22proxyToken%22%3A%223869457006a84fa28886d4c1e546defb%22%7D%5D&area_code=610103&beginTime=1504195200000&withdrawalId=5&pic=http%3A%2F%2F7xovjx.com2.z0.glb.qiniucdn.com%2Fbb.jpg%3FimageView2%2F2%2Fw%2F640%2Fh%2F427%2Finterlace%2F0%2Fq%2F100&type=&endTime=1506873599000&preFee=1.00&touNum=100&formData=%7B%0D%0A++%22advStrategyList%22%3A+%5B%0D%0A++++%7B%0D%0A++++++%22groupName%22%3A+%22%E6%B5%8B%E8%AF%95%E5%88%86%E7%BB%841%22%2C%0D%0A++++++%22proxyName%22%3A+%22%E9%83%AD%E6%B5%A9%E6%B5%8B%E8%AF%95%22%2C%0D%0A++++++%22groupId%22%3A+158%2C%0D%0A++++++%22proxyToken%22%3A+%223869457006a84fa28886d4c1e546defb%22%0D%0A++++%7D%0D%0A++%5D%2C%0D%0A++%22area_code%22%3A+%22610103%22%2C%0D%0A++%22media_type%22%3A+%22P%22%2C%0D%0A++%22withdrawalId%22%3A+5%2C%0D%0A++%22touNum%22%3A+100%2C%0D%0A++%22type%22%3A+%22%22%2C%0D%0A++%22pic%22%3A+%22http%3A%2F%2F7xovjx.com2.z0.glb.qiniucdn.com%2Fbb.jpg%3FimageView2%2F2%2Fw%2F640%2Fh%2F427%2Finterlace%2F0%2Fq%2F100%22%2C%0D%0A++%22logo%22%3A+%22http%3A%2F%2F7xovjx.com2.z0.glb.qiniucdn.com%2Fbb.jpg%3FimageView2%2F2%2Fw%2F120%2Fh%2F120%2Finterlace%2F0%2Fq%2F100%22%2C%0D%0A++%22advUrl%22%3A+%22http%3A%2F%2F123.com%22%2C%0D%0A++%22beginTime%22%3A+1504195200000%2C%0D%0A++%22endTime%22%3A+1506873599000%2C%0D%0A++%22storeName%22%3A+%2212345%22%2C%0D%0A++%22preFee%22%3A+1%0D%0A%7D&advUrl=http%3A%2F%2F123.com&method=area";
        try {
            String decode = URLDecoder.decode(s, "UTF-8");
            System.out.println(decode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testString(){
        String s= "logo=http%3A%2F%2F7xovjx.com2.z0.glb.qiniucdn.com%2Fbb.jpg%3FimageView2%2F2%2Fw%2F120%2Fh%2F120%2Finterlace%2F0%2Fq%2F100&media_type=P&storeName=12345&advStrategyList=%5B%7B%22groupName%22%3A%22%E6%B5%8B%E8%AF%95%E5%88%86%E7%BB%841%22%2C%22proxyName%22%3A%22%E9%83%AD%E6%B5%A9%E6%B5%8B%E8%AF%95%22%2C%22groupId%22%3A158%2C%22proxyToken%22%3A%223869457006a84fa28886d4c1e546defb%22%7D%5D&area_code=610103&beginTime=1504195200000&withdrawalId=5&pic=http%3A%2F%2F7xovjx.com2.z0.glb.qiniucdn.com%2Fbb.jpg%3FimageView2%2F2%2Fw%2F640%2Fh%2F427%2Finterlace%2F0%2Fq%2F100&type=&endTime=1506873599000&preFee=1.00&touNum=100&formData=%7B%0D%0A++%22advStrategyList%22%3A+%5B%0D%0A++++%7B%0D%0A++++++%22groupName%22%3A+%22%E6%B5%8B%E8%AF%95%E5%88%86%E7%BB%841%22%2C%0D%0A++++++%22proxyName%22%3A+%22%E9%83%AD%E6%B5%A9%E6%B5%8B%E8%AF%95%22%2C%0D%0A++++++%22groupId%22%3A+158%2C%0D%0A++++++%22proxyToken%22%3A+%223869457006a84fa28886d4c1e546defb%22%0D%0A++++%7D%0D%0A++%5D%2C%0D%0A++%22area_code%22%3A+%22610103%22%2C%0D%0A++%22media_type%22%3A+%22P%22%2C%0D%0A++%22withdrawalId%22%3A+5%2C%0D%0A++%22touNum%22%3A+100%2C%0D%0A++%22type%22%3A+%22%22%2C%0D%0A++%22pic%22%3A+%22http%3A%2F%2F7xovjx.com2.z0.glb.qiniucdn.com%2Fbb.jpg%3FimageView2%2F2%2Fw%2F640%2Fh%2F427%2Finterlace%2F0%2Fq%2F100%22%2C%0D%0A++%22logo%22%3A+%22http%3A%2F%2F7xovjx.com2.z0.glb.qiniucdn.com%2Fbb.jpg%3FimageView2%2F2%2Fw%2F120%2Fh%2F120%2Finterlace%2F0%2Fq%2F100%22%2C%0D%0A++%22advUrl%22%3A+%22http%3A%2F%2F123.com%22%2C%0D%0A++%22beginTime%22%3A+1504195200000%2C%0D%0A++%22endTime%22%3A+1506873599000%2C%0D%0A++%22storeName%22%3A+%2212345%22%2C%0D%0A++%22preFee%22%3A+1%0D%0A%7D&advUrl=http%3A%2F%2F123.com&method=area";
        String decode = "";
        try {
            decode = URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String substringAfter = org.apache.commons.lang.StringUtils.substringAfter(decode, "formData=");
        String json = org.apache.commons.lang.StringUtils.substringBefore(substringAfter, "&");
        JSONObject jsonObject = JSON.parseObject(json);
        System.out.println(JSON.toJSONString(jsonObject));

    }

    @Test
    public void testRegExp(){
        String s= "logo=http%3A%2F%2F7xovjx.com2.z0.glb.qiniucdn.com%2Fbb.jpg%3FimageView2%2F2%2Fw%2F120%2Fh%2F120%2Finterlace%2F0%2Fq%2F100&media_type=P&storeName=12345&advStrategyList=%5B%7B%22groupName%22%3A%22%E6%B5%8B%E8%AF%95%E5%88%86%E7%BB%841%22%2C%22proxyName%22%3A%22%E9%83%AD%E6%B5%A9%E6%B5%8B%E8%AF%95%22%2C%22groupId%22%3A158%2C%22proxyToken%22%3A%223869457006a84fa28886d4c1e546defb%22%7D%5D&area_code=610103&beginTime=1504195200000&withdrawalId=5&pic=http%3A%2F%2F7xovjx.com2.z0.glb.qiniucdn.com%2Fbb.jpg%3FimageView2%2F2%2Fw%2F640%2Fh%2F427%2Finterlace%2F0%2Fq%2F100&type=&endTime=1506873599000&preFee=1.00&touNum=100&formData=%7B%0D%0A++%22advStrategyList%22%3A+%5B%0D%0A++++%7B%0D%0A++++++%22groupName%22%3A+%22%E6%B5%8B%E8%AF%95%E5%88%86%E7%BB%841%22%2C%0D%0A++++++%22proxyName%22%3A+%22%E9%83%AD%E6%B5%A9%E6%B5%8B%E8%AF%95%22%2C%0D%0A++++++%22groupId%22%3A+158%2C%0D%0A++++++%22proxyToken%22%3A+%223869457006a84fa28886d4c1e546defb%22%0D%0A++++%7D%0D%0A++%5D%2C%0D%0A++%22area_code%22%3A+%22610103%22%2C%0D%0A++%22media_type%22%3A+%22P%22%2C%0D%0A++%22withdrawalId%22%3A+5%2C%0D%0A++%22touNum%22%3A+100%2C%0D%0A++%22type%22%3A+%22%22%2C%0D%0A++%22pic%22%3A+%22http%3A%2F%2F7xovjx.com2.z0.glb.qiniucdn.com%2Fbb.jpg%3FimageView2%2F2%2Fw%2F640%2Fh%2F427%2Finterlace%2F0%2Fq%2F100%22%2C%0D%0A++%22logo%22%3A+%22http%3A%2F%2F7xovjx.com2.z0.glb.qiniucdn.com%2Fbb.jpg%3FimageView2%2F2%2Fw%2F120%2Fh%2F120%2Finterlace%2F0%2Fq%2F100%22%2C%0D%0A++%22advUrl%22%3A+%22http%3A%2F%2F123.com%22%2C%0D%0A++%22beginTime%22%3A+1504195200000%2C%0D%0A++%22endTime%22%3A+1506873599000%2C%0D%0A++%22storeName%22%3A+%2212345%22%2C%0D%0A++%22preFee%22%3A+1%0D%0A%7D&advUrl=http%3A%2F%2F123.com&method=area";
        String decode = "";
        try {
            decode = URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String s1 = decode.split("^[formData].*(&)$")[0];
        System.out.println(s1);


/*        Pattern pattern = Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+");
        Matcher matcher = pattern.matcher("dsdsds<http://dsds//gfgffdfd>fdf");
        StringBuffer buffer = new StringBuffer();
        while(matcher.find()){
            buffer.append(matcher.group());
            buffer.append("\r\n");
            System.out.println(buffer.toString());
        }*/
    }

    @Test
    public void testArray(){
        int a = 1;
        int[] b = {1};
        plus(a);
        plus(b);
        System.out.println(a);
        System.out.println(b[0]);
    }

    private void plus(int[] b) {
        b[0] = b[0]+1;
    }

    private void plus(int a) {
        a = a+1;
    }

    @Test
    public void testString111(){
        int i = 5;
        for (int j = 0; j < 10; j++) {
            if(j==i){
                continue;
            }
            System.out.println(j);
        }
    }

    @Test
    public void testNextMonth(){
        String time = "2017-07-01";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("time",time);
        //Date stime = jsonObject.getDate("time");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Date stime = null;
        try {
            stime = format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //Date stime = (Date)time;s
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(stime);
        calendar.add(Calendar.MONTH, 1);
        Date etime = calendar.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
        String s = simpleDateFormat.format(stime);
        String e = simpleDateFormat.format(etime);
        System.out.println(s);
        System.out.println(e);
    }

}

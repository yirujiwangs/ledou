import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utils.common.LogUtil;
import utils.finance.FinanceUtil;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/conf/spring-mvc.xml","/conf/spring-mybatis.xml","/conf/spring.xml"})
public class test01 extends TestCase{
	@Test
	public void test() {
	/*	BigDecimal d1 = new BigDecimal(1.06);
		System.out.println(d1.setScale(0, RoundingMode.HALF_UP).doubleValue());

		System.out.println(106.0/1.06);*/

	//	System.out.println(FinanceUtil.Fen2Yuan((double) 1));

	//	LogUtil.log(test01.class, LogUtil.LogType.SUCCESS,"hello");

		try {
			InputStream inputStream = new FileInputStream(new File("D:/Users/zyzs/Desktop/ss.txt"));
			try {
                inputStream.close();
				inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}

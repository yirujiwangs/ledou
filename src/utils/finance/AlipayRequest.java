package utils.finance;

import java.util.Map;

       //发起请求接口
public interface AlipayRequest {
	//远程模拟http请求方式  不可用
	public String buildRequest(String strParaFileName, String strFilePath, Map<String, String> sParaTemp)
			throws Exception ;
	
	public  String buildRequest(Map<String, String> sParaTemp, String strMethod, String strButtonName);
	
}

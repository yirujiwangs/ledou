package service;

import model.Admin;
import model.ProxyAccountInfo;
import model.Supervisor;
import model.dto.FranchiseFee;
import weixin.popular.bean.User;

import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * Created by lenovo on 2015/12/10.
 */
public interface AdminService {
    Supervisor superLogin(String username, String password);

    List<Admin> getAllProxy();

    Admin loginCheck(String param, String  pass);

    Admin getAdminByPhone(String phone);

    Admin getAdminByProxyToken(String proxy_token);

    Admin getAdminById(Integer id);

    boolean exitAdmin(String phone);

    ProxyAccountInfo getProxyAccountInfo(Admin admin);

    Admin getProxy(String proxyToken);

    /**
     * 精简Admin
     * @param admin
     * @return
     */
    Admin cutAdmin(Admin admin);

    Admin cutAdmin(String phone);

    Admin cutAdmin(Integer id);

    int updateProxyAccountInfo(ProxyAccountInfo proxyAccountInfo);

    boolean updatePsw(Admin admin, String psw, String oldpsw);


    boolean updateAdmin(Admin admin);

    /**
     * 绑定微信到代理商账号上，需要进行重复查询
     *
     * @param proxy_token
     * @param ptoken
     * @param openId
     * @return
     */
    boolean bindWx(String proxy_token,String ptoken,String openId);

    String wxLogin(HttpSession httpSession, String state, String openId, String account);

    boolean updateProxyAccountWxInfo(String proxy_token, User user);

    List<Admin> dealers(String phone,String utoken);

    List<String> dealerTokens(String utoken);

    Admin adminByUtoken(String utoken);

    List<FranchiseFee> franchiseFeeList(String utoken,int startSize,int pageSize);
}

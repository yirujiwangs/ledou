package service.Impl;

import dao.AdminMapper;
import dao.ProxyAccountInfoMapper;
import dao.ProxyFinanceMapper;
import dao.SupervisorMapper;
import model.*;
import model.dto.FranchiseFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.AdminService;
import utils.common.Constant;
import utils.common.LogUtil;
import utils.secret.Secret;
import utils.secret.SecretCoder;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lenovo on 2015/12/10.
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private ProxyAccountInfoMapper proxyAccountInfoMapper;

    @Autowired
    private ProxyFinanceMapper proxyFinanceMapper;
    @Autowired
    private SupervisorMapper supervisorMapper;


    @Override
    public Supervisor superLogin(String username, String password) {
        LogUtil.log(AdminServiceImpl.class, LogUtil.LogType.INFO, "控制中心登陆，登陆账号:" + username);
        try {
            SupervisorExample supervisorExample = new SupervisorExample();
            supervisorExample.createCriteria().andAccountEqualTo(username).andPasswordEqualTo(password);
            List<Supervisor> supervisors = supervisorMapper.selectByExample(supervisorExample);
            if (supervisors != null && supervisors.size() == 1) {
                LogUtil.log(AdminServiceImpl.class, LogUtil.LogType.SUCCESS, "控制中心登陆成功，登陆账号:" + username);
                return supervisors.get(0);
            }
        } catch (Exception e) {
            LogUtil.log(AdminServiceImpl.class, LogUtil.LogType.ERROR, "控制中心登陆出现异常，登陆账号:" + username);
            e.printStackTrace();
        }
        LogUtil.log(AdminServiceImpl.class, LogUtil.LogType.WARN, "控制中心登陆失败，登陆账号:" + username);
        return null;
    }


    @Override
    public Admin loginCheck(String username, String password) {
        try {
            AdminExample adminExample = new AdminExample();
            adminExample.createCriteria().andAccountEqualTo(username);
            List<Admin> admins = adminMapper.selectByExample(adminExample);
            if (admins != null && admins.size() > 0) {
                Admin admin = admins.get(0);
                //System.out.println(admin.getAccount());
                String true_password = SecretCoder.decode(admin.getPassword()
                        , SecretCoder.ENCODE_TYPE.PRIVATE, admin.getSecretkey());
                if (!true_password.equals(password))
                    return null;
                //判断是否有财务表，如果没有则新建
                ProxyFinance proxyFinance = proxyFinanceMapper.selectByPhone(admin.getPhoneNum());
                if (proxyFinance == null) {
                    proxyFinance = new ProxyFinance();
                    proxyFinance.setPhoneNum(admin.getPhoneNum());
                    proxyFinanceMapper.insertSelective(proxyFinance);
                }
                return admin;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Admin getAdminByPhone(String phone) {
        if (phone != null && !phone.trim().isEmpty()) {
            AdminExample adminExample = new AdminExample();
            adminExample.createCriteria().andPhoneNumEqualTo(phone);
            List<Admin> admins = adminMapper.selectByExample(adminExample);
            if(admins != null && admins.size() > 0){
                return admins.get(0);
            }else{
                return null;
            }
        }
        return null;
    }

    @Override
    public Admin getAdminByProxyToken(String proxy_token) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andProxy_tokenEqualTo(proxy_token);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins != null && admins.size() > 0)
            return admins.get(0);
        return null;
    }

    @Override
    public Admin getAdminById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean exitAdmin(String phone) {
        if (adminMapper.exitAdmin(phone) != null)
            return true;
        return false;
    }

    @Override
    public ProxyAccountInfo getProxyAccountInfo(Admin admin) {
        if (admin == null)
            return null;
        if (admin.getId() == null)
            admin = adminMapper.selectByAdmin(admin);
        if (admin != null) {
            ProxyAccountInfoExample proxyAccountInfoExample = new ProxyAccountInfoExample();
            proxyAccountInfoExample.createCriteria().andAccount_idEqualTo(admin.getId());
            List<ProxyAccountInfo> proxyAccountInfoList = proxyAccountInfoMapper.selectByExample(proxyAccountInfoExample);
            if (proxyAccountInfoList != null && proxyAccountInfoList.size() > 0)
                return proxyAccountInfoList.get(0);
        }
        return null;
    }


    @Override
    public Admin cutAdmin(Admin admin) {
        if (admin == null)
            return null;
        admin.setPassword(null);
        admin.setId(null);
        admin.setCreateTime(null);
        admin.setDescr(null);
        return admin;
    }

    @Override
    public Admin cutAdmin(String phone) {
        Admin admin = getAdminByPhone(phone);
        return cutAdmin(admin);
    }

    @Override
    public Admin cutAdmin(Integer id) {
        Admin admin = adminMapper.selectByPrimaryKey(id);
        return cutAdmin(admin);
    }

    @Override
    public int updateProxyAccountInfo(ProxyAccountInfo proxyAccountInfo) {
        return proxyAccountInfoMapper.updateByPrimaryKeySelective(proxyAccountInfo);
    }

    @Override
    public boolean updatePsw(Admin admin, String psw, String oldpsw) {
        if (admin != null) {
            AdminExample adminExample = new AdminExample();
            adminExample.createCriteria().andPhoneNumEqualTo(admin.getPhoneNum());
            List<Admin> admins = adminMapper.selectByExample(adminExample);
            if (admins != null && admins.size() > 0) {
                Admin admin1 = admins.get(0);
                String true_psw = SecretCoder.decode(admin1.getPassword(), SecretCoder.ENCODE_TYPE.PRIVATE,
                        admin1.getSecretkey());
                if (true_psw.equals(oldpsw)) {
                    LogUtil.log(AdminServiceImpl.class, LogUtil.LogType.INFO, "密码验证正确");
                    Secret secret = SecretCoder.encode(psw, SecretCoder.ENCODE_TYPE.PRIVATE);
                    String key = secret.getKey();
                    String encodePsw = secret.getResult();
                    admin1.setPassword(encodePsw);
                    admin1.setSecretkey(key);

                    try {
                        int flag = adminMapper.updateByPrimaryKey(admin1);
                        if (flag > 0){
                            LogUtil.log(AdminServiceImpl.class, LogUtil.LogType.INFO, "密码修改成功");
                            return true;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return false;
    }

    @Override
    public boolean updateAdmin(Admin admin) {
        if (adminMapper.updateByPrimaryKey(admin) > 0)
            return true;
        return false;
    }

    @Override
    public boolean bindWx(String proxy_token, String ptoken, String openId) {
        LogUtil.log(AdminService.class, LogUtil.LogType.INFO, "代理商微信号绑定", "proxy_token=" + proxy_token);

        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andProxy_tokenEqualTo(proxy_token);
        adminExample.setDistinct(true);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins != null && admins.size() > 0) {
            Admin admin = admins.get(0);
            if (admin.getOpenid() == null || admin.getOpenid().isEmpty()) {
                //执行绑定
                admin.setOpenid(openId);
                admin.setPtoken(ptoken);
                admin.setStatus("true");
                if (adminMapper.updateByPrimaryKey(admin) > 0) {
                    LogUtil.log(AdminService.class, LogUtil.LogType.SUCCESS, "代理商微信号绑定成功", "proxy_token=" + proxy_token);
                    return true;
                }
            } else {
                LogUtil.log(AdminService.class, LogUtil.LogType.WARN, "代理商微信号绑定失败", "proxy_token=" + proxy_token + "" +
                        "此代理商已经绑定过微信");
            }
        }
        LogUtil.log(AdminService.class, LogUtil.LogType.ERROR, "代理商微信号绑定失败", "proxy_token=" + proxy_token + "" +
                "无此代理商信息，请小心恶意攻击");
        return false;
    }

    @Override
    public String wxLogin(HttpSession session, String state, String openId, String account) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andOpenidEqualTo(openId);
        adminExample.setDistinct(true);
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        if (admins != null && admins.size() > 0) {
            //登陆成功
            Admin admin = admins.get(0);
            admin.setPassword(null);
            admin.setAccount(null);
            session.setAttribute(Constant.SESSION_KEY_PROXY, admin);
            session.setAttribute("userTag", admin.getUsername());
            session.setAttribute("userId", admin.getId());

            account = admin.getUsername();

            //判断是否有财务表，如果没有则新建
            ProxyFinance proxyFinance = proxyFinanceMapper.selectByPhone(admin.getPhoneNum());
            if (proxyFinance == null) {
                proxyFinance = new ProxyFinance();
                proxyFinance.setPhoneNum(admin.getPhoneNum());
                proxyFinanceMapper.insertSelective(proxyFinance);
            }

            return account;
        }
        return null;
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean updateProxyAccountWxInfo(String proxy_token, weixin.popular.bean.User user) {
        try {
            String wxNickName = user.getNickname();
            String logoUrl = user.getHeadimgurl();
            Admin admin = getAdminByProxyToken(proxy_token);
            ProxyAccountInfo proxyAccountInfo = getProxyAccountInfo(admin);
            if (wxNickName != null)
                proxyAccountInfo.setNickname(wxNickName);
            if (logoUrl != null)
                proxyAccountInfo.setLogourl(logoUrl);
            updateProxyAccountInfo(proxyAccountInfo);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Admin> dealers(String phone, String utoken) {
        if (utoken!=null) {
            AdminExample adminExample = new AdminExample();
            adminExample.createCriteria().andRecommend_tokenEqualTo(utoken);

            return adminMapper.selectByExample(adminExample);
        }

        return null;
    }


    @Override
    public List<String> dealerTokens(String utoken) {
        return adminMapper.selectDealerUtoken(utoken);
    }

    @Override
    public Admin adminByUtoken(String utoken) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andProxy_tokenEqualTo(utoken);
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        return adminList.get(0);
    }

    @Override
    public List<FranchiseFee> franchiseFeeList(String utoken, int startSize, int pageSize) {
        return adminMapper.franchiseFeeList(utoken,startSize,pageSize);
    }


    @Override
    public List<Admin> getAllProxy() {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andIdIsNotNull();
        List<Admin> admins = adminMapper.selectByExample(adminExample);

        for (Admin admin : admins) {
            admin.setPassword(null);
        }
        return admins;
    }

    @Override
    public Admin getProxy(String proxyToken) {
        AdminExample adminExample = new AdminExample();
        adminExample.createCriteria().andProxy_tokenEqualTo(proxyToken);
        Admin admin = adminMapper.selectByExample(adminExample).get(0);
        return admin;
    }
}

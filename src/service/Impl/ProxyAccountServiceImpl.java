package service.Impl;


import dao.ProxyAccountBuyRecordMapper;
import dao.ProxyAccountCountMapper;
import dao.ProxyAccountInfoMapper;
import dao.StoreAccountBuyRecordMapper;
import model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.AdminService;
import service.FinanceService;
import service.ProxyAccountService;
import service.UserService;
import utils.common.LogUtil;
import utils.finance.FinanceUtil;
import utils.serialnum.OrderNumCreater;
import utils.serialnum.SerialNumCreater;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yeran on 2016/8/11.
 */

@Service
public class ProxyAccountServiceImpl implements ProxyAccountService {

    @Autowired
    ProxyAccountCountMapper proxyAccountCountMapper;

    @Autowired
    ProxyAccountBuyRecordMapper proxyAccountBuyRecordMapper;


    @Autowired
    private FinanceService financeService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProxyAccountInfoMapper proxyAccountInfoMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private StoreAccountBuyRecordMapper storeAccountBuyRecordMapper;

    @Override
    public List<ProxyAccountCount> getProxyAccountCountInfo(Admin admin) {
        ProxyAccountCountExample proxyAccountCountExample = new ProxyAccountCountExample();
        proxyAccountCountExample.createCriteria().andProxyPhoneEqualTo(admin.getPhoneNum());
        return proxyAccountCountMapper.selectByExample(proxyAccountCountExample);
    }

    @Override
    public ProxyAccountCount getCertainProxyAccountCountInfoById(int id) {
        return proxyAccountCountMapper.selectByPrimaryKey(id);
    }

    @Override
    public ProxyAccountCount getCertainProxyAccountCountInfo(String phone) {
        ProxyAccountCount proxyAccountCount = proxyAccountCountMapper.selectByProxyPhone(phone);
        if (proxyAccountCount == null) {
            proxyAccountCount = new ProxyAccountCount(0, 0, 0, 0, 0, 0, 0, 0, phone);
            proxyAccountCountMapper.insertSelective(proxyAccountCount);
        }
        return proxyAccountCount;
    }

    @Override
    public StoreAccountRule getStoreAccountRule() {

        return null;
    }

    @Override
    public void consumeProxyAccountCount(ProxyAccountCount proxyAccountCount, String storeType, int numb) {
        if ("NORMAL".equals(storeType))
            proxyAccountCount.setNormal_num_used(proxyAccountCount.getNormal_num_used() + numb);
        else if ("PLATFORM".equals(storeType))
            proxyAccountCount.setPlatform_num_used(proxyAccountCount.getPlatform_num_used() + numb);
        proxyAccountCountMapper.updateByPrimaryKeySelective(proxyAccountCount);
    }

    @Override
    public int addProxyAccountBuyRecord(String phone, int normal_num, int playform_num) {
        try {
            ProxyAccountBuyRecord proxyAccountBuyRecord = new ProxyAccountBuyRecord();
            proxyAccountBuyRecord.setProxy_phone(phone);
            proxyAccountBuyRecord.setNormal_num_pay(normal_num);
            proxyAccountBuyRecord.setPlatform_num_pay(playform_num);
            return  proxyAccountBuyRecordMapper.insertSelective(proxyAccountBuyRecord);
        }catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ProxyAccountBuyRecord getProxyAccountBuyRecord(int id) {
        return proxyAccountBuyRecordMapper.selectByPrimaryKey(id);
    }

    @Override
    public int countProxyAccountBuyRecords(String phone, Date startTime, Date endTime) {
        if (startTime == null) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(1971, 1, 1);
            startTime = calendar.getTime();
        }
        if (endTime == null) {
            endTime = new Date();
        }
        ProxyAccountBuyRecordExample proxyAccountBuyRecordExample = new ProxyAccountBuyRecordExample();
        proxyAccountBuyRecordExample.createCriteria().andProxy_phoneEqualTo(phone)
                .andCreatetimeBetween(startTime, endTime)
                .andStateEqualTo(1);
        return proxyAccountBuyRecordMapper.countByExample(proxyAccountBuyRecordExample);
    }

    @Override
    public List<ProxyAccountBuyRecord> getProxyAccountBuyRecords(String phone, Date startTime, Date endTime, Integer startPage, Integer pageSize, String keyword) {
        if (phone != null) {

            if (startTime == null) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(1971, 1, 1);
                startTime = calendar.getTime();
            }
            if (endTime == null) {
                endTime = new Date();
            }

            ProxyAccountBuyRecordExample proxyAccountBuyRecordExample = new ProxyAccountBuyRecordExample();
            ProxyAccountBuyRecordExample.Criteria criteria = proxyAccountBuyRecordExample.createCriteria()
                    .andProxy_phoneEqualTo(phone).andCreatetimeBetween(startTime, endTime)
                    .andStateEqualTo(1);
            if (keyword != null)
                criteria.andLike(new String[]{}, keyword);
            proxyAccountBuyRecordExample.setDividePage(startPage, pageSize);
            return proxyAccountBuyRecordMapper.selectByExample(proxyAccountBuyRecordExample);
        }
        return null;
    }

    @Override
    public int countProxyAccountBuyRecords(String phone, Date startTime, Date endTime, String keyword) {
        return countProxyAccountBuyRecords(phone, startTime, endTime);
    }

    @Override
    public boolean initProxyAccount(Integer account_num, String phoneNum, String proxy_token) {
        LogUtil.log(ProxyAccountServiceImpl.class, LogUtil.LogType.INFO, "开始创建代理商默认库存，phone=" + phoneNum);
        try {
            ProxyAccountCount proxyAccountCount = new ProxyAccountCount();
            proxyAccountCount.setProxy_phone(phoneNum);
            proxyAccountCount.setNormal_num_total(account_num);
            //System.out.println("account="+account_num);
            if (proxyAccountCountMapper.insertSelective(proxyAccountCount) > 0) {
                LogUtil.log(ProxyAccountServiceImpl.class, LogUtil.LogType.INFO, "开始创建代理商默认库存，phone=" + phoneNum);
                return true;
            } else LogUtil.log(ProxyAccountServiceImpl.class, LogUtil.LogType.ERROR, "创建代理商默认库存失败，phone=" + phoneNum);
        } catch (Exception e) {
            LogUtil.log(ProxyAccountServiceImpl.class, LogUtil.LogType.ERROR, "创建代理商默认库存失败，phone=" + phoneNum, e.getMessage());
        }

        return false;
    }

    @Override
    public void initProxyInfo(Integer id) {
        ProxyAccountInfo proxyAccountInfo = new ProxyAccountInfo();
        proxyAccountInfo.setAccount_id(id);
        proxyAccountInfoMapper.insertSelective(proxyAccountInfo);
    }

    @Override
    public StoreAccountBuyRecord buyStoreAccount(String utoken, Integer amount, Integer unitPrice, Integer totalPrice, Integer ruleId,String comment) {
        double total_fee = userService.getValue(amount, 0);
        int price = (int) (100 * total_fee);
        if(totalPrice.intValue() != price){
            return null;
        }

        String tradeNo = SerialNumCreater.serialNum(OrderNumCreater.class);

        StoreAccountBuyRecord storeAccountBuyRecord = new StoreAccountBuyRecord();
        storeAccountBuyRecord.setAmount(amount);
        storeAccountBuyRecord.setPartner_trade_no(tradeNo);
        storeAccountBuyRecord.setRule_id(ruleId);
        storeAccountBuyRecord.setTotal_fee(totalPrice);
        storeAccountBuyRecord.setType_sec("WX");
        storeAccountBuyRecord.setUnit_price(unitPrice);
        storeAccountBuyRecord.setComment(comment);

        if(storeAccountBuyRecordMapper.insertSelective(storeAccountBuyRecord)>0){
            return storeAccountBuyRecord;
        }
        return null;
    }


    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class,propagation = Propagation.REQUIRED)
    public boolean accountBuyResult(String out_trade_no,boolean result){

        StoreAccountBuyRecordExample accountBuyRecordExample = new StoreAccountBuyRecordExample();
        accountBuyRecordExample.createCriteria().andPartner_trade_noLike(out_trade_no)
                .andStatusEqualTo("P");

        StoreAccountBuyRecord storeAccountBuyRecord = new StoreAccountBuyRecord();

        if(result){
            //购买成功
            storeAccountBuyRecord.setStatus("N");
        }else{
            //购买失败
            storeAccountBuyRecord.setStatus("D");
        }

        int rows = storeAccountBuyRecordMapper.updateByExampleSelective(storeAccountBuyRecord,accountBuyRecordExample);
        if (rows>0)
            return true;
        return false;
    }

    /**
     * 获取代理商购买账号记录
     *
     * @param phone
     * @param startTime
     * @param endTime
     * @return
     */
    @Override
    public List<ProxyAccountBuyRecord> getProxyAccountBuyRecords(String phone
            , Date startTime, Date endTime, Integer startPage, Integer pageSize) {
        if (phone != null) {

            if (startTime == null) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(1971, 1, 1);
                startTime = calendar.getTime();
            }
            if (endTime == null) {
                endTime = new Date();
            }

            ProxyAccountBuyRecordExample proxyAccountBuyRecordExample = new ProxyAccountBuyRecordExample();
            proxyAccountBuyRecordExample.createCriteria()
                    .andProxy_phoneEqualTo(phone).andCreatetimeBetween(startTime, endTime)
                    .andStateEqualTo(1);
            proxyAccountBuyRecordExample.setDividePage(startPage, pageSize);
            return proxyAccountBuyRecordMapper.selectByExample(proxyAccountBuyRecordExample);
        }
        return null;
    }

    @Override
    public int updateProxyAccountBuyRecord(ProxyAccountBuyRecord proxyAccountBuyRecord) {
        return proxyAccountBuyRecordMapper.updateByPrimaryKey(proxyAccountBuyRecord);
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean updateProxyAccountInfo(Integer accountNum, String descr, String account) {
        try {
            Admin admin = adminService.getAdminByPhone(account);
            admin.setDescr(descr);
            ProxyAccountCount proxyAccountCount = getCertainProxyAccountCountInfo(admin.getPhoneNum());
            proxyAccountCount.setNormal_num_total(accountNum);
            adminService.updateAdmin(admin);
            proxyAccountCountMapper.updateByPrimaryKey(proxyAccountCount);
            LogUtil.log(ProxyAccountServiceImpl.class, LogUtil.LogType.ERROR, "更新代理商信息成功");
            return true;
        } catch (Exception e) {
            LogUtil.log(ProxyAccountServiceImpl.class, LogUtil.LogType.ERROR, "更新代理商信息出现错误", e.getMessage());
        }
        LogUtil.log(ProxyAccountServiceImpl.class, LogUtil.LogType.ERROR, "更新代理商信息失败");
        return false;
    }

    /**
     * 修改运营商状态 禁用或者解封（账号管理页面）
     * @param account
     * @param status
     * @return
     */
    @Override
    public boolean updateStatus(String account, String status) {
        if (account == null || status == null) {
            return false;
        }
        Admin admin = adminService.getAdminByPhone(account);
        if (admin == null)
            return false;
        admin.setStatus(status);
        if (adminService.updateAdmin(admin))
            return true;
        return false;
    }


    /**
     * 代理商购买设备成功
     *
     * @param id
     * @param phone
     * @return
     */
    @Override
    public int buyProxyAccountSuccess(String out_trade_no, int id, String phone,Integer fee) {
        ProxyAccountBuyRecord proxyAccountBuyRecord = proxyAccountBuyRecordMapper.selectByPrimaryKey(id);
        if (proxyAccountBuyRecord.getProxy_phone().equals(phone) && proxyAccountBuyRecord.getState() == 0) {
            proxyAccountBuyRecord.setState(1);
            proxyAccountBuyRecord.setOut_trade_no(out_trade_no);
            proxyAccountBuyRecord.setFee(fee);
            proxyAccountBuyRecordMapper.updateByPrimaryKeySelective(proxyAccountBuyRecord);

            //更新代理商设备总表
            ProxyAccountCount proxyAccountCount = proxyAccountCountMapper.selectByProxyPhone(phone);
            if (proxyAccountCount == null) {
                //新加一个代理商记录
                proxyAccountCount = new ProxyAccountCount();
                proxyAccountCount.setProxy_phone(phone);
                proxyAccountCount.setNormal_num_total(proxyAccountBuyRecord.getNormal_num_pay());
                proxyAccountCount.setPlatform_num_total(proxyAccountBuyRecord.getPlatform_num_pay());
                return proxyAccountCountMapper.insertSelective(proxyAccountCount);
            } else {
                int totalNormal = proxyAccountCount.getNormal_num_total();
                int totalPlatform = proxyAccountCount.getPlatform_num_total();
                proxyAccountCount.setNormal_num_total(totalNormal + proxyAccountBuyRecord.getNormal_num_pay());
                proxyAccountCount.setPlatform_num_total(totalPlatform + proxyAccountBuyRecord.getPlatform_num_pay());
                return proxyAccountCountMapper.updateByPrimaryKeySelective(proxyAccountCount);
            }
        }

        return 0;
    }

    @Override
    public String notifyBuyUserService(HttpServletRequest req) {
        String param = req.getParameter("extra_common_param");
        String[] str = param.split("#");
        if (str.length >= 2) {
            int id = Integer.parseInt(str[0]);
            String phoneNum = str[1];

            if (financeService.insertDesport(req, phoneNum)) {
                //更新代理商购买商户信息
                try {
                    String out_trade_no = req.getParameter("out_trade_no");
                    Integer fee = (int) FinanceUtil.Yuan2Fen(Double.parseDouble(req
                            .getParameter("total_fee")));
                    if (buyProxyAccountSuccess(out_trade_no, id, phoneNum,fee) > -1)
                        return "success";
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return "fail";
    }

}

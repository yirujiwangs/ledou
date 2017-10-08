package utils.common;

import dao.AdminMapper;
import dao.ProxyAccountBuyRecordMapper;
import model.Admin;
import model.AdminExample;
import model.ProxyAccountBuyRecord;
import model.ProxyAccountBuyRecordExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/19.
 */
public class ProxyAccountBuyOrderUtil {

    public static List<ProxyAccountBuyRecord> accountOrderSearch(String keyword,Date startTime,Date endTime,int startPage,int pageSize,
                                                                 AdminMapper adminMapper, ProxyAccountBuyRecordMapper proxyAccountBuyrecordMapper){
        AdminExample adminExample = new AdminExample();
        ProxyAccountBuyRecordExample proxyAccountBuyrecordExample = new ProxyAccountBuyRecordExample();

        List<Admin> admins = new ArrayList<Admin>();
        List<ProxyAccountBuyRecord> proxyAccountBuyrecords = new ArrayList<ProxyAccountBuyRecord>();
        List<ProxyAccountBuyRecord> proxyAccountBuyrecords1 = new ArrayList<ProxyAccountBuyRecord>();

        //°´ÈÕÆÚËÑË÷
        if(startTime != null && endTime !=null){
            proxyAccountBuyrecordExample.createCriteria().andCreatetimeBetween(startTime, endTime);
        }
        else if(startTime != null && endTime ==null){
            proxyAccountBuyrecordExample.createCriteria().andCreatetimeGreaterThanOrEqualTo(startTime);
        }
        else if(startTime == null && endTime !=null){
            proxyAccountBuyrecordExample.createCriteria().andCreatetimeLessThanOrEqualTo(endTime);
        }
        else{
            proxyAccountBuyrecordExample.createCriteria().andIdIsNotNull();
        }
        proxyAccountBuyrecords = proxyAccountBuyrecordMapper.selectByExample(proxyAccountBuyrecordExample);

        if(keyword !=null){
            keyword = "%"+keyword+"%";
            adminExample = new AdminExample();
            adminExample.or().andAccountLike(keyword);
            adminExample.or().andUsernameLike(keyword);

            admins = adminMapper.selectByExample(adminExample);

            List<String> adminsPhone = new ArrayList<String>();
            for(Admin admin :admins){
                adminsPhone.add(admin.getPhoneNum());
            }

            proxyAccountBuyrecordExample = new ProxyAccountBuyRecordExample();
            proxyAccountBuyrecordExample.or().andOut_trade_noLike(keyword);

            if(adminsPhone.size() !=0){
                proxyAccountBuyrecordExample.or().andProxy_phoneIn(adminsPhone);
            }

        }
        else{
            proxyAccountBuyrecordExample = new ProxyAccountBuyRecordExample();
            proxyAccountBuyrecordExample.createCriteria().andIdIsNotNull();
        }

        proxyAccountBuyrecords1 = proxyAccountBuyrecordMapper.selectByExample(proxyAccountBuyrecordExample);
        proxyAccountBuyrecords.retainAll(proxyAccountBuyrecords1);

        List<Integer> pabId = new ArrayList<Integer>();
        for(ProxyAccountBuyRecord pab :proxyAccountBuyrecords){
            pabId.add(pab.getId());
        }

        if(pabId.size()==0){
            return proxyAccountBuyrecords;
        }

        proxyAccountBuyrecordExample = new ProxyAccountBuyRecordExample();
        proxyAccountBuyrecordExample.createCriteria().andIdIn(pabId);
        proxyAccountBuyrecordExample.setDividePage(startPage, pageSize);

        List<ProxyAccountBuyRecord> proxyAccountBuyrecords2 = proxyAccountBuyrecordMapper.selectByExample(proxyAccountBuyrecordExample);

        return proxyAccountBuyrecords2;

    }
}

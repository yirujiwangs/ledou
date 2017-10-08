package utils.func;

import com.alibaba.druid.sql.PagerUtils;
import dao.AdminMapper;
import dao.ProxyFinanceMapper;
import dao.ProxyFinanceRecordMapper;
import model.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/19.
 */

public class ProxyFinanceRecordUtil {

    public static List<ProxyFinanceRecord> settlementOrderSearch(String keyword,Date startTime,Date endTime,int startPage,int pageSize,int[] pages,
                                                                 AdminMapper adminMapper, ProxyFinanceRecordMapper proxyFinanceRecordMapper){
        AdminExample adminExample = new AdminExample();
        ProxyFinanceRecordExample proxyFinanceRecordExample = new ProxyFinanceRecordExample();

        List<Admin> admins = new ArrayList<Admin>();
        List<ProxyFinanceRecord> proxyFinanceRecords = new ArrayList<ProxyFinanceRecord>();
        List<ProxyFinanceRecord> proxyFinanceRecords1 = new ArrayList<ProxyFinanceRecord>();

        //°´ÈÕÆÚËÑË÷
        if(startTime != null && endTime !=null){
            proxyFinanceRecordExample.createCriteria().andCreatetimeBetween(startTime, endTime);
        }
        else if(startTime != null && endTime ==null){
            proxyFinanceRecordExample.createCriteria().andCreatetimeGreaterThanOrEqualTo(startTime);
        }
        else if(startTime == null && endTime !=null){
            proxyFinanceRecordExample.createCriteria().andCreatetimeLessThanOrEqualTo(endTime);
        }
        else{
            proxyFinanceRecordExample.createCriteria().andIdIsNotNull();
        }
        proxyFinanceRecords = proxyFinanceRecordMapper.selectByExample(proxyFinanceRecordExample);
        proxyFinanceRecordExample = new ProxyFinanceRecordExample();
        if(keyword !=null && !keyword.trim().isEmpty()){
            keyword = "%"+keyword+"%";
            adminExample = new AdminExample();
            adminExample.or().andAccountLike(keyword);
            adminExample.or().andUsernameLike(keyword);

            admins = adminMapper.selectByExample(adminExample);

            List<String> adminsPhone = new ArrayList<String>();
            for(Admin admin :admins){
                adminsPhone.add(admin.getPhoneNum());
            }

            if(adminsPhone.size() !=0){
                proxyFinanceRecordExample.or().andProxy_phoneIn(adminsPhone);
            }else{
                proxyFinanceRecordExample.or().andIdEqualTo(Integer.parseInt(keyword.replace("%","")));
            }
        }else{
            proxyFinanceRecordExample.createCriteria().andIdIsNotNull();
        }
        proxyFinanceRecords1 = proxyFinanceRecordMapper.selectByExample(proxyFinanceRecordExample);
        proxyFinanceRecords.retainAll(proxyFinanceRecords1);

        List<Integer> pfrId = new ArrayList<Integer>();
        for(ProxyFinanceRecord pfr :proxyFinanceRecords){
            pfrId.add(pfr.getId());
        }

        if(pfrId.size()==0){
            return proxyFinanceRecords;
        }

        proxyFinanceRecordExample = new ProxyFinanceRecordExample();
        proxyFinanceRecordExample.createCriteria().andIdIn(pfrId);
        pages[0] = (int)Math.ceil((double)proxyFinanceRecordMapper.countByExample(proxyFinanceRecordExample)/pageSize);

        proxyFinanceRecordExample.setDividePage(startPage, pageSize);

        List<ProxyFinanceRecord> proxyFinanceRecords2 = proxyFinanceRecordMapper.selectByExample(proxyFinanceRecordExample);

        return proxyFinanceRecords2;

    }
}

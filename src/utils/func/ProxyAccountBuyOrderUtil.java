package utils.func;

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

    public static List<ProxyAccountBuyRecord> accountOrderSearch(String keyword,Date startTime,Date endTime,int startPage,int pageSize,Integer state,int[] pages,
                                                                 AdminMapper adminMapper, ProxyAccountBuyRecordMapper proxyAccountBuyrecordMapper){
        AdminExample adminExample = new AdminExample();
        ProxyAccountBuyRecordExample proxyAccountBuyrecordExample = new ProxyAccountBuyRecordExample();
        ProxyAccountBuyRecordExample.Criteria criteria = proxyAccountBuyrecordExample.createCriteria();

        List<Admin> admins;
        List<ProxyAccountBuyRecord> proxyAccountBuyrecords;
        List<ProxyAccountBuyRecord> proxyAccountBuyrecords1;

        if(startTime != null && endTime !=null){
            criteria.andCreatetimeBetween(startTime, endTime);
        }
        else if(startTime != null && endTime ==null){
            criteria.andCreatetimeGreaterThanOrEqualTo(startTime);
        }
        else if(startTime == null && endTime !=null){
            criteria.andCreatetimeLessThanOrEqualTo(endTime);
        }
        else{
            criteria.andIdIsNotNull();
        }

        if(state!=null){
            criteria.andStateEqualTo(state);
        }
        proxyAccountBuyrecords = proxyAccountBuyrecordMapper.selectByExample(proxyAccountBuyrecordExample);

        proxyAccountBuyrecordExample = new ProxyAccountBuyRecordExample();
        if(keyword !=null && !keyword.trim().isEmpty()){
            keyword = "%"+keyword+"%";
            adminExample.or().andAccountLike(keyword);
            adminExample.or().andUsernameLike(keyword);

            admins = adminMapper.selectByExample(adminExample);

            List<String> adminsPhone = new ArrayList<String>();
            for(Admin admin :admins){
                adminsPhone.add(admin.getPhoneNum());
            }
            if(adminsPhone.size() !=0){
                proxyAccountBuyrecordExample.or().andProxy_phoneIn(adminsPhone);
            }else{
                proxyAccountBuyrecordExample.or().andOut_trade_noLike(keyword);
            }
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
        pages[0] = (int)Math.ceil((double)proxyAccountBuyrecordMapper.countByExample(proxyAccountBuyrecordExample)/pageSize);
        proxyAccountBuyrecordExample.setOrderByClause("createtime DESC");
        proxyAccountBuyrecordExample.setDividePage(startPage, pageSize);

        List<ProxyAccountBuyRecord> proxyAccountBuyrecords2 = proxyAccountBuyrecordMapper.selectByExample(proxyAccountBuyrecordExample);

        return proxyAccountBuyrecords2;

    }
}

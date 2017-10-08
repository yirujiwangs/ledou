package utils.func;

import dao.AdminMapper;
import dao.CommonAreaMapper;
import dao.ProxyAreaMapper;
import model.Admin;
import model.AdminExample;
import model.base.ProxyArea;
import model.base.ProxyAreaExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/19.
 */
public class AdminUtil {
    public static List<Admin> corporationAccountSearch(int[] pages,String keyword,Date startTime,Date endTime,int startPage,int pageSize, String areaCode,AdminMapper adminMapper, ProxyAreaMapper proxyAreaMapper, CommonAreaMapper commonAreaMapper){

        //分页 按搜索条件获取运营商信息列表

        AdminExample adminExample = new AdminExample();
        List<Admin> admins;
        List<Admin> admins1;

        //按日期搜索
        if(startTime != null && endTime !=null){
            adminExample.createCriteria().andCreateTimeBetween(startTime,endTime);
        }
        else if(startTime != null){
            adminExample.createCriteria().andCreateTimeGreaterThanOrEqualTo(startTime);
        }
        else if(endTime != null){
            adminExample.createCriteria().andCreateTimeLessThanOrEqualTo(endTime);
        }
        else{
            adminExample.createCriteria().andIdIsNotNull();
        }
        admins = adminMapper.selectByExample(adminExample);
        adminExample.createCriteria().andIdIsNotNull();
        adminExample = new AdminExample();
        //按照模糊条件搜索
        if(keyword != null && !keyword.trim().isEmpty()){
            keyword = "%"+keyword+"%";

            adminExample.or().andAccountLike(keyword);
            adminExample.or().andUsernameLike(keyword);
            adminExample.or().andDescrLike(keyword);
        }

        admins1 = adminMapper.selectByExample(adminExample);
        admins.retainAll(admins1);



        //在已知结果中进行分页操作
        List<String> adminsProxyToken = new ArrayList<String>();

        for(Admin admin:admins){
            adminsProxyToken.add(admin.getProxy_token());
        }

        if(adminsProxyToken.size()== 0){
            //json.put("corporationInfo","null");
            pages[0] = 0;
            return admins;
        }

        if(areaCode!=null){
            List<String> proxyTokenList = new ArrayList<String>();
            List<String> areaCodeList = commonAreaMapper.selectSubByAreaCode(areaCode);
            ProxyAreaExample proxyAreaExample = new ProxyAreaExample();
            proxyAreaExample.createCriteria().andArea_ridIn(areaCodeList);
            List<ProxyArea> proxyAreaList = proxyAreaMapper.selectByExample(proxyAreaExample);
            for (ProxyArea proxyArea : proxyAreaList){
                proxyTokenList.add(proxyArea.getUtoken());
            }
            adminsProxyToken.retainAll(proxyTokenList);
        }

        adminExample = new AdminExample();
        adminExample.createCriteria().andProxy_tokenIn(adminsProxyToken);
        int total = adminMapper.countByExample(adminExample);

        pages[0] = (int) Math.ceil(total / (double) pageSize);

        adminExample.setDividePage(startPage, pageSize);

        List<Admin> admins2 = adminMapper.selectByExample(adminExample);
        admins.clear();
        admins.addAll(admins2);

        return admins;
    }

    public static List<Admin> corporationAccountSearch(int[] pages,String keyword,Date startTime,Date endTime,int startPage,int pageSize,AdminMapper adminMapper){

        //分页 按搜索条件获取运营商信息列表

        AdminExample adminExample = new AdminExample();
        List<Admin> admins;
        List<Admin> admins1;

        //按日期搜索
        if(startTime != null && endTime !=null){
            adminExample.createCriteria().andCreateTimeBetween(startTime,endTime);
        }
        else if(startTime != null){
            adminExample.createCriteria().andCreateTimeGreaterThanOrEqualTo(startTime);
        }
        else if(endTime != null){
            adminExample.createCriteria().andCreateTimeLessThanOrEqualTo(endTime);
        }
        else{
            adminExample.createCriteria().andIdIsNotNull();
        }
        admins = adminMapper.selectByExample(adminExample);
        adminExample.createCriteria().andIdIsNotNull();
        adminExample = new AdminExample();
        //按照模糊条件搜索
        if(keyword != null && !keyword.trim().isEmpty()){
            keyword = "%"+keyword+"%";

            adminExample.or().andAccountLike(keyword);
            adminExample.or().andUsernameLike(keyword);
            adminExample.or().andDescrLike(keyword);
        }


        admins1 = adminMapper.selectByExample(adminExample);
        admins.retainAll(admins1);

        pages[0] = (int) Math.ceil(admins.size() / (double) pageSize);

        //在已知结果中进行分页操作
        List<Integer> adminsId = new ArrayList<Integer>();

        for(Admin admin:admins){
            adminsId.add(admin.getId());
        }

        if(adminsId.size()== 0){
            //json.put("corporationInfo","null");
            return admins;
        }

        adminExample = new AdminExample();
        adminExample.createCriteria().andIdIn(adminsId);
        adminExample.setDividePage(startPage, pageSize);

        List<Admin> admins2 = adminMapper.selectByExample(adminExample);
        admins.clear();
        admins.addAll(admins2);

        return admins;
    }

    public static List<Admin> corporationAccountSearch(int[] pages,String keyword,int startPage,int pageSize,AdminMapper adminMapper){

        //分页 按搜索条件获取运营商信息列表

        AdminExample adminExample = new AdminExample();
        List<Admin> admins;
        List<Admin> admins1;
        adminExample.createCriteria().andIdIsNotNull();
        admins = adminMapper.selectByExample(adminExample);
        adminExample.createCriteria().andIdIsNotNull();
        adminExample = new AdminExample();
        //按照模糊条件搜索
        if(keyword != null && !keyword.trim().isEmpty()){
            keyword = "%"+keyword+"%";
            adminExample.or().andAccountLike(keyword);
            adminExample.or().andUsernameLike(keyword);
            adminExample.or().andDescrLike(keyword);
        }

        admins1 = adminMapper.selectByExample(adminExample);
        admins.retainAll(admins1);

        pages[0] = (int) Math.ceil(admins.size() / (double) pageSize);

        //在已知结果中进行分页操作
        List<Integer> adminsId = new ArrayList<Integer>();

        for(Admin admin:admins){
            adminsId.add(admin.getId());
        }

        if(adminsId.size()== 0){
            //json.put("corporationInfo","null");
            return admins;
        }

        adminExample = new AdminExample();
        adminExample.createCriteria().andIdIn(adminsId);
        adminExample.setDividePage(startPage, pageSize);

        List<Admin> admins2 = adminMapper.selectByExample(adminExample);
        admins.clear();
        admins.addAll(admins2);

        return admins;
    }
}

package utils.common;

import dao.AdminMapper;
import dao.UserMapper;
import model.Admin;
import model.AdminExample;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2016/8/19.
 */
public class AdminUtil {
    public static List<Admin> corporationAccountSearch(String keyword,Date startTime,Date endTime,int startPage,int pageSize, AdminMapper adminMapper){

        //��ҳ ������������ȡ��Ӫ����Ϣ�б�

        AdminExample adminExample = new AdminExample();
        List<Admin> admins = new ArrayList<Admin>();
        List<Admin> admins1 = new ArrayList<Admin>();

        //����������
        if(startTime != null && endTime !=null){
            adminExample.createCriteria().andCreateTimeBetween(startTime,endTime);
        }
        else if(startTime != null && endTime ==null){
            adminExample.createCriteria().andCreateTimeGreaterThanOrEqualTo(startTime);
        }
        else if(startTime == null && endTime !=null){
            adminExample.createCriteria().andCreateTimeLessThanOrEqualTo(endTime);
        }
        else{
            adminExample.createCriteria().andIdIsNotNull();
        }
        admins = adminMapper.selectByExample(adminExample);

        //����ģ����������
        if(keyword != null){
            keyword = "%"+keyword+"%";
            adminExample = new AdminExample();
            adminExample.or().andAccountLike(keyword);
            adminExample.or().andUsernameLike(keyword);
            adminExample.or().andDescrLike(keyword);
        }
        else{
            adminExample = new AdminExample();
            adminExample.createCriteria().andIdIsNotNull();
        }

        admins1 = adminMapper.selectByExample(adminExample);
        admins.retainAll(admins1);

        //����֪����н��з�ҳ����
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

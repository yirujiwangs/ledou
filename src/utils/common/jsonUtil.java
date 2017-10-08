package utils.common;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.DeviceMapper;
import dao.GroupMapper;
import dao.User_infoMapper;
import model.*;

import java.util.List;

/**
 * Created by lenovo on 2015/12/1.
 */
public class jsonUtil {
    /**
     * 控制user
     *
     * @param list
     * @param user_infoMapper
     * @return JSONArray
     */
    public static JSONArray userList2json(List<User> list, User_infoMapper user_infoMapper) {
        JSONObject json;
        JSONArray jsonArray = new JSONArray();
        for (User user : list) {
            User_info userInfo = user_infoMapper.selectByPrimaryKey(user.getUserinfoid());
            String type = "";
            switch (user.getStoreTypeEnum()) {
                case UNINIT:
                    type = "未初始化";
                    break;
                case NORMAL:
                    type = "普通类型";
                    break;
                case PLATFORM:
                    type = "平台类型";
                    break;
                default:
                    break;
            }
            String status = user.getStatus();
            if ("NORMAL".equals(status))
                status = "true";
            else if ("NOTACTIVITION".equals(status))
                status = "false";

            json = new JSONObject();
            json.put("id", user.getId());
            json.put("account", user.getPhonenum());
            json.put("storeName", user.getStorename());
            json.put("utoken", user.getUtoken());
            json.put("storeType", type);
            json.put("status", status);
            json.put("createTime", user.getCreatetime());
            json.put("maxGroupNum", user.getMaxgroupnum());
            json.put("maxStoreNum", user.getMaxstorenum());
            json.put("remark", userInfo.getRemark());
            json.put("descr", user.getDescr());
            jsonArray.add(json);
        }
        return jsonArray;
    }

    public static JSONArray device2json(List<User> list, DeviceMapper deviceMapper, GroupMapper groupMapper, User_infoMapper userInfoMapper) {
        JSONObject json;
        JSONArray jsonArray = new JSONArray();
        for (User user : list) {
            json = new JSONObject();
            json.put("account", user.getPhonenum());
            json.put("storeName", user.getStorename());

            DeviceExample deviceExample = new DeviceExample();
            if (user.getPhonenum() == null)
                user.setPhonenum("");
            deviceExample.createCriteria().andPhoneEqualTo(user.getPhonenum());
            //设备总数
            int deviceNum = deviceMapper.countByExample(deviceExample);

            GroupExample groupExample = new GroupExample();
            groupExample.createCriteria().andPhonenumEqualTo(user.getPhonenum());
            //商家个人分组总数
            int groupNum = groupMapper.countByExample(groupExample);
            json.put("deviceNum", deviceNum);
            json.put("groupNum", groupNum);
            json.put("remark", userInfoMapper.selectByPrimaryKey(user.getUserinfoid()).getRemark());

            jsonArray.add(json);
        }
        return jsonArray;
    }

}

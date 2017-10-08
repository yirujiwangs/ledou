package service.Impl;

import com.alibaba.fastjson.JSONObject;
import dao.GroupMapper;
import model.Group;
import model.GroupExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.GroupService;

import java.util.List;

/**
 * Created by Administrator on 2016/10/6.
 */
@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    @Override
    public String getDeviceGroups(String phoneNum) {
        GroupExample groupExample = new GroupExample();

        groupExample.createCriteria().andPhonenumEqualTo(phoneNum);

        List<Group> list = groupMapper.selectByExample(groupExample);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("allDeviceGroup",-1);
        jsonObject.put("unAssignedGroup",0);

        for(Group group :list){
            jsonObject.put(group.getGroopname(),group.getId());
        }
        return jsonObject.toJSONString();
    }
}

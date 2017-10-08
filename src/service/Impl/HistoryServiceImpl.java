package service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import dao.HistoryMapper;
import model.History;
import model.HistoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.HistoryService;

import java.util.Date;
import java.util.List;

/**
 * Created by lenovo on 2015/12/5.
 */
@Service
public class HistoryServiceImpl implements HistoryService{
    @Autowired
    private HistoryMapper historyMapper;

    public JSONObject index() {
        JSONObject json;
        JSONArray jsons=new JSONArray();
        List<History> histories = historyMapper.selectByExample(null);
        for(History history:histories){
            json=new JSONObject();    
            json.put("createTime",history.getCreateTime());
            json.put("username",history.getUsername());
            String type=history.getType();
            json.put("type",type);
            json.put("content",history.getContent());
            json.put("adminname",history.getAdminname());
            jsons.add(json);
        }
        json=new JSONObject();
       // HistoryExample historyExample=new HistoryExample();
        List<String> list = historyMapper.selectByAdminAccount();
        //list.addAll(historyMapper.selectByUserAccount());

        json.put("admin", list);
        json.put("user",historyMapper.selectByUserAccount());
        json.put("generalData",jsons);
        return json;
    }

    public JSONObject search(String param) {
        JSONObject json;
        json=JSON.parseObject(param);
        String type = json.getString("type");
        String adminName = json.getString("adminName");
        String username = json.getString("username");
        Date startTime = json.getDate("startTime");
        Date endTime = json.getDate("endTime");

        HistoryExample historyExample=new HistoryExample();
        HistoryExample.Criteria criteria = historyExample.createCriteria();
        if(!type.equalsIgnoreCase("all")) {
        	criteria.andTypeEqualTo(type);
        }
        if(!adminName.equalsIgnoreCase("all")) criteria.andAdminnameEqualTo(adminName);
        if(!username.equalsIgnoreCase("all")) criteria.andUsernameEqualTo(username);
        if(startTime!=null)criteria.andCreatetimeGreaterThanOrEqualTo(startTime);
        if(endTime!=null) criteria.andCreatetimeLessThanOrEqualTo(endTime);
        List<History> histories = historyMapper.selectByExample(historyExample);
        json=new JSONObject();
        json.put("generalData",histories);
        return json;
    }


}

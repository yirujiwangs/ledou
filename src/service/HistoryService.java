package service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by lenovo on 2015/12/5.
 * 操作历史
 */

public interface HistoryService {

    JSONObject index();

    JSONObject search(String param);

}

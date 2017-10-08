package service;

import model.AdvOrderRecord;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/10/5.
 */
public interface AdvService {
     AdvOrderRecord addAdvOrder(AdvOrderRecord advOrderRecord);

     String advPayNotify(HttpServletRequest req);

     Integer getPayStatus(String advUUID);

    boolean insertRecord(HttpServletRequest req, String advUuid,String proxy_token);
}

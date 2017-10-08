package service;

import model.ProxyAreaReport;
import model.ProxyAreaReportLog;
import model.base.ProxyArea;
import model.dto.AdminCity;

import java.util.Date;
import java.util.List;

/**
 * Created by yeran on 2017/5/2.
 */
public interface ProxyAreaService {

    /**
     *
     * 报备搜索
     *
     * @param cid  //搜索的最小城市id
     *
     * @return
     */
   List<ProxyAreaReport> reports(Integer startPage,Integer pageSize,Integer cid,Integer[] pages);

    /**
     *
     * 执行报备
     *
     * @param utoken
     * @param rid
     * @return
     */
    boolean report(String utoken,Integer rid,String company,String contact_name,String contact_way,String comment);

    List<ProxyAreaReportLog> myReports(String utoken, Integer startPage, Integer pageSize, Integer[] pages);

    List<ProxyAreaReportLog> proxyReports(Integer rid, Integer startPage, Integer pageSize, Integer[] pages);

    boolean updateReportStatus(Integer id,String status);

    int dealerCountByUtoken(String utoken);

    int distCountByUtoken(String utoken);

    //下级区县级代理
    List<ProxyArea> distProxyAreaByUtoken(String utoken);

    //市级代理下的区县
    List<AdminCity> distAreaByUtoken(String utoken);

    ProxyArea proxyAreaByUtoken(String utoken);

    String distProxyAreaList(String param,String utoken);


 /**
  * 加盟区域列表
  *
  * @param utoken
  * @param stime
  * @param etime
  * @return
  */
    List<ProxyArea> distProxyAreaByUtoken(String utoken, Date stime,Date etime);
}

package service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import dao.ProxyAreaMapper;
import dao.ProxyAreaReportLogMapper;
import dao.ProxyAreaReportMapper;
import model.ProxyAreaReport;
import model.ProxyAreaReportExample;
import model.ProxyAreaReportLog;
import model.ProxyAreaReportLogExample;
import model.base.ProxyArea;
import model.base.ProxyAreaExample;
import model.dto.AdminCity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.BaseService;
import service.ProxyAreaService;
import utils.common.DividePageUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by yeran on 2017/5/2.
 */

@Service
public class ProxyAreaServiceImpl extends BaseService implements ProxyAreaService {

    @Autowired
    private ProxyAreaReportMapper proxyAreaReportMapper;

    @Autowired
    private ProxyAreaMapper proxyAreaMapper;

    @Autowired
    private ProxyAreaReportLogMapper proxyAreaReportLogMapper;

    @Override
    public List<ProxyAreaReport> reports(Integer startPage, Integer pageSize, Integer rid, Integer[] pages) {

        ProxyAreaReportExample proxyAreaReportExample = new ProxyAreaReportExample();
        proxyAreaReportExample.setDividePage(startPage, pageSize);
        //  proxyAreaReportExample.setOrderByClause("id");
        if (Objects.equals(rid, 1)) {
            proxyAreaReportExample.createCriteria().andArea_levelEqualTo(3);
        } else {
            proxyAreaReportExample.or().andRidEqualTo(rid).andArea_levelEqualTo(3);
            proxyAreaReportExample.or().andProvince_ridEqualTo(rid).andArea_levelEqualTo(3);
            proxyAreaReportExample.or().andCity_ridEqualTo(rid).andArea_levelEqualTo(3);
        }
//        proxyAreaReportExample.createCriteria().andRidEqualTo(cid);
//        proxyAreaReportExample.or().andPidEqualTo(cid);

        Integer count = proxyAreaReportMapper.countByExample(proxyAreaReportExample);
        pages[0] = DividePageUtil.getPages(count, pageSize);

        List<ProxyAreaReport> proxyAreaReports = proxyAreaReportMapper.selectByExample(proxyAreaReportExample);
        List<Integer> areas = new ArrayList<>();

        if(proxyAreaReports==null || proxyAreaReports.size()==0){
           return proxyAreaReports;
        }

        for (ProxyAreaReport proxyAreaReport : proxyAreaReports ){
            areas.add(proxyAreaReport.getCity_rid());
        }

        ProxyAreaReportLogExample proxyAreaReportLogExample = new ProxyAreaReportLogExample();
        proxyAreaReportExample.createCriteria().andRidIn(areas);
        List<ProxyAreaReportLog> proxyAreaReportLogs = proxyAreaReportLogMapper.selectByExample(proxyAreaReportLogExample);

        if(proxyAreaReportLogs==null || proxyAreaReportLogs.size()==0){
            return proxyAreaReports;
        }

        //当前按照后台设置的签约状态作为区域是否被签约的判断，也可以通过实际开设账户进行判断，为了方便状态切换以及后续代理商账号的变动
        //使用后台设置的签约状态作为区域是否被签约的判断的处理方式
        for (ProxyAreaReport proxyAreaReport : proxyAreaReports ){
            for (ProxyAreaReportLog proxyAreaReportLog : proxyAreaReportLogs){
                if(proxyAreaReportLog.getArea_rid().equals(""+proxyAreaReport.getRid())){
                    if(proxyAreaReportLog.getStatus().equals("N")) {
                        proxyAreaReport.setState(proxyAreaReportLog.getStatus());
                        break;
                    }
                }
            }
        }

        return proxyAreaReports;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean report(String utoken, Integer rid, String company, String contact_name, String contact_way, String comment) {
        try {
            //事务
            ProxyAreaReportLog proxyArea = new ProxyAreaReportLog();
            proxyArea.setUtoken(utoken);
            proxyArea.setArea_rid(rid + "");
            proxyArea.setCompany(company);
            proxyArea.setContact_name(contact_name);
            proxyArea.setContact_way(contact_way);
            proxyArea.setComment(comment);

            ProxyAreaReport proxyAreaReport = getProxyAreaReportByRid(rid);
            if (proxyAreaReport != null) {
                String areaName = proxyAreaReport.getFullname();
                proxyArea.setArea_name(areaName);
            }

            //System.out.println(JSONObject.toJSONString(proxyArea));

            int rows = proxyAreaReportLogMapper.insertSelective(proxyArea);
            if (rows > 0) {
                rows = proxyAreaReportMapper.updateReportNum(rid);
                if (rows > 0)
                    return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public ProxyAreaReport getProxyAreaReportByRid(Integer rid) {
        ProxyAreaReportExample proxyAreaReportExample = new ProxyAreaReportExample();
        proxyAreaReportExample.createCriteria().andRidEqualTo(rid);

        List<ProxyAreaReport> proxyAreaReports = proxyAreaReportMapper.selectByExample(proxyAreaReportExample);
        if (proxyAreaReports != null && proxyAreaReports.size() > 0) {
            return proxyAreaReports.get(0);
        }
        return null;
    }


    @Override
    public List<ProxyAreaReportLog> myReports(String utoken, Integer startPage, Integer pageSize, Integer[] pages) {

        ProxyAreaReportLogExample proxyAreaExample = new ProxyAreaReportLogExample();
        proxyAreaExample.setDividePage(startPage, pageSize);
        proxyAreaExample.createCriteria().andUtokenEqualTo(utoken);
        proxyAreaExample.setOrderByClause("id");
        int count = proxyAreaReportLogMapper.countByExample(proxyAreaExample);
        pages[0] = DividePageUtil.getPages(count, pageSize);

        return proxyAreaReportLogMapper.selectByExample(proxyAreaExample);
    }

    @Override
    public List<ProxyAreaReportLog> proxyReports(Integer rid, Integer startPage, Integer pageSize, Integer[] pages) {
        try {
            int count = proxyAreaReportLogMapper.countBySearchRid(rid);
            int startSize = (startPage - 1) * pageSize;
            pages[0] = DividePageUtil.getPages(count, pageSize);

            return proxyAreaReportLogMapper.selectBySearchRid(rid, startSize, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    public boolean updateReportStatus(Integer id, String status) {
        try {
            ProxyAreaReportLog proxyArea = proxyAreaReportLogMapper.selectByPrimaryKey(id);
            if (proxyArea == null)
                return false;
            proxyArea.setStatus(status);

            if (proxyAreaReportLogMapper.updateByPrimaryKeySelective(proxyArea) > 0)
                return true;
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public int dealerCountByUtoken(String utoken) {
        return proxyAreaMapper.dealerCountByUtoken(utoken);
    }

    @Override
    public int distCountByUtoken(String utoken) {
        return proxyAreaMapper.distCountByUtoken(utoken);
    }

    @Override
    public List<ProxyArea> distProxyAreaByUtoken(String utoken) {
        ProxyAreaExample proxyAreaExample = new ProxyAreaExample();
        proxyAreaExample.createCriteria().andSupertokenEqualTo(utoken);
        return proxyAreaMapper.selectByExample(proxyAreaExample);
    }

    @Override
    public List<AdminCity> distAreaByUtoken(String utoken) {
        return proxyAreaMapper.distAreaByUtoken(utoken);
    }

    @Override
    public ProxyArea proxyAreaByUtoken(String utoken) {
        ProxyAreaExample proxyAreaExample = new ProxyAreaExample();
        proxyAreaExample.createCriteria().andUtokenEqualTo(utoken);
        List<ProxyArea> proxyAreaList = proxyAreaMapper.selectByExample(proxyAreaExample);
        if(proxyAreaList!=null && proxyAreaList.size() > 0){
            return proxyAreaList.get(0);
        }else{
            return null;
        }
    }

    @Override
    public String distProxyAreaList(String param,String utoken) {
        JSONObject jsonObject = JSON.parseObject(param);
        Integer startPage = jsonObject.getInteger("startPage");
        Integer pageSize = jsonObject.getInteger("pageSize");
        ProxyAreaExample proxyAreaExample = new ProxyAreaExample();
        proxyAreaExample.createCriteria().andSupertokenEqualTo(utoken);
        int pages = DividePageUtil.getPages(proxyAreaMapper.countByExample(proxyAreaExample), pageSize);
        jsonObject.put("pages",pages);
        proxyAreaExample.setDividePage(startPage, pageSize);
        List<ProxyArea> proxyAreaList = proxyAreaMapper.selectByExample(proxyAreaExample);
        jsonObject.put("proxyAreaList",proxyAreaList);
        return JSON.toJSONString(jsonObject);
    }

    @Override
    public List<ProxyArea> distProxyAreaByUtoken(String utoken, Date stime, Date etime) {
        ProxyAreaExample proxyAreaExample = new ProxyAreaExample();
        proxyAreaExample.createCriteria().andSupertokenEqualTo(utoken).andCreatetimeBetween(stime,etime);
        return proxyAreaMapper.selectByExample(proxyAreaExample);
    }

}

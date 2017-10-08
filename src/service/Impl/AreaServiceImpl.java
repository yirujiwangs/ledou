package service.Impl;

import dao.CommonAreaMapper;
import dao.ProxyAreaMapper;
import model.Admin;
import model.base.CommonArea;
import model.base.CommonAreaExample;
import model.base.ProxyArea;
import model.base.ProxyAreaExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import service.AdminService;
import service.AreaService;
import service.BaseService;
import utils.common.LogUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by yeran on 2016/10/2.
 */
@Service
public class AreaServiceImpl extends BaseService implements AreaService {

    @Autowired
    private CommonAreaMapper commonAreaMapper;

    @Autowired
    private ProxyAreaMapper proxyAreaMapper;

    @Autowired
    private AdminService adminService;

    @Override
    public List<CommonArea> getCommonAreasByFId(Integer fId) {
        if (fId == null)
            return null;
        CommonAreaExample commonAreaExample = new CommonAreaExample();
        commonAreaExample.createCriteria().andPidEqualTo(fId);
        return commonAreaMapper.selectByExample(commonAreaExample);
    }

    @Override
    public CommonArea getCommonAreaById(Integer id) {
        if (id != null) {
            CommonAreaExample commonAreaExample = new CommonAreaExample();
            commonAreaExample.createCriteria().andRidEqualTo(id);
            List<CommonArea> commonAreas = commonAreaMapper.selectByExample(commonAreaExample);
            if (commonAreas != null && commonAreas.size() > 0)
                return commonAreas.get(0);
        }
        return null;
    }


    @Transactional(readOnly = false, rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public boolean createProxyArea(Integer cityCode, String proxy_token,String areaName,String proxyType,Integer policy) {
        try {
            LogUtil.log(AreaServiceImpl.class, LogUtil.LogType.INFO, "开始创建代理商区域信息代理商token:" + proxy_token + "---城市编号:" + cityCode);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            java.sql.Date startDate = new java.sql.Date(calendar.getTimeInMillis());
            calendar.add(Calendar.YEAR, 1);
            java.sql.Date endDate = new java.sql.Date(calendar.getTimeInMillis());

            ProxyArea proxyArea = new ProxyArea();
            proxyArea.setUtoken(proxy_token);
            proxyArea.setProxy_level(policy);
            proxyArea.setStart_date(startDate);
            proxyArea.setEnd_date(endDate);
            proxyArea.setArea_rid("" + cityCode);
            proxyArea.setArea_name(areaName);
            proxyArea.setStatus("N");

            if(proxyType.equals("P")){
                String superToken = getSuperTokenByAId(cityCode);
                if(superToken==null){
                    Admin admin = adminService.getAdminByProxyToken(proxy_token);
                    admin.setShield(1);
                    adminService.updateAdmin(admin);
                }
                proxyArea.setSupertoken(superToken);
            }else if(proxyType.equals("M")){
                //更新区县级代理
                ProxyAreaExample proxyAreaExample  = new ProxyAreaExample();
                List<String> areaCodeList = commonAreaMapper.selectDirSubByAreaCode("" + cityCode);
                proxyAreaExample.createCriteria().andArea_ridIn(areaCodeList);
                List<ProxyArea> proxyAreaList = proxyAreaMapper.selectByExample(proxyAreaExample);
                if(proxyAreaList!=null && !proxyAreaList.isEmpty()) {
                    for (ProxyArea area : proxyAreaList) {
                        area.setSupertoken(proxy_token);
                        proxyAreaMapper.updateByPrimaryKey(area);
                    }
                }
            }

            int id = proxyAreaMapper.insertSelective(proxyArea);

            if (id > 0) {
                LogUtil.log(AreaServiceImpl.class, LogUtil.LogType.SUCCESS, "创建代理商区域信息成功", "代理商token:" + proxy_token + "---城市编号:" + cityCode);
                return true;
            } else
                LogUtil.log(AreaServiceImpl.class, LogUtil.LogType.ERROR, "创建代理商区域信息失败", "代理商token:" + proxy_token + "---城市编号:" + cityCode);

        } catch (Exception e) {
            e.printStackTrace();
            LogUtil.log(AreaServiceImpl.class, LogUtil.LogType.ERROR, "创建代理商区域出现出错", e.getMessage());
        }
        return false;
    }

    @Override
    public String getSuperTokenByAId(Integer cityCode) {
        CommonAreaExample commonAreaExample = new CommonAreaExample();
        commonAreaExample.createCriteria().andRidEqualTo(cityCode);
        List<CommonArea> commonAreaList = commonAreaMapper.selectByExample(commonAreaExample);
        if(commonAreaList!=null && !commonAreaList.isEmpty()){
            CommonArea commonArea = commonAreaList.get(0);
            Integer pid = commonArea.getPid();
            ProxyAreaExample proxyAreaExample = new ProxyAreaExample();
            proxyAreaExample.createCriteria().andArea_ridEqualTo(pid + "");
            List<ProxyArea> proxyAreaList = proxyAreaMapper.selectByExample(proxyAreaExample);
            if(proxyAreaList!=null && !proxyAreaList.isEmpty()){
                ProxyArea proxyArea = proxyAreaList.get(0);
                return proxyArea.getUtoken();
            }
        }
        return null;
    }
}

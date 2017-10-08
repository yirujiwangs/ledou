package service;

import model.base.CommonArea;
import org.apache.poi.common.usermodel.LineStyle;

import java.util.List;

/**
 * Created by yeran on 2016/10/2.
 *
 * 区域管理
 */
public interface AreaService {

    /**
     * 根据父级城市的编码，获取下级所有城市的集合
     * @param fId
     * @return
     */
    List<CommonArea> getCommonAreasByFId(Integer fId);

    /**
     * 根据城市编码获取城市,验证合法性
     * @param id
     * @return
     */
    CommonArea getCommonAreaById(Integer id);


    /**
     * 创建代理商区域信息
     *        默认起始时间为创建时间，有效期365天，等级默认为1
     * @param cityCode
     * @param proxy_token
     * @param proxyType 代理等级
     * @return
     */
    boolean createProxyArea(Integer cityCode,String proxy_token,String areaName,String proxyType,Integer policy);

    /**
     * 根据区域码获取superTokenRid
     * @param cityCode
     * @return
     */
    String getSuperTokenByAId(Integer cityCode);

}

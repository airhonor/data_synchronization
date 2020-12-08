package com.honorzhang.data.synchronization.model.oracle;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @program: data_synchronization
 * @author: zgr
 * @create: 2020-12-07 19:37
 **/
@Data
public class InfoBasisInstance {

    private String id;

    private String instanceCode;

    private String satelliteId;

    private Timestamp epochTime;

    private String orbitNum;

    /**
     * 轨道半径长度，米
     */
    private String semiMajorAxis;

    /**
     * 偏心率
     */
    private String eccentricity;

    /**
     * 轨道倾角
     */
    private String inclination;

    /**
     * 升交点赤经
     */
    private String raan;

    /**
     * 近地点辐角
     */
    private String argumentOfPerigee;

    /**
     * 平近点角
     */
    private String meanAnoMaly;

    /**
     * 大气阻尼系数
     */
    private String atmDampingPara;

    /**
     * 光压反射系数
     */
    private String lpReflectPara;

    /**
     * 文件保存路径 计算不需要
     */
    private String filePath;

    /**
     * 来源（类型） 0外部、1本地、2转换
     */
    private Integer source;

    /**
     * 源轨道数据类型 0无转换源、1瞬根、2GPS、3两行
     */
    private Integer sourceType;

    /**
     * 源轨道数据UUID 可能是这个表的ORBIT_EPH_ID，或者GPS和Twoline表的ORBIT_EPH_ID，只有Converted类型的有
     */
    private String sourceId;

    /**
     * 备注
     */
    private String remark;

    /**
     * 记录更新时间
     */
    private Timestamp updateTime;

}

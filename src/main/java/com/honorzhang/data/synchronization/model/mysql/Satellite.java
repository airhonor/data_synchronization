package com.honorzhang.data.synchronization.model.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @program: data_synchronization
 * @author: zgr
 * @create: 2020-09-15 14:39
 **/
@Data
public class Satellite {
    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 卫星名称
     */
    private String name;

    /**
     * 卫星编号
     */
    private String code;

    /**
     * 卫星类型 高轨，低轨
     */
    private String satelliteType;

    /**
     * 重访周期（小时）
     */
    private Double revisitPeriod;

    /**
     * 单圈时间（小时）
     */
    private Double lapTime;

    /**
     * 传感器类型
     */
    private String senorType;

    /**
     * 侧摆能力
     */
    private String sidePendulumRange;

    /**
     * 两行轨道参数1
     */
    private String oneLineParams;

    /**
     * 两行轨道参数2
     */
    private String twoLineParams;

    /**
     * 载荷信息
     */
    private String loadInfo;

    /**
     * 轨道顏色
     */
    private String orbitColor;

    /**
     * 连接器的类型
     */
    private String connectorType;

    /**
     * 记录创建时间
     */
    private Long createTime;

    /**
     * 记录更新时间
     */
    private Long updateTime;
}

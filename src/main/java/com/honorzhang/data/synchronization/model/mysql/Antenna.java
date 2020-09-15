package com.honorzhang.data.synchronization.model.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能：地面站中天线模型
 * <p>
 * Created by ZY on 2020/9/10.
 */
@Slf4j
@Data
public class Antenna {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 关联的地面站ID
     */
    private Integer stationId;

    /**
     * 天线编号
     */
    private String code;

    /**
     * 天线名称
     */
    private String name;

    /**
     * 天线角度,后期计算的重要依据
     */
    private Double angle;

    /**
     * 记录创建时间
     */
    private Long createTime;

    /**
     * 记录更新时间
     */
    private Long updateTime;
}

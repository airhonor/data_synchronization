package com.honorzhang.data.synchronization.model.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 卫星测控站模型
 *
 * @author zew
 * @modify: zy
 * @Date: 2020/9/10
 */
@Slf4j
@Data
public class Station {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 站名称
     */
    private String name;

    /**
     * 站编号
     */
    private String code;

    /**
     * 匹配的卫星编号
     */
    private String matchedSatellitesCodes;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 高度
     */
    private Double height;

    /**
     * 站类别
     */
    private String type;

    /**
     * 记录创建时间
     */
    private Long createTime;

    /**
     * 记录更新时间
     */
    private Long updateTime;

    /**
     * 天线
     */
    @TableField(exist = false)
    private List<Antenna> antennas;
}

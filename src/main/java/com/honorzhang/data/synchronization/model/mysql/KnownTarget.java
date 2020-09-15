package com.honorzhang.data.synchronization.model.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 目标库
 *
 * @author admin
 */
@Data
public class KnownTarget {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 目标编号
     */
    private String code;

    /**
     * 目标名称
     */
    private String name;

    /**
     * 国别
     */
    private String country;

    /**
     * 类别
     */
    private String type;

    /**
     * 位置（存储geojson,便于扩展）
     */
    private String location;

    /**
     * 记录创建时间
     */
    private Long createTime;

    /**
     * 记录更新时间
     */
    private Long updateTime;
}
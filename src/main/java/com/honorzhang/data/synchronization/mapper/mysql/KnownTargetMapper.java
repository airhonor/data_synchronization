package com.honorzhang.data.synchronization.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.honorzhang.data.synchronization.model.mysql.KnownTarget;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * 功能：目标库Mapper接口
 * <p>
 * Created by ZY on 2020/9/3.
 */
@Component
@Mapper
public interface KnownTargetMapper extends BaseMapper<KnownTarget> {
}

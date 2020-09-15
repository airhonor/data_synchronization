package com.honorzhang.data.synchronization.mapper.mysql;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.honorzhang.data.synchronization.model.mysql.Satellite;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: data_synchronization
 * @author: zgr
 * @create: 2020-09-15 14:38
 **/
@Mapper
public interface SatelliteMapper extends BaseMapper<Satellite> {
}

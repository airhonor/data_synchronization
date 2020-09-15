package com.honorzhang.data.synchronization.model.oracle;

import lombok.Data;

/**
 * @program: data_synchronization
 * @author: zgr
 * @create: 2020-09-15 16:58
 **/
@Data
public class InfoBasisStation {
    private String id;

    private String stationName;

    private String stationType;

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
    private Double altitude;
}

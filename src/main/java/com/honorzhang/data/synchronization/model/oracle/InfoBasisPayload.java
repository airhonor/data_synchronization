package com.honorzhang.data.synchronization.model.oracle;

import lombok.Data;

/**
 * @program: data_synchronization
 * @author: zgr
 * @create: 2020-09-24 10:01
 **/
@Data
public class InfoBasisPayload {

    private String name;

    private String type;

    private String payloadSeries;

    private String groundResolution;

    private String swathWidth;

    private String overLap;

    private String minSunElevation;

    private String maxSunElevation;

    private String status;

    private String fov;

    private String model;

    private String timeOffset;
}

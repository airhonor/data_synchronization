package com.honorzhang.data.synchronization.model.oracle;

import lombok.Data;

/**
 * @program: data_synchronization
 * @author: zgr
 * @create: 2020-09-15 21:05
 **/
@Data
public class BizTargetPoint {
    private String targetId;
    private String latitude;
    private String longitude;
    private String targetPointSequence;

}

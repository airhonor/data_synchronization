package com.honorzhang.data.synchronization.model.oracle;

import lombok.Data;

/**
 * @program: data_synchronization
 * @author: zgr
 * @create: 2020-09-15 20:50
 **/
@Data
public class BizTarget {
    private String id;

    private String targetName;

    private String countryName;

    private String targetType;
}

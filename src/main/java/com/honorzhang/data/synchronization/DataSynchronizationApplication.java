package com.honorzhang.data.synchronization;

import com.honorzhang.data.synchronization.service.DataSynchronization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author honorzhang
 */
@SpringBootApplication
public class DataSynchronizationApplication {

    public static void main(String[] args) {
        SpringApplication.run(DataSynchronizationApplication.class, args);

    }


}

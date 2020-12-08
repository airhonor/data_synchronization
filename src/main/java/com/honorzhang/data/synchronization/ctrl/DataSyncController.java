package com.honorzhang.data.synchronization.ctrl;

import com.honorzhang.data.synchronization.service.DataSynchronization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: data_synchronization
 * @author: zgr
 * @create: 2020-09-15 15:12
 **/
@RestController
@RequestMapping("/data-sync")
public class DataSyncController {
    @Autowired
    private DataSynchronization dataSynchronization;

    @PostMapping("/satellite")
    public void satelliteDataSync() {
        dataSynchronization.satelliteDataSync();
    }

    @PostMapping("/station")
    public void stationDataSync() {
        dataSynchronization.stationDataSync();
    }

    @PostMapping("/antenna")
    public void antennaDataSync() {
        dataSynchronization.antennaDataSync();
    }

    @PostMapping("/target")
    public void targetDataSync() {
        dataSynchronization.targetDataSync();
    }

    @PostMapping("/instance")
    public void updateInstanceEpochTime() {
        dataSynchronization.updateInstanceEpochTime();
    }
}

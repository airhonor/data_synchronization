package com.honorzhang.data.synchronization.ctrl;

import com.honorzhang.data.synchronization.service.DataSynchronization;
import org.junit.Test;
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
        dataSynchronization.SatelliteDataSync();
    }

    @PostMapping("/station")
    public void stationDataSync() {
        dataSynchronization.StationDataSync();
    }

    @PostMapping("/antenna")
    public void antennaDataSync() {
        dataSynchronization.AntennaDataSync();
    }

    @PostMapping("/target")
    public void targetDataSync() {
        dataSynchronization.targetDataSync();
    }
}

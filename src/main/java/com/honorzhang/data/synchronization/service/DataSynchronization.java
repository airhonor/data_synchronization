package com.honorzhang.data.synchronization.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.honorzhang.data.synchronization.mapper.mysql.*;
import com.honorzhang.data.synchronization.mapper.oracle.*;
import com.honorzhang.data.synchronization.model.mysql.Antenna;
import com.honorzhang.data.synchronization.model.mysql.KnownTarget;
import com.honorzhang.data.synchronization.model.mysql.Satellite;
import com.honorzhang.data.synchronization.model.mysql.Station;
import com.honorzhang.data.synchronization.model.oracle.*;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @program: data_synchronization
 * @author: zgr
 * @create: 2020-09-15 14:50
 **/
@Service
public class DataSynchronization {
    private final SatelliteMapper satelliteMapper;
    private final InfoBasisSatelliteMapper infoBasisSatelliteMapper;
    private final InfoBasisStationMapper infoBasisStationMapper;
    private final StationMapper stationMapper;
    private final InfoBasisAntennaMapper infoBasisAntennaMapper;
    private final AntennaMapper antennaMapper;
    private final BizTargetMapper bizTargetMapper;
    private final BizTargetPointMapper bizTargetPointMapper;
    private final KnownTargetMapper knownTargetMapper;
    private final InfoBasisPayloadMapper infoBasisPayloadMapper;
    private final InfoBasisInstanceMapper infoBasisInstanceMapper;

    public DataSynchronization(SatelliteMapper satelliteMapper, InfoBasisSatelliteMapper infoBasisSatelliteMapper, InfoBasisStationMapper infoBasisStationMapper, StationMapper stationMapper, InfoBasisAntennaMapper infoBasisAntennaMapper, AntennaMapper antennaMapper, BizTargetMapper bizTargetMapper, BizTargetPointMapper bizTargetPointMapper, KnownTargetMapper knownTargetMapper, InfoBasisPayloadMapper infoBasisPayloadMapper, InfoBasisInstanceMapper infoBasisInstanceMapper) {
        this.satelliteMapper = satelliteMapper;
        this.infoBasisSatelliteMapper = infoBasisSatelliteMapper;
        this.infoBasisStationMapper = infoBasisStationMapper;
        this.stationMapper = stationMapper;
        this.infoBasisAntennaMapper = infoBasisAntennaMapper;
        this.antennaMapper = antennaMapper;
        this.bizTargetMapper = bizTargetMapper;
        this.bizTargetPointMapper = bizTargetPointMapper;
        this.knownTargetMapper = knownTargetMapper;
        this.infoBasisPayloadMapper = infoBasisPayloadMapper;
        this.infoBasisInstanceMapper = infoBasisInstanceMapper;
    }
    //读取info卫星数据

    public void satelliteDataSync() {

        List<InfoBasisSatellite> infoBasisSatellites = infoBasisSatelliteMapper.selectList(new QueryWrapper<>());
        for (InfoBasisSatellite i : infoBasisSatellites) {
            List<InfoBasisPayload> payloads = infoBasisPayloadMapper.selectList(new QueryWrapper<InfoBasisPayload>().eq("satellite_id", i.getId()));
            Satellite satellite = new Satellite();
            satellite.setName(i.getSatelliteName());
            satellite.setCode(i.getId());
            satellite.setSatelliteType(i.getSatelliteType());
            satellite.setRevisitPeriod(Double.parseDouble(i.getOrbitPeriod()));
            satellite.setLoadInfo(JSONObject.toJSONString(payloads));
            satellite.setCreateTime(System.currentTimeMillis());
            satellite.setUpdateTime(System.currentTimeMillis());
            satelliteMapper.insert(satellite);
        }
    }

    public void stationDataSync() {
        List<InfoBasisStation> infoBasisStations = infoBasisStationMapper.selectList(new QueryWrapper<>());
        for (InfoBasisStation station : infoBasisStations) {
            Station stationSync = new Station();
            stationSync.setCode(station.getId());
            stationSync.setName(station.getStationName());
            stationSync.setType(station.getStationType());
            stationSync.setLatitude(station.getLatitude());
            stationSync.setLongitude(station.getLongitude());
            stationSync.setHeight(station.getAltitude());
            stationSync.setCreateTime(System.currentTimeMillis());
            stationSync.setUpdateTime(System.currentTimeMillis());
            stationMapper.insert(stationSync);
        }
    }

    public void antennaDataSync() {
        List<InfoBasisAntenna> infoBasisAntennas = infoBasisAntennaMapper.selectList(new QueryWrapper<>());
        for (InfoBasisAntenna antenna : infoBasisAntennas) {
            Antenna antennaSync = new Antenna();
            antennaSync.setCode(antenna.getId());
            antennaSync.setName(antenna.getAntennaName());
            Station station = stationMapper.selectOne(new QueryWrapper<Station>().eq("code", antenna.getStationId()));
            antennaSync.setStationId(station.getId());
            antennaSync.setCreateTime(System.currentTimeMillis());
            antennaSync.setUpdateTime(System.currentTimeMillis());
            antennaMapper.insert(antennaSync);
        }
    }

    public void targetDataSync() {
        List<BizTarget> bizTargets = bizTargetMapper.selectList(new QueryWrapper<>());
        for (BizTarget target : bizTargets) {
            KnownTarget targetSync = new KnownTarget();
            targetSync.setCode(target.getId());
            targetSync.setCountry(target.getCountryName());
            targetSync.setName(target.getTargetName());
            targetSync.setType(target.getTargetType());
            List<BizTargetPoint> bizTargetPoints = bizTargetPointMapper.selectList(new QueryWrapper<BizTargetPoint>().eq("target_id", target.getId()).orderByAsc("target_point_sequence"));
            String location = bizTargetPoints.get(0).getLongitude() + " " + bizTargetPoints.get(0).getLatitude();
            for (int i = 1; i < bizTargetPoints.size(); i++) {
                location = location + "," + bizTargetPoints.get(i).getLongitude() + " " + bizTargetPoints.get(i).getLatitude();
            }
            targetSync.setLocation(location);
            targetSync.setCreateTime(System.currentTimeMillis());
            targetSync.setUpdateTime(System.currentTimeMillis());
            knownTargetMapper.insert(targetSync);

        }
    }

    public void updateInstanceEpochTime() {
        List<Satellite> satellites = satelliteMapper.selectList(new QueryWrapper<>());
        satellites.forEach(satellite -> {
            List<InfoBasisInstance> infoBasisInstances = infoBasisInstanceMapper.selectList(new QueryWrapper<InfoBasisInstance>().eq("satellite_id", satellite.getCode()).orderByDesc("epoch_time"));
            //更新瞬根的元立时间到程序启动时间的早上八点
            InfoBasisInstance infoBasisInstance = infoBasisInstances.get(0);
            Calendar calendar = Calendar.getInstance();
            calendar.set(2020, 11, 7, 8, 0, 0);
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
            Date date = calendar.getTime();
            Timestamp timestamp = new Timestamp(date.getTime());
            infoBasisInstance.setEpochTime(timestamp);
            infoBasisInstanceMapper.updateById(infoBasisInstance);
        });
    }

}

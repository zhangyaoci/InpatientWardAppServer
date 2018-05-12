package service.impl;

import dao.TemperatureDao;
import domain.Temperature;
import service.TemperatureService;

import java.util.Date;
import java.util.List;

public class TemperatureServiceImpl implements TemperatureService {
    private TemperatureDao temperatureDao;

    public void setTemperatureDao(TemperatureDao temperatureDao) {
        this.temperatureDao = temperatureDao;
    }

    /*获取病人体温值*/
    @Override
    public List<Temperature> getTemperatureDataByPatientIdAndTime(Integer patientId, Date startTime, Date endTime) {
        return this.temperatureDao.findTemperatureDataByPatientIdAndTime(patientId,startTime,endTime);
    }
}

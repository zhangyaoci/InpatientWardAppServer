package service;

import domain.Temperature;

import java.util.Date;
import java.util.List;

public interface TemperatureService {
    /*获取病人体温值*/
    public List<Temperature> getTemperatureDataByPatientIdAndTime(Integer patientId, Date startTime,Date endTime);
}

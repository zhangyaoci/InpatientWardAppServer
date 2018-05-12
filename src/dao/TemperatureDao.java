package dao;

import domain.Temperature;

import java.util.Date;
import java.util.List;

public interface TemperatureDao {
    /*得到病人体温值*/
    public List<Temperature> findTemperatureDataByPatientIdAndTime(Integer patientId, Date startTime,Date endTime);
}

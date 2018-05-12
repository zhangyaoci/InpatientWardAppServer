package service;

import domain.Heartrate;

import java.util.Date;
import java.util.List;

public interface HeartRateService {
    /*获取病人心率值*/
    public List<Heartrate> getHeartRateDataByPatientIdAndTime(Integer patientId, Date startTime,Date endTime);
}

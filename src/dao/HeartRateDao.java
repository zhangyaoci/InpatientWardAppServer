package dao;

import domain.Heartrate;

import java.util.Date;
import java.util.List;

public interface HeartRateDao {
    /*得到病人心率值*/
    public List<Heartrate> findHeartRateDataByPatientIdAndTime(Integer patientId, Date startTime,Date endTime);
}

package service;

import domain.Bloodglucose;

import java.util.Date;
import java.util.List;

public interface BloodGlucoseService {
    /*获取病人血糖值*/
    public List<Bloodglucose> getBloodGlucoseDataByPatientIdAndTime(Integer patientId, Date startTime,Date endTime);
}

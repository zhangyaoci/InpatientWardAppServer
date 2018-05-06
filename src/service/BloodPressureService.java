package service;

import domain.Bloodpressure;

import java.util.Date;
import java.util.List;

public interface BloodPressureService {
    /*获取病人的血压值*/
    public List<Bloodpressure> getBloodPressureByPatientIdAndTime(Integer patientId, Date startTime,Date endTime);
}

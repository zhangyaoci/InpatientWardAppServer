package dao;

import domain.Bloodglucose;

import java.util.Date;
import java.util.List;

public interface BloodGlucoseDao {
    /*得到病人血糖值*/
    public List<Bloodglucose> fingBloodGlucoseDataByPatientIdAndTime(Integer patientId, Date startTime,Date endTime);
}

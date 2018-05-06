package dao;

import domain.Bloodpressure;

import java.util.Date;
import java.util.List;

public interface BloodPressureDao {
    /*根据病人ID号，开始时间、结束时间，获取病人在这个时间段的血压数据*/
    public List<Bloodpressure> findBloodpressureByPatientIdAndTime(Integer patientId, Date startTime,Date endTime);
}

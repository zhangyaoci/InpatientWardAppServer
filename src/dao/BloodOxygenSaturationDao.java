package dao;

import domain.Bloodoxygensaturation;

import java.util.Date;
import java.util.List;

public interface BloodOxygenSaturationDao {
    /*得到血氧饱和度值*/
    public List<Bloodoxygensaturation> findBloodOxygenSaturationDataByPatientIdAndTime(Integer patientId, Date startTime,Date endTime);
}

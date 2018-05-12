package service;

import domain.Bloodoxygensaturation;

import java.util.Date;
import java.util.List;

public interface BloodOxygenSaturationService {
    /*获取病人血氧饱和度值*/
    public List<Bloodoxygensaturation> getBloodOxygenSaturationDataByPatientIdAndTime(Integer patientId, Date startTime,Date endTime);
}

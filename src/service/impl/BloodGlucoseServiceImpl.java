package service.impl;

import dao.BloodGlucoseDao;
import domain.Bloodglucose;
import service.BloodGlucoseService;

import java.util.Date;
import java.util.List;

public class BloodGlucoseServiceImpl implements BloodGlucoseService {
    private BloodGlucoseDao bloodGlucoseDao;

    public void setBloodGlucoseDao(BloodGlucoseDao bloodGlucoseDao) {
        this.bloodGlucoseDao = bloodGlucoseDao;
    }

    /*获取病人血糖值*/
    @Override
    public List<Bloodglucose> getBloodGlucoseDataByPatientIdAndTime(Integer patientId, Date startTime, Date endTime) {
        return this.bloodGlucoseDao.fingBloodGlucoseDataByPatientIdAndTime(patientId,startTime,endTime);
    }
}

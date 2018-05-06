package service.impl;

import dao.BloodPressureDao;
import domain.Bloodpressure;
import service.BloodPressureService;

import java.util.Date;
import java.util.List;

public class BloodPressureServiceImpl implements BloodPressureService {

    private BloodPressureDao bloodPressureDao;

    public void setBloodPressureDao(BloodPressureDao bloodPressureDao) {
        this.bloodPressureDao = bloodPressureDao;
    }


    /*获取病人的血压值*/
    @Override
    public List<Bloodpressure> getBloodPressureByPatientIdAndTime(Integer patientId, Date startTime, Date endTime) {
        return this.bloodPressureDao.findBloodpressureByPatientIdAndTime(patientId,startTime,endTime);
    }
}

package service.impl;

import dao.HeartRateDao;
import domain.Heartrate;
import service.HeartRateService;

import java.util.Date;
import java.util.List;

public class HeartRateServiceImpl implements HeartRateService {
    private HeartRateDao heartRateDao;
    public void setHeartRateDao(HeartRateDao heartRateDao) {
        this.heartRateDao = heartRateDao;
    }

    /*获取病人心率值*/
    @Override
    public List<Heartrate> getHeartRateDataByPatientIdAndTime(Integer patientId, Date startTime, Date endTime) {
        return this.heartRateDao.findHeartRateDataByPatientIdAndTime(patientId,startTime,endTime);
    }
}

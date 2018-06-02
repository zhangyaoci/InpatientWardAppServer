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
        return this.bloodPressureDao.findBloodpressureByPatientIdAndTime(patientId, startTime, endTime);
    }

    /*后台服务 分页*/
    @Override
    public List<Bloodpressure> getBPListWithPage(int page, int rows, int patientId, Date startTime, Date endTime) {
        return this.bloodPressureDao.findBPListWithPage(page,rows,patientId,startTime,endTime);
    }

    /*后台服务 大小*/
    @Override
    public int getBPListSizeWithPage(int patientId, Date startTime, Date endTime) {
        return this.bloodPressureDao.findBPListSizeWithPage(patientId,startTime,endTime);
    }
}

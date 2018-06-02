package service;

import domain.Bloodpressure;

import java.util.Date;
import java.util.List;

public interface BloodPressureService {
    /*获取病人的血压值*/
    public List<Bloodpressure> getBloodPressureByPatientIdAndTime(Integer patientId, Date startTime,Date endTime);

    /*后台服务 分页*/
    public List<Bloodpressure> getBPListWithPage(int page,int rows ,int patientId,Date startTime,Date endTime);

    /*后台服务，获取大小*/
    public int getBPListSizeWithPage(int patientId,Date startTime,Date endTime);
}

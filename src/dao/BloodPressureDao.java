package dao;

import domain.Bloodpressure;

import java.util.Date;
import java.util.List;

public interface BloodPressureDao {
    /*根据病人ID号，开始时间、结束时间，获取病人在这个时间段的血压数据*/
    public List<Bloodpressure> findBloodpressureByPatientIdAndTime(Integer patientId, Date startTime,Date endTime);

    /*根据条件搜索，进行分页处理 ，后台服务*/
    public List<Bloodpressure> findBPListWithPage(int page,int rows,int patientId,Date startTime,Date endTime);

    /*根据条件搜索，进行分页，获取总的大小*/
    public int findBPListSizeWithPage(int patientId,Date startTime,Date endTime);
}

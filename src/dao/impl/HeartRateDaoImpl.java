package dao.impl;

import dao.HeartRateDao;
import domain.Heartrate;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.Date;
import java.util.List;

public class HeartRateDaoImpl implements HeartRateDao {
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /*得到病人心率值*/
    @Override
    public List<Heartrate> findHeartRateDataByPatientIdAndTime(Integer patientId, Date startTime, Date endTime) {
        String[] params = new String[]{"patientId","startTime","endTime"};
        Object[] values = new Object[]{patientId,startTime,endTime};
        List<Heartrate> heartrateList = (List<Heartrate>)this.hibernateTemplate.findByNamedParam("from Heartrate where patient.patientId=:patientId and (time>=:startTime and time<=:endTime)", params, values);
        return heartrateList;
    }
}

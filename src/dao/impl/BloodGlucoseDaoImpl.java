package dao.impl;

import dao.BloodGlucoseDao;
import domain.Bloodglucose;
import domain.Bloodpressure;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.Date;
import java.util.List;

public class BloodGlucoseDaoImpl implements BloodGlucoseDao {
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /*得到病人血糖值*/
    @Override
    public List<Bloodglucose> fingBloodGlucoseDataByPatientIdAndTime(Integer patientId, Date startTime, Date endTime) {
        String[] params = new String[]{"patientId","startTime","endTime"};
        Object[] values = new Object[]{patientId,startTime,endTime};
        List<Bloodglucose> bloodglucoseList = (List<Bloodglucose>)this.hibernateTemplate.findByNamedParam("from Bloodglucose where patient.patientId=:patientId and (time>=:startTime and time<=:endTime)", params, values);
        return bloodglucoseList;
    }
}

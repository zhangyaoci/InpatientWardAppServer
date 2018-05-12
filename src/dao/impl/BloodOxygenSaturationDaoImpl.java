package dao.impl;

import dao.BloodOxygenSaturationDao;
import domain.Bloodoxygensaturation;
import domain.Bloodpressure;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.Date;
import java.util.List;

public class BloodOxygenSaturationDaoImpl implements BloodOxygenSaturationDao {
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /*得到病人血氧饱和度值*/
    @Override
    public List<Bloodoxygensaturation> findBloodOxygenSaturationDataByPatientIdAndTime(Integer patientId, Date startTime, Date endTime) {
        String[] params = new String[]{"patientId","startTime","endTime"};
        Object[] values = new Object[]{patientId,startTime,endTime};
        List<Bloodoxygensaturation> bloodoxygensaturationList = (List<Bloodoxygensaturation>)this.hibernateTemplate.findByNamedParam("from Bloodoxygensaturation where patient.patientId=:patientId and (time>=:startTime and time<=:endTime)", params, values);
        return bloodoxygensaturationList;
    }
}

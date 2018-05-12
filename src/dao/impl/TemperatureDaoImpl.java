package dao.impl;

import dao.TemperatureDao;
import domain.Temperature;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.Date;
import java.util.List;

public class TemperatureDaoImpl implements TemperatureDao {
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /*得到病人体温值*/
    @Override
    public List<Temperature> findTemperatureDataByPatientIdAndTime(Integer patientId, Date startTime, Date endTime) {
        String[] params = new String[]{"patientId","startTime","endTime"};
        Object[] values = new Object[]{patientId,startTime,endTime};
        List<Temperature> temperatureList = (List<Temperature>)this.hibernateTemplate.findByNamedParam("from Temperature where patient.patientId=:patientId and (time>=:startTime and time<=:endTime)", params, values);
        return temperatureList;
    }
}

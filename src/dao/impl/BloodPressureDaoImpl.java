package dao.impl;

import dao.BloodPressureDao;
import domain.Bloodpressure;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.Date;
import java.util.List;

public class BloodPressureDaoImpl implements BloodPressureDao {

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }


    /*获取病人的血压值*/
    @Override
    public List<Bloodpressure> findBloodpressureByPatientIdAndTime(Integer patientId, Date startTime, Date endTime) {

        String[] params = new String[]{"patientId","startTime","endTime"};

       // this.hibernateTemplate.findByNamedParam("frome Bloodpressure where ",params,1);
        return null;
    }
}

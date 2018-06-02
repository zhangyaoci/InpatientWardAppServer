package dao.impl;

import dao.BloodPressureDao;
import domain.Bloodpressure;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
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
        Object[] values = new Object[]{patientId,startTime,endTime};
        List<Bloodpressure> bloodPressureList = (List<Bloodpressure>)this.hibernateTemplate.findByNamedParam("from Bloodpressure where patient.patientId=:patientId and (time>=:startTime and time<=:endTime)", params, values);
        return bloodPressureList;
    }

    /*血压，后台服务*/
    @Override
    public List<Bloodpressure> findBPListWithPage(int page, int rows, int patientId, Date startTime, Date endTime) {
        List<Bloodpressure> bloodpressureList = this.hibernateTemplate.execute(new HibernateCallback<List<Bloodpressure>>() {
            @Override
            public List<Bloodpressure> doInHibernate(Session session) throws HibernateException {
                Query query= session.createQuery(" from Bloodpressure where isDelete=0 and patient.patientId =? and (time>=? and time<=?)");
                query.setParameter(0,patientId);
                query.setParameter(1,startTime);
                query.setParameter(2,endTime);
                query.setFirstResult((page - 1) * rows);
                query.setMaxResults(rows);
                return query.list();
            }
        });

        /*设置病人名字*/
        for(Bloodpressure bloodpressure :bloodpressureList){
            bloodpressure.setPatientName(bloodpressure.getPatient().getName());
        }


        return bloodpressureList;
    }

    /*后台服务*/
    @Override
    public int findBPListSizeWithPage(int patientId, Date startTime, Date endTime) {
        String[] params = new String[]{"patientId","startTime","endTime"};
        Object[] values = new Object[]{patientId,startTime,endTime};
        String size =this.hibernateTemplate.findByNamedParam("select count(*) from Bloodpressure where patient.patientId=:patientId and (time>=:startTime and time<=:endTime)", params, values).get(0).toString();
        return  Integer.parseInt(size);

    }
}

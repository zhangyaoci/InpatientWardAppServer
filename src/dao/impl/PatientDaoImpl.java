package dao.impl;

import dao.PatientDao;
import domain.Patient;
import domain.User;
import domain.UserPatient;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class PatientDaoImpl implements PatientDao {

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void save(Patient patient) {
        this.hibernateTemplate.save(patient);
    }

    @Override
    public void update(Patient Patient) {
        this.hibernateTemplate.update(Patient);
    }

    @Override
    public Patient getPatientById(int patientId) {
       return this.hibernateTemplate.get(Patient.class,patientId);
    }

    @Override
    public void delete(Patient patient) {
        this.hibernateTemplate.delete(patient);
    }


    /*根据用户Id，获取他所关注的病人*/
    @Override
    public List<Patient> getPatientByUserId(int userId) {

        Set<UserPatient> userPatients = this.hibernateTemplate.get(User.class,userId).getUserPatients();
        List<Patient> patients = new ArrayList<>();

        for(UserPatient userPatient:userPatients){
            Patient patient = userPatient.getPatient();
            patient.setRelationShip(userPatient.getRelation());

            /*这里很可能引起空指针异常*//*
            patient.setBloodglucoses(null);
            patient.setBloodoxygensaturations(null);
            patient.setBloodpressures(null);
            patient.setHeartrates(null);
            patient.setTemperatures(null);
            patient.setHospitalizations(null);
            patient.setAdvices(null);
            patient.setInformations(null);
            patient.setUserPatients(null);*/

            patients.add(patient);
        }


        return patients;
    }

    /*实现病人数据分页获取*/
    @Override
    public List<Patient> findPatientByPageAndRows(int page, int row) {
      List<Patient> patientList= this.hibernateTemplate.execute(new HibernateCallback<List<Patient>>() {
            @Override
            public List<Patient> doInHibernate(Session session) throws HibernateException {
             Query query= session.createQuery(" from Patient");
             query.setFirstResult((page - 1) * row);
             query.setMaxResults(row);
             return query.list();

            }
        });

      return patientList;
    }
}

package dao.impl;

import dao.PatientDao;
import domain.Patient;
import domain.User;
import domain.UserPatient;
import jdk.nashorn.internal.ir.IdentNode;
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
    public void update(Patient patient) {
        Patient patientTemp = this.hibernateTemplate.get(Patient.class,patient.getPatientId());
        patientTemp.setName(patient.getName());
        patientTemp.setSex(patient.getSex());
        patientTemp.setPhone(patient.getPhone());
        patientTemp.setDateOfBirth(patient.getDateOfBirth());
        patientTemp.setAddress(patient.getAddress());
        patientTemp.setIntroduction(patient.getIntroduction());
        patientTemp.setPicturePath(patient.getPicturePath());
        this.hibernateTemplate.save(patientTemp);

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

    /*实现病人数据分页获取 后台服务*/
    @Override
    public List<Patient> findPatientByPageAndRows(int page, int row,String patientName) {
      List<Patient> patientList= this.hibernateTemplate.execute(new HibernateCallback<List<Patient>>() {
            @Override
            public List<Patient> doInHibernate(Session session) throws HibernateException {
             Query query= session.createQuery(" from Patient where isDelete=? and name like ?");
             query.setParameter(0,0);
             query.setParameter(1,"%"+patientName+"%");
             query.setFirstResult((page - 1) * row);
             query.setMaxResults(row);
             return query.list();

            }
        });

      return patientList;
    }

    /*获取所有病人信息 后台服务*/
    @Override
    public Integer findPatientSize(String patientName) {
       String size = this.hibernateTemplate.find("select  count (*) from Patient where isDelete=0 and name like ?","%"+patientName+"%").get(0).toString();
       return Integer.parseInt(size);
    }

    @Override
    public List<Patient> findPatientByPatientName(String patientName) {
        List<Patient> patientList = (List<Patient>) this.hibernateTemplate.find("from Patient where isDelete=0 and name like ?","%"+patientName+"%");
        return patientList;
    }


    /*服务器删除数据*/
    @Override
    public String deleteByPatientId(int patientId) {
        try {
            Patient patient= this.hibernateTemplate.get(Patient.class,patientId);
            patient.setIsDelete(1);
            this.hibernateTemplate.save(patient);
            return "successResult";
        }
        catch (Exception e){
            System.out.println("dao层删除异常");
            return "errorResult";
        }
    }

    /*后台服务查找所有病人数据*/
    @Override
    public List<Patient> findAllPatient() {
        List<Patient> patientList  = (List<Patient>) this.hibernateTemplate.find("from Patient where isDelete=0");
        return patientList;
    }
}

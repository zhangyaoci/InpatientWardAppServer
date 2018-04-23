package dao.impl;

import dao.PatientDao;
import domain.Patient;
import domain.User;
import domain.UserPatient;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.ArrayList;
import java.util.HashSet;
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

            System.out.println(patient+"wewewe"+userPatient.getRelation());

            /*这里很可能引起空指针异常*/
            patient.setBloodglucoses(null);
            patient.setBloodoxygensaturations(null);
            patient.setBloodpressures(null);
            patient.setHeartrates(null);
            patient.setTemperatures(null);
            patient.setHospitalizations(null);
            patient.setAdvices(null);
            patient.setInformations(null);

            patients.add(patient);
        }


        return patients;
    }
}

package service.impl;

import dao.PatientDao;
import dao.UserPatientDao;
import domain.Patient;
import domain.UserPatient;
import service.PatientService;

import java.util.ArrayList;
import java.util.List;

public class PatientServiceImpl implements PatientService {

    private PatientDao patientDao;
    public void setPatientDao(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    private UserPatientDao userPatientDao;
    public void setUserPatientDao(UserPatientDao userPatientDao) {
        this.userPatientDao = userPatientDao;
    }

    @Override
    public void save(Patient patient) {
        patientDao.save(patient);
    }

    @Override
    public void update(Patient patient) {
        this.patientDao.update(patient);
    }


    /*通过用户的ID号，获取相关的病人列表*/
    @Override
    public List<Patient> getPatientByUserId(int userId) {
      List<UserPatient> userPatientList =  this.userPatientDao.findUserPatientByUserId(userId);
      List<Patient> patientList  = new ArrayList<>();
      for(UserPatient userPatient:userPatientList){
          Patient patient = this.patientDao.getPatientById(userPatient.getPatient().getPatientId());
          patient.setRelationShip(userPatient.getRelation());
          patientList.add(patient);
      }
      return patientList;
    }

    @Override
    public List<Patient> getPatientByPageAndRows(int page, int rows,String patientName) {
        return this.patientDao.findPatientByPageAndRows(page,rows,patientName);
    }

    @Override
    public Integer getPatientSize(String patientName) {
        return this.patientDao.findPatientSize(patientName);
    }



    @Override
    public Integer getPatientSizeByCondition(String patientName, String userName) {
        return null;
    }

    @Override
    public String deletePatientByPatientId(int patientId) {
        return this.patientDao.deleteByPatientId(patientId);
    }
}

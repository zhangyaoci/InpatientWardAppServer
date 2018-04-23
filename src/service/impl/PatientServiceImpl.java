package service.impl;

import dao.PatientDao;
import domain.Patient;
import service.PatientService;

import java.util.List;

public class PatientServiceImpl implements PatientService {

    private PatientDao patientDao;
    public void setPatientDao(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public void save(Patient patient) {
        patientDao.save(patient);
    }

    @Override
    public List<Patient> getPatientByUserId(int userId) {
        return this.patientDao.getPatientByUserId(userId);
    }
}

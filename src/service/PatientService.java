package service;

import domain.Patient;

import java.util.List;

public interface PatientService {
    public void save(Patient patient);

    /*根据用户ID号，获取该用户所关注的病人*/
    public List<Patient> getPatientByUserId(int userId);
}

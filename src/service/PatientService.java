package service;

import domain.Patient;

import java.util.List;

public interface PatientService {
    public void save(Patient patient);
    public List<Patient> getPatientByUserId(int userId);
}

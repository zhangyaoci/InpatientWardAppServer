package dao;

import domain.Patient;

import java.util.List;

public interface PatientDao {
    public void save(Patient patient);
    public void update(Patient Patient);
    public Patient getPatientById(int patientId);
    public void delete(Patient patient);

    public List<Patient> getPatientByUserId(int userId);
}

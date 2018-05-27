package dao;

import domain.Patient;

import java.util.List;

public interface PatientDao {
    public void save(Patient patient);
    public void update(Patient patient);
    public Patient getPatientById(int patientId);
    public void delete(Patient patient);

    public List<Patient> getPatientByUserId(int userId);

    /*分页获取病人数据*/
    public List<Patient> findPatientByPageAndRows(int page,int row,String patientName);

    public Integer findPatientSize(String patientName);

    /*服务器删除数据*/
    public String deleteByPatientId(int patientId);
}

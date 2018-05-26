package service;

import domain.Patient;

import java.util.List;

public interface PatientService {
    public void save(Patient patient);

    public void update(Patient patient);

    /*根据用户ID号，获取该用户所关注的病人*/
    public List<Patient> getPatientByUserId(int userId);

    /*分页获取病人数据*/
    public List<Patient> getPatientByPageAndRows(int page,int rows);

    /*获取病人数据的大小*/
    public Integer getPatientSize();

    /*服务器删除操作*/
    public String deletePatientByPatientId(int patientId);
}

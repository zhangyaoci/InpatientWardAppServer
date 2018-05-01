package dao;

import domain.UserPatient;

import java.util.List;

public interface UserPatientDao {

    /*根据用户ID，获取用户与病人的关系*/
    public List<UserPatient> findUserPatientByUserId(int userId);

    /*通过病人ID号找到与该病人相关的用户*/
    public  List<UserPatient> findUserPatientByPatientId(int patientId);
}

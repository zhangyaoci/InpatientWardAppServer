package service;

import domain.User;
import domain.UserPatient;

import java.util.List;

public interface UserPatientService {
    /*传入一个用户的Id  病人Id 返回这个两者之间的关系*/
    public String  getRelationShipByUserIdAndPatientId(Integer userId,Integer patientId);

    /*后台服务,根据病人Id，获取病人的关联的用户信息*/
    public List<UserPatient> getUsersByPatientId(Integer patientId);

}

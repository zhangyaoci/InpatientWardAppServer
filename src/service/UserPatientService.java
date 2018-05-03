package service;

public interface UserPatientService {
    /*传入一个用户的Id  病人Id 返回这个两者之间的关系*/
    public String  getRelationShipByUserIdAndPatientId(Integer userId,Integer patientId);
}

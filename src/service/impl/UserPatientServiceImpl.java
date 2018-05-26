package service.impl;

import dao.UserPatientDao;
import domain.User;
import domain.UserPatient;
import service.UserPatientService;

import java.util.List;

public class UserPatientServiceImpl implements UserPatientService {

    private UserPatientDao userPatientDao ;
    public void setUserPatientDao(UserPatientDao userPatientDao) {
        this.userPatientDao = userPatientDao;
    }

    /*获取病人和用户之间的关系*/
    @Override
    public String getRelationShipByUserIdAndPatientId(Integer userId, Integer patientId) {
        String relationShip = this.userPatientDao.findRelationShipByUserIdAndPatientId(userId,patientId);
        return relationShip;
    }

    /*后台服务*/
    @Override
    public List<UserPatient> getUsersByPatientId(Integer patientId) {
        return this.userPatientDao.findUserPatientByPatientId(patientId);
    }
}

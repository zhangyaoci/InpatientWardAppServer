package service.impl;

import dao.UserPatientDao;
import service.UserPatientService;

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
}

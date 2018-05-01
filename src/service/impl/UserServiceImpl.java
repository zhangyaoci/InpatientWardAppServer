package service.impl;

import dao.UserDao;
import dao.UserPatientDao;
import domain.User;
import domain.UserPatient;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {


    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    private UserPatientDao userPatientDao;
    public void setUserPatientDao(UserPatientDao userPatientDao) {
        this.userPatientDao = userPatientDao;
    }

    @Override
    public void saveUser(User user) {
        this.userDao.save(user);
    }

    @Override
    public void updateUser(User user) {
        this.userDao.update(user);
    }

    @Override
    public void deleteUser(User user) {
        this.userDao.delete(user);
    }

    @Override
    public User findUserById(int id) {
        return this.userDao.findById(id);
    }



    @Override
    public List<User> findUserByPhone(String phone) {
        return this.userDao.getUserByPhone(phone);
    }

    @Override
    public List<User> findAllUser() {
        return (List<User>) this.userDao.findAll();
    }


    /*通过病人ID号，找到病人的监护人*/
    @Override
    public User getUserOfGuardianByPatientId(int patientId) {
       List<UserPatient>  userPatientList = this.userPatientDao.findUserPatientByPatientId(patientId);
       for(UserPatient userPatient :userPatientList){
           if(userPatient.getGuardian().equals("1")){
               User user = this.userDao.findById(userPatient.getUser().getUserId());
               return  user;
           }
       }
       return null;
    }
}

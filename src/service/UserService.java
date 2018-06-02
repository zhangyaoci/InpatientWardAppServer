package service;

import domain.User;

import java.util.List;

public interface UserService {
    public void saveUser(User user);
    public void updateUser(User user);
    public void deleteUser(User user);

    public  User findUserById(int id);
    public  User getUserOfGuardianByPatientId(int patientId);

    public  List<User> findUserByPhone(String phone);
    public List<User> findAllUser();

    /*后台服务，获取用户列表*/
    public List<User> getUserListByUserName(int page, int rows, String userName);

    /*后台服务，获取用户列表大小*/
    public int getUserListSizeByUserName(String userName);

}

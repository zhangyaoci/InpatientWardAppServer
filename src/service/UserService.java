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
}

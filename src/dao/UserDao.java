package dao;

import domain.User;

import java.util.List;

public  interface UserDao {
    public void  save(User user);
    public void  update(User user);
    public void  delete(User user);


    public User  findById(Integer id);


    public List<User>  getUserByPhone(String phone);
    public User getUserOfGuardianByPatientId(int patientId);
    public List<?> findAll();


    /*根据用户名字，分页处理用户列表*/
    public List<User> findUserListByUserName(int page, int rows, String userName);

    /*根据用户名字，获取当前列表的大小*/
    public int findUserListSizeByUserName(String userName);



}

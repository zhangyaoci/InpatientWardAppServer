package dao;

import domain.User;

import java.util.List;

public interface UserDao {
    public void  save(User user);
    public void  update(User user);
    public void  delete(User user);
    public User  findById(String id);

    public List<?> findAll();
}

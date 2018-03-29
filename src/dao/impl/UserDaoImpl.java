package dao.impl;

import dao.UserDao;
import domain.User;
import org.springframework.orm.hibernate5.HibernateTemplate;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public void save(User user) {
        this.hibernateTemplate.save(user);
    }

    @Override
    public void update(User user) {
        this.hibernateTemplate.update(user);
    }

    @Override
    public void delete(User user) {
        this.hibernateTemplate.delete(user);
    }

    @Override
    public User findById(String id) {
        return this.hibernateTemplate.get(User.class,id);
    }

    @Override
    public List<?> findAll() {
        return  this.hibernateTemplate.find("from User ");
    }
}

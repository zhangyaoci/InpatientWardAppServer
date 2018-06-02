package dao.impl;

import dao.UserDao;
import domain.Patient;
import domain.User;
import domain.UserPatient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.scheduling.support.SimpleTriggerContext;

import java.util.List;
import java.util.Set;

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
    public User findById(Integer id) {
        return this.hibernateTemplate.get(User.class,id);
    }

    @Override
    public List<User> getUserByPhone(String phone) {
        return  (List<User>) this.hibernateTemplate.findByNamedParam("from User where phone=:phone","phone",phone);
    }

    @Override
    public User getUserOfGuardianByPatientId(int patientId) {
        Set<UserPatient> userPatients= this.hibernateTemplate.get(Patient.class,patientId).getUserPatients();
        for(UserPatient userPatient :userPatients){
            if(userPatient.getGuardian().equals("1")){
                return userPatient.getUser();
            }
        }

        return null;
    }

    @Override
    public List<?> findAll() {
        return  this.hibernateTemplate.find("from User ");
    }


    /*后台获取用户列表服务*/
    @Override
    public List<User> findUserListByUserName(int page, int rows, String userName) {
        List<User> userList = this.hibernateTemplate.execute(new HibernateCallback<List<User>>() {
            @Override
            public List<User> doInHibernate(Session session) throws HibernateException {
                Query query= session.createQuery(" from User where  name like ? and isDelete=0");
                query.setParameter(0,"%"+userName+"%");
                query.setFirstResult((page - 1) *rows);
                query.setMaxResults(rows);
                return query.list();
            }
        });
        return userList;
    }

    /*后台服务，获取用户列表大小*/
    @Override
    public int findUserListSizeByUserName(String userName) {
        String size = this.hibernateTemplate.find("select count (*) from User where isDelete=0 and name like ?","%"+userName+"%").get(0).toString();
        return Integer.parseInt(size);
    }
}

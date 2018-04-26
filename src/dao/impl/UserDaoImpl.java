package dao.impl;

import dao.UserDao;
import domain.Patient;
import domain.User;
import domain.UserPatient;
import org.springframework.orm.hibernate5.HibernateTemplate;
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
    public User findById(String id) {
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
}

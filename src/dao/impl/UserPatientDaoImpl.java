package dao.impl;

import dao.UserPatientDao;
import domain.UserPatient;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

public class UserPatientDaoImpl implements UserPatientDao {

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /*通过用户ID号获取用户和病人的消息*/
    @Override
    public List<UserPatient> findUserPatientByUserId(int userId) {
      List<UserPatient> userPatientList =(List<UserPatient>)this.hibernateTemplate.findByNamedParam(
              "from UserPatient where user.userId=:userId","userId",userId);
      return  userPatientList;
    }


    /*通过病人ID号找到与该病人相关的用户*/
    @Override
    public List<UserPatient> findUserPatientByPatientId(int patientId) {
        List<UserPatient> userPatientList = (List<UserPatient>)this.hibernateTemplate.findByNamedParam(
                "from UserPatient where patient.patientId=:patientId","patientId",patientId);
        return userPatientList;
    }

}

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
    /*后台删除服务也到了该方法*/
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

    /*根据病人和用户ID号找到两者之间的亲戚关系*/
    @Override
    public String findRelationShipByUserIdAndPatientId(Integer userId, Integer patientId) {
        String[] paramName = new String[]{"userId","patientId"};
        Integer[]  values = new Integer[]{userId,patientId};
        List<UserPatient> userPatientList = (List<UserPatient>) this.hibernateTemplate.findByNamedParam(
                "from UserPatient where user.userId=:userId and patient.patientId=:patientId",
                paramName,values);

        if(userPatientList.size()!=0){
          return  userPatientList.get(0).getRelation();
        }

        return null;
    }


}

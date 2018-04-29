package dao.impl;

import dao.InformationDao;
import dao.InformationUserDao;
import domain.Information;
import domain.InformationUser;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;
import java.util.Set;

public class InformationUserDaoImpl implements InformationUserDao{

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /*获取用户未读的消息*/
    @Override
    public List<InformationUser> findInformationUserByUserId(int useId) {

        String[] paramName= new String[]{"isRead", "userId"};

        Integer[] values = new Integer[]{0,useId};

        return (List<InformationUser>) this.hibernateTemplate.findByNamedParam("from InformationUser where isRead=:isRead and user.userId=:userId"
                ,paramName,values);
    }
}

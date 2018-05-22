package dao.impl;

import dao.ServerAdminUserDao;
import domain.AdminUser;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

public class ServerAdminUserDaoImpl implements ServerAdminUserDao {

    private HibernateTemplate hibernateTemplate;
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public AdminUser findAdminUserByName(String name) {
        List<AdminUser> adminUserList = (List<AdminUser>) this.hibernateTemplate.findByNamedParam("from AdminUser where name=:name","name",name);
        if(adminUserList.size()!=0){
            return  adminUserList.get(0);
        }
        else{
            return null;
        }
    }
}

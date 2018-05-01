package dao.impl;

import dao.InformationDao;
import domain.Information;
import org.springframework.orm.hibernate5.HibernateTemplate;

public class InformationDaoImpl implements InformationDao {
    /*获取消息，通过消息ID来查找*/
    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /*通过消息id,查找消息，其中如果patientID为null时默认是系统发布的消息*/
    @Override
    public Information findInformationByInformationId(int informationId) {
       Information information = this.hibernateTemplate.get(Information.class,informationId);
       return  information;
    }
}

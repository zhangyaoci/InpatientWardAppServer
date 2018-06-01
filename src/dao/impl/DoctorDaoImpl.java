package dao.impl;

import dao.DoctorDao;
import domain.Doctor;
import domain.Patient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

public class DoctorDaoImpl implements DoctorDao {
    private HibernateTemplate hibernateTemplate;
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /*获取医生列表，分页处理*/
    @Override
    public List<Doctor> findDoctorListByDoctorName(int page, int row, String doctorName) {
        List<Doctor> doctorList= this.hibernateTemplate.execute(new HibernateCallback<List<Doctor>>() {
            @Override
            public List<Doctor> doInHibernate(Session session) throws HibernateException {
                Query query= session.createQuery(" from Doctor where isDelete=? and  name like ?");
                query.setParameter(0,0);
                query.setParameter(1,"%"+doctorName+"%");
                query.setFirstResult((page - 1) * row);
                query.setMaxResults(row);
                return query.list();

            }
        });

        return doctorList;
    }

    /*获取医生列表的大小*/
    @Override
    public int findDoctorListSizeByDoctorName(String doctorName) {
        String size = this.hibernateTemplate.find("select count (*) from Doctor  where name like ? and isDelete=0","%"+doctorName+"%").get(0).toString();
        return Integer.parseInt(size);
    }
}

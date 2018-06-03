package dao.impl;

import dao.NurseDao;
import domain.Nurse;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;

public class NurseDaoImpl implements NurseDao {
    private HibernateTemplate hibernateTemplate;
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /*分页处理*/
    @Override
    public List<Nurse> findNurseListByNurseName(int page, int rows, String nurseName) {
        List<Nurse> nurseList= this.hibernateTemplate.execute(new HibernateCallback<List<Nurse>>() {
            @Override
            public List<Nurse> doInHibernate(Session session) throws HibernateException {
                Query query= session.createQuery(" from Nurse where isDelete=? and  name like ?");
                query.setParameter(0,0);
                query.setParameter(1,"%"+nurseName+"%");
                query.setFirstResult((page - 1) *rows);
                query.setMaxResults(rows);
                return query.list();

            }
        });
        return nurseList;
    }

    /*获取列表大小*/
    @Override
    public int findNurseListSizeByNurseName(String nurseName) {
        String size = this.hibernateTemplate.find("select count(*) from Nurse where isDelete=0 and name like ?", "%" + nurseName + "%").get(0).toString();
        return Integer.parseInt(size);
    }

    /*获取所有的护士列表*/
    @Override
    public List<Nurse> findAllNurseList() {
        List<Nurse> nurseList = (List<Nurse>) this.hibernateTemplate.find("from Nurse  where isDelete=0");
        return nurseList;
    }
}

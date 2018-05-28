package dao.impl;

import dao.HospitalizationDao;
import domain.Hospitalization;
import domain.Patient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HospitalizationDaoImpl implements HospitalizationDao {

    private HibernateTemplate hibernateTemplate;
    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Override
    public List<Hospitalization> getHospitalizationBypatientId(int patientId) {
       List<Hospitalization> hospitalizations=(List<Hospitalization>) this.hibernateTemplate.findByNamedParam(
               "from Hospitalization where patient.patientId=:patientId","patientId",patientId);
       return hospitalizations;
    }

    /*后台服务，病人的住院记录*/
    @Override
    public List<Hospitalization> getHospitalizationByPatientName(int page, int rows, String patientName) {

        List<Hospitalization> patientList= this.hibernateTemplate.execute(new HibernateCallback<List<Hospitalization>>() {
            @Override
            public List<Hospitalization> doInHibernate(Session session) throws HibernateException {
                Query query= session.createQuery("select h FROM Hospitalization h inner join Patient p on h.patient.patientId=p.patientId and p.name like ? and h.isDelete=?");
                query.setParameter(0,"%"+patientName+"%");
                query.setParameter(1,0);
                query.setFirstResult((page - 1) * rows);
                query.setMaxResults(rows);
                return query.list();

            }
        });
        return patientList;
    }

    @Override
    public Integer getHospitalizationSizeByPatientName(String patientName) {
      String size =   this.hibernateTemplate.find("select count (h) FROM Hospitalization h inner join Patient p on h.patient.patientId=p.patientId and p.name like ? and h.isDelete=0","%"+patientName+"%").get(0).toString();
      return Integer.parseInt(size);
    }
}

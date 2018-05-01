package dao.impl;

import dao.HospitalizationDao;
import domain.Hospitalization;
import domain.Patient;
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
}

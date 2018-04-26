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
    public Set<Hospitalization> getHospitalizationBypatientId(int patientId) {
       Set<Hospitalization> hospitalizations= hibernateTemplate.get(Patient.class,patientId).getHospitalizations();
       return hospitalizations;
    }
}

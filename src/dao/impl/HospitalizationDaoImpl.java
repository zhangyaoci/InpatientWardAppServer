package dao.impl;

import dao.HospitalizationDao;
import domain.Hospitalization;
import domain.Patient;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.orm.hibernate5.HibernateCallback;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.sql.Timestamp;
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
        List<Hospitalization> hospitalizations = (List<Hospitalization>) this.hibernateTemplate.findByNamedParam(
                "from Hospitalization where patient.patientId=:patientId", "patientId", patientId);
        return hospitalizations;
    }

    /*后台服务，病人的住院记录*/
    @Override
    public List<Hospitalization> getHospitalizationByPatientName(int page, int rows, String patientName) {

        List<Hospitalization> patientList = this.hibernateTemplate.execute(new HibernateCallback<List<Hospitalization>>() {
            @Override
            public List<Hospitalization> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("select h FROM Hospitalization h inner join Patient p on h.patient.patientId=p.patientId and p.name like ? and h.isDelete=?");
                query.setParameter(0, "%" + patientName + "%");
                query.setParameter(1, 0);
                query.setFirstResult((page - 1) * rows);
                query.setMaxResults(rows);
                return query.list();

            }
        });
        return patientList;
    }

    @Override
    public Integer getHospitalizationSizeByPatientName(String patientName) {
        String size = this.hibernateTemplate.find("select count (h) FROM Hospitalization h inner join Patient p on h.patient.patientId=p.patientId and p.name like ? and h.isDelete=0", "%" + patientName + "%").get(0).toString();
        return Integer.parseInt(size);
    }

    /*删除一条住院记录*/
    @Override
    public String deleteHospitalizationById(Integer hospitalId) {
        try {
            Hospitalization hospitalization = this.hibernateTemplate.get(Hospitalization.class, hospitalId);
            hospitalization.setIsDelete(1);
            this.hibernateTemplate.save(hospitalization);
            return "delete_success";
        } catch (Exception e) {
            e.printStackTrace();
            return "delete_error";
        }
    }

    /*添加一条住院记录*/
    @Override
    public String addHospitalization(Hospitalization hospitalization) {

        /*判断该病人是否正在住院*/
        try {
         List<Hospitalization> hospitalizationList = (List<Hospitalization>) this.hibernateTemplate.find("from Hospitalization where patient.patientId=? and isDelete=0",hospitalization.getPatient().getPatientId());
         boolean flag = true;
         for (Hospitalization hospitalizationTemp : hospitalizationList) {
            if(hospitalizationTemp.getEndTime()==null){
                flag=false;
                break;
            }
         }

         if(flag){
             this.hibernateTemplate.save(hospitalization);
             return "add_success";
         }
         else {
             return "该病人正在住院";
         }



        }catch (Exception e){
            e.printStackTrace();
            return "住院记录添加失败";
        }

    }


    /*更新一条住院记录*/
    @Override
    public String updateHospitalization(Hospitalization hospitalization) {
        Hospitalization hospitalization_temp = this.hibernateTemplate.get(Hospitalization.class,hospitalization.getHospitalId());
        hospitalization_temp.setStartTime(new Timestamp(hospitalization.getTempStartTime().getTime()+8*60*60*1000));

        if(hospitalization.getTempEndTime()!=null){
            hospitalization_temp.setEndTime(new Timestamp(hospitalization.getTempEndTime().getTime()+8*60*60*1000));
        }

        hospitalization_temp.setRoom(hospitalization.getRoom());

        hospitalization_temp.setPatient(hospitalization.getPatient());
        hospitalization_temp.setDoctor(hospitalization.getDoctor());
        hospitalization_temp.setNurse(hospitalization.getNurse());

        try {
            this.hibernateTemplate.save(hospitalization_temp);
            return "update_success";
        }catch (Exception e){
            e.printStackTrace();
            return "update_error";
        }
    }
}

package service.impl;
import dao.HospitalizationDao;
import domain.Hospitalization;
import service.HospitalizationService;

import java.util.List;
import java.util.Set;

public class HospitalizationServiceImpl implements HospitalizationService {

    private HospitalizationDao hospitalizationDao;

    public void setHospitalizationDao(HospitalizationDao hospitalizationDao) {
        this.hospitalizationDao = hospitalizationDao;
    }


    /*通过病人ID，获取病人的住院记录*/
    @Override
    public List<Hospitalization> getHospitalizationByPatientId(int patientId) {
        return this.hospitalizationDao.getHospitalizationBypatientId(patientId);
    }

    @Override
    public List<Hospitalization> getHospitalizationByPatientName(int page,int rows,String patientName) {
       List<Hospitalization> hospitalizationList= this.hospitalizationDao.getHospitalizationByPatientName(page,rows,patientName);


       /*要保存的数据传递到临时变量*/
       for(Hospitalization hospitalization:hospitalizationList){
           /*临时存储变量*/
           hospitalization.setDoctorId(hospitalization.getDoctor().getDoctorId());
           hospitalization.setDoctorName(hospitalization.getDoctor().getName());
           hospitalization.setDoctorPhone(hospitalization.getDoctor().getPhone());
           hospitalization.setNurseId(hospitalization.getNurse().getNurseId());
           hospitalization.setNurseName(hospitalization.getNurse().getName());
           hospitalization.setNursePhone(hospitalization.getNurse().getPhone());
           hospitalization.setPatientId(hospitalization.getPatient().getPatientId());
           hospitalization.setPatientName(hospitalization.getPatient().getName());
           hospitalization.setPatientPhone(hospitalization.getPatient().getPhone());
       }

       /**/
        return hospitalizationList;
    }

    @Override
    public Integer getHospitalizationSizeByPatientName(String patientName) {
        return this.hospitalizationDao.getHospitalizationSizeByPatientName(patientName);
    }

    /*删除一条住院记录*/
    @Override
    public String deleteHospitalizationById(Integer hospitalId) {
        return this.hospitalizationDao.deleteHospitalizationById(hospitalId);
    }

    /*添加一条住院记录*/
    @Override
    public String addHospitalization(Hospitalization hospitalization) {
        return this.hospitalizationDao.addHospitalization(hospitalization);
    }

    /*更新一条住院记录*/
    @Override
    public String updateHospitalization(Hospitalization hospitalization) {
        return this.hospitalizationDao.updateHospitalization(hospitalization);
    }


}

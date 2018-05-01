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
}

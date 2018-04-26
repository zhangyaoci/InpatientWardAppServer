package service.impl;
import dao.HospitalizationDao;
import domain.Hospitalization;
import service.HospitalizationService;

import java.util.Set;

public class HospitalizationServiceImpl implements HospitalizationService {

    private HospitalizationDao hospitalizationDao;

    public void setHospitalizationDao(HospitalizationDao hospitalizationDao) {
        this.hospitalizationDao = hospitalizationDao;
    }

    @Override
    public Set<Hospitalization> getHospitalizationByPatientId(int patientId) {
        return this.hospitalizationDao.getHospitalizationBypatientId(patientId);
    }
}

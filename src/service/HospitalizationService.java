package service;

import domain.Hospitalization;

import java.util.List;
import java.util.Set;

public interface HospitalizationService {
    /*通过病人ID，获取病人的住院记录*/
    public List<Hospitalization> getHospitalizationByPatientId(int patientId);
}

package service;

import domain.Hospitalization;

import java.util.Set;

public interface HospitalizationService {
    public Set<Hospitalization> getHospitalizationByPatientId(int patientId);
}

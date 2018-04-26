package dao;

import domain.Hospitalization;
import java.util.List;
import java.util.Set;

public interface HospitalizationDao {
    /*通过病人的Id获取病人的住院信息*/
    public Set<Hospitalization> getHospitalizationBypatientId(int patientId);
}

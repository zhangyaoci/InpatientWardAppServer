package service;

import domain.Hospitalization;

import java.util.List;
import java.util.Set;

public interface HospitalizationService {
    /*通过病人ID，获取病人的住院记录*/
    public List<Hospitalization> getHospitalizationByPatientId(int patientId);

    /*根据病人名字，获取病人的住院记录，分页进行查询 后台服务*/
    public List<Hospitalization> getHospitalizationByPatientName(int page,int rows,String patientName);

    /*根据病人名字，获取病人的住院记录的总记录数*/
    public Integer getHospitalizationSizeByPatientName(String patientName);

    /*根据住院号，删除一条住院信息*/
    public String deleteHospitalizationById(Integer hospitalId);
}

package dao;

import domain.Hospitalization;
import java.util.List;
import java.util.Set;

public interface HospitalizationDao {
    /*通过病人的Id获取病人的住院信息*/
    public List<Hospitalization> getHospitalizationBypatientId(int patientId);

    /*根据病人名字，获取病人的住院记录，并且进行分页处理*/
    public List<Hospitalization> getHospitalizationByPatientName(int page,int rows ,String patientName);

    /*根据名字获取病人的住院记录总数*/
    public Integer  getHospitalizationSizeByPatientName(String patientName);

    /*后台服务，删除住院记录*/
    public String deleteHospitalizationById(Integer hospitalId);

    /*添加一条住院记录*/
    public String  addHospitalization(Hospitalization hospitalization);

    /*更新一条住院记录*/
    public String updateHospitalization(Hospitalization hospitalization);
}

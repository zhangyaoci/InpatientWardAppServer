package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Hospitalization;
import service.HospitalizationService;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HospitalizationAction  extends ActionSupport {

    //获取病人的Id号
    private  Integer patientId;

    public Integer getPatientId() {
        return patientId;
    }

    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    private HospitalizationService hospitalizationService;

    public void setHospitalizationService(HospitalizationService hospitalizationService) {
        this.hospitalizationService = hospitalizationService;
    }


    //成功向前台传入的数据,springMVC 默认是单例模式
    private Map<String,Object> jsonDataOfSuccess = new HashMap<String,Object>();
    //失败向前台传入的数据
    private Map<String,Object> jsonDataOfError = new HashMap<String,Object>();

    public Map<String, Object> getJsonDataOfSuccess() {
        return jsonDataOfSuccess;
    }

    public void setJsonDataOfSuccess(Map<String, Object> jsonDataOfSuccess) {
        this.jsonDataOfSuccess = jsonDataOfSuccess;
    }

    public Map<String, Object> getJsonDataOfError() {
        return jsonDataOfError;
    }

    public void setJsonDataOfError(Map<String, Object> jsonDataOfError) {
        this.jsonDataOfError = jsonDataOfError;
    }

    //根据病人的id号，从数据库中找到他的住院信息
    public String getHospitalizationByPatientId(){

        if(this.patientId!=null){
            Set<Hospitalization> hospitalizationSet = this.hospitalizationService.getHospitalizationByPatientId(patientId);
            if(hospitalizationSet.size()!=0){
                jsonDataOfSuccess.put("hospitalizationList",hospitalizationSet);
                return SUCCESS;
            }
        }
        return ERROR;
    }
}

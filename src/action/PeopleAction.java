package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Patient;
import service.PatientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeopleAction extends ActionSupport {


    private Integer userId;
    private PatientService patientService;


    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    //成功向前台传入的数据,springMVC 默认是单例模式
    private Map<String,Object> jsonDataOfSuccess = new HashMap<String,Object>();

    public Map<String, Object> getJsonDataOfError() {
        return jsonDataOfError;
    }

    public void setJsonDataOfError(Map<String, Object> jsonDataOfError) {
        this.jsonDataOfError = jsonDataOfError;
    }

    public Map<String, Object> getJsonDataOfSuccess() {
        return jsonDataOfSuccess;
    }

    public void setJsonDataOfSuccess(Map<String, Object> jsonDataOfSuccess) {
        this.jsonDataOfSuccess = jsonDataOfSuccess;
    }

    //失败向前台传入的数据
    private Map<String,Object> jsonDataOfError = new HashMap<String,Object>();


    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    //根据当前用户的Id，来获取他（她）所关注的病人

    public String getPatientsByUserId(){
        if(this.userId!=null){
            List<Patient> patientList = this.patientService.getPatientByUserId(this.userId);
            this.jsonDataOfSuccess.put("patientList",patientList);
            return SUCCESS;
        }

        return ERROR;
    }
}

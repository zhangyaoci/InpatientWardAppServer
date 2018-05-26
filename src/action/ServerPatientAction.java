package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Hospitalization;
import domain.Patient;
import domain.User;
import domain.UserPatient;
import entity.QueryParameter;
import service.HospitalizationService;
import service.PatientService;
import service.UserPatientService;
import service.UserService;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerPatientAction extends ActionSupport {

    /*病人序号*/
    private Integer patientId;
    public Integer getPatientId() {
        return patientId;
    }
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    private QueryParameter parameter;

    public QueryParameter getParameter() {
        return parameter;
    }

    public void setParameter(QueryParameter parameter) {
        this.parameter = parameter;
    }

    /*病人服务层*/
    private PatientService patientService;

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    /*用户服务*/
    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /*后台传出数据*/
    private Map<String,Object> jsonData = new HashMap<String,Object>();

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    /*住院记录服务*/
    private HospitalizationService hospitalizationService;
    public void setHospitalizationService(HospitalizationService hospitalizationService) {
        this.hospitalizationService = hospitalizationService;
    }
    /*病人用户关联服务*/
    private UserPatientService userPatientService;
    public void setUserPatientService(UserPatientService userPatientService) {
        this.userPatientService = userPatientService;
    }

    /*前台传入病人信息*/
    private Patient patient;
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String acquirePatientList() {
        if (this.parameter!= null) {
            /*获取数据的大小*/
            Integer size = this.patientService.getPatientSize();
            List<Patient> patientList = this.patientService.getPatientByPageAndRows(this.parameter.getPage(), this.parameter.getRows());
            for(Patient patient:patientList){
                User user = this.userService.getUserOfGuardianByPatientId(patient.getPatientId());
                if(user!=null){
                    patient.setGuardianName(user.getName());
                    patient.setGuardianPhone(user.getPhone());
                }
            }
            this.jsonData.put("patientList",patientList);
            this.jsonData.put("size",size);
            return SUCCESS;
        } else {
            return SUCCESS;
        }
    }


    /*添加一个病人*/
    public String addPatientAction(){
        if(this.patient!=null){
            Date dateOfBirth = new Date(this.patient.getTempDate().getTime());
            this.patient.setDateOfBirth(dateOfBirth);
            this.patientService.save(this.patient);
            this.jsonData.put("result","success");
            return SUCCESS;
        }
        else {
            this.jsonData.put("result","error");
            return SUCCESS;
        }

    }


    /*删除一条病人数据*/
    public String deletePatientAction(){
        if(patientId!=null){
          List<Hospitalization> hospitalizationList=  this.hospitalizationService.getHospitalizationByPatientId(this.patientId);
          List<UserPatient> userPatientList = this.userPatientService.getUsersByPatientId(this.patientId);
          if (hospitalizationList.size()==0&&userPatientList.size()==0){
             String result = this.patientService.deletePatientByPatientId(this.patientId);
             if(result.equals("successResult")){
                 this.jsonData.put("result","success");
                 return SUCCESS;
             }else {
                 this.jsonData.put("result","error");
                 return SUCCESS;
             }
          }
          else {
              this.jsonData.put("result","error");
              return SUCCESS;
          }

        }
        else {
            this.jsonData.put("result","error");
            return SUCCESS;
        }
    }
}

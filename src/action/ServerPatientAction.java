package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Patient;
import domain.User;
import entity.QueryParameter;
import service.PatientService;
import service.UserService;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerPatientAction extends ActionSupport {

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


}

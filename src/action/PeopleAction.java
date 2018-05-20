package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Doctor;
import domain.Hospitalization;
import domain.Nurse;
import domain.Patient;
import service.HospitalizationService;
import service.PatientService;

import java.util.*;

public class PeopleAction extends ActionSupport {


    private Integer userId;
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private PatientService patientService;
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }


    private Integer patientId;
    public Integer getPatientId() {
        return patientId;
    }
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    private Integer[] patientIds;
    public Integer[] getPatientIds() {
        return patientIds;
    }
    public void setPatientIds(Integer[] patientIds) {
        this.patientIds = patientIds;
    }


    /*住院服务*/
    private HospitalizationService hospitalizationService;
    public void setHospitalizationService(HospitalizationService hospitalizationService) {
        this.hospitalizationService = hospitalizationService;
    }

    //成功向前台传入的数据jsonData,springMVC 默认是单例模式
    private Map<String,Object> jsonData = new HashMap<String,Object>();
    public Map<String, Object> getJsonData() {
        return jsonData;
    }
    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }



    /*根据当前用户的Id，来获取他（她）所关注的病人*/
    public String getPatientsByUserId(){
        if(this.userId!=null){
            List<Patient> patientList = this.patientService.getPatientByUserId(this.userId);
            this.jsonData.put("success",patientList);
            return SUCCESS;
        }
        else{
            this.jsonData.put("error","后台获取用户当前关注病人信息失败");
            return SUCCESS;
        }
    }

    /*根据病人的ID号，获取病人的住院记录*/
    public String getHospitalizationByPatientId(){
        if(this.patientId!=null){
            List<Hospitalization> hospitalizationList = this.hospitalizationService.getHospitalizationByPatientId(this.patientId);
            this.jsonData.put("success",hospitalizationList);
            return "hospitalizationSuccess";
        }
        else{
            this.jsonData.put("error","后台获取病人住院信息失败");
            return "hospitalizationSuccess";
        }
    }

    /*通过病人的ID号，获取病人相关的医生和护士信息*/
    public String getDoctorAndNurseByPatientIds(){

        List<Integer> doctorIds = new ArrayList<>();
        List<Integer> nurseIds  = new ArrayList<>();

        if(this.patientIds!=null){
            List<Doctor> doctorList = new ArrayList<>();
            List<Nurse>  nurseList = new ArrayList<>();

           for(Integer patientId_ : this.patientIds){
            List<Hospitalization> hospitalizationList = this.hospitalizationService.getHospitalizationByPatientId(patientId_);
            for(Hospitalization hospitalization: hospitalizationList){
                boolean flag = true;
                for(Integer doctorId_ :doctorIds){
                    if(doctorId_==hospitalization.getDoctor().getDoctorId()){
                        flag=false;
                    }
                }
                if(flag){
                    doctorList.add(hospitalization.getDoctor());
                    doctorIds.add(hospitalization.getDoctor().getDoctorId());
                }

                boolean flag_ = true;
                for(Integer nurseId_:nurseIds){
                    if(nurseId_==hospitalization.getNurse().getNurseId()){
                        flag_=false;
                    }
                }
                if(flag_){
                    nurseList.add(hospitalization.getNurse());
                    nurseIds.add(hospitalization.getNurse().getNurseId());
                }

            }
           }

           jsonData.put("successOfDoctor",doctorList);
           jsonData.put("successOfNurse",nurseList);

           return "DoctorAndNurseSuccess";
        }else{
            jsonData.put("error", "后台获取医生和护士信息失败");
            return "DoctorAndNurseSuccess";
        }

    }
}

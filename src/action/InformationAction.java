package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Information;
import domain.Patient;
import service.InformationService;
import service.UserPatientService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InformationAction extends ActionSupport {
    /*消息提醒用户的ID*/
    private Integer userId;
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    private Integer informationId;

    public Integer getInformationId() {
        return informationId;
    }

    public void setInformationId(Integer informationId) {
        this.informationId = informationId;
    }

    private InformationService informationService;

    public void setInformationService(InformationService informationService) {
        this.informationService = informationService;
    }


    private UserPatientService userPatientService;
    public void setUserPatientService(UserPatientService userPatientService) {
        this.userPatientService = userPatientService;
    }

    /*需要向前台传递的数据*/
    private Map<String,Object> jsonData = new HashMap<>();
    public Map<String, Object> getJsonData() {
        return jsonData;
    }
    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }



    /*根据需要提醒的用户ID，获取该用户还未阅读的消息*/
    public String acquireInformation(){
        if(this.userId!=null){
            List<Information> informationList =this.informationService.getInformationByUserId(this.userId);
            for(Information information : informationList){
                /*获取当前消息的用户*/
                Patient patient  = information.getPatient();
                /*获取病人和用户之间的亲戚关系*/
                patient.setRelationShip(this.userPatientService.getRelationShipByUserIdAndPatientId(this.userId,patient.getPatientId()));
            }
            jsonData.put("success",informationList);
            return  SUCCESS;
        }else{
            jsonData.put("error","拉取消息失败");
            return  SUCCESS;
        }
    }

    /*修改消息的状态为已读*/
    public  String  changeReadingStateOfInformation(){
        if(this.userId!=null){
            String  result= this.informationService.alterReadingStateByUserIdAndInformationId(this.userId,this.informationId);
            if(result.equals("succesOfUpdatingState")){
                jsonData.put("success","消息状态修改成功");
                return SUCCESS;
            }
            else {
                jsonData.put("error","消息状态修改失败");
                return SUCCESS;
            }
        }
        else {
            jsonData.put("error","消息状态修改失败");
            return SUCCESS;
        }

    }

    /*修改消息的拉取状态为未被拉取*/
    public String changeIsPopToZeroForInformationUser(){
        if(this.userId!=null){
            this.informationService.setIsPopToZeroForInformationUser(this.userId);
            this.jsonData.put("success", "消息拉取状态修改成功");
            return SUCCESS;
        }
        else{
            this.jsonData.put("error","消息拉取状态修改失败");
            return SUCCESS;
        }
    }
}

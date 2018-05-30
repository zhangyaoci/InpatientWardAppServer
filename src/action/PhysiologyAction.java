package action;

import com.opensymphony.xwork2.ActionSupport;
import com.sun.net.httpserver.Authenticator;
import domain.*;
import service.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class PhysiologyAction extends ActionSupport {
    private Integer patientId;
    public Integer getPatientId() {
        return patientId;
    }
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }

    /*筛选条件，开始时间*/
    private Date startTime;
    public Date getStartTime() {
        return startTime;
    }
    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    /*筛选条件，结束时间*/
    private Date endTime;
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    /*提供病人血压数值服务*/
    private BloodPressureService bloodPressureService;
    public void setBloodPressureService(BloodPressureService bloodPressureService) {
        this.bloodPressureService = bloodPressureService;
    }
    /*提供病人体温数值服务*/
    private TemperatureService temperatureService;
    public void setTemperatureService(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }
    /*提供病人心率数值服务*/
    private HeartRateService heartRateService;
    public void setHeartRateService(HeartRateService heartRateService) {
        this.heartRateService = heartRateService;
    }
    /*提供病人血糖数值服务*/
    private BloodGlucoseService bloodGlucoseService;
    public void setBloodGlucoseService(BloodGlucoseService bloodGlucoseService) {
        this.bloodGlucoseService = bloodGlucoseService;
    }
    /*提供病人血氧饱和度服务*/
    private BloodOxygenSaturationService bloodOxygenSaturationService;
    public void setBloodOxygenSaturationService(BloodOxygenSaturationService bloodOxygenSaturationService) {
        this.bloodOxygenSaturationService = bloodOxygenSaturationService;
    }

    /*传递给前台数据*/
    private HashMap<String,Object> jsonData = new HashMap<>();
    public HashMap<String, Object> getJsonData() {
        return jsonData;
    }
    public void setJsonData(HashMap<String, Object> jsonData) {
        this.jsonData = jsonData;
    }




    /*根据病人ID号获取病人的血压值*/
    public String  acquirePhysiologyDataOfBloodPressure(){
        if(this.startTime!=null&&this.endTime!=null&&this.patientId!=null){
            List<Bloodpressure> bloodpressureList =this.bloodPressureService.getBloodPressureByPatientIdAndTime(patientId,startTime,endTime);
            this.jsonData.put("success",bloodpressureList);
            return SUCCESS;
        }else{
            this.jsonData.put("error","后台获取病人血压错误");
            return  SUCCESS;
        }
    }
    /*根据病人ID号获取病人的体温值 、 格林时间转换成中国时间*/
    public String acquirePhysiologyDataOfTemperature(){
        if(this.startTime!=null&&this.endTime!=null&&this.patientId!=null){
            this.startTime.setTime(this.startTime.getTime()+8*1000*60*60);
            this.endTime.setTime(this.endTime.getTime()+8*1000*60*60);
            List<Temperature> temperatureList = this.temperatureService.getTemperatureDataByPatientIdAndTime(patientId,startTime,endTime);
            this.jsonData.put("success",temperatureList);
            return SUCCESS;
        }else{
            this.jsonData.put("error","后台获取病人体温失败");
            return SUCCESS;
        }

    }

    /*根据病人ID号， 获取心率值*/
    public String  acquirePhysiologyDataOfHeartRate(){
        if(this.startTime!=null&&this.endTime!=null&&this.patientId!=null) {
            this.startTime.setTime(this.startTime.getTime() + 8 * 1000 * 60 * 60);
            this.endTime.setTime(this.endTime.getTime() + 8 * 1000 * 60 * 60);
            List<Heartrate> heartrateList = this.heartRateService.getHeartRateDataByPatientIdAndTime(patientId,startTime,endTime);
            this.jsonData.put("success",heartrateList);
            return SUCCESS;
        }else {
            this.jsonData.put("error","后台获取病人心率失败");
            return SUCCESS;
        }
    }

    /*根据病人ID号，获取血糖值*/
    public String acquirePhysiologyDataOfBloodGlucose() {

        if(this.startTime!=null&&this.endTime!=null&&this.patientId!=null) {
            this.startTime.setTime(this.startTime.getTime() + 8 * 1000 * 60 * 60);
            this.endTime.setTime(this.endTime.getTime() + 8 * 1000 * 60 * 60);
            List<Bloodglucose> bloodglucoseList = this.bloodGlucoseService.getBloodGlucoseDataByPatientIdAndTime(patientId,startTime,endTime);
            this.jsonData.put("success",bloodglucoseList);
            return  SUCCESS;
        }else {
            this.jsonData.put("error","后台获取病人血糖失败");
            return SUCCESS;
        }

    }

    /*根据病人ID号，获取血压饱和度值*/
    public String acquirePhysiologyDataOfSaturationOfBloodOxygen(){
        if(this.startTime!=null&&this.endTime!=null&&this.patientId!=null) {
            this.startTime.setTime(this.startTime.getTime() + 8 * 1000 * 60 * 60);
            this.endTime.setTime(this.endTime.getTime() + 8 * 1000 * 60 * 60);
            List<Bloodoxygensaturation> bloodoxygensaturationList = this.bloodOxygenSaturationService.getBloodOxygenSaturationDataByPatientIdAndTime(patientId,startTime,endTime);
            this.jsonData.put("success",bloodoxygensaturationList);
            return SUCCESS;
        }else {
            this.jsonData.put("error","后台获取病人血氧饱和度失败");
            return SUCCESS;
        }
    }
}

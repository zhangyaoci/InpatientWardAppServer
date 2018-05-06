package action;

import com.opensymphony.xwork2.ActionSupport;
import service.BloodPressureService;

import java.util.Date;
import java.util.HashMap;

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

    /*判断前台需要的是那种生理数据*/
    private String physiologyType;
    public String getPhysiologyType() {
        return physiologyType;
    }

    public void setPhysiologyType(String physiologyType) {
        this.physiologyType = physiologyType;
    }


    /*提供病人血压数值服务*/
    private BloodPressureService bloodPressureService;
    public void setBloodPressureService(BloodPressureService bloodPressureService) {
        this.bloodPressureService = bloodPressureService;
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
        if(this.startTime!=null){
            System.out.println(startTime.toString());
        }
        return  SUCCESS;
    }
}

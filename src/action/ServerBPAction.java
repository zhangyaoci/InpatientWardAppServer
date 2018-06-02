package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Bloodpressure;
import entity.QueryParameter;
import service.BloodPressureService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerBPAction extends ActionSupport {

    private QueryParameter queryParameter;

    public QueryParameter getQueryParameter() {
        return queryParameter;
    }

    public void setQueryParameter(QueryParameter queryParameter) {
        this.queryParameter = queryParameter;
    }

    private Map<String,Object> jsonData = new HashMap<>();

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }


    private BloodPressureService bloodPressureService;

    public void setBloodPressureService(BloodPressureService bloodPressureService) {
        this.bloodPressureService = bloodPressureService;
    }


    /*后台服务，获取所有病人血压*/
    public String acquireBPListWithPage(){
        if(this.queryParameter!=null){
            int size  = this.bloodPressureService.getBPListSizeWithPage(this.queryParameter.getPatientId(),this.queryParameter.getStartTime(),this.queryParameter.getEndTime());
            List<Bloodpressure> bloodpressureList = this.bloodPressureService.getBPListWithPage(this.queryParameter.getPage(), this.queryParameter.getRows(), this.queryParameter.getPatientId(), this.queryParameter.getStartTime(), this.queryParameter.getEndTime());
            this.jsonData.put("bloodpressureList",bloodpressureList);
            this.jsonData.put("size",size);
            return SUCCESS;
        }else {
            return SUCCESS;
        }
    }
}

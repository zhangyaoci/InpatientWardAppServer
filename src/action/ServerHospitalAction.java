package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Hospitalization;
import entity.QueryParameter;
import service.HospitalizationService;

import java.lang.reflect.Parameter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*病人的住院记录、以病人名字进行查询*/
public class ServerHospitalAction extends ActionSupport{

    /*查询参数*/
    private QueryParameter parameter;

    public QueryParameter getParameter() {
        return parameter;
    }

    public void setParameter(QueryParameter parameter) {
        this.parameter = parameter;
    }

    /*后台传出数据*/
    private Map<String,Object> jsonData = new HashMap<String,Object>();

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }


    private HospitalizationService hospitalizationService;

    public void setHospitalizationService(HospitalizationService hospitalizationService) {
        this.hospitalizationService = hospitalizationService;
    }

    /*分页获取病人信息*/
    public String acquireHospitalList(){
        if(this.parameter!=null){

            Integer size = this.hospitalizationService.getHospitalizationSizeByPatientName(this.parameter.getPatientName());
            List<Hospitalization> hospitalizationList= this.hospitalizationService.getHospitalizationByPatientName(this.parameter.getPage(),this.parameter.getRows(),this.parameter.getPatientName());
            this.jsonData.put("hospitalizationList",hospitalizationList);
            this.jsonData.put("size",size);
            return SUCCESS;
        }
        else{

            return SUCCESS;
        }

    }
}

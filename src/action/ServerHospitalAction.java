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

    /*后台传递的参数*/
    private Integer hospitalId;
    public Integer getHospitalId() {
        return hospitalId;
    }
    public void setHospitalId(Integer hospitalId) {
        this.hospitalId = hospitalId;
    }

    /*后台传递的参数*/
    public Hospitalization hospitalization;
    public Hospitalization getHospitalization() {
        return hospitalization;
    }
    public void setHospitalization(Hospitalization hospitalization) {
        this.hospitalization = hospitalization;
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

    /*删除一条住院记录信息*/
    public String eraseHospitalByHospitalId(){
        if(this.hospitalId!=null){
            String result = this.hospitalizationService.deleteHospitalizationById(this.hospitalId);
            if(result.equals("delete_success")){
                this.jsonData.put("result","success");
                return SUCCESS;
            }else{
                this.jsonData.put("result", "error");
                return SUCCESS;
            }
        }
        else {
            this.jsonData.put("result","error");
            return  SUCCESS;
        }
    }

    /*添加一条住院记录*/
    public String addHospitalizationAction(){

        System.out.println("222");
        return SUCCESS;
    }
}

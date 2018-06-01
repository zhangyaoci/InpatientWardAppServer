package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Doctor;
import entity.QueryParameter;
import org.apache.commons.lang3.ObjectUtils;
import service.DoctorService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerDoctorAction extends ActionSupport {
    private DoctorService doctorService;

    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }


    /*后台传出数据*/
    private Map<String,Object> jsonData = new HashMap<String,Object>();
    public Map<String, Object> getJsonData() {
        return jsonData;
    }
    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }


    /*前台传递参数*/
    private QueryParameter queryParameter;
    public QueryParameter getQueryParameter() {
        return queryParameter;
    }
    public void setQueryParameter(QueryParameter queryParameter) {
        this.queryParameter = queryParameter;
    }

    public String acquireDoctorListByDoctorName(){
        if (this.queryParameter != null) {
            int size  = this.doctorService.getDoctorListSizeByDoctorName(this.queryParameter.getDoctorName());
            List<Doctor> doctorList= this.doctorService.getDoctorListByDoctorName(this.queryParameter.getPage(),this.queryParameter.getRows(),this.queryParameter.getDoctorName());
            this.jsonData.put("doctorList",doctorList);
            this.jsonData.put("size",size);
            return SUCCESS;
        }else {
            return SUCCESS;
        }

    }
}

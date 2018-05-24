package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Patient;
import entity.QueryParameter;
import service.PatientService;

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

    /*前台传入数据*/
    private Map<String,Object> jsonData = new HashMap<String,Object>();

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public String acquirePatientList() {
        if (this.parameter!= null) {
            /*获取数据的大小*/
            Integer size = this.patientService.getPatientSize();
            List<Patient> patientList = this.patientService.getPatientByPageAndRows(this.parameter.getPage(), this.parameter.getRows());
            this.jsonData.put("patientList",patientList);
            this.jsonData.put("size",size);
            return SUCCESS;
        } else {
            return SUCCESS;
        }
    }


}

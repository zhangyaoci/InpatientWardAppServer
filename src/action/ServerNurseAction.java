package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Nurse;
import entity.QueryParameter;
import service.NurseService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerNurseAction extends ActionSupport {

    /*前台传递参数*/
    private QueryParameter queryParameter;
    public QueryParameter getQueryParameter() {
        return queryParameter;
    }

    public void setQueryParameter(QueryParameter queryParameter) {
        this.queryParameter = queryParameter;
    }


    /*向前台传递参数*/
    private Map<String,Object> jsonData = new HashMap<>();

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }


    /*服务类*/
    private NurseService nurseService;

    public void setNurseService(NurseService nurseService) {
        this.nurseService = nurseService;
    }


    /*获取医生数据列表*/
    public  String acquireNurseListByName(){
        if (this.queryParameter != null) {
            int size = this.nurseService.getNurseListSizeByNurseName(this.queryParameter.getNurseName());
            List<Nurse> nurseList = this.nurseService.getNurseListByNurseName(this.queryParameter.getPage(), this.queryParameter.getRows(), this.queryParameter.getNurseName());
            jsonData.put("nurseList", nurseList);
            jsonData.put("size", size);
            return SUCCESS;
        } else {
            return SUCCESS;
        }
    }

}

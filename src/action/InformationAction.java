package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Information;
import service.InformationService;

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


    private InformationService informationService;

    public void setInformationService(InformationService informationService) {
        this.informationService = informationService;
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
    public void  acquireInformation(){
        if(this.userId!=null){
            List<Information> informationList =this.informationService.getInformationByUserId(this.userId);
            jsonData.put("success",informationList);
        }
    }
}

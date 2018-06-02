package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.User;
import entity.QueryParameter;
import service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServerUserAction extends ActionSupport {
    /*前台传参*/
    private QueryParameter queryParameter;

    public QueryParameter getQueryParameter() {
        return queryParameter;
    }

    public void setQueryParameter(QueryParameter queryParameter) {
        this.queryParameter = queryParameter;
    }

    /*向前台传递数据*/
    private Map<String,Object> jsonData = new HashMap<>();

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }


    public String acquireUserListByName(){
        if (this.queryParameter != null) {
            int size = this.userService.getUserListSizeByUserName(this.queryParameter.getUserName());
            List<User> userList = this.userService.getUserListByUserName(this.queryParameter.getPage(),this.queryParameter.getRows(),this.queryParameter.getUserName());
            this.jsonData.put("userList",userList);
            this.jsonData.put("size",size);
            return SUCCESS;
        } else {
            return SUCCESS;
        }

    }
}

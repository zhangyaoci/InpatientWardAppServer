package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.User;
import service.UserService;

import java.util.HashMap;
import java.util.Map;


public class LoginAction extends ActionSupport{

    private User user;
    private UserService userService;

    private Map<String,Object> jsonData;



    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }
    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    //登录验证
    public String login(){

        if(this.user!=null) {
            this.user.getName();
        }

        jsonData = new HashMap<String,Object>();
        jsonData.put("one","one's apple");


        System.out.println("11111111");
        return SUCCESS;
    }

}

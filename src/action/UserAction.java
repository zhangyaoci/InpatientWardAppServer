package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.User;
import org.apache.struts2.json.annotations.JSON;
import service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserAction extends ActionSupport {

    private String phone;
    private User user;
    private UserService userService;


    //成功向前台传入的数据,springMVC 默认是单例模式
    private Map<String,Object> jsonDataOfSuccess = new HashMap<String,Object>();
    //失败向前台传入的数据
    private Map<String,Object> jsonDataOfError = new HashMap<String,Object>();

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public Map<String, Object> getJsonDataOfSuccess() {
        return jsonDataOfSuccess;
    }

    public void setJsonDataOfSuccess(Map<String, Object> jsonDataOfSuccess) {
        this.jsonDataOfSuccess = jsonDataOfSuccess;
    }

    public Map<String, Object> getJsonDataOfError() {
        return jsonDataOfError;
    }

    public void setJsonDataOfError(Map<String, Object> jsonDataOfError) {
        this.jsonDataOfError = jsonDataOfError;
    }

    /*根据电话号码获取用户基础信息*/
    public String getUserByPhone(){

        System.out.println("获取用户信息"+this.phone);
        if(this.phone!=null){
            List<User> users =  this.userService.findUserByPhone(this.phone);
            if(users.size()!=0){
                //消除空对象，json转换的错误
                users.get(0).setUserPatients(null);
                jsonDataOfSuccess.put("user", users.get(0));
                return SUCCESS;
            }
            else{
                jsonDataOfError.put("message","没有该电话号码");
                return ERROR;
            }
        }
        else {
            jsonDataOfError.put("message","获取用户基本信息失败");
            return ERROR;
        }
    }

    //修改用户信息
    public String editUser(){
        if(this.user!=null){
            try{
                this.userService.updateUser(this.user);
                jsonDataOfSuccess.put("message","用户修改信息成功");
                return  SUCCESS;
            }
            catch (Exception e){
                jsonDataOfError.put("message","用户修改信息失败");
                return ERROR;
            }

        }else {
            jsonDataOfError.put("message","用户修改信息失败");
            return ERROR;
        }
    }


}

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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }

    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }


    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    private Integer patientId;
    public Integer getPatientId() {
        return patientId;
    }
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }



    //成功向前台传入的数据,springMVC 默认是单例模式
    private Map<String,Object> jsonData = new HashMap<String,Object>();
    public Map<String, Object> getJsonData() {
        return jsonData;
    }
    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }



    /*根据电话号码获取用户基础信息*/
    public String getUserByPhone(){

        System.out.println("获取用户信息"+this.phone);
        if(this.phone!=null){
            List<User> users =  this.userService.findUserByPhone(this.phone);
            if(users.size()!=0){
                /*消除空对象，json转换的错误
                users.get(0).setUserPatients(null); json在转换时是调用user集合中的get方法，发现这里是一个代理属性，者赋值为null*/
                jsonData.put("user", users.get(0));
                return SUCCESS;
            }
            else{
                jsonData.put("message","没有该电话号码");
                return ERROR;
            }
        }
        else {
            jsonData.put("message","获取用户基本信息失败");
            return ERROR;
        }
    }

    //修改用户信息
    public String editUser(){
        if(this.user!=null){
            try{
                this.userService.updateUser(this.user);
                jsonData.put("message","用户修改信息成功");
                return  SUCCESS;
            }
            catch (Exception e){
                jsonData.put("message","用户修改信息失败");
                return ERROR;
            }

        }else {
            jsonData.put("message","用户修改信息失败");
            return ERROR;
        }
    }


    /*通过病人的Id来获取他的监护人信息*/
    public String getUserOfGuardianByPatientId(){
        if(patientId!=null){
           User userOfGuardian = this.userService.getUserOfGuardianByPatientId(patientId);
           jsonData.put("success",userOfGuardian);
           return SUCCESS;
        }
        return ERROR;
    }
}

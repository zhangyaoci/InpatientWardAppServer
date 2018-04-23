package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.User;
import service.UserService;
import util.MD5Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LoginAction extends ActionSupport{

    private User user;
    private UserService userService;


    //成功向前台传入的数据,springMVC 默认是单例模式
    private Map<String,Object> jsonDataOfSuccess = new HashMap<String,Object>();
    //失败向前台传入的数据
    private Map<String,Object> jsonDataOfError = new HashMap<String,Object>();


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

    //登录验证
    public String login(){
        if(this.user!=null) {
            List<User> users =this.userService.findUserByPhone(this.user.getPhone());
            if(users.size()!=0){
               User userForVerification= users.get(0);
               if(MD5Util.verify(user.getPassword(),userForVerification.getPassword())){
                   users.get(0).setUserPatients(null);
                   jsonDataOfSuccess.put("user",users.get(0));
                   return  SUCCESS;
               }else{
                   jsonDataOfError.put("message","密码错误");
                   return  ERROR;
               }
            }
            else {
                jsonDataOfError.put("message","该手机号码还没有注册");
                return ERROR;
            }
        }
        else {
            jsonDataOfError.put("message","登录失败");
            return ERROR;
        }
    }

    //用户信息注册
    public  String register(){
        if (this.user!=null){
            try {
                List<User> users= this.userService.findUserByPhone(this.user.getPhone());
                if(users.size()!=0){
                    jsonDataOfError.put("message","该电话已经被注册过");
                    return ERROR;
                }
                else{
                    this.user.setPassword(MD5Util.generate(this.user.getPassword()));
                    this.userService.saveUser(user);
                    jsonDataOfSuccess.put("message","注册成功");
                    return SUCCESS;
                }
            }
            catch (Exception e){
                jsonDataOfError.put("message","注册失败");
                return ERROR;
            }
        }else{
            jsonDataOfError.put("message","注册失败");
            return ERROR;
        }
    }

    //用户修改密码
    public String amendPassword(){
        if(this.user!=null){
            List<User> users = this.userService.findUserByPhone(user.getPhone());
            if(users.size()!=0){
                users.get(0).setPassword(MD5Util.generate(this.user.getPassword()));
                this.userService.updateUser(users.get(0));
                jsonDataOfSuccess.put("message","密码修改成功");
                return SUCCESS;
            }else{
                jsonDataOfError.put("message","不存在该用户手机号码");
                return ERROR;
            }
        }else {
            jsonDataOfError.put("message","密码修改失败");
            return ERROR;
        }
    }

}

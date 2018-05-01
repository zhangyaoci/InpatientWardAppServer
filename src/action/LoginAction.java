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


    //成功向前台传入的数据,springMVC 默认是单例模式
    private Map<String,Object> jsonData = new HashMap<String,Object>();
    public Map<String, Object> getJsonData() {
        return jsonData;
    }
    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    /*用户用电话号码和密码登录*/
    public String login(){
        if(this.user!=null) {
            List<User> users =this.userService.findUserByPhone(this.user.getPhone());
            if(users.size()!=0){
               User userForVerification= users.get(0);
               if(MD5Util.verify(user.getPassword(),userForVerification.getPassword())){
                   jsonData.put("success",users.get(0));
                   return  SUCCESS;
               }else{
                   jsonData.put("error","密码错误");
                   return  SUCCESS;
               }
            }
            else {
                jsonData.put("error","该手机号码还没有注册");
                return SUCCESS;
            }
        }else{
            jsonData.put("error","用户登录失败");
            return SUCCESS;
        }
    }




    /*用户信息注册*/
    public  String register(){
        if (this.user!=null){
            List<User> users= this.userService.findUserByPhone(this.user.getPhone());
            if(users.size()!=0){
                jsonData.put("error","该电话已经被注册过");
                return SUCCESS;
            }
            else{
                this.user.setPassword(MD5Util.generate(this.user.getPassword()));
                this.userService.saveUser(user);
                List<User> userList =  this.userService.findUserByPhone(this.user.getPhone());
                int userId = userList.get(0).getUserId();
                jsonData.put("success","注册成功");
                jsonData.put("userId",userId);
                return SUCCESS;
            }
        }else{
            jsonData.put("error","注册失败");
            return SUCCESS;
        }
    }

    //用户修改密码
    public String amendPassword(){
        if(this.user!=null){
            List<User> users = this.userService.findUserByPhone(user.getPhone());
            if(users.size()!=0){
                users.get(0).setPassword(MD5Util.generate(this.user.getPassword()));
                this.userService.updateUser(users.get(0));
                jsonData.put("success","密码修改成功");
                return SUCCESS;
            }else{
                jsonData.put("error","不存在该用户手机号码");
                return SUCCESS;
            }
        }else{
            jsonData.put("error","密码修改失败");
            return SUCCESS;
        }

    }

}

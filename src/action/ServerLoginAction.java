package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import domain.AdminUser;
import service.ServerAdminUserService;
import util.MD5Util;

import java.util.Map;

public class ServerLoginAction extends ActionSupport {

    private AdminUser adminUser;
    public AdminUser getAdminUser() {
        return adminUser;
    }
    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    /*错误信息处理*/
    private String errorMessage;
    public String getErrorMessage() {
        return errorMessage;
    }
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private ServerAdminUserService serverAdminUserService;
    public void setServerAdminUserService(ServerAdminUserService serverAdminUserService) {
        this.serverAdminUserService = serverAdminUserService;
    }

    public String adminLogin(){
        if(adminUser!=null){
            AdminUser adminUserTemp = this.serverAdminUserService.getAdminUserByName(adminUser.getName());
            if(adminUserTemp!=null){
                String passwordTemp = MD5Util.md5Hex(adminUserTemp.getPassword());
                if(adminUser.getPassword().equals(passwordTemp)){
                    /*在session中保持用户信息*/
                    ActionContext actionContext = ActionContext.getContext();
                    Map session = actionContext.getSession();
                    session.put("adminUser", adminUserTemp);
                    return SUCCESS;
                }
                else {
                    this.errorMessage="密码错误";
                    return ERROR;
                }
            }
            else{
                this.errorMessage="没有该账户";
                return ERROR;
            }

        }
        else{
            return  ERROR;
        }

    }
}

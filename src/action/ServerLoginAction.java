package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.AdminUser;

public class ServerLoginAction extends ActionSupport {

    private AdminUser adminUser;
    public AdminUser getAdminUser() {
        return adminUser;
    }
    public void setAdminUser(AdminUser adminUser) {
        this.adminUser = adminUser;
    }

    public String adminLogin(){
        System.out.println("登录");
        return null;
    }
}

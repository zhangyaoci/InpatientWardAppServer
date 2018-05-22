package service;

import domain.AdminUser;

public interface ServerAdminUserService {
    /*通过名字查询后台用户*/
    public AdminUser getAdminUserByName(String name);
}

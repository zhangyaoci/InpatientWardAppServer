package dao;

import domain.AdminUser;

public interface ServerAdminUserDao {
    /*通过名字查询后台*/
    public AdminUser findAdminUserByName(String name);
}

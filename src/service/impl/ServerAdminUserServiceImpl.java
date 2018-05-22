package service.impl;

import dao.ServerAdminUserDao;
import domain.AdminUser;
import service.ServerAdminUserService;

public class ServerAdminUserServiceImpl implements ServerAdminUserService {

    private ServerAdminUserDao serverAdminUserDao;
    public void setServerAdminUserDao(ServerAdminUserDao serverAdminUserDao) {
        this.serverAdminUserDao = serverAdminUserDao;
    }

    @Override
    public AdminUser getAdminUserByName(String name) {
        return  this.serverAdminUserDao.findAdminUserByName(name);
    }
}

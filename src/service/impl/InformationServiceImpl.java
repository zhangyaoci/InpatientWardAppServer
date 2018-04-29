package service.impl;

import dao.InformationDao;
import dao.InformationUserDao;
import domain.Information;
import domain.InformationUser;
import service.InformationService;

import java.util.ArrayList;
import java.util.List;

public class InformationServiceImpl implements InformationService {

    private InformationUserDao informationUserDao;

    public void setInformationUserDao(InformationUserDao informationUserDao) {
        this.informationUserDao = informationUserDao;
    }

    private InformationDao informationDao;

    public void setInformationDao(InformationDao informationDao) {
        this.informationDao = informationDao;
    }

    /*找出用户还未读的消息*/
    @Override
    public List<Information> getInformationByUserId(int userId) {
        List<Information> informationList = new ArrayList<>();
        List<InformationUser> informationUserList =this.informationUserDao.findInformationUserByUserId(userId);
        for (InformationUser informationUser:informationUserList){
           Information information= this.informationDao.findInformationByInformationId(informationUser.getInformation().getInformationId());
           informationList.add(information);
        }
        return  informationList;
    }
}

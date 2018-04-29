package service;

import domain.Information;

import java.util.List;

public interface InformationService {
    /*根据用户Id 来找到改用户还没有读到的消息*/
    public List<Information> getInformationByUserId(int userId);
}


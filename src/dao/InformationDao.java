package dao;

import domain.Information;

import java.util.Set;

public interface InformationDao {
    /*通过消息id，获取需要通知给用户的消息；该消息对于用户是未读*/
    public Information findInformationByInformationId(int informationId);
}

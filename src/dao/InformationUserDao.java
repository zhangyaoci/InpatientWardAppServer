package dao;

import domain.InformationUser;
import java.util.List;

public interface InformationUserDao {
    /*通过用户Id号，获得发给该用户还没有阅读的消息的ID号*/
    public List<InformationUser> findInformationUserByUserId(int useId);

}

package dao;

import domain.InformationUser;
import java.util.List;

public interface InformationUserDao {
    /*通过用户Id号，获得发给该用户还没有阅读的消息的ID号*/
    public List<InformationUser> findInformationUserByUserId(int useId);

    /*修改消息的状态为已读*/
    public String updateReadStateByInformationIdAndUserId(Integer userId, Integer informationId);

    /*修改消息是否被拉取，设置为拉取;通过informationUserId*/
    public String updateisPopforInformationUser(Integer informationUserId);

    /*根据用UseID号，把拉取状态全部设置为0*/
    public String updateisPopToZero(Integer userId);
}

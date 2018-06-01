package dao;

import domain.InformationUser;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface InformationUserDao {
    /*通过用户Id号，获得发给该用户还没有阅读的消息的ID号*/
    public List<InformationUser> findInformationUserByUserId(int useId);

    /*修改消息的状态为已读*/
    public String updateReadStateByInformationIdAndUserId(Integer userId, Integer informationId);


    /*修改消息为已删除*/
    public String deleteInformationByUserIdAndInformationId(Integer userId, Integer informationId);

}

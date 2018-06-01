package service;

import domain.Information;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

public interface InformationService {
    /*根据用户Id 来找到改用户还没有读到的消息*/
    public List<Information> getInformationByUserId(int userId);

    /*修改消息状态为已读*/
    public String alterReadingStateByUserIdAndInformationId(Integer userId,Integer informationId);

    /*修改消息状态为删除*/
    public String deleteInformationByUserIdAndInformationId(Integer userId, Integer informationId);
}


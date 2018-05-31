package dao.impl;

import dao.InformationDao;
import dao.InformationUserDao;
import domain.Information;
import domain.InformationUser;
import org.springframework.orm.hibernate5.HibernateTemplate;

import java.util.List;
import java.util.Set;

public class InformationUserDaoImpl implements InformationUserDao{

    private HibernateTemplate hibernateTemplate;

    public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    /*获取用户未读的消息*/
    @Override
    public List<InformationUser> findInformationUserByUserId(int useId) {

        String[] paramName= new String[]{"userId"};

        Integer[] values = new Integer[]{useId};

        List<InformationUser> informationUserList =(List<InformationUser>) this.hibernateTemplate.findByNamedParam("from InformationUser where  user.userId=:userId"
                ,paramName,values);
        return informationUserList;
    }

    /*修改消息的状态为已读*/
    @Override
    public String updateReadStateByInformationIdAndUserId(Integer userId, Integer informationId) {
        String[] paramName= new String[]{"userId","informationId"};
        Integer[] values = new Integer[]{userId,informationId};

        List<InformationUser> informationUserList = (List<InformationUser>) this.hibernateTemplate.findByNamedParam(
                "from InformationUser where user.userId =:userId and information.informationId=:informationId order by information.time ",
                paramName,values);

        if(informationUserList.size()!=0){
            informationUserList.get(0).setIsRead(1);
            this.hibernateTemplate.save(informationUserList.get(0));
            return "succesOfUpdatingState";
        }
        return  "errorOfUpdatingState";
    }

}

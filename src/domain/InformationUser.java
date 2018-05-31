package domain;

import java.util.Objects;

public class InformationUser {
    private int informationUserId;
    private int isRead;//判定该消息是否被用户已读



    public int getInformationUserId() {
        return informationUserId;
    }

    public void setInformationUserId(int informationUserId) {
        this.informationUserId = informationUserId;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }


    /*对应的消息信息*/
    private Information information;

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }

    /*消息提醒所对应的用户*/
    private User user;
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InformationUser that = (InformationUser) o;
        return informationUserId == that.informationUserId &&
                isRead == that.isRead &&
                Objects.equals(information, that.information) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(informationUserId, isRead, information, user);
    }
}

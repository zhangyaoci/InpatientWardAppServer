package domain;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
    private int userId;
    private String name;
    private String password;
    private byte sex;
    private String picturePath;
    private String phone;
    private String address;
    private String remarks;

    private int isDelete;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    //用户对应病人的关系表，采用两个一对多实现（多个病人、多个用户对应一个用户病人关系表）
    private Set<UserPatient> userPatients = new HashSet<UserPatient>();
    public Set<UserPatient> getUserPatients() {
        return userPatients;
    }
    public void setUserPatients(Set<UserPatient> userPatients) {
        this.userPatients = userPatients;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return userId == user.userId &&
                sex == user.sex &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(name, user.name) &&
                Objects.equals(password, user.password) &&
                Objects.equals(remarks, user.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, name, password, sex, phone, remarks);
    }
}

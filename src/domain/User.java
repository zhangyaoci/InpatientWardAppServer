package domain;


import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User {
    private int userId;
    private String name;
    private String password;
    private byte sex;
    private String phone;
    private String address;
    private String remarks;


    //采用两个一对多实现多对多
    private Set<UserPatient> userPatients = new HashSet<UserPatient>();
    public Set<UserPatient> getUserPatients() {
        return userPatients;
    }
    public void setUserPatients(Set<UserPatient> userPatients) {
        this.userPatients = userPatients;
    }

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

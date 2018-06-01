package domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Nurse {
    private int nurseId;
    private String name;
    private Byte sex;
    private String picturePath;
    private String phone;
    private String introduction;
    private String remarks;

    private int isDelete;

    public int getNurseId() {
        return nurseId;
    }

    public void setNurseId(int nurseId) {
        this.nurseId = nurseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
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

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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

    //病人的住院对应的护士，一个护士可以对多个住院记录
    private Set<Hospitalization> hospitalizations = new HashSet<Hospitalization>();
    public Set<Hospitalization> getHospitalizations() {
        return hospitalizations;
    }
    public void setHospitalizations(Set<Hospitalization> hospitalizations) {
        this.hospitalizations = hospitalizations;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Nurse nurse = (Nurse) o;
        return nurseId == nurse.nurseId &&
                Objects.equals(name, nurse.name) &&
                Objects.equals(sex, nurse.sex) &&
                Objects.equals(phone, nurse.phone) &&
                Objects.equals(introduction, nurse.introduction) &&
                Objects.equals(remarks, nurse.remarks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(nurseId, name, sex, phone, introduction, remarks);
    }
}

package domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Nurse {
    private int nurseId;
    private String name;
    private Byte sex;
    private String phone;
    private String introduction;
    private String remarks;


    //住院对应的护士，一对多
    private Set<Hospitalization> hospitalizations = new HashSet<Hospitalization>();

    public Set<Hospitalization> getHospitalizations() {
        return hospitalizations;
    }

    public void setHospitalizations(Set<Hospitalization> hospitalizations) {
        this.hospitalizations = hospitalizations;
    }

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

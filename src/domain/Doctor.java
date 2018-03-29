package domain;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Doctor {
    private int doctorId;
    private String name;
    private byte sex;
    private String phone;
    private String department;
    private String introduction;
    private String remarks;



    //医生可以提出多个医嘱
    private Set<Advice> advices = new HashSet<Advice>();

    public Set<Advice> getAdvices() {
        return advices;
    }
    public void setAdvices(Set<Advice> advices) {
        this.advices = advices;
    }

    //医生对应的住院记录，一对多
    private Set<Hospitalization> hospitalizations = new HashSet<Hospitalization>();

    public Set<Hospitalization> getHospitalizations() {
        return hospitalizations;
    }

    public void setHospitalizations(Set<Hospitalization> hospitalizations) {
        this.hospitalizations = hospitalizations;
    }


    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
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
        Doctor doctor = (Doctor) o;
        return doctorId == doctor.doctorId &&
                sex == doctor.sex &&
                Objects.equals(phone, doctor.phone) &&
                Objects.equals(name, doctor.name) &&
                Objects.equals(department, doctor.department) &&
                Objects.equals(introduction, doctor.introduction) &&
                Objects.equals(remarks, doctor.remarks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(doctorId, name, sex, phone, department, introduction, remarks);
    }
}

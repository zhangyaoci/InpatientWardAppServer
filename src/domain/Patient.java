package domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Patient {
    private int patientId;
    private String name;
    private int sex;
    private String picturePath;
    private String phone;
    private Date dateOfBirth;
    private String address;
    private String introduction;
    private String remarks;
    private int hospitalState;//数据库中 1代表正在住院 0代表已经出院
    private String roomNumber;
    private int isDelete;

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getHospitalState() {
        return hospitalState;
    }

    public void setHospitalState(int hospitalState) {
        this.hospitalState = hospitalState;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }

    //描述当前病人与用户之间的关系，不在数据库存储（为方便操作临时添加的字段）
    private String relationShip;
    public String getRelationShip() {
        return relationShip;
    }
    public void setRelationShip(String relationShip) {
        this.relationShip = relationShip;
    }





    //采用两个一对多实现多对多(病人和用户家属之间的关系)
    private Set<UserPatient> userPatients = new HashSet<UserPatient>();
    public Set<UserPatient> getUserPatients() {
        return userPatients;
    }
    public void setUserPatients(Set<UserPatient> userPatients) {
        this.userPatients = userPatients;
    }



    //病人的消息（生理数据超标和医嘱）提醒，一对多
    private  Set<Information>  informations = new HashSet<Information>();
    public Set<Information> getInformations() {
        return informations;
    }
    public void setInformations(Set<Information> informations) {
        this.informations = informations;
    }

    //病人对应的住院记录
    private Set<Hospitalization> hospitalizations = new HashSet<Hospitalization>();
    public Set<Hospitalization> getHospitalizations() {
        return hospitalizations;
    }
    public void setHospitalizations(Set<Hospitalization> hospitalizations) {
        this.hospitalizations = hospitalizations;
    }


    //病人的血糖，一对多
    private  Set<Bloodglucose> bloodglucoses = new HashSet<Bloodglucose>();
    public Set<Bloodglucose> getBloodglucoses() {
        return bloodglucoses;
    }
    public void setBloodglucoses(Set<Bloodglucose> bloodglucoses) {
        this.bloodglucoses = bloodglucoses;
    }

    //病人的血压饱和度，一对多
    private Set<Bloodoxygensaturation> bloodoxygensaturations = new HashSet<Bloodoxygensaturation>();
    public Set<Bloodoxygensaturation> getBloodoxygensaturations() {
        return bloodoxygensaturations;
    }
    public void setBloodoxygensaturations(Set<Bloodoxygensaturation> bloodoxygensaturations) {
        this.bloodoxygensaturations = bloodoxygensaturations;
    }

    //病人的血压， 一对多
    private  Set<Bloodpressure> bloodpressures = new HashSet<Bloodpressure>();
    public Set<Bloodpressure> getBloodpressures() {
        return bloodpressures;
    }
    public void setBloodpressures(Set<Bloodpressure> bloodpressures) {
        this.bloodpressures = bloodpressures;
    }

    //病人的心率，一对多
    private Set<Heartrate> heartrates = new HashSet<Heartrate>();
    public Set<Heartrate> getHeartrates() {
        return heartrates;
    }
    public void setHeartrates(Set<Heartrate> heartrates) {
        this.heartrates = heartrates;
    }

    //病人的体温，一对多
    private Set<Temperature> temperatures = new HashSet<Temperature>();
    public Set<Temperature> getTemperatures() {
        return temperatures;
    }
    public void setTemperatures(Set<Temperature> temperatures) {
        this.temperatures = temperatures;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return patientId == patient.patientId &&
                sex == patient.sex &&
                Objects.equals(phone, patient.phone ) &&
                Objects.equals(name, patient.name) &&
                Objects.equals(dateOfBirth, patient.dateOfBirth) &&
                Objects.equals(address, patient.address) &&
                Objects.equals(introduction, patient.introduction) &&
                Objects.equals(remarks, patient.remarks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(patientId, name, sex, phone, dateOfBirth, address, introduction, remarks);
    }




    /*监护人名字*/
    private  String guardianName;

    public String getGuardianName() {
        return guardianName;
    }

    public void setGuardianName(String guardianName) {
        this.guardianName = guardianName;
    }


    /*监护人号码*/
    private String guardianPhone;

    public String getGuardianPhone() {
        return guardianPhone;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    /*临时时间变量*/
    private java.util.Date tempDate;

    public java.util.Date getTempDate() {
        return tempDate;
    }

    public void setTempDate(java.util.Date tempDate) {
        this.tempDate = tempDate;
    }
}

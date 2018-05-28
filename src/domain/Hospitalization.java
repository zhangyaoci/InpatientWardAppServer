package domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Hospitalization {
    private int hospitalId;
    private Timestamp startTime;
    private Timestamp endTime;
    private String room;
    private String remarks;

    private int isDelete; /*确认该记录是否删除*/

    public int getHospitalId() {
        return hospitalId;
    }

    public void setHospitalId(int hospitalId) {
        this.hospitalId = hospitalId;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
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

    //某个病人住院记录对应护士
    private Nurse nurse;
    public Nurse getNurse() {
        return nurse;
    }
    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    //某个病人住院记录对应医生
    private  Doctor doctor;
    public   Doctor getDoctor() {
        return doctor;
    }
    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    //住院记录所对应病人
    private Patient patient;
    public Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hospitalization that = (Hospitalization) o;
        return hospitalId == that.hospitalId &&
                Objects.equals(startTime, that.startTime) &&
                Objects.equals(endTime, that.endTime) &&
                Objects.equals(room, that.room) &&
                Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hospitalId, startTime, endTime, room, remarks);
    }


    /*临时记录变量*/
    private String doctorName;
    private String doctorPhone;

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDoctorPhone() {
        return doctorPhone;
    }

    public void setDoctorPhone(String doctorPhone) {
        this.doctorPhone = doctorPhone;
    }

    private String nurseName;
    private String nursePhone;

    public String getNurseName() {
        return nurseName;
    }

    public void setNurseName(String nurseName) {
        this.nurseName = nurseName;
    }

    public String getNursePhone() {
        return nursePhone;
    }

    public void setNursePhone(String nursePhone) {
        this.nursePhone = nursePhone;
    }

    private String patientName;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    private String patientPhone;

    public String getPatientPhone() {
        return patientPhone;
    }

    public void setPatientPhone(String patientPhone) {
        this.patientPhone = patientPhone;
    }
}

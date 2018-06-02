package domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Bloodpressure {

    private int bpId;
    private Double value;
    private Timestamp time;
    private int type;/*1 代表收缩压  0 代表舒张压*/
    private String remarks;

    private int isDelete;

    public int getBpId() {
        return bpId;
    }

    public void setBpId(int bpId) {
        this.bpId = bpId;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    //血压值所对应的病人
    private  Patient patient;
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
        Bloodpressure that = (Bloodpressure) o;
        return bpId == that.bpId &&

                Objects.equals(value, that.value) &&
                Objects.equals(time, that.time) &&
                Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bpId,value, time, remarks);
    }


    /*临时存储变量*/
    private String patientName;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }
}

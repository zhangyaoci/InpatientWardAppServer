package domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Bloodpressure {
    private int bpId;
    private Double value;
    private Timestamp time;
    private String remarks;


    //病人对应的血压
    private  Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

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
}

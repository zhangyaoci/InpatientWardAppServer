package domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Bloodoxygensaturation {

    private int bosId;
    private Double value;
    private Timestamp time;
    private String remarks;

    public int getBosId() {
        return bosId;
    }

    public void setBosId(int bosId) {
        this.bosId = bosId;
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


    //血氧饱和度的值对应的病人
    private  Patient patient;
    public   Patient getPatient() {
        return patient;
    }
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bloodoxygensaturation that = (Bloodoxygensaturation) o;
        return bosId == that.bosId &&
                Objects.equals(value, that.value) &&
                Objects.equals(time, that.time) &&
                Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(bosId, value, time, remarks);
    }
}

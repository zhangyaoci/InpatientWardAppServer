package domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Heartrate {
    private int hrId;
    private Double value;
    private Timestamp time;
    private String remarks;


    //病人的心率
    private Patient patient;
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getHrId() {
        return hrId;
    }

    public void setHrId(int hrId) {
        this.hrId = hrId;
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
        Heartrate heartrate = (Heartrate) o;
        return hrId == heartrate.hrId &&
                Objects.equals(value, heartrate.value) &&
                Objects.equals(time, heartrate.time) &&
                Objects.equals(remarks, heartrate.remarks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(hrId, value, time, remarks);
    }
}

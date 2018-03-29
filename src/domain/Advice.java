package domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Advice {
    private int adviceId;
    private String content;
    private Timestamp time;
    private String remarks;


    //医嘱对应的病人
    private Patient patient;

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    //医嘱对应的医生
    private Doctor doctor;

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public int getAdviceId() {
        return adviceId;
    }

    public void setAdviceId(int adviceId) {
        this.adviceId = adviceId;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
        Advice advice = (Advice) o;
        return adviceId == advice.adviceId &&
                Objects.equals(content, advice.content) &&
                Objects.equals(time, advice.time) &&
                Objects.equals(remarks, advice.remarks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(adviceId, content, time, remarks);
    }
}

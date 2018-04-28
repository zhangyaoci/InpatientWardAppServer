package domain;

import java.util.Objects;

public class UserPatient {
    private int userPatientId;
    private String relation;
    private String guardian;
    private String remarks;

    public int getUserPatientId() {
        return userPatientId;
    }

    public void setUserPatientId(int userPatientId) {
        this.userPatientId = userPatientId;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }



    //多对多关系的实现，用户
    private User user;
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }

    //多对多关系实现，病人
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
        UserPatient that = (UserPatient) o;
        return userPatientId == that.userPatientId &&
                Objects.equals(relation, that.relation) &&
                Objects.equals(remarks, that.remarks);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userPatientId, relation, remarks);
    }
}

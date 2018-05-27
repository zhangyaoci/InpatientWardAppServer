package entity;

import java.util.Objects;

public class QueryParameter {
    /*分页搜索第几页*/
    private Integer page;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    /*页码的大小*/
    private Integer rows;

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    private String patientName;

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QueryParameter that = (QueryParameter) o;
        return Objects.equals(page, that.page) &&
                Objects.equals(rows, that.rows);
    }

    @Override
    public int hashCode() {

        return Objects.hash(page, rows);
    }
}

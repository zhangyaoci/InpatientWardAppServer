package dao;

import domain.Doctor;

import java.util.List;

public interface DoctorDao {
    /*根据医生的名字进行搜索，分页处理*/
    public List<Doctor> findDoctorListByDoctorName(int page, int row, String doctorName);

    /*根据医生的名字获取，该医生列表的总数*/
    public int findDoctorListSizeByDoctorName(String doctorName);
}

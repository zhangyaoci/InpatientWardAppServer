package service;

import domain.Doctor;

import java.util.List;

public interface DoctorService {

    /*根据医生姓名获取医生列表，分页处理*/
    public List<Doctor> getDoctorListByDoctorName(int page,int rows,String doctorName);

    /*根据医生名字，获取医生列表的大小*/
    public int getDoctorListSizeByDoctorName(String doctorName);
}

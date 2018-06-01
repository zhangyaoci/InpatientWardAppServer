package service.impl;

import dao.DoctorDao;
import domain.Doctor;
import service.DoctorService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService {

    private DoctorDao doctorDao;
    public void setDoctorDao(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    /*获取医生列表，分页处理*/
    @Override
    public List<Doctor> getDoctorListByDoctorName(int page, int rows, String doctorName) {
        return doctorDao.findDoctorListByDoctorName(page,rows,doctorName);
    }

    @Override
    public int getDoctorListSizeByDoctorName(String doctorName) {
        return this.doctorDao.findDoctorListSizeByDoctorName(doctorName);
    }
}

package service;

import domain.Nurse;

import java.util.List;

public interface NurseService {
    /*分页处理*/
    public List<Nurse> getNurseListByNurseName(int page, int rows, String nurseName);

    /*获取列表总页数*/
    public int getNurseListSizeByNurseName(String nurseName);

    /*获取护士所有列表*/
    public List<Nurse> getAllNurseList();
}

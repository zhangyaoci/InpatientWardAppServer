package service.impl;

import dao.NurseDao;
import domain.Nurse;
import service.NurseService;

import java.util.List;

public class NurseServiceImpl implements NurseService {


    private NurseDao nurseDao;

    public void setNurseDao(NurseDao nurseDao) {
        this.nurseDao = nurseDao;
    }

    /*获取列表数据*/
    @Override
    public List<Nurse> getNurseListByNurseName(int page, int rows, String nurseName) {
        return this.nurseDao.findNurseListByNurseName(page,rows,nurseName);
    }

    /*获取列表大小*/
    @Override
    public int getNurseListSizeByNurseName(String nurseName) {
        return this.nurseDao.findNurseListSizeByNurseName(nurseName);
    }
}

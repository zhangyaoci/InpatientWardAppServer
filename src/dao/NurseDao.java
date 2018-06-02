package dao;

import domain.Nurse;

import java.util.List;

public interface NurseDao {
    /*根据护士名字进行分页列表*/
    public List<Nurse> findNurseListByNurseName(int page, int rows, String nurseName);

    public int  findNurseListSizeByNurseName(String nurseName);

}

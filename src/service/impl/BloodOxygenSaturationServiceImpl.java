package service.impl;

import dao.BloodOxygenSaturationDao;
import domain.Bloodoxygensaturation;
import service.BloodOxygenSaturationService;

import java.util.Date;
import java.util.List;

public class BloodOxygenSaturationServiceImpl implements BloodOxygenSaturationService {
    private BloodOxygenSaturationDao bloodOxygenSaturationDao;

    public void setBloodOxygenSaturationDao(BloodOxygenSaturationDao bloodOxygenSaturationDao) {
        this.bloodOxygenSaturationDao = bloodOxygenSaturationDao;
    }

    /*获取血氧饱和度值*/
    @Override
    public List<Bloodoxygensaturation> getBloodOxygenSaturationDataByPatientIdAndTime(Integer patientId, Date startTime, Date endTime) {
        return this.bloodOxygenSaturationDao.findBloodOxygenSaturationDataByPatientIdAndTime(patientId,startTime,endTime);
    }
}

package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Patient;
import domain.User;
import org.apache.poi.hssf.usermodel.*;
import service.PatientService;
import service.UserService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class FileDownloadAction extends ActionSupport {

    /*对应的文件名字*/
    private String fileName;
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /*对应的输出流*/
    private InputStream excelStream;
    public InputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(InputStream excelStream) {
        this.excelStream = excelStream;
    }


    /*搜索条件*/
    private String patientName;
    public String getPatientName() {
        return patientName;
    }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    /*病人服务*/
    private PatientService patientService;
    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }
    /*用户服务*/
    private UserService userService;
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public String exportPatient(){

        /*生产文件名字*/
        Date currentTime = new Date();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        String name = simpleDateFormat.format(currentTime);
        this.fileName = name+"patientInfo.xls";


        try {
            //第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = new HSSFWorkbook();

            //第二步，在webbook中添加一个sheet，对应Excel文件中的 sheet
            HSSFSheet sheet = wb.createSheet("病人信息数据");

            //第三步，在sheet中添加表头第0行，注意老版本poi对Excel的行数列数有限制
            HSSFRow row = sheet.createRow(0);

            //第四步，创建表头单元格
            HSSFCell cell;
            cell = row.createCell(0);
            cell.setCellValue("姓名");
            cell = row.createCell(1);
            cell.setCellValue("性别");
            cell = row.createCell(2);
            cell.setCellValue("电话号码");
            cell = row.createCell(3);
            cell.setCellValue("出生日期");
            cell = row.createCell(4);
            cell.setCellValue("现住地址");
            cell = row.createCell(5);
            cell.setCellValue("病例简介");
            cell = row.createCell(6);
            cell.setCellValue("住院状态");
            cell= row.createCell(7);
            cell.setCellValue("病房号");
            cell=row.createCell(8);
            cell.setCellValue("监护人姓名");
            cell =row.createCell(9);
            cell.setCellValue("监护人电话号码");

            List<Patient> patientList = this.patientService.getPatientByPatientName(this.patientName);
            for(Patient patient:patientList){
                User user = this.userService.getUserOfGuardianByPatientId(patient.getPatientId());
                if(user!=null){
                    System.out.println("");
                    patient.setGuardianName(user.getName());
                    patient.setGuardianPhone(user.getPhone());
                }
                else {
                    patient.setGuardianName("无监护人");
                    patient.setGuardianPhone("");
                }
            }


            //第五步，文件写入
            for(int i=1;i<=patientList.size();i++){
                row =sheet.createRow(i);
                row.createCell(0).setCellValue(patientList.get(i-1).getName());
                row.createCell(1).setCellValue(patientList.get(i-1).getSex()==1?"男":"女");
                row.createCell(2).setCellValue(patientList.get(i-1).getPhone());
                SimpleDateFormat simpleDateFormat_temp = new SimpleDateFormat("yyyy-MM-dd");
                String patientDate = simpleDateFormat_temp.format(patientList.get(i-1).getDateOfBirth().getTime());
                row.createCell(3).setCellValue(patientDate);
                row.createCell(4).setCellValue(patientList.get(i-1).getAddress());
                row.createCell(5).setCellValue(patientList.get(i-1).getIntroduction());
                row.createCell(6).setCellValue(patientList.get(i-1).getHospitalState()==1?"正在住院":"已经离院");
                row.createCell(7).setCellValue(patientList.get(i-1).getRoomNumber());
                row.createCell(8).setCellValue(patientList.get(i-1).getGuardianName());
                row.createCell(9).setCellValue(patientList.get(i-1).getGuardianPhone());

            }



            //第六步，将文件存到流中
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            wb.write(byteArrayOutputStream);
            byte[] fileContent = byteArrayOutputStream.toByteArray();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileContent);

            excelStream = byteArrayInputStream;             //文件流

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return "success";
    }
}

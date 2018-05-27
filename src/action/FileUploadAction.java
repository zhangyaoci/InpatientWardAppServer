package action;

import com.opensymphony.xwork2.ActionSupport;
import domain.Patient;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.PatientService;

import javax.swing.plaf.synth.SynthTextAreaUI;

public class FileUploadAction extends ActionSupport {


    private PatientService patientService;

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    /*前台传入数据*/
    private File file;
    public File getFile() {
        return file;
    }
    public void setFile(File file) {
        this.file = file;
    }

    private String fileFileName;  //文件名

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    private String fileContentType; //文件类型

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }


    /*后台传前台数据*/
    private Map<String,Object> jsonData = new HashMap<String,Object>();

    public Map<String, Object> getJsonData() {
        return jsonData;
    }

    public void setJsonData(Map<String, Object> jsonData) {
        this.jsonData = jsonData;
    }

    /*遍历存储数据*/
    private List<Map<String,Object>> list = new ArrayList<>();


    /*病人数据上传*/
    public String patientUpload(){

        /*文件先保存到服务器上，然后进行数据的导入*/
      if(this.file!=null){
          /*保存文件*/
         String message_tep1 = this.fileSave();

         String message_tep2="tep2_success";

         if(message_tep1.equals("file_success")){
             for(int i=0;i<list.size();i++){
                 try{
                     Patient patient = new Patient();
                     patient.setName(list.get(i).get("姓名").toString());
                     patient.setSex(list.get(i).get("性别").toString().equals("男")?1:0);
                     patient.setPhone(list.get(i).get("电话号码").toString());
                     java.sql.Date dateTemp = new java.sql.Date(Long.parseLong(list.get(i).get("出身日期").toString()));
                     patient.setDateOfBirth(dateTemp);
                     patient.setAddress(list.get(i).get("现住地址").toString());
                     patient.setIntroduction(list.get(i).get("病例简介").toString());
                     patientService.save(patient);
                 }catch (Exception e){
                      message_tep2="tep2_error";
                      e.printStackTrace();
                      break;
                 }

             }

             if(message_tep2.equals("tep2_success")){
                 this.jsonData.put("result","success");
                 this.jsonData.put("message","成功导入"+list.size()+"数据");
                 return SUCCESS;
             }
             else {
                 this.jsonData.put("result","error");
                 return SUCCESS;
             }

         }
         else {
             this.jsonData.put("result","error");
             return SUCCESS;
         }


      }else{
          this.jsonData.put("result","error");
          return SUCCESS;
      }

    }



    private String fileSave(){

        String message="file_success";

        /*建立一个文件夹*/
        String tempDir = "/upload/Excel";
        /*获取全路径*/
        String tempDirRealPath = ServletActionContext.getServletContext().getRealPath(tempDir);
        Date currrentTime = new Date();
        String prefix = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss").format(currrentTime);
        File target = new File(tempDirRealPath,prefix+"_"+this.fileFileName);
        if(target.exists()){
            target.delete();
        }
        try{
            FileUtils.copyFile(this.file,target);
        }catch (Exception e){
            message="file_error";
            e.printStackTrace();

        }

        /*将文件导入到数据库中*/
        try {
            FileInputStream fileInputStream = new FileInputStream(target);
            HSSFWorkbook xssfWorkbook = new HSSFWorkbook(fileInputStream);

            /*获取第一页*/
            Sheet sheet = xssfWorkbook.getSheetAt(0);
            /*获取有多少行数据*/
            int rowsNumber = sheet.getPhysicalNumberOfRows();

            /*获取头部数据*/
            Row rowHead = sheet.getRow(0);
            /*获取一行有多少列*/
            int columnNumber= rowHead.getPhysicalNumberOfCells();

            /*保存头部信息*/
            String[] stringsHead = new String[columnNumber];
            for(int i=0;i<columnNumber;i++){
                Object cellHeadData =  getCellFormatValue(rowHead.getCell(i));
                stringsHead[i]=cellHeadData.toString();
            }



            /*从第二行读取数据*/
            for(int i =1;i<rowsNumber;i++){
                Map<String,Object > map = new HashMap<>();
                Row row = sheet.getRow(i);
                if(row!=null){
                    for(int j =0;j<columnNumber;j++){
                        Object cellData = getCellFormatValue(row.getCell(j));
                        map.put(stringsHead[j],cellData);
                    }
                    list.add(map);
                }
            }

        }catch (Exception e){
            message="file_error";
            e.printStackTrace();
        }


        return  message;
    }

    /*没有值，返回空*/
    private Object getCellFormatValue(Cell cell){
        /*定义值*/
        Object value=null;

        if(cell!=null){
            switch (cell.getCellTypeEnum()) {
                case STRING:
                    value = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if ("m/d/yy".equals(cell.getCellStyle().getDataFormatString())){
                        /*传递毫秒数*/
                        value =  cell.getDateCellValue().getTime();
                    }else {
                        value = cell.getNumericCellValue();
                    }

                    break;
                default:
                    value ="";break;
            }
        }
        else {
            value="";
        }
        return value;

    }

}


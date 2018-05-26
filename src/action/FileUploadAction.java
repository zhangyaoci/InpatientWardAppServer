package action;

import com.opensymphony.xwork2.ActionSupport;

import java.io.File;

public class FileUploadAction extends ActionSupport {

    private File file;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String uploadAction(){
        System.out.println("成功");
        return  SUCCESS;
    }
}


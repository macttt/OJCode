package Utils;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;

/**
 * 从excel文件操作工具
 * */
public class excelUtils {

    /** 从excel中操作数据*/
    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if (fileName.endsWith("xls")) {
                //excel2003
                workbook = new HSSFWorkbook(is);
            } else if (fileName.endsWith("xlsx")) {
                //excel2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {
            //log.info e.getMessage()
            e.printStackTrace();
        }
        return workbook;
    }
}

package controller;

import bean.ConvergeGatheringTest;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.upload.UploadFile;
import common.util.DateStyle;
import common.util.DateUtil;
import common.util.StringUtil;
import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static common.util.CustomExcelUtil.readerAndReturnByFile;


public class ExcelController extends Controller {

    private Logger L=Logger.getLogger(ExcelController.class);


    public void test(){

        L.info("当前是测试");
        System.out.println("哈哈");
    }

    public void uploadExcel(){

    }

}

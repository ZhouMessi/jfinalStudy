package controller;

import bean.ConvergeGatheringTest;
import bean.User;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.jfinal.core.Controller;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Record;
import com.jfinal.upload.UploadFile;
import common.util.DateStyle;
import common.util.DateUtil;
import common.util.StringUtil;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import static common.util.CustomExcelUtil.readerAndReturnByFile;

public class HelloController extends Controller {



    public void index(){
        setAttr("hello", "hello jfinal+beetl");
        renderTemplate("index.html");
    }

    public void getData(){
        try{
          // List<User> userList= User.dao.find("select * from user");
           List<Record> userList2= Db.use("jfinal1").find("select * from user");

           renderJson(userList2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void getData2(){
        try{
            // List<User> userList= User.dao.find("select * from user");
            List<Record> userList2= Db.use("jfinal2").find("select * from user");

            renderJson(userList2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void excel() {
        System.out.println("正在处理信息");

        FileOutputStream os = null;
        Workbook workbook = null;
        try {
            UploadFile tempfile = getFile();
            File file = tempfile.getFile();
            ImportParams importParams=new ImportParams();

            String mypath="";
            String path = new File("").getAbsolutePath().replaceAll("\\\\", "/"); //获得Tomcat的默认路径
            mypath = path.substring(0,path.lastIndexOf("/"))+"/webapps/"+ DateUtil.DateToString(new Date(), DateStyle.YYYY_MM_DD_HH_MM_SS_NOSPLIT)+".xls"; //截取字符串
            os = new FileOutputStream(mypath);

            String sql="select cash from t_trade  where no = ?";
            //如果有标题必须要配置否则会报错
            importParams.setTitleRows(0);
            //读取指定的sheet,比如要读取上传得第二个sheet 那么需要把startSheetIndex = 1 就可以了
            // importParams.setStartSheetIndex(1);
            List<ConvergeGatheringTest> list = readerAndReturnByFile(file,importParams, ConvergeGatheringTest.class);
            int count=1;
            for (ConvergeGatheringTest convergeGatheringTest : list) {
                String merchantOrderNum = convergeGatheringTest.getMerchantOrderNum();

                BigDecimal o1 = Db.use("140").queryColumn(sql,  merchantOrderNum.replace("\t",""));
                if(StringUtil.notNull(o1)){
                    convergeGatheringTest.setEnvironment140(o1);
                }

                BigDecimal o2 = Db.use("142").queryColumn(sql,  merchantOrderNum.replace("\t",""));
                if(StringUtil.notNull(o2)){
                    convergeGatheringTest.setEnvironment142(o2);
                }

                BigDecimal o3 = Db.use("143").queryColumn(sql,  merchantOrderNum.replace("\t",""));
                if(StringUtil.notNull(o3)){
                    convergeGatheringTest.setEnvironment143(o3);
                }

                BigDecimal o4 = Db.use("150").queryColumn(sql,  merchantOrderNum.replace("\t",""));
                if(StringUtil.notNull(o4)){
                    convergeGatheringTest.setEnvironment150(o4);
                }

                BigDecimal o5 = Db.use("141").queryColumn(sql,  merchantOrderNum.replace("\t",""));
                if(StringUtil.notNull(o5)){
                    convergeGatheringTest.setEnvironment141(o5);
                }


                System.out.println("这是第"+count+"个");
                count++;
            }

            //导出参数设置
            ExportParams exportParams=new ExportParams();
            exportParams.setSheetName("汇聚收款模块");

            workbook = ExcelExportUtil.exportExcel(exportParams, ConvergeGatheringTest.class, list);
            workbook.write(os);


            //判断路径是否存在
            if(new File(mypath).isFile()){
                renderFile(new File(mypath));
            }else{
                renderNull();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                workbook.close();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public void modify(){

        List<User> userList=User.dao.find("select * from user");
        //模板里访问cateLists,atr,

        for (User user : userList) {
            user.set("high",user.getInt("id")+1);
        }
        setAttr("userList", userList);
       // System.out.println(userList);
        render("test.html");
    }
}

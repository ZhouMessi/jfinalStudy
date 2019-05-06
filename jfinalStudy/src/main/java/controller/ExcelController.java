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
import java.util.Date;
import java.util.List;

import static common.util.CustomExcelUtil.readerAndReturnByFile;


public class ExcelController extends Controller {

    private Logger L=Logger.getLogger(ExcelController.class);


    public void test(){

        render("");
    }


    public void uploadExcel(){


    }



    public void excel150() {
        L.info("正在匹配核对150环境信息");

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
                Double o = Db.queryColumn(sql, merchantOrderNum);
                if(StringUtil.notNull(o)){
                    convergeGatheringTest.setEnvironment150(o);
                }
                // System.out.println("商户编号"+convergeGatheringTest.getMerchantNo());
                // System.out.println("商户名称"+convergeGatheringTest.getMerchantName());
                // System.out.println("创建时间"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(convergeGatheringTest.getCreateTime()));
                // System.out.println("支付时间(年月日)"+new SimpleDateFormat("yyyy-MM-dd").format(convergeGatheringTest.getPayTimeYMD()));
                // System.out.println("支付时间(时分秒)"+new SimpleDateFormat("HH:mm:ss").format(convergeGatheringTest.getPayTimeHMS()));
                // System.out.println("商户订单号"+convergeGatheringTest.getMerchantOrderNum());
                // System.out.println("支付流水号"+convergeGatheringTest.getPaySerialNum());
                // System.out.println("支付方式"+convergeGatheringTest.getPayType());
                // System.out.println("订单状态"+convergeGatheringTest.getOrderStatus());
                // System.out.println("订单金额"+convergeGatheringTest.getOrderMoney());
                // System.out.println("手续费"+convergeGatheringTest.getOrderNum());
                // System.out.println("产品名称"+convergeGatheringTest.getProductName());

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
}

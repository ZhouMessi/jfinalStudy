package common.util;
import bean.*;
import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import cn.afterturn.easypoi.excel.entity.result.ExcelImportResult;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomExcelUtil<T> extends ExcelUtiles {


    /**
     * 根据读取文件信息并改名为新名字并返回可以操作的excel
     * @param path
     * @param importParams 导入的参数配置
     * @param clazz 需要操作的对象的字节码文件
     * @return
     */
    public static <T>List<T> readerAndReturnByAP(String path,ImportParams importParams,Class clazz){
        File file=new File(path);
        // 数据处理
        //IExcelDataHandler<User> handler = new UserExcelHandler();
        //handler.setNeedHandlerFields(new String[] { "姓名" });// 注意这里对应的是excel的列名。也就是对象上指定的列名。
        //importParams.setDataHanlder(handler);
        // 需要验证
      //  importParams.setNeedVerfiy(true);
        try {
            ExcelImportResult<T> result = ExcelImportUtil.importExcelMore(file, clazz,importParams);
            List<T> successList = result.getList();
            List<T> failList = result.getFailList();
            System.out.println("是否存在验证未通过的数据:" + result.isVerfiyFail());
            System.out.println("验证通过的数量:" + successList.size());
            System.out.println("验证未通过的数量:" + failList.size());

            return successList;


        } catch (Exception e) {
            System.out.println("导入数据异常请检查文件格式,或咨询开发人员");
        }
        return null;
    }

    public static <T> List<T> readerAndReturnByFile(File file, ImportParams importParams, Class clazz){
        // 数据处理
        //IExcelDataHandler<User> handler = new UserExcelHandler();
        //handler.setNeedHandlerFields(new String[] { "姓名" });// 注意这里对应的是excel的列名。也就是对象上指定的列名。
        //importParams.setDataHanlder(handler);
        // 需要验证
        importParams.setNeedVerfiy(true);
        try {
            ExcelImportResult<T> result = ExcelImportUtil.importExcelMore(file, clazz,importParams);
            List<T> successList = result.getList();
            List<T> failList = result.getFailList();
            System.out.println("是否存在验证未通过的数据:" + result.isVerfiyFail());
            System.out.println("验证通过的数量:" + successList.size());
            System.out.println("验证未通过的数量:" + failList.size());

            return successList;


        } catch (Exception e) {
            System.out.println("导入数据异常请检查文件格式,或咨询开发人员");
        }
        return null;
    }





    /**
     * 导出对应的excel并制定导出的文件名
     * @param list
     * @param fileName
     */
    public static void exportExcel(List list,String fileName,Class<?> clazz) throws IOException {
        ExportParams e=new ExportParams();
        exportExcel(list,fileName,clazz,null);
    }

    public static void exportExcel(List list,String fileName,Class<?> clazz,ExportParams exportParams) throws IOException {
        FileOutputStream fos = null;
        Workbook workbook=null;
        try {
            if(exportParams==null){
                workbook = ExcelExportUtil.exportExcel(new ExportParams(), clazz, list);
            }else {
                workbook = ExcelExportUtil.exportExcel(exportParams, clazz, list);
            }

            //保存数据
            fos = new FileOutputStream(fileName+".xls");
            workbook.write(fos);
            System.out.println("导入数据完成");
        } catch (IOException e) {
            System.out.println("请检查输入的文件格式是否正确");
        }finally {
            fos.close();
        }
    }

    /**
     * 导入的自定义Excel
     * @param path 文件路径
     * @return CustomExcels 自定义的excel实体类
     */
    public static CustomExcels importCustomExcels(String path, CustomImportParamList customImportParamList){
        CustomExcels customExcels=new CustomExcels();

        //读取支付宝异常订单
        customExcels.setAliPayExceptionOrderList(CustomExcelUtil.<AliPayExceptionOrder>readerAndReturnByAP(path,customImportParamList.getAliPayExceptionOrderIP(), AliPayExceptionOrder.class));

        //读取汇聚付款测试
        customExcels.setConvergeGatheringTestList(CustomExcelUtil.<ConvergeGatheringTest>readerAndReturnByAP(path,customImportParamList.getConvergeGatheringTestIP(), ConvergeGatheringTest.class));

        //读取汇聚异常订单
        customExcels.setConvergeExceptionOrderList(CustomExcelUtil.<ConvergeExceptionOrder>readerAndReturnByAP(path,customImportParamList.getConvergeExceptionOrderIP(), ConvergeExceptionOrder.class));

        //读取汇聚付款测试
        customExcels.setConvergeRefundTestList(CustomExcelUtil.<ConvergeRefundTest>readerAndReturnByAP(path,customImportParamList.getConvergeRefundTestIP(), ConvergeRefundTest.class));

        //读取支付宝退款测试
        customExcels.setAliPayRefundTestList(CustomExcelUtil.<AliPayRefundTest>readerAndReturnByAP(path,customImportParamList.getAliPayRefundTestIP(), AliPayRefundTest.class));

        //读取支付宝在线测试
        customExcels.setAliPayOnLineTestList(CustomExcelUtil.<AliPayOnLineTest>readerAndReturnByAP(path,customImportParamList.getAliPayOnLineTestIP(), AliPayOnLineTest.class));

        return customExcels;
    }


    /**
     * 周洲
     * @param customExcels 自定义的excel实体类
     * @param fileName 导出的文件名前缀不包含.xls
     * 2019年4月29日14:51:19
     */
    public static void exportCustomExcels(CustomExcels customExcels,String fileName,CustomExportParamList customExportParamList){
        Workbook workbook=null;
        FileOutputStream fos=null;
        try{
            //导出第1页AliPayExceptionOrder(支付宝异常订单)
            Map<String,Object>map1=new HashMap<>();
            map1.put("title",customExportParamList.getAliPayExceptionOrderEP());
            map1.put("entity",AliPayExceptionOrder.class);
            map1.put("data",customExcels.getAliPayExceptionOrderList());

            //导出第2页ConvergeGatheringTest(汇聚收款测试)
            Map<String,Object>map2=new HashMap<>();
            map2.put("title",customExportParamList.getConvergeGatheringTestEP());
            map2.put("entity",ConvergeGatheringTest.class);
            map2.put("data",customExcels.getConvergeGatheringTestList());

            //导出第3页ConvergeExceptionOrder(汇聚异常订单)
            Map<String,Object>map3=new HashMap<>();
            map3.put("title",customExportParamList.getConvergeExceptionOrderEP());
            map3.put("entity",ConvergeExceptionOrder.class);
            map3.put("data",customExcels.getConvergeExceptionOrderList());

            //导出第4页ConvergeRefundTest(汇聚退款测试)
            Map<String,Object>map4=new HashMap<>();
            map4.put("title",customExportParamList.getConvergeRefundTestEP());
            map4.put("entity",ConvergeRefundTest.class);
            map4.put("data",customExcels.getConvergeRefundTestList());

            //导出第5页AliPayRefundTest(支付宝退款测试)
            Map<String,Object>map5=new HashMap<>();
            map5.put("title",customExportParamList.getAliPayRefundTestEP());
            map5.put("entity",AliPayRefundTest.class);
            map5.put("data",customExcels.getAliPayRefundTestList());

            //导出第6页AliPayOnLineTest(支付宝在线测试)
            Map<String,Object>map6=new HashMap<>();
            map6.put("title",customExportParamList.getAliPayOnLineTestEP());
            map6.put("entity",AliPayOnLineTest.class);
            map6.put("data",customExcels.getAliPayOnLineTestList());

            // 将sheet1、sheet2、sheet3...使用得map进行包装
            List<Map<String, Object>> sheetsList = new ArrayList<>();
            sheetsList.add(map1);
            sheetsList.add(map2);
            sheetsList.add(map3);
            sheetsList.add(map4);
            sheetsList.add(map5);
            sheetsList.add(map6);

            //执行方法
            workbook = ExcelExportUtil.exportExcel(sheetsList, ExcelType.HSSF);
            //保存数据

            fos=new FileOutputStream(fileName+".xls");
            workbook.write(fos);
            System.out.println("导出数据完成");

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fos.close();
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        CustomExcels customExcels = importCustomExcels("D:\\IDE\\Github\\jfinalStudy\\jfinalStudy\\3月份测试明细.xls", new CustomImportParamList());

        List<AliPayExceptionOrder> aliPayExceptionOrderList = customExcels.getAliPayExceptionOrderList();

        for (AliPayExceptionOrder aliPayExceptionOrder : aliPayExceptionOrderList) {
            aliPayExceptionOrder.setIsEqual("是");
            aliPayExceptionOrder.setIsExist("是");
        }

        exportCustomExcels(customExcels,"财务需要",new CustomExportParamList());
    }

}
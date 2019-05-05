package bean;

import cn.afterturn.easypoi.excel.entity.ExportParams;

public class CustomExportParamList {

    private ExportParams aliPayExceptionOrderEP=new ExportParams();

    private ExportParams aliPayOnLineTestEP=new ExportParams();

    private ExportParams aliPayRefundTestEP=new ExportParams();

    private ExportParams convergeExceptionOrderEP=new ExportParams();

    private ExportParams convergeGatheringTestEP=new ExportParams();

    private ExportParams convergeRefundTestEP=new ExportParams();

    /**
     *
     */
    public CustomExportParamList() {
        this.aliPayExceptionOrderEP.setSheetName("支付宝异常订单");
        this.aliPayExceptionOrderEP.setTitle("3月份支付宝异常订单");

        this.aliPayOnLineTestEP.setSheetName("支付宝在线测试");
        this.aliPayOnLineTestEP.setTitle("3月份支付宝在线测试明细");

        this.aliPayRefundTestEP.setSheetName("支付宝退款测试");
        this.aliPayRefundTestEP.setTitle("3月份支付宝退款测试明细");

        this.convergeExceptionOrderEP.setSheetName("汇聚异常订单");
        this.convergeExceptionOrderEP.setTitle("3月份汇聚异常订单");

        this.convergeGatheringTestEP.setSheetName("汇聚收款测试");

        this.convergeRefundTestEP.setSheetName("汇聚退款测试");
    }



    public ExportParams getAliPayExceptionOrderEP() {
        return aliPayExceptionOrderEP;
    }

    public void setAliPayExceptionOrderEP(ExportParams aliPayExceptionOrderEP) {
        this.aliPayExceptionOrderEP = aliPayExceptionOrderEP;
    }

    public ExportParams getAliPayOnLineTestEP() {
        return aliPayOnLineTestEP;
    }

    public void setAliPayOnLineTestEP(ExportParams aliPayOnLineTestEP) {
        this.aliPayOnLineTestEP = aliPayOnLineTestEP;
    }

    public ExportParams getAliPayRefundTestEP() {
        return aliPayRefundTestEP;
    }

    public void setAliPayRefundTestEP(ExportParams aliPayRefundTestEP) {
        this.aliPayRefundTestEP = aliPayRefundTestEP;
    }

    public ExportParams getConvergeExceptionOrderEP() {
        return convergeExceptionOrderEP;
    }

    public void setConvergeExceptionOrderEP(ExportParams convergeExceptionOrderEP) {
        this.convergeExceptionOrderEP = convergeExceptionOrderEP;
    }

    public ExportParams getConvergeGatheringTestEP() {
        return convergeGatheringTestEP;
    }

    public void setConvergeGatheringTestEP(ExportParams convergeGatheringTestEP) {
        this.convergeGatheringTestEP = convergeGatheringTestEP;
    }

    public ExportParams getConvergeRefundTestEP() {
        return convergeRefundTestEP;
    }

    public void setConvergeRefundTestEP(ExportParams convergeRefundTestEP) {
        this.convergeRefundTestEP = convergeRefundTestEP;
    }
}

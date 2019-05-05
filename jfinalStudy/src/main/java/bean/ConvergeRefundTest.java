package bean;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.util.Date;

/**
 * 汇聚退款测试pojo
 *2019年4月29日09:17:51
 * 周洲
 */
public class ConvergeRefundTest {

    @Excel(name = "商户编号",width = 25)
    private String merchantNo;

    @Excel(name = "商户名称",width = 30)
    private String merchantName;

    @Excel(name = "退款请求时间",importFormat = "yyyy-MM-dd HH:mm:ss",exportFormat = "yyyy-MM-dd HH:mm:ss",width = 20)
    private Date refundApplyTime;

    @Excel(name = "退款完成时间YMD",importFormat = "yyyy-MM-dd ",exportFormat = "yyyy-MM-dd",width = 20)
    private Date refundSuccessTime1;

    @Excel(name = "退款完成时间HMS",importFormat = "HH:mm:ss",exportFormat = " HH:mm:ss",width = 20)
    private Date refundSuccessTime2;

    @Excel(name = "原商户订单号",width = 25)
    private String originalMerchantOrderNum;

    @Excel(name = "原支付流水号",width = 25)
    private String originalPaySerialNum;

    @Excel(name = "退款流水号",width = 25)
    private String refundSerialNum;

    @Excel(name = "商户退款订单号",width = 25)
    private String merchantRefundOrderNum;

    @Excel(name = "退款金额")
    private Double refundMoney;

    @Excel(name = "退款手续费")
    private Double refundPoundage;

    @Excel(name = "退款状态")
    private String refundState;

    @Excel(name = "退款方式")
    private String refundWay;

    @Excel(name = "退款申请原因")
    private String refundApplyCause;

    public String getMerchantNo() {
        return merchantNo;
    }

    public void setMerchantNo(String merchantNo) {
        this.merchantNo = merchantNo;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public Date getRefundApplyTime() {
        return refundApplyTime;
    }

    public void setRefundApplyTime(Date refundApplyTime) {
        this.refundApplyTime = refundApplyTime;
    }

    public Date getRefundSuccessTime1() {
        return refundSuccessTime1;
    }

    public void setRefundSuccessTime1(Date refundSuccessTime1) {
        this.refundSuccessTime1 = refundSuccessTime1;
    }

    public Date getRefundSuccessTime2() {
        return refundSuccessTime2;
    }

    public void setRefundSuccessTime2(Date refundSuccessTime2) {
        this.refundSuccessTime2 = refundSuccessTime2;
    }

    public String getOriginalMerchantOrderNum() {
        return originalMerchantOrderNum;
    }

    public void setOriginalMerchantOrderNum(String originalMerchantOrderNum) {
        this.originalMerchantOrderNum = originalMerchantOrderNum;
    }

    public String getOriginalPaySerialNum() {
        return originalPaySerialNum;
    }

    public void setOriginalPaySerialNum(String originalPaySerialNum) {
        this.originalPaySerialNum = originalPaySerialNum;
    }

    public String getRefundSerialNum() {
        return refundSerialNum;
    }

    public void setRefundSerialNum(String refundSerialNum) {
        this.refundSerialNum = refundSerialNum;
    }

    public String getMerchantRefundOrderNum() {
        return merchantRefundOrderNum;
    }

    public void setMerchantRefundOrderNum(String merchantRefundOrderNum) {
        this.merchantRefundOrderNum = merchantRefundOrderNum;
    }

    public Double getRefundMoney() {
        return refundMoney;
    }

    public void setRefundMoney(Double refundMoney) {
        this.refundMoney = refundMoney;
    }

    public Double getRefundPoundage() {
        return refundPoundage;
    }

    public void setRefundPoundage(Double refundPoundage) {
        this.refundPoundage = refundPoundage;
    }

    public String getRefundState() {
        return refundState;
    }

    public void setRefundState(String refundState) {
        this.refundState = refundState;
    }

    public String getRefundWay() {
        return refundWay;
    }

    public void setRefundWay(String refundWay) {
        this.refundWay = refundWay;
    }

    public String getRefundApplyCause() {
        return refundApplyCause;
    }

    public void setRefundApplyCause(String refundApplyCause) {
        this.refundApplyCause = refundApplyCause;
    }
}

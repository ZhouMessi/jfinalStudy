package bean;

import cn.afterturn.easypoi.excel.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 汇聚收款测试pojo
 * 2019年4月26日16:35:03
 * 周洲
 */
public class ConvergeGatheringTest {

    @Excel(name = "商户编号",width = 25)
    private String merchantNo;

    @Excel(name = "商户名称",width = 30)
    private String merchantName;

    @Excel(name = "创建时间",importFormat = "yyyy-MM-dd HH:mm:ss",exportFormat = "yyyy-MM-dd HH:mm:ss",width = 20)
    private Date createTime;

    @Excel(name = "支付时间YMD",importFormat = "yyyy-MM-dd",exportFormat = "yyyy-MM-dd ")
    private Date payTimeYMD;

    @Excel(name = "支付时间HMS",importFormat = "HH:mm:ss",exportFormat = "HH:mm:ss")
    private Date payTimeHMS;

    @Excel(name = "商户订单号",width = 20)
    private String merchantOrderNum;

    @Excel(name = "支付流水号",width = 25)
    private String paySerialNum;

    @Excel(name = "支付方式")
    private String payType;

    @Excel(name = "订单状态")
    private String orderStatus;

    @Excel(name = "订单金额")
    private Double orderMoney;

    @Excel(name = "手续费")
    private Double orderNum;

    @Excel(name = "产品名称")
    private String productName;

    @Excel(name = "150")
    private BigDecimal environment150;

    @Excel(name = "140")
    private BigDecimal environment140;

    @Excel(name = "142")
    private BigDecimal environment142;

    @Excel(name = "143")
    private BigDecimal environment143;



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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getPayTimeYMD() {
        return payTimeYMD;
    }

    public void setPayTimeYMD(Date payTimeYMD) {
        this.payTimeYMD = payTimeYMD;
    }

    public Date getPayTimeHMS() {
        return payTimeHMS;
    }

    public void setPayTimeHMS(Date payTimeHMS) {
        this.payTimeHMS = payTimeHMS;
    }

    public String getMerchantOrderNum() {
        return merchantOrderNum;
    }

    public void setMerchantOrderNum(String merchantOrderNum) {
        this.merchantOrderNum = merchantOrderNum;
    }

    public String getPaySerialNum() {
        return paySerialNum;
    }

    public void setPaySerialNum(String paySerialNum) {
        this.paySerialNum = paySerialNum;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    public Double getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Double orderNum) {
        this.orderNum = orderNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getEnvironment150() {
        return environment150;
    }

    public void setEnvironment150(BigDecimal environment150) {
        this.environment150 = environment150;
    }

    public BigDecimal getEnvironment140() {
        return environment140;
    }

    public void setEnvironment140(BigDecimal environment140) {
        this.environment140 = environment140;
    }

    public BigDecimal getEnvironment142() {
        return environment142;
    }

    public void setEnvironment142(BigDecimal environment142) {
        this.environment142 = environment142;
    }

    public BigDecimal getEnvironment143() {
        return environment143;
    }

    public void setEnvironment143(BigDecimal environment143) {
        this.environment143 = environment143;
    }
}
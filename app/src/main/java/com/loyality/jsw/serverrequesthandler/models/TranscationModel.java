package com.loyality.jsw.serverrequesthandler.models;

import java.io.Serializable;

public class TranscationModel implements Serializable {
    private String totalPending;
    private String totalPurchase;
    private String totalApprove;
    private String transactionId;
    private String product;
    private String size;
    private String quantity;
    private String unit;
    private String amount;
    private String retailer;
    private String status;
    private String date;
    private String sheets;
    private String fabricator;

    private String fabricatorID;
    private String fabricatorName;
    private String purchaseDtae;
    private String fabricatorNumber;
    private String remark;

    public String getFabricatorID() {
        return fabricatorID;
    }

    public void setFabricatorID(String fabricatorID) {
        this.fabricatorID = fabricatorID;
    }

    public String getFabricatorName() {
        return fabricatorName;
    }

    public void setFabricatorName(String fabricatorName) {
        this.fabricatorName = fabricatorName;
    }

    public String getPurchaseDtae() {
        return purchaseDtae;
    }

    public void setPurchaseDtae(String purchaseDtae) {
        this.purchaseDtae = purchaseDtae;
    }

    public String getFabricatorNumber() {
        return fabricatorNumber;
    }

    public void setFabricatorNumber(String fabricatorNumber) {
        this.fabricatorNumber = fabricatorNumber;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTotalPending() {
        return totalPending;
    }

    public void setTotalPending(String totalPending) {
        this.totalPending = totalPending;
    }

    public String getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(String totalPurchase) {
        this.totalPurchase = totalPurchase;
    }

    public String getTotalApprove() {
        return totalApprove;
    }

    public void setTotalApprove(String totalApprove) {
        this.totalApprove = totalApprove;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    private String productName;

    public String getFabricator() {
        return fabricator;
    }

    public void setFabricator(String fabricator) {
        this.fabricator = fabricator;
    }

    public String getSheets() {
        return sheets;
    }

    public void setSheets(String sheets) {
        this.sheets = sheets;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRetailer() {
        return retailer;
    }

    public void setRetailer(String retailer) {
        this.retailer = retailer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

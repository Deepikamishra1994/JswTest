package com.loyality.jsw.serverrequesthandler.models;

public class AddProductModel {
    public String getDateOfProduct() {
        return dateOfProduct;
    }

    public void setDateOfProduct(String dateOfProduct) {
        this.dateOfProduct = dateOfProduct;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    private String dateOfProduct;
    private String productName;
    private String size;
    private String quantity;
    private String unit;
    private String amount;
    private String retailer;
    private String productId;
    private String transactionId;
    private String remarks;
    private String fabricatorId;
    private String fabricatorName;
    private String purchaseDtae;
    private String fabricatorNumber;
    private String fabricator;
    private String remark;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    private String product;

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSheets() {
        return sheets;
    }

    public void setSheets(String sheets) {
        this.sheets = sheets;
    }

    private String sheets;


    public String getFabricator() {
        return fabricator;
    }

    public void setFabricator(String fabricator) {
        this.fabricator = fabricator;
    }

    public String getFabricatorId() {
        return fabricatorId;
    }

    public void setFabricatorId(String fabricatorId) {
        this.fabricatorId = fabricatorId;
    }
}

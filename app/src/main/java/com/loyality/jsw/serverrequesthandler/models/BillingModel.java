package com.loyality.jsw.serverrequesthandler.models;

public class BillingModel {
	private String image;
	private String productCode;
	private String quantity;
	private String totalPoints;
	private String billing;

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	private String points;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	private String productName;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setProductCode(String productCode){
		this.productCode = productCode;
	}

	public String getProductCode(){
		return productCode;
	}

	public void setQuantity(String quantity){
		this.quantity = quantity;
	}

	public String getQuantity(){
		return quantity;
	}

	public void setTotalPoints(String totalPoints){
		this.totalPoints = totalPoints;
	}

	public String getTotalPoints(){
		return totalPoints;
	}

	public void setBilling(String billing){
		this.billing = billing;
	}

	public String getBilling(){
		return billing;
	}

	@Override
 	public String toString(){
		return 
			"BillingModel{" + 
			"image = '" + image + '\'' + 
			",UNIQUE_CODE = '" + productCode + '\'' +
			",quantity = '" + quantity + '\'' + 
			",totalPoints = '" + totalPoints + '\'' + 
			",billing = '" + billing + '\'' + 
			"}";
		}
}

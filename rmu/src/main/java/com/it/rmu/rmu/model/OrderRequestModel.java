package com.it.rmu.rmu.model;



public class OrderRequestModel {
	
	private Integer ordersId;
	private Integer ProductId[];
	private Integer userId;
	private String address;
	private Integer provinceId;
	private Integer amphureId;
	private Integer tambonId;
	private Integer zipcode;
	private Integer quantity[];
	private String status;
	public Integer getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}
	public Integer[] getProductId() {
		return ProductId;
	}
	public void setProductId(Integer[] productId) {
		ProductId = productId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getAmphureId() {
		return amphureId;
	}
	public void setAmphureId(Integer amphureId) {
		this.amphureId = amphureId;
	}
	public Integer getTambonId() {
		return tambonId;
	}
	public void setTambonId(Integer tambonId) {
		this.tambonId = tambonId;
	}
	public Integer getZipcode() {
		return zipcode;
	}
	public void setZipcode(Integer zipcode) {
		this.zipcode = zipcode;
	}
	public Integer[] getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer[] quantity) {
		this.quantity = quantity;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	
	
	
}

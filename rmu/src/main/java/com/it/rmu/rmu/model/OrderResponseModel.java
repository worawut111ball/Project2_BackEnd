package com.it.rmu.rmu.model;

import java.util.Date;

public class OrderResponseModel {
	
	private Integer ordersId;
	private Integer[] productId;
	private Integer userId;
	private String address;
	private Integer provinceId;
	private Integer amphureId;
	private Integer tambonId;
	private Integer zipcode;
	private Integer[] quantity;
	private String status;
	private Date createDate;
	public Integer getOrdersId() {
		return ordersId;
	}
	public void setOrdersId(Integer ordersId) {
		this.ordersId = ordersId;
	}
	public Integer[] getProductId() {
		return productId;
	}
	public void setProductId(Integer[] productId) {
		this.productId = productId;
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
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	
	
	

}

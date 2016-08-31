package com.chinaway.tms.basic.model;

import java.io.Serializable;
import com.alibaba.fastjson.annotation.JSONField;

/**
 * 运单
 * @author zhang
 *
 */
public class Waybill implements Serializable {
	
	private static final long serialVersionUID = -5499181670910811369L;
	
	private Integer id;//   
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private String ordersid;//   订单id
	private String wlcompany;//   承运商
	private String vehiclemodel;//   车型
	
	public String getOrdersid() {
		return ordersid;
	}
	public void setOrdersid(String ordersid) {
		this.ordersid = ordersid;
	}
	public String getWlcompany() {
		return wlcompany;
	}
	public void setWlcompany(String wlcompany) {
		this.wlcompany = wlcompany;
	}
	public String getVehiclemodel() {
		return vehiclemodel;
	}
	public void setVehiclemodel(String vehiclemodel) {
		this.vehiclemodel = vehiclemodel;
	}
	
}
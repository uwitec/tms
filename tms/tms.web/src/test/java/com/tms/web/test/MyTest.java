package com.tms.web.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.chinaway.tms.basic.model.Orders;
import com.chinaway.tms.utils.GPSUtil;
import com.chinaway.tms.utils.http.HttpClientUtils;
import com.chinaway.tms.utils.json.JsonUtil;
import com.chinaway.tms.utils.lang.BigDecimalUtil;
import com.chinaway.tms.utils.lang.DateUtil;
import com.chinaway.tms.utils.lang.StringUtil;

//	1、订单导入接口-order.order.createOrders
//	2、订单生成车次接口-order.order.createDeparture
//	3、订单增量列表查询接口-order.order.getUpdateOrderList
//	4、单个订单详情查询接口byNo-order.order.getOrderInfoByNo
public class MyTest {
	
	private final static double EARTH_RADIUS = 6378.137;
	private static double rad(double d){
	   return d * Math.PI / 180.0;
	}

	/**
	 * 返回2点距离，单位公里(km)
	 * @param lat1
	 * @param lng1
	 * @param lat2
	 * @param lng2
	 * @return
	 */
	public static double GetDistance(double lat1, double lng1, double lat2, double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);
		double s = 2 * Math.asin(Math.sqrt(
				Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
//		s = s * EARTH_RADIUS;
		s = Math.round(s * EARTH_RADIUS * 10000) / 10000.0;
		return s;
	}
	
	@Test
	public void test5() throws Exception {
		//51	104.198984	30.537145
		/* W001	104.062254	30.630957
		 * W030	104.052506	30.708621
		 * W031	30.572404	104.071839
		 */
		//W030
		double distance2 = GetDistance(30.537145, 104.198984, 30.708621, 104.052506);
		//W031
		double distance = GPSUtil.getDistance(30.537145, 104.198984, 30.572404, 104.071839);
		System.out.println(distance);
		System.out.println(distance2);
//		14.1861
//		16.9583
//		12.8048
//		23.6911

	}
	
	@Test
	public void test3() throws Exception {
		List<String> list = new ArrayList<>();
		List<String> list2 = new ArrayList<>();
		List<String> list3 = new ArrayList<>();
		list2.add("2-111");
		list2.add("2-112");
		
		list3.add("3-111");
		list3.add("3-112");
		
		list.addAll(list2);
		list.addAll(list3);
		System.out.println(list);
	}
	
	@Test
	public void test2() throws Exception {
		//http://10.4.100.152:8080/1919wms-2.0/scpwms/tmsInterface?stowageNumber=ST16020200007&status=600
//		String param = "";
		Map<String, Object> map =new HashMap<>();
		map.put("stowageNumber", "tms201609131153011865");
		map.put("status", "2");
		
		String urlRoot ="http://10.4.100.152:8080/1919wms-2.0/scpwms/tmsInterface";
		Map<String, Object> resultMap = HttpClientUtils.getResult(map, urlRoot, "", "get");
		System.out.println(resultMap);
	}
	
	//t02
	@Test
	public void test1() throws Exception {
		//http://10.4.100.152:8080/1919wms-2.0/scpwms/tmsInterface?orderInfo=xxx
		String param = "{\"carrierCode\":\"anxun\",\"carrierName\":\"安迅\",\"stowageNumber\":\"tms201609131153011865\",\"wh\":\"51\","
				+ "\"details\":[{\"obdNumber\":\"OBSO16082700131\"}]}";
		Map<String, Object> map =new HashMap<>();
		map.put("orderInfo", param);
		System.out.println(map);
		
		String urlRoot ="http://10.4.100.152:8080/1919wms-2.0/scpwms/tmsInterface";
		Map<String, Object> resultMap = HttpClientUtils.getResult(map, urlRoot, "", "post");
		System.out.println(resultMap);
	}
	
	@Test
	public void test0() throws Exception {
		String orderInfo = "{\"amount\":10.0,\"createtim\":\"2016-08-30\",\"deptname\":\"5121\",\"fhaddress\":\"51\",\"fromcode\":\"OBSO16083100162\",\"orderfrom\":\"WMS\",\"requstarttime\":\"2016-08-30\",\"shaddress\":\"W031\","
				+ "\"goods\":[{\"goodsid\":\"4\",\"amount\":\"5\"}]}";
		Map<String, Object> map = JsonUtil.jsonStr2Map(orderInfo);
		List<Map<String, Object>> goods = (List<Map<String, Object>>) map.get("goods");
		System.out.println(goods);
		Orders order = JsonUtil.jsonStr2Obj(orderInfo, Orders.class);
		System.out.println(order);
		if("wms".equalsIgnoreCase(order.getOrderfrom())){
			System.out.println(true);
		}else{
			System.out.println(false);
		}
	}
}

package com.springbook.ioc.injection;

import java.util.List;
import java.util.Map;

public class CollectionBean {
	private Map<String, String> addressList;
	
	public void setAddressList(Map<String, String> addressList) {
		this.addressList = addressList;
	}

	public Map<String, String> getAddressList() {
		return addressList;
	}
	
	
	private List<String> addressList1;
	
	public void setAddressList1(List<String> addressList) {
		this.addressList1 = addressList;
	}
	
	public List<String> getAddressList1() {
		return addressList1;
	}
}

package com.springbook.ioc.injection;

import java.util.List;
import java.util.Map;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class CollectionBeanClient {

	public static void main(String[] args) {
		AbstractApplicationContext factory =
				new GenericXmlApplicationContext("applicationContext.xml");
		
		CollectionBean bean = (CollectionBean) factory.getBean("collectionBean");
		CollectionBean bean1 = (CollectionBean) factory.getBean("collectionBean1");
		
		// Map
		Map<String, String> addressList = bean.getAddressList();
		for (String key: addressList.keySet()) {
			System.out.println(String.format("키:%s, 값:%s", key, addressList.get(key)));
		}

		// List
		List<String>addressList1 = bean1.getAddressList1();
		for (String address : addressList1) {
			System.out.println(address.toString());
		}
		
		factory.close();
	}

}

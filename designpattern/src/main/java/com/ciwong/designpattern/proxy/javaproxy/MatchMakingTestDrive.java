package com.ciwong.designpattern.proxy.javaproxy;

import java.lang.reflect.Proxy;
import java.util.Hashtable;

public class MatchMakingTestDrive {
	Hashtable datingDB = new Hashtable();
 	
	public static void main(String[] args) {
		MatchMakingTestDrive test = new MatchMakingTestDrive();
		test.drive();
	}
 
	public MatchMakingTestDrive() {
		initializeDatabase();
	}

	public void drive() {
		com.ciwong.designpattern.proxy.javaproxy.PersonBean joe = getPersonFromDatabase("Joe Javabean");
		com.ciwong.designpattern.proxy.javaproxy.PersonBean ownerProxy = getOwnerProxy(joe);
		System.out.println("Name is " + ownerProxy.getName());
		ownerProxy.setInterests("bowling, Go");
		System.out.println("Interests set from owner proxy");
		try {
			ownerProxy.setHotOrNotRating(10);
		} catch (Exception e) {
			System.out.println("Can't set rating from owner proxy");
		}
		System.out.println("Rating is " + ownerProxy.getHotOrNotRating());

		com.ciwong.designpattern.proxy.javaproxy.PersonBean nonOwnerProxy = getNonOwnerProxy(joe);
		System.out.println("Name is " + nonOwnerProxy.getName());
		try {
			nonOwnerProxy.setInterests("bowling, Go");
		} catch (Exception e) {
			System.out.println("Can't set interests from non owner proxy");
		}
		nonOwnerProxy.setHotOrNotRating(3);
		System.out.println("Rating set from non owner proxy");
		System.out.println("Rating is " + nonOwnerProxy.getHotOrNotRating());
	}

	com.ciwong.designpattern.proxy.javaproxy.PersonBean getOwnerProxy(com.ciwong.designpattern.proxy.javaproxy.PersonBean person) {
 		
        return (com.ciwong.designpattern.proxy.javaproxy.PersonBean) Proxy.newProxyInstance(
            	person.getClass().getClassLoader(),
            	person.getClass().getInterfaces(),
                new com.ciwong.designpattern.proxy.javaproxy.OwnerInvocationHandler(person));
	}

	com.ciwong.designpattern.proxy.javaproxy.PersonBean getNonOwnerProxy(com.ciwong.designpattern.proxy.javaproxy.PersonBean person) {
		
        return (com.ciwong.designpattern.proxy.javaproxy.PersonBean) Proxy.newProxyInstance(
            	person.getClass().getClassLoader(),
            	person.getClass().getInterfaces(),
                new com.ciwong.designpattern.proxy.javaproxy.NonOwnerInvocationHandler(person));
	}

	com.ciwong.designpattern.proxy.javaproxy.PersonBean getPersonFromDatabase(String name) {
		return (com.ciwong.designpattern.proxy.javaproxy.PersonBean)datingDB.get(name);
	}

	void initializeDatabase() {
		com.ciwong.designpattern.proxy.javaproxy.PersonBean joe = new com.ciwong.designpattern.proxy.javaproxy.PersonBeanImpl();
		joe.setName("Joe Javabean");
		joe.setInterests("cars, computers, music");
		joe.setHotOrNotRating(7);
		datingDB.put(joe.getName(), joe);

		com.ciwong.designpattern.proxy.javaproxy.PersonBean kelly = new com.ciwong.designpattern.proxy.javaproxy.PersonBeanImpl();
		kelly.setName("Kelly Klosure");
		kelly.setInterests("ebay, movies, music");
		kelly.setHotOrNotRating(6);
		datingDB.put(kelly.getName(), kelly);
	}
}

package com.lab3.app.client;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.lab3.app.service.*;
import com.lab3.app.domain.*;

public class orderApp {
	public static void main(String args[]){	
		AbstractApplicationContext container = new ClassPathXmlApplicationContext("OrderTest-context.xml");
		container.registerShutdownHook();
		
		
		//ApplicationContext container = new ClassPathXmlApplicationContext("application.xml");
		OrderProcessor orderProc=(OrderProcessor)container.getBean("orderProc");
		//InventoryService inventService=(InventoryService) container.getBean("inventSerivce");
		AccountingService acct_service=(AccountingService)container.getBean("acctService");
		
		
		Product p1=new Product("HP", 700);
		Product p2=new Product("Acer", 500);
		Product p3=new Product("Lenovo", 400);
		
		OrderItem item1=new OrderItem(p1,1);
		OrderItem item2=new OrderItem(p2,2);
		OrderItem item3=new OrderItem(p3,3);

		
		ArrayList<OrderItem> ItemList=new ArrayList<OrderItem>();
		ItemList.add(item1);
		ItemList.add(item2);
		ItemList.add(item3);

		Customer customer=new Customer("Mingyi","CA");	
		
		Order order=new Order(customer, ItemList);	
		
		//for aop test
		acct_service.computeTax(order);
		
		//print info before ordering
		//System.out.println("==========Info Before ordering========== ");
		//inventService.printCurrentInventory();
		
		orderProc.newOrder(order);
		
		//print info of order
		System.out.println("==========Order info==========");		
		System.out.println("Customer name:" +order.getCustomer().getName());
		System.out.println("Customer state:" +order.getCustomer().getState());
		for(OrderItem item: order.getItemList()){
			System.out.println("Item: " +item.getProduct().getName());
			System.out.println("price: " +item.getProduct().getPrice());
			System.out.println("quantity: " +item.getQuantity());
			System.out.println("");
		}
		System.out.println("Subtotal: " + order.getSubtotal());
		System.out.println("Tax: "+ order.getTax());
		System.out.println("Total:"+order.getTotal());
		System.out.println("");
		System.out.println("");

		//print info after ordering
		System.out.println("==========Info After ordering==========");
		//inventService.printCurrentInventory();		
		
	}
	

}

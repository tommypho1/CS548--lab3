package com.lab3.app;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.HashMap;

import com.lab3.app.domain.*;
import com.lab3.app.service.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@ContextConfiguration("file:src/test/resources/OrderTest-context.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class TaxServiceTest {

	@Autowired
	
	private TaxService taxServ;
	private Product prod, prod2;
	private OrderItem orderItem, orderItem2;	
	private Customer cust;
	private ArrayList<OrderItem> OrderItemlist;
	private Order order;
	final double DELTA = 1e-15;
	
	
	@Before
	public void setUp(){
		prod=new Product("HP", 600.00);
		prod2=new Product("Acer", 700.00);
	    orderItem=new OrderItem(prod, 1);
	    orderItem2=new OrderItem(prod2, 1);
	    cust=new Customer("Mingyi", "CA");
	    OrderItemlist=new ArrayList<OrderItem>();
	    OrderItemlist.add(orderItem);
	    OrderItemlist.add(orderItem2);
	    order=new Order(cust,OrderItemlist);
	    HashMap<String,Double> hm=new HashMap<String,Double>();
	    hm.put("CA", 0.75);
	    hm.put("NY", 0.70);

	}
	
	@Test
	public void testComputTotal(){
		
		double tax=taxServ.computeTax(order);
		assertEquals(tax, 120.25, DELTA);
	}
	
}

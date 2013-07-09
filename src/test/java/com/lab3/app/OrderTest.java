package com.lab3.app;

import static org.junit.Assert.*;
import java.util.ArrayList;
import com.lab3.app.domain.*;

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
public class OrderTest {
	private Product prod, prod2,prod3;
	private OrderItem orderItem, orderItem2, orderItem3;	
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
	}
	
	
	@Test
	public void testComputTotal(){
		
		order.setTax(45);
		order.computTotal();
		double total= order.getTotal();
		assertEquals(total, 1345.0, DELTA);				
	}
	
	
	@Test(expected = NullPointerException.class)
	public void testAddItem() throws QuantityZeroException {
		prod3=new Product("Dell", 500.00);
		orderItem3=null;
		
		order.addItem(orderItem3);
		assertTrue(order.getItemList().contains(prod3));
	}
		
	@Test
	public void testRemoveItem(){
		
		order.removeProduct(prod2);
		assertFalse(order.getItemList().contains(prod2));
	}
	
}

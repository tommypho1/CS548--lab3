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
public class OrderItemTest {
	private Product prod, prod2, prod3;
	private OrderItem orderItem, orderItem2, orderItem3;	
	
	@Before
	public void setUp(){
		prod=new Product("HP", 600.00);
		prod2=new Product("Acer", 700.00); 
		prod3=new Product("Dell", 500.00);
	}
	
	@Test
	public void testGetProduct(){
		orderItem=new OrderItem(prod, 1);
		Product product=orderItem.getProduct();
		assertEquals(prod, product);

	}
	

	@Test(expected = QuantityZeroException.class)
	public void testSetQuantity() throws QuantityZeroException{
		orderItem2=new OrderItem(prod2, 1);
		orderItem2.setQuantity(0);
		assertEquals(5, orderItem2.getQuantity());
	}
	
	@Test
	public void testGetQuantity(){
		orderItem3=new OrderItem(prod3, 2);		
		assertEquals(2, orderItem3.getQuantity());
	}


}

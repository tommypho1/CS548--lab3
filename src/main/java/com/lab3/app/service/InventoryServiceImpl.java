package com.lab3.app.service;


import java.util.HashMap;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import com.lab3.app.domain.*;
import org.springframework.stereotype.Service;

@Service("inventSerivce")
public class InventoryServiceImpl implements InventoryService{
	private HashMap<Product,Integer> ProductInInventory=new HashMap<Product,Integer>();
	private HashMap<Product,Integer> ProductInStock=new HashMap<Product,Integer>();
	/*public InventoryService(){
		Product p1=new Product("ASUS", 800);
		Product p2=new Product("HP", 700);
		Product p3=new Product("DELL", 600);
		Product p4=new Product("Acer", 500);
		Product p5=new Product("Lenovo", 400);
		
		ProductInInventory.put(p1, 10);
		ProductInInventory.put(p2, 20);
		ProductInInventory.put(p3, 30);
		ProductInInventory.put(p4, 40);
		ProductInInventory.put(p5, 50);
		
		ProductInStock.put(p1, 50);
		ProductInStock.put(p2, 40);
		ProductInStock.put(p3, 30);
		ProductInStock.put(p4, 20);
		ProductInStock.put(p5, 10);
	}*/
	
	@PostConstruct
	public void initializeData(){
		Product p1=new Product("ASUS", 800);
		Product p2=new Product("HP", 700);
		Product p3=new Product("DELL", 600);
		Product p4=new Product("Acer", 500);
		Product p5=new Product("Lenovo", 400);
		
		ProductInInventory.put(p1, 10);
		ProductInInventory.put(p2, 20);
		ProductInInventory.put(p3, 30);
		ProductInInventory.put(p4, 40);
		ProductInInventory.put(p5, 50);
		
		ProductInStock.put(p1, 50);
		ProductInStock.put(p2, 40);
		ProductInStock.put(p3, 30);
		ProductInStock.put(p4, 20);
		ProductInStock.put(p5, 10);		
	}
	
	public void adjustInventory(Order order){
		for(OrderItem item: order.getItemList()){
			OrderItem order_item=item;
			for(Product prod: ProductInStock.keySet() ){
				Product pd=prod;
				int numInStock=ProductInStock.get(pd);
				int numInInventory=ProductInInventory.get(pd);
				if(order_item.getProduct().getName().equals(pd.getName())){
					if(numInStock>=order_item.getQuantity()){
						ProductInStock.put(pd, numInStock-order_item.getQuantity());
					    ProductInInventory.put(pd, numInInventory+order_item.getQuantity());
					}else{
						System.out.println(prod.getName()+" is out of stock.");
					}
					break;					
				}
			}
		}
		
	}
	/*public void printCurrentInventory(){
		for (Product prod: ProductInStock.keySet()){
            System.out.println("Product Name: "+prod.getName());
            System.out.println("Price: $"+prod.getPrice());
            System.out.println("Number in stock: "+ProductInStock.get(prod));            
            System.out.println();
        } 	
	}*/
	
	@PreDestroy
	public void destroyPrint(){
		for (Product prod: ProductInStock.keySet()){
            System.out.println("Product Name: "+prod.getName());
            System.out.println("Price: $"+prod.getPrice());
            System.out.println("Number in stock: "+ProductInStock.get(prod));            
            System.out.println();
        } 
	}

}

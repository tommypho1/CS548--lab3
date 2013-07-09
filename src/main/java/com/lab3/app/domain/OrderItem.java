package com.lab3.app.domain;

public class OrderItem {
	private Product product;
	private int quantity;
	
	public OrderItem(Product p, int q){
		product=p;
		quantity=q;
	}
	
	public Product getProduct(){return product;}
	public int getQuantity(){return quantity;}
	
	public void setQuantity(int q) throws QuantityZeroException{
		if(q==0){
			throw new QuantityZeroException();
		}else{quantity=q;}
		
	}
}

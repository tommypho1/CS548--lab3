package com.lab3.app.domain;

import java.util.ArrayList;

public class Order {
	
	private Customer customer;
	private ArrayList<OrderItem> item_list;
	private double subtotal;
	private double tax;
	private double total;
	
	public Order(Customer c, ArrayList<OrderItem> list){
		customer=c;
		item_list=list;
		for(OrderItem item: item_list){
			subtotal+=(item.getProduct().getPrice())*(item.getQuantity());
		}
	}
	
	public void computTotal(){
		total=subtotal+tax;
	}
	
	public Customer getCustomer(){return customer;}
	public double getSubtotal(){return subtotal;}
	public ArrayList<OrderItem> getItemList(){return item_list;}
	public void setTax(double t){tax=t;}
	public void setTotal(double t){total=t;}
	public double getTax(){return tax;}
	public double getTotal(){return total;}

	
	public void addItem(OrderItem item) throws QuantityZeroException ,NullPointerException {
		if(item==null){
			throw new NullPointerException();
		}else{
		boolean found=false;
		for(int i=0; i<item_list.size(); i++){
			if(item_list.get(i).getProduct().getName().equals(item.getProduct().getName())){
				int q=(int) (item_list.get(i).getQuantity()+item.getQuantity());
				item_list.get(i).setQuantity(q);
				found=true;
			}
		}
		if(found==false){
			item_list.add(item);
		}
		for(int i=0; i<item_list.size(); i++){
		    double st=0;
			st+=item_list.get(i).getProduct().getPrice();
			subtotal=st;
		}
		}
			
	}
	public void removeProduct(Product prod) throws NullPointerException {

		if(prod==null){
			throw new NullPointerException();
		}
		else{
		for(int i=0; i<item_list.size(); i++){
			if(item_list.get(i).getProduct().getName().equals(prod.getName())){
				item_list.remove(i);
			}
		}
		for(int i=0; i<item_list.size(); i++){
		    double st=0;
			st+=item_list.get(i).getProduct().getPrice();
			subtotal=st;
		}
		}
	}
	

}

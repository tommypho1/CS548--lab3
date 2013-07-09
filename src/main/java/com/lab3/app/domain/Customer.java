package com.lab3.app.domain;

import java.util.ArrayList;

public class Customer {
	private String cust_name;
	private String state;
	
	public Customer(String n, String s){
		cust_name=n;
		state=s;		
	}
	
	public String getState(){return state;}
	public String getName(){return cust_name;}
	
}

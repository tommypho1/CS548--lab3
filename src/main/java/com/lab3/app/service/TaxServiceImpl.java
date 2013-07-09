package com.lab3.app.service;
import java.util.HashMap;
import org.springframework.stereotype.Service;


import com.lab3.app.domain.*;


public class TaxServiceImpl implements TaxService{
	private HashMap<String,Double> taxOfEachState;
	public HashMap<String, Double> getTaxOfEachState() {
		return taxOfEachState;
	}

	public void setTaxOfEachState(HashMap<String,Double> toes){
		taxOfEachState=toes;
	}
	
	public double computeTax(Order order){
		double subtotal=order.getSubtotal();
		String cust_state=order.getCustomer().getState();
		double tax=0;

		if(taxOfEachState.containsKey(cust_state)){
			tax=subtotal*taxOfEachState.get(cust_state);
		}
		return tax;
	}

}

package com.lab3.app.service;

import javax.annotation.Resource;

import com.lab3.app.domain.*;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service("acctService")
public class AccountingServiceImpl implements AccountingService  {
	@Resource(name="taxServiceForSales")
	private TaxService taxService;	
	
	public void setTaxService(TaxService taxService) {
		this.taxService = taxService;
	}
	
	public double computeTax(Order order){
		return taxService.computeTax(order);
	}
	
	

}
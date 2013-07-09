package com.lab3.app.service;

import javax.annotation.Resource;

import com.lab3.app.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;


@Service("orderProc")
public class OrderProcessorImpl implements OrderProcessor {
	@Resource(name="acctService")
	private AccountingService acctService;
	@Resource(name="inventSerivce")
	private InventoryService inventService;
	
	public AccountingService getAcctService() {
		return acctService;
	}
	public InventoryService getInventService() {
		return inventService;
	}
	public void setAcctService(AccountingService as){acctService=as;}
	public void setInventService(InventoryService is){inventService=is;}
	
	public void newOrder(Order order){

		double tax=acctService.computeTax(order);
		order.setTax(tax);
		order.computTotal();
		inventService.adjustInventory(order);

	}
	

}

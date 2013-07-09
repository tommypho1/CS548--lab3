package com.lab3.app.aspects;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class LoggingAspect {
	private Logger logger = Logger.getLogger(LoggingAspect.class);
	private final double nanoTimeInASecond = 1000000000;
	
	@Pointcut("execution(* com.lab3.app.service.*.set*(..))")
	public void allSetMethod(){}
	
	@Before("allSetMethod()")
	public void logSetMethods(JoinPoint joinPt){
		Object methodArgs[];
		int argCnt;
		
		logger.debug("Method called: " + joinPt.getSignature());
		methodArgs=joinPt.getArgs();
		
		argCnt=1;
		for(Object methodArg:methodArgs){
			logger.debug("Method argument: "+argCnt+methodArg);
			argCnt++;
		}
		System.out.println("Method called: " + joinPt.getSignature());
	}
	
	@Pointcut("execution(* com.lab3.app.service.*.newOrder(..))")
	public void newOrderMethod(){}
	
	@After("newOrderMethod()")
	public void logNewOrderMethod(JoinPoint joinPt){
		Object methodArgs[];
		int argCnt;
		
		logger.debug("Method called: " + joinPt.getSignature());
		methodArgs=joinPt.getArgs();
		
		argCnt=1;
		for(Object methodArg:methodArgs){
			logger.debug("Method argument: "+argCnt+methodArg);
			argCnt++;
		}
		System.out.println("A new order has been created---Order Processor class---newOrder method¡£");
		System.out.println("Method called: " + joinPt.getSignature());
	}
	
	@Pointcut("execution(* com.lab3.app.service.*.computeTax(..))")
	public void computeTaxMethod(){}
	
	@AfterReturning(pointcut="computeTaxMethod()", returning="result")
	public void logcomputeTaxMethod(JoinPoint joinPt, String result){
		Object methodArgs[];
		int argCnt;
		
		logger.debug("Method called: " + joinPt.getSignature());
		methodArgs=joinPt.getArgs();
		
		argCnt=1;
		for(Object methodArg:methodArgs){
			logger.debug("Method argument: "+argCnt+methodArg);
			argCnt++;
		}
		logger.debug("Returning value: "+result);
		System.out.println("Method called: " + joinPt.getSignature());
		System.out.println("Returning value: "+result);
	}
	
	@Pointcut("execution(* com.lab3.app.domain.*.removeProduct(..))")
	public void removeProductMethod(){}
	
	@AfterThrowing(pointcut="removeProductMethod()",throwing = "ex")
	public void logRemoveProductMethod(JoinPoint joinPt, Exception ex){
		Object methodArgs[];
		int argCnt;
		
		logger.debug("Method called: " + joinPt.getSignature());
		methodArgs=joinPt.getArgs();
		
		argCnt=1;
		for(Object methodArg:methodArgs){
			logger.debug("Method argument: "+argCnt+methodArg);
			argCnt++;
		}
		
		logger.debug("Exception: "+ ex +"was thrown " );
		
	}
	
	@Pointcut("execution(* com.lab3.app.service.*.adjustInventory(..))")
	public void adjustInventoryMethod(){}
	
	@Around("adjustInventoryMethod()")
	public Object logAdjustInventoryMethod(ProceedingJoinPoint method) throws Throwable{
		String msgPrefix="Total time for ";
		String msg;
		
		
		long startTime = System.nanoTime();
		
		Object returnValue = method.proceed();
		
		long stopTime=System.nanoTime();
		long totalTimeNanos=stopTime-startTime;
		
		double seconds=(double)totalTimeNanos/nanoTimeInASecond;
		logger.debug("Seconds to add a nnew student: "+seconds);
		System.out.println("Seconds to add a nnew student: "+seconds);
		return returnValue;
				
		}	

	@After("execution(public * com.lab3.app.service.OrderProcessorImpl.newOrder(..))")
	public void aop(){
		System.out.println(" here we are");
	}
}

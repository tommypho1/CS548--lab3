package com.lab3.app.domain;

public class QuantityZeroException extends Exception {
	public QuantityZeroException (){}

    public QuantityZeroException (String message)
    {   super (message);}

}

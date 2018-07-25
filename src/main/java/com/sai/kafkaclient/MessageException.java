package com.sai.kafkaclient;

public class MessageException extends Exception{

	private static final long serialVersionUID = 1L;
	
	public MessageException(){
		
	}
	
	public MessageException(Exception e){
			super(e);
	}
	
	public MessageException(String e){
		super(e);
	}
	
	public MessageException(String message, Exception e){
		super(message, e);
	}

}

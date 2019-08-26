package com.sumavision.tetris.commons.util.wrapper;

public class StringBufferWrapper {

private StringBuffer buffer;
	
	public StringBufferWrapper(){
		this.buffer = new StringBuffer();
	}
	
	public StringBufferWrapper append(Object target){
		this.buffer.append(target);
		return this;
	}
	
	public StringBufferWrapper append(String target){
		this.buffer.append(target);
		return this;
	}
	
	public StringBufferWrapper append(int target){
		this.buffer.append(target);
		return this;
	}
	
	public StringBufferWrapper append(boolean target){
		this.buffer.append(target);
		return this;
	}
	
	public StringBufferWrapper append(long target){
		this.buffer.append(target);
		return this;
	}
	
	public StringBufferWrapper append(float target){
		this.buffer.append(target);
		return this;
	}
	
	public StringBufferWrapper append(double target){
		this.buffer.append(target);
		return this;
	}
	
	public String toString(){
		return this.buffer.toString();
	}
	
}

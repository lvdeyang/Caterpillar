package com.sumavision.tetris.commons.util.wrapper;

public class StringBuilderWrapper {

	private StringBuilder builder;
	
	public StringBuilderWrapper(){
		this.builder = new StringBuilder();
	}
	
	public StringBuilderWrapper append(Object target){
		this.builder.append(target);
		return this;
	}
	
	public StringBuilderWrapper append(String target){
		this.builder.append(target);
		return this;
	}
	
	public StringBuilderWrapper append(int target){
		this.builder.append(target);
		return this;
	}
	
	public StringBuilderWrapper append(boolean target){
		this.builder.append(target);
		return this;
	}
	
	public StringBuilderWrapper append(long target){
		this.builder.append(target);
		return this;
	}
	
	public StringBuilderWrapper append(float target){
		this.builder.append(target);
		return this;
	}
	
	public StringBuilderWrapper append(double target){
		this.builder.append(target);
		return this;
	}
	
	public String toString(){
		return this.builder.toString();
	}
}

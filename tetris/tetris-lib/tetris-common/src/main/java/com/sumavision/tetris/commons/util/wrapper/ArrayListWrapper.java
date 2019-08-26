package com.sumavision.tetris.commons.util.wrapper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class ArrayListWrapper<T>{

	private List<T> list = null;
	
	public ArrayListWrapper(){
		this.list = new ArrayList<T>();
	}
	
	public List<T> getList(){
		return this.list;
	}
	
	public ArrayListWrapper<T> add(T e){
		this.list.add(e);
		return this;
	}

	public ArrayListWrapper<T> addAll(Collection<? extends T> c){
		this.list.addAll(c);
		return this;
	}
	
	public ArrayListWrapper<T> addAll(ArrayListWrapper<? extends T> aw){
		return this.addAll(aw.getList());
	}
	
	public ArrayListWrapper<T> addAll(HashSetWrapper<? extends T> sw) {
		return this.addAll(sw.getSet());
	}
	
	public ArrayListWrapper<T> removeByIndex(int index){
		this.list.remove(index);
		return this;
	}
	
	public ArrayListWrapper<T> removeAllByIndex(Collection<Integer> c){
		for(Integer i:c){
			this.list.remove(i);
		}
		return this;
	}
	
	public ArrayListWrapper<T> removeAllByIndex(ArrayListWrapper<Integer> aw){
		return removeAllByIndex(aw.getList());
	}
	
	public ArrayListWrapper<T> removeAllByIndex(HashSetWrapper<Integer> sw){
		return removeAllByIndex(sw.getSet());
	}
	
	public ArrayListWrapper<T> remove(T e){
		this.list.remove(e);
		return this;
	}
	
	public ArrayListWrapper<T> removeAll(Collection<? extends T> c){
		this.list.removeAll(c);
		return this;
	}
	
	public ArrayListWrapper<T> removeAll(ArrayListWrapper<? extends T> aw){
		return this.removeAll(aw.getList());
	}
	
	public ArrayListWrapper<T> removeAll(HashSetWrapper<? extends T> sw){
		return this.removeAll(sw.getSet());
	}
	
	public T[] toArray(T[] a){
		return this.list.toArray(a);
	}
	
	public Object[] toArray(){
		return this.list.toArray();
	}
	
	public T get(int index){
		return this.list.get(index);
	}
	
	public int indexOf(T t){
		return this.list.indexOf(t);
	}
	
	public int lastIndexOf(T t){
		return this.list.lastIndexOf(t);
	}
	
	public ArrayListWrapper<T> clear(){
		this.list.clear();
		return this;
	}
	
	public boolean contains(T t){
		return this.list.contains(t);
	}
	
	public boolean containsAll(Collection<? extends T> c){
		return this.list.containsAll(c);
	}
	
	public boolean containsAll(ArrayListWrapper<? extends T> aw){
		return this.containsAll(aw.getList());
	}
	
	public boolean containsAll(HashSetWrapper<? extends T> sw){
		return this.containsAll(sw.getSet());
	}
	
	public boolean isEmpty(){
		return this.list.isEmpty();
	}
	
}

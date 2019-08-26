package com.sumavision.tetris.commons.util.wrapper;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class HashSetWrapper<T> {

	private Set<T> set = null;
	
	public HashSetWrapper (){
		this.set = new HashSet<T>();
	}
	
	public Set<T> getSet(){
		return this.set;
	}
	
	public HashSetWrapper<T> add(T e){
		this.set.add(e);
		return this;
	}

	public HashSetWrapper<T> addAll(Collection<? extends T> c){
		this.set.addAll(c);
		return this;
	}
	
	public HashSetWrapper<T> addAll(HashSetWrapper<? extends T> sw) {
		return this.addAll(sw.getSet());
	}
	
	public HashSetWrapper<T> addAll(ArrayListWrapper<? extends T> aw){
		return this.addAll(aw.getList());
	}
	
	public HashSetWrapper<T> remove(T e){
		this.set.remove(e);
		return this;
	}
	
	public HashSetWrapper<T> removeAll(Collection<? extends T> c){
		this.set.removeAll(c);
		return this;
	}
	
	public HashSetWrapper<T> removeAll(HashSetWrapper<? extends T> sw){
		return this.removeAll(sw.getSet());
	}
	
	public HashSetWrapper<T> removeAll(ArrayListWrapper<? extends T> aw){
		return this.removeAll(aw.getList());
	}
	
	public T[] toArray(T[] a){
		return this.set.toArray(a);
	}
	
	public Object[] toArray(){
		return this.set.toArray();
	}
	
	public HashSetWrapper<T> clear(){
		this.set.clear();
		return this;
	}
	
	public boolean contains(T t){
		return this.set.contains(t);
	}
	
	public boolean containsAll(Collection<? extends T> c){
		return this.set.containsAll(c);
	}
	
	public boolean containsAll(HashSetWrapper<? extends T> sw){
		return this.containsAll(sw.getSet());
	}
	
	public boolean containsAll(ArrayListWrapper<? extends T> aw){
		return this.containsAll(aw.getList());
	}
	
	public boolean isEmpty(){
		return this.set.isEmpty();
	}
	
}

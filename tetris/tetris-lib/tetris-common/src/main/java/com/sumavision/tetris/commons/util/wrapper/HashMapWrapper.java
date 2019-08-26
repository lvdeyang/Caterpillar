package com.sumavision.tetris.commons.util.wrapper;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class HashMapWrapper<K, V> {

	private Map<K, V> map;
	
	public HashMapWrapper(){
		this.map = new HashMap<K, V>();
	}
	
	public Map<K, V> getMap(){
		return this.map;
	}
	
	public HashMapWrapper<K, V> put(K key, V value){
		this.map.put(key, value);
		return this;
	}
	
	public HashMapWrapper<K, V> putAll(Map<? extends K, ? extends V> m){
		this.map.putAll(m);
		return this;
	}
	
	public HashMapWrapper<K, V> putAll(HashMapWrapper<? extends K, ? extends V> mw){
		this.map.putAll(mw.getMap());
		return this;
	}
	
	public HashMapWrapper<K, V> removeByKey(K key){
		this.map.remove(key);
		return this;
	}
	
	public HashMapWrapper<K, V> removeByValue(V value){
		if(this.map==null || this.map.size()<=0) return this;
		
		K targetK = null;
		Set<K> keySet = this.map.keySet();
		for(K key:keySet){
			V targetV = this.map.get(key);
			if((value!=null && value.equals(targetV)) || (value==null && value==targetV)){
				targetK = key;
				break;
			}
		}
		
		if(targetK != null) this.map.remove(targetK);
		
		return this;
	}
	
	public HashMapWrapper<K, V> removeAll(Collection<K> c){
		for(K key:c){
			this.map.remove(key);
		}
		return this;
	}
	
	public HashMapWrapper<K, V> removeAll(ArrayListWrapper<K> aw){
		return this.removeAll(aw.getList());
	}
	
	public HashMapWrapper<K, V> removeAll(HashSetWrapper<K> sw){
		return this.removeAll(sw.getSet());
	}
	
	public HashMapWrapper<K, V> removeAll(Map<K, V> m){
		return this.removeAll(m.keySet());
	}
	
	public HashMapWrapper<K, V> removeAll(HashMapWrapper<K, V> mw){
		return this.removeAll(mw.getMap().keySet());
	}
	
	public Set<K> keySet(){
		return this.map.keySet();
	}
	
	public Collection<V> values(){
		return this.map.values();
	}
	
	public int size(){
		return this.map.size();
	}
	
	public HashMapWrapper<K, V> clear(){
		this.map.clear();
		return this;
	}
	
	public boolean isEmpty(){
		return this.map.isEmpty();
	}
	
	public boolean containsKey(K key){
		return this.map.containsKey(key);
	}
	
	public boolean containsValue(V value){
		return this.map.containsValue(value);
	}
	
}

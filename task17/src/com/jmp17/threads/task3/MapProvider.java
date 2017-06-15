package com.jmp17.threads.task3;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MapProvider {
	
	Map<Integer, Integer> map;

	public Map<Integer, Integer> getMap() {
		return map;
	}
	
	public void setHashMap() {
		map = new HashMap<>();
	}
	
	public void setConcurrentMap() {
		map = new ConcurrentHashMap<>();
	}
	
	public void setSynchronizedMap() {
		map = Collections.synchronizedMap(new HashMap<>());
	}
}

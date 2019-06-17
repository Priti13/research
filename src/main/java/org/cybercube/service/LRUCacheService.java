package org.cybercube.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class to handle LRU related logic
 * @author pjha
 *
 */
@Service
public class LRUCacheService {

	@Autowired
	private LRUCache cache;


	/**
	 * @param key
	 * @param value
	 * @return
	 */
	public Integer putValue(Integer key,Integer value) {
		return cache.put(key,value);
	}

	/**
	 * @param key
	 * @return
	 */
	public Map<Integer,Map<Integer,Integer>> getValue(Integer key){
		Integer val = cache.get(key);
		Map<Integer,Map<Integer,Integer>> returnMap = new HashMap<>();
		if(val!= -1) {
			Map<Integer,Integer> entry = new HashMap<>();
			entry.put(key, val);
			returnMap.put(200, entry);
		}
		else {
			returnMap.put(404, null);
		}
		return returnMap;
	}

}

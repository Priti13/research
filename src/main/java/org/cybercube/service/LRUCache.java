/**
 * 
 */
package org.cybercube.service;

import java.util.Deque;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

import org.springframework.stereotype.Component;

/**
 * @author pjha
 *
 */
@Component
public class LRUCache {

	static Deque<Integer> queue;
	static HashMap<Integer, Integer> map;
	static int capacity;

	/**
	 * Constructor
	 * @param cacheSize
	 */
	public LRUCache() {
		queue = new LinkedList<>();
		map = new HashMap<>();
		capacity = 5;
	}

	/**
	 * Updates the value for key if present in cache, updates the index of the key
	 * @param key
	 * @param value
	 */
	public Integer put(Integer key, Integer value) {
		if (!map.containsKey(key)) {
			if (queue.size() == capacity) {
				int last = queue.removeLast();
				map.remove(last);
			}
		} else {
			int index = 0, i = 0;
			Iterator<Integer> itr = queue.iterator();
			while (itr.hasNext()) {
				if (itr.next() == key) {
					index = i;
					break;
				}
				i++;
			}
			queue.remove(index);
		}
		queue.push(key);
		map.put(key, value);
		return 200;
	}

	/**
	 * @param key
	 * @return
	 */
	public int get(int key) {
		if (map.get(key) != null) {
			Integer result = map.get(key);
			queue.remove(result);
			queue.push(result);
			return result;
		}
		System.out.println("Did not get any value" + " for the key: " + key);
		return -1;
	}

}

/**
 * 
 */
package org.cybercube.controller;

import java.util.Map;

import javax.websocket.server.PathParam;

import org.cybercube.service.LRUCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pjha
 *
 */
@RestController
public class CacheController {

	@Autowired
	LRUCacheService cacheService;
	
	@GetMapping("/get/{key}")
    public Map<Integer, Map<Integer, Integer>> getValueFromCache(@PathParam(value="key") String key) {
        return cacheService.getValue(Integer.parseInt(key));
    }
	
	@PostMapping("/put/{key}")
	public Integer putValueInCache(@PathParam(value="key") String key,@RequestBody String value) {
		return cacheService.putValue(Integer.parseInt(key), Integer.parseInt(value));
	}
	
}

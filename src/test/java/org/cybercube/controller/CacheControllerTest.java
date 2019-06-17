package org.cybercube.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.cybercube.service.LRUCacheService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CacheControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	LRUCacheService lruCacheMockService;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getValueFromCacheTest() throws Exception {
		
		when(lruCacheMockService.getValue(1)).thenReturn(getDummyResponse());
		this.mockMvc.perform(get("/get/1").param("key", "1")).andExpect(status().isOk());
	}

	@Test
	public void putValueInCacheTest() throws Exception {
		when(lruCacheMockService.putValue(1,2)).thenReturn(200);
		this.mockMvc.perform(post("/put/1").param("key", "1").contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(2))).andExpect(status().isOk())
		;
	}

	private static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	private Map<Integer,Map<Integer,Integer>> getDummyResponse(){
		Map<Integer,Map<Integer,Integer>> resultMap = new HashMap<>();
		Map<Integer,Integer> entry = new HashMap<>();
		entry.put(1, 2);
		resultMap.put(200, entry);
		return resultMap;
	}
}

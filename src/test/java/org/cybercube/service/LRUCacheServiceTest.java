/**
 * 
 */
package org.cybercube.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * @author pjha
 *
 */
public class LRUCacheServiceTest {

	@InjectMocks
	LRUCacheService lRUCacheService;

	@Mock
	LRUCache lruCacheMock;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void putValueTest() throws Exception {
		when(lruCacheMock.put(1, 2)).thenReturn(200);
		Integer status = lRUCacheService.putValue(1, 2);
		assertEquals(new Integer(200), status);
		verify(lruCacheMock, times(1)).put(1, 2);
	}

	@Test
	public void getValueTest() {
		when(lruCacheMock.get(1)).thenReturn(1);
		Map<Integer, Map<Integer, Integer>> returnMap = lRUCacheService.getValue(1);

		assertEquals(true, returnMap.containsKey(200));
		verify(lruCacheMock, times(1)).get(1);
	}
}

package com.jmp17.task10;

import static org.junit.Assert.*;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.jmp17.task10.help.WSClient;

public class HelloControllerIntTest {
	
	private static String port;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		port = System.getProperty("testServerPortNumber", "13400");
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testSayHello() {
		WSClient client = new WSClient();
		String url = "http://localhost:" + port + "/task10-1/hello";
		try (CloseableHttpResponse resp = client.callGet(url);) {
			assertEquals(200, resp.getStatusLine().getStatusCode());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}

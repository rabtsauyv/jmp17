package com.jmp17.task10.help;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class WSClient {
	
	private CloseableHttpClient client = HttpClientBuilder.create().build();

	public CloseableHttpResponse callGet(String url) throws ClientProtocolException, IOException {
		HttpGet get = new HttpGet(url);
		CloseableHttpResponse resp = client.execute(get);
		return resp;
	}
}

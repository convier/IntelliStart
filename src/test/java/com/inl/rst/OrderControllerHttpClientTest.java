package com.inl.rst;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.inl.rst.service.OrderDTO;

public class OrderControllerHttpClientTest {

	public final static String HOST = "http://localhost:8080/";

	@Test
	@SuppressWarnings("unchecked")
	public void shouldReturnAllOrders() throws ClientProtocolException, IOException {
		// Given
		HttpUriRequest request = new HttpGet(HOST + "orders");
		// When
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		// Then
		List<OrderDTO> resource = retrieveResourceFromResponse(httpResponse, List.class);
		assertFalse(resource.isEmpty());
	}

	public static <T> T retrieveResourceFromResponse(HttpResponse response, Class<T> clazz) throws IOException {
		String jsonFromResponse = EntityUtils.toString(response.getEntity());
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		return mapper.readValue(jsonFromResponse, clazz);
	}
}

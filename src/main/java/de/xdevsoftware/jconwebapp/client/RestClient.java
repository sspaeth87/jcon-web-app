
package de.xdevsoftware.jconwebapp.client;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;


public class RestClient
{
	public static WebTarget getWebTarget()
	{
		final Client client = ClientBuilder
			.newBuilder()
			.build();

		return client.target(RestClient.getBaseURI());
	}

	private static URI getBaseURI()
	{
		return UriBuilder.fromUri("http://localhost:8081/").build();
	}
}

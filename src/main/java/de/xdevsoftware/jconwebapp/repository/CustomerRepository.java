
package de.xdevsoftware.jconwebapp.repository;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import de.xdevsoftware.jconwebapp.client.RestClient;
import de.xdevsoftware.jconwebapp.dto.CustomerCreateDTO;
import de.xdevsoftware.jconwebapp.dto.CustomerDTO;
import de.xdevsoftware.jconwebapp.dto.CustomerUpdateDTO;
import de.xdevsoftware.jconwebapp.model.Customer;
import de.xdevsoftware.jconwebapp.util.ItemMapper;


public class CustomerRepository
{
	public static List<Customer> findAll()
	{
		final Response response = RestClient.getWebTarget()
			.path("customer")
			.request()
			.get(Response.class);

		if(response.getStatus() == 200)
		{
			final String json = response.readEntity(String.class);
			final Type   type = TypeToken.getParameterized(ArrayList.class, CustomerDTO.class).getType();

			final ArrayList<CustomerDTO> customers = new Gson().fromJson(json, type);
			return ItemMapper.fromList(customers, Customer.class);
		}
		
		return Collections.emptyList();
	}

	public static Customer findById(final String id)
	{
		final Response response = RestClient.getWebTarget()
			.path("customer")
			.path(id)
			.request()
			.get();
		
		if(response.getStatus() == 200)
		{
			final String      json        = response.readEntity(String.class);
			final CustomerDTO customerDTO = new Gson().fromJson(json, CustomerDTO.class);
			
			return ItemMapper.fromItem(customerDTO, Customer.class);
		}

		return null;
	}
	
	public static boolean save(final CustomerCreateDTO customerDTO)
	{
		final Response response = RestClient.getWebTarget()
			.path("customer")
			.request()
			.post(Entity.entity(customerDTO, MediaType.APPLICATION_JSON));
		
		if(response.getStatus() == 201)
		{
			return true;
		}

		return false;
	}

	public static boolean update(final CustomerUpdateDTO customerDTO)
	{
		final Response response = RestClient.getWebTarget()
			.path("customer")
			.request()
			.put(Entity.entity(customerDTO, MediaType.APPLICATION_JSON));

		if(response.getStatus() == 200)
		{
			return true;
		}

		return false;
	}
}

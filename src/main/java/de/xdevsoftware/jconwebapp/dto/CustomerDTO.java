
package de.xdevsoftware.jconwebapp.dto;

import com.fasterxml.jackson.annotation.JsonProperty;


public class CustomerDTO
{
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("firstname")
	private String firstname;
	
	@JsonProperty("lastname")
	private String lastname;
	
	@JsonProperty("email")
	private String email;
	
	public String getId()
	{
		return this.id;
	}
	
	public void setId(final String id)
	{
		this.id = id;
	}
	
	public String getFirstname()
	{
		return this.firstname;
	}
	
	public void setFirstname(final String firstname)
	{
		this.firstname = firstname;
	}
	
	public String getLastname()
	{
		return this.lastname;
	}
	
	public void setLastname(final String lastname)
	{
		this.lastname = lastname;
	}
	
	public String getEmail()
	{
		return this.email;
	}
	
	public void setEmail(final String email)
	{
		this.email = email;
	}
	
}

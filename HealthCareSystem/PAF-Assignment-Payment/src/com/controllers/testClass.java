package com.controllers;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType; 

@Path("/HCHome") 
public class testClass {
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String hello() {
		return "Welcome to the Health Care System"; 
	}
	
}

package com.ricardo.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ricardo.models.Customers;

@Path("/customers")
public class Customerservice {
	
	
private static final List<Customers> clientes = new ArrayList<Customers>();
	
	static {
		clientes.add(new Customers(1,"Federico","52872","pepe@grr.la"));
		clientes.add(new Customers(2,"Barbarroja","52872","pepe@grr.la"));
		clientes.add(new Customers(3,"Gundario","52872","pepe@grr.la"));
		clientes.add(new Customers(4,"Carlo Magno","52872","pepe@grr.la"));
		clientes.add(new Customers(5,"Pipino el breve","52872","pepe@grr.la"));
	}
	
	@GET
	@Produces("application/json")
	public List<Customers> getCustomers(){
		return this.clientes;
	}
	
	
	
	@Path("/{cid}")
	@GET
	@Produces("application/json")
	public Customers getPedido(@PathParam("cid") int cid) {
		
		Customers Clienteret=null;
		
		for(Customers clientes1: clientes) {
			
			if(clientes1.getCid()==cid) {
				
				Clienteret= clientes1;
				break;
			}
		}
		return Clienteret;
		
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean addCliente(Customers clientenuevo) {
		clientenuevo.setCid(clientes.size() + 1);
		clientes.add(clientenuevo);
		return true;

	}
	
	

	@Path("/{cid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public boolean deletecliente(@PathParam("cid") int cid) {
		boolean ok = false;

		for (Customers cliente1 : clientes) {

			if (cliente1.getCid() == cid) {
				ok = true;
				clientes.remove(cliente1);
				break;
			}
		}

		return ok;

	}
	
	@Path("/{cid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public boolean actualizacliente(@PathParam("cid") int cid, Customers uncliente) {
boolean ok =false;
		for (Customers cliente1 : clientes) {

			if (cliente1.getCid() == cid) {
				clientes.remove(cliente1);
				clientes.add(uncliente);
				 ok=true;
				break;
				
			
				
				
			}
			
			
		}
		return ok;
	}
		
}

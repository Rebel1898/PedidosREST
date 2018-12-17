package com.ricardo.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.ricardo.models.Pedido;
import com.ricardo.models.StatusMessage;
import com.ricardo.models.Usuario;
import com.ricardo.services.JSONService;

@Path("/pedidos")
public class PedidosService extends JSONService {

	private static final List<Pedido> pedidos = new ArrayList<Pedido>();

	static {
		pedidos.add(new Pedido(1, "Producto 1", 23));
		pedidos.add(new Pedido(2, "Producto 2", 24));
		pedidos.add(new Pedido(3, "Producto 3", 25));
		pedidos.add(new Pedido(4, "Producto 4", 26));
		pedidos.add(new Pedido(5, "Producto 5", 27));
	}

	private static List<Usuario> misUsuarios;

	static {
		misUsuarios = new ArrayList<Usuario>();

		misUsuarios.add(new Usuario(1, "Ric", "AG", "r@e.es"));
		misUsuarios.add(new Usuario(2, "Juana", "Juanasson", "j@e.es"));
		misUsuarios.add(new Usuario(3, "Pepa", "Pepasson", "p@e.es"));
	}

	@GET
	@Produces("application/json")
	 //List<Pedido>
	public Response getPedido(@HeaderParam("token") String token) {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;
	
		if (userEmail == null) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
			mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}else { mResponse=Response.status(200).entity(pedidos).build();
		
	

		}
		
	return mResponse;
	}

	@Path("/{pid}")
	@GET
	@Produces("application/json")

	public Response getPedido(@PathParam("pid") int pid, @HeaderParam("token") String token) {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;
		Response resp= null;

		if(userEmail!=null)
		{

		Pedido pedidoRet = null;

		for (Pedido pedido : pedidos) {

			if (pedido.getPid() == pid) {

				pedidoRet = pedido;
				break;
			}
		}
		
		
		if(pedidoRet==null)
		{	resp=Response.status(404).entity(new StatusMessage(404,"El pedido no existe")).build();}

	
	else {resp=Response.status(200).entity(pedidoRet).build();}
		return resp;
		}
		
		
		
		else {resp=Response.status(403).entity(new StatusMessage(403,"Necesitas permisos")).build();
	}
		return resp;}

	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPedido(Pedido pedidoNuevo,@HeaderParam("token") String token) {
		Response resp=null;
		if(token!=null) {
		pedidoNuevo.setPid(pedidos.size() + 1);
		pedidos.add(pedidoNuevo);
	    System.out.println(token);
		
		if(!(pedidoNuevo instanceof Pedido))
		{	resp=Response.status(404).entity(new StatusMessage(404,"El pedido no existe")).build();}

	
		else {resp=Response.status(200).entity(new StatusMessage(200,"Pedido creado"+pedidoNuevo)).build();}
		return resp;
		}
		else {
			resp=Response.status(403).entity(new StatusMessage(403,"Necesitas permisos")).build();	
		}
		return resp;

		}
		

		
		
		
		
		
	

	@Path("/{pid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public boolean deletepedido(@PathParam("pid") int pid) {
		boolean ok = false;

		for (Pedido pedido : pedidos) {

			if (pedido.getPid() == pid) {
				ok = true;
				pedidos.remove(pedido);
				break;
			}
		}

		return ok;

	}
	
	
	
	@Path("/{pid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)

	public boolean actualizapedido(@PathParam("pid") int pid, Pedido unpedido) {
		boolean ok = false;

		for (Pedido pedido : pedidos) {

			if (pedido.getPid() == pid) {
				ok = true;
				pedidos.remove(pedido);
				pedidos.add(unpedido);

				break;
			}
		}

		return ok;

	}
	
	
	
	
	
	
	

}

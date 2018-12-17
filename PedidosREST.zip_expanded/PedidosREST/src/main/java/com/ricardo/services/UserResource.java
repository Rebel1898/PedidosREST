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

import com.ricardo.models.StatusMessage;
import com.ricardo.models.Usuario;



@Path("/usuarios")
public class UserResource extends JSONService{
	private static List<Usuario> misUsuarios;

	static {
		misUsuarios = new ArrayList<Usuario>();

		misUsuarios.add(new Usuario(1, "Ric", "AG", "r@e.es"));
		misUsuarios.add(new Usuario(2, "Juana", "Juanasson", "j@e.es"));
		misUsuarios.add(new Usuario(3, "Pepa", "Pepasson", "p@e.es"));
	}

	// -------------------------------------------------------

	/* GET|POST /usuarios */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuarioList(@HeaderParam("token") String token) {
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;

		if (userEmail == null) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
			mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}else  mResponse=Response.status(200).entity(misUsuarios).build();
		
		return mResponse;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response insertUsuario(Usuario nuevoUsuario,@HeaderParam("token") String token) {
		
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;

		if (userEmail == null) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
			mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}else  {
			this.misUsuarios.add(nuevoUsuario);
			StatusMessage statusMessage = new StatusMessage(Status.CREATED.getStatusCode(),"Usuario Añadido!!!");
			mResponse=Response.status(200).entity(statusMessage).build();
		}
		
		return mResponse;
	}

	/* GET|PUT|DELETE /usuarios/{uid} */
	@Path("/{uid}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsuario(@PathParam("uid") int uid,@HeaderParam("token") String token) {
		
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;

		if (userEmail == null) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
			mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}else  {
			Usuario unUsuario = new Usuario();
			for (Usuario user : misUsuarios) {
				if(user.getUid()==uid){
					unUsuario=user;
					break;
				}
			}
			mResponse=Response.status(200).entity(unUsuario).build();
		}
		
		return mResponse;
	}
	
	@Path("/{uid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUsuario(@PathParam("uid") int uid,Usuario aUsuario,@HeaderParam("token") String token) {
		
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;

		if (userEmail == null) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
			mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}else  {
			for (Usuario user : misUsuarios) {
				if(user.getUid()==uid){
					misUsuarios.remove(user);
					misUsuarios.add(aUsuario);
					break;
				}
			}
			StatusMessage statusMessage = new StatusMessage(Status.CREATED.getStatusCode(),"Usuario modificado!!!");
			mResponse=Response.status(200).entity(statusMessage).build();
		}
		
		return mResponse;
	}
	
	@Path("/{uid}")
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUsuario(@PathParam("uid") int uid,@HeaderParam("token") String token) {
		
		String userEmail = this.getUserEmailFromToken(token);
		Response mResponse=null;

		if (userEmail == null) {
			StatusMessage statusMessage = new StatusMessage(Status.FORBIDDEN.getStatusCode(),"Access Denied for this functionality !!!");
			mResponse=Response.status(Status.FORBIDDEN.getStatusCode()).entity(statusMessage).build();
		}else  {
			for (Usuario user : misUsuarios) {
				if(user.getUid()==uid){
					misUsuarios.remove(user);
					break;
				}
			}
			StatusMessage statusMessage = new StatusMessage(Status.CREATED.getStatusCode(),"Usuario borrado!!!");
			mResponse=Response.status(200).entity(statusMessage).build();
		}
		
		return mResponse;
		
	}
	

}

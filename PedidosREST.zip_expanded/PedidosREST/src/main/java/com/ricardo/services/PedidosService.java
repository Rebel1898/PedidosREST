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
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import com.ricardo.models.Pedido;
import com.ricardo.models.StatusMessage;
import com.ricardo.persistence.PedidosManager;

@Path("/pedidos")
public class PedidosService {

	private static final List<Pedido> pedidos = new ArrayList<Pedido>();

	static {
		pedidos.add(new Pedido(1, "Producto 1", 23));
		pedidos.add(new Pedido(2, "Producto 2", 24));
		pedidos.add(new Pedido(3, "Producto 3", 25));
		pedidos.add(new Pedido(4, "Producto 4", 26));
		pedidos.add(new Pedido(5, "Producto 5", 27));
	}

	@GET
	@Produces("application/json")
	public Response getPedidos() {

		PedidosManager pm = PedidosManager.getInstance();

		return Response.status(200).entity(pm.getPedidos()).build();
	}

	@Path("/{id}")
	@GET
	@Produces("application/json")
	public Response getPedido(@PathParam("id") int pid) {
		Response resp = null;
		Pedido pedidoRet = null;

		for (Pedido pedido : pedidos) {
			if (pedido.getPid() == pid) {
				pedidoRet = pedido;
				break;
			}
		}

		if (pedidoRet == null) {
			resp = Response.status(404).entity(new StatusMessage(404, "El pedido no existe")).build();
		} else {
			resp = Response.status(200).entity(pedidoRet).build();
		}

		return resp;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addPedido(Pedido pedidoNuevo) {
		Response resp = null;

		if (pedidoNuevo.validate()) {
			pedidoNuevo.setPid(pedidos.size() + 1);
			pedidos.add(pedidoNuevo);
			resp = Response.status(200).entity(pedidoNuevo).build();
		} else {
			resp = Response.status(400).entity(new StatusMessage(400, "Pedido incompleto")).build();
		}

		return resp;
	}
	
	

	
	
	@Path("/{id}")
	@DELETE
	@Produces("application/json")
	public ResponseBuilder deletePedido(@PathParam("id") int pid) {
		PedidosManager pm = PedidosManager.getInstance();
		if(pid>0) {
			try {
		return Response.status(200).entity(PedidosManager.getInstance().deletepedido(pid));
			}
		catch(Exception e) {System.out.println("Todo mal");}
		}
		return Response.status(200).entity(new StatusMessage(404,"No existe"));

	}

	@Path("/{id}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public boolean actualizarPedido(@PathParam("id") int pid, Pedido unPedido) {
		boolean OK = false;

		for (Pedido pedido : pedidos) {
			if (pedido.getPid() == pid) {
				pedidos.remove(pedido);
				pedidos.add(unPedido);
				OK = true;
				break;
			}
		}

		return OK;
	}

}

package com.ricardo.persistence;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.ricardo.models.Pedido;

public class PedidosManager {

	private static PedidosManager instance = null;

	private static SessionFactory sfactory;

	public static PedidosManager getInstance() {
		if (instance == null) instance = new PedidosManager();
		return instance;
	}

	private PedidosManager() {
		sfactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
	}

	public List<Pedido> getPedidos() {
		Session sess = sfactory.openSession();

		List<Pedido> listaPedidos = sess.createQuery("from Pedido").list();

		sess.close();
		return listaPedidos;
		
		
		

		
		
		
	}

	public boolean deletepedido(int pid) {
		
		
		
		boolean ok=false;
		System.out.println(pid);
		Session sess = sfactory.openSession();
		Transaction tx= sess.beginTransaction();
		
		sess.delete(sess.find(Pedido.class,pid));
		tx.commit();
		
		ok=true;
		
		sess.close();

		return ok;
	}

}

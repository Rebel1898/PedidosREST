package com.ricardo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pedido")
public class Pedido {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pid;
	
	@Column
	private String descripcion;
	
	@Column
	private double monto;
	
	public Pedido() {
		
	}

	public Pedido(int pid, String descripcion, double monto) {
		super();
		this.pid = pid;
		this.descripcion = descripcion;
		this.monto = monto;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public boolean validate() {
		boolean isValid=true;
		
		if(this.descripcion==null || this.descripcion.equals("")) isValid=false;
		if(this.monto<=0) isValid=false;
		
		return isValid;
	}

}

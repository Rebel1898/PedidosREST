package com.ricardo.models;

public class Customers {
	private int cid;
	private String name;
	private String dni;
	private String email;
	
	public Customers() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCid() {
		return cid;
	}
	public void setCid(int cid) {
		this.cid = cid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Customers(int cid, String name, String dni, String email) {
		super();
		this.cid = cid;
		this.name = name;
		this.dni = dni;
		this.email = email;
	}
	

	
	
	
	
}

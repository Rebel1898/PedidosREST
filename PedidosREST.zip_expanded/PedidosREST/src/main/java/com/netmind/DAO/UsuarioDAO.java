package com.netmind.DAO;

import com.ricardo.models.Usuario;

public abstract class UsuarioDAO extends DAO {
	public abstract Usuario getUsuario(String username, String password) throws Exception;
	public abstract Usuario getUsuarioByMail(String email) throws Exception;
}

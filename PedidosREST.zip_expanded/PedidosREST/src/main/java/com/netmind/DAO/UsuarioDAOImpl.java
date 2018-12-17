package com.netmind.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import com.ricardo.models.Usuario;

public class UsuarioDAOImpl extends UsuarioDAO{
	
	private static UsuarioDAOImpl instance = null;
	
	public static UsuarioDAOImpl getInstance() throws Exception {
	      if(instance == null) {
	         instance = new UsuarioDAOImpl();
	      }
	      return instance;
	}

	@Override
	public Usuario getUsuario(String username, String password) throws Exception {
		Usuario aUser=null;
		
		Connection conn = dataSource.getConnection(); // pool de Tomcat
		
		PreparedStatement pstmt = null;
		String selectSQL = "SELECT * FROM usuario WHERE email=? AND pass=?";
		pstmt  = conn.prepareStatement(selectSQL);
		pstmt.setString(1, username);
		pstmt.setString(2, password);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			aUser = new Usuario();
			aUser.setUid(rs.getInt("uid"));
			aUser.setNombre(rs.getString("nombre"));
			aUser.setApellido(rs.getString("apellido"));
			aUser.setEmail(rs.getString("email"));
			
			Date stamp = rs.getTimestamp("creationdate");
			aUser.setFechaCreacion(stamp);
			
			aUser.setStatus(rs.getInt("status"));
			aUser.setEmpresa(rs.getString("empresa"));
			aUser.setNif(rs.getString("NIF"));
			aUser.setPais(rs.getString("pais"));
			break ;
		}

		pstmt.close();
		conn.close();

		return aUser;

	}

	@Override
	public Usuario getUsuarioByMail(String email) throws Exception {
		Usuario aUser=null;
		
		Connection conn = dataSource.getConnection(); // pool de Tomcat
		
		PreparedStatement pstmt = null;
		String selectSQL = "SELECT * FROM usuario WHERE email=?";
		pstmt  = conn.prepareStatement(selectSQL);
		pstmt.setString(1, email);
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
			aUser = new Usuario();
			aUser.setUid(rs.getInt("uid"));
			aUser.setNombre(rs.getString("nombre"));
			aUser.setApellido(rs.getString("apellido"));
			aUser.setEmail(rs.getString("email"));
			
			Date stamp = rs.getTimestamp("creationdate");
			aUser.setFechaCreacion(stamp);
			
			aUser.setStatus(rs.getInt("status"));
			aUser.setEmpresa(rs.getString("empresa"));
			aUser.setNif(rs.getString("NIF"));
			aUser.setPais(rs.getString("pais"));
			break ;
		}

		pstmt.close();
		conn.close();

		return aUser;
	}
	
	

}
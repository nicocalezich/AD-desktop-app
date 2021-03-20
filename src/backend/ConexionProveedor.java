package backend;

import java.sql.SQLException;
import java.sql.Statement;

public class ConexionProveedor extends Conexion{
	
	public void agregarProveedor(String nombre, String productos, String telefono, String direccion, String correo) throws SQLException {
		
		crearConexion();
		
		String query = "INSERT INTO proveedores (nombre,productos,telefono,direccion,correo) values('"+nombre+"','"+productos+"','"+telefono+"','"+direccion+"','"+correo+"')";
		
		Statement stmt = (Statement) con.createStatement();	
		
		stmt.executeUpdate(query);	
		
		con.close();	
	}
	
	public void eliminarProveedor(String ID) throws SQLException {
		
		crearConexion();
		
		String query = "DELETE FROM proveedores WHERE ID='"+ID+"'";
		
		Statement stmt = (Statement) con.createStatement();	
		
		stmt.executeUpdate(query);	
		
		con.close();	
		
	}
	
	public void editarProveedor(String ID, String campo, String editado) throws SQLException {
		
		crearConexion();
		
		String query = "UPDATE `proveedores` SET `"+campo+"`='"+editado+"' WHERE ID='"+ID+"'";
		
		Statement stmt = (Statement) con.createStatement();	
		
		stmt.executeUpdate(query);	
		
		con.close();	
		
	}
	
	

}

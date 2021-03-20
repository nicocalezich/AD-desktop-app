package backend;

import java.sql.SQLException;
import java.sql.Statement;

public class ConexionCliente extends Conexion{
	
	public void agregarCliente(String nombre, String telefono, String correo, String direccion, String localidad) throws SQLException {
		
		crearConexion();		
		
		String query = "INSERT INTO clientes (nombre,telefono,correo,direccion,localidad)" + " values('"+nombre+"','"+telefono+"','"+correo+"','"+direccion+"','"+localidad+"')";
		
		Statement stmt = (Statement) con.createStatement();	
		
		stmt.executeUpdate(query);	
		
		con.close();	
	}
	
	public void eliminarCliente(String ID) throws ClassNotFoundException, SQLException {
		
		crearConexion();		
			
		String query = "DELETE FROM clientes WHERE ID='"+ID+"'";
				
		Statement stmt = (Statement) con.createStatement();	
		
		stmt.executeUpdate(query);	
		
		con.close();	
		
	}
	
	public void editarCliente(String ID, String campo, String editado) throws ClassNotFoundException, SQLException {	
		
		crearConexion();		
			
		String query = "UPDATE `clientes` SET `"+campo+"`='"+editado+"' WHERE ID='"+ID+"'";
					
		Statement stmt = (Statement) con.createStatement();	
		
		stmt.executeUpdate(query);	
		
		con.close();	
	}

}

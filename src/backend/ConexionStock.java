package backend;

import java.sql.SQLException;
import java.sql.Statement;

public class ConexionStock extends Conexion {
	
	public void agregarItemStock(String producto,double cantidad, double precio) throws SQLException {
		
		crearConexion();
		
		String query = "INSERT INTO stock (producto,cantidad,Precio) values('"+producto+"','"+cantidad+"','"+precio+"')";
		
		Statement stmt = (Statement) con.createStatement();	
		
		stmt.executeUpdate(query);	
		
		con.close();	
	}
	
	public void eliminarItemStock(String id) throws SQLException {
		
		crearConexion();			
	
		String query = "DELETE FROM stock WHERE ID='"+id+"'";

		java.sql.Statement stm = con.createStatement();

		stm.executeUpdate(query);

		con.close();
	}
	
	public void actualizarItemStock(String id,String nuevoProducto) throws ClassNotFoundException, SQLException {
		
		crearConexion();			
		
		String query = "UPDATE `stock` SET `producto`='"+nuevoProducto+"' WHERE ID='"+id+"'";
				
		java.sql.Statement stm = con.createStatement();
									
		stm.executeUpdate(query);	
		
		con.close();	
	}
	
	public void actualizaraCantidadStock(String id, double nuevaCantidad) throws SQLException {
		
		crearConexion();				

		String query = "UPDATE `stock` SET `cantidad`='"+nuevaCantidad+"' WHERE ID='"+id+"'";
		
		java.sql.Statement stm = con.createStatement();
							
		stm.executeUpdate(query);
		
		con.close();	
		
	}
	
	public void actualizaraPrecioStock(String id, double nuevoPrecio) throws SQLException {
			
			crearConexion();				
	
			String query = "UPDATE `stock` SET `Precio`='"+nuevoPrecio+"' WHERE ID='"+id+"'";
			
			java.sql.Statement stm = con.createStatement();
								
			stm.executeUpdate(query);
			
			con.close();	
			
		}	
	
	public void restarStock(String id, double dif) throws ClassNotFoundException, SQLException {
		
		crearConexion();			
		
		String query = "UPDATE `stock` SET `cantidad`='"+dif+"' WHERE ID = '"+id+"'";
				
		java.sql.Statement stm = con.createStatement();
									
		stm.executeUpdate(query);	
		
		con.close();
		
	}

}

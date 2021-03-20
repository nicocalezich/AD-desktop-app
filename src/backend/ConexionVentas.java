package backend;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ConexionVentas extends Conexion {
	
	public void guardarVenta(String producto, double cantidad) throws ClassNotFoundException, SQLException {		
		
		crearConexion();
		
		String query = "INSERT INTO ventas (IDventas,producto,cantidad) values('"+traerID()+"','"+producto+"','"+cantidad+"')";
		
		Statement stmt = (Statement) con.createStatement();	
		
		stmt.executeUpdate(query);	
		
		con.close();
		
	}
	
	public void registrar(String fecha, double ganancia, String nombre, int estaPago, int estaEntregado, int pedidoCerrado) throws SQLException {
		
		crearConexion();
		
		String query = "INSERT INTO registro (fecha,ganancia,cliente,estaPago,estaEntregado,pedidoCerrado) VALUES ('"+fecha+"','"+ganancia+"','"+nombre+"','"+estaPago+"','"+estaEntregado+"','"+pedidoCerrado+"')";
		
		Statement stmt = (Statement) con.createStatement();	
		
		stmt.executeUpdate(query);	
		
		con.close();
		
	}
	
	public String traerID() throws SQLException, ClassNotFoundException {
				
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
		
		String query = "SELECT MAX(IDventa) AS IDventa FROM registro";
		
		java.sql.Statement st;	
		
		String dato = null;
		
		try {
			
			st = conexion.createStatement();
			
			ResultSet result = st.executeQuery(query);
			
			while (result.next()) {
			
				dato = result.getString(1);				
					
			}					
		}
		
		catch (Exception e) {			
			e.printStackTrace();		
		}		
					
		return dato;
				
	}
	
	public void eliminarVenta(String id) throws SQLException {
		
		crearConexion();			
		
		String query = "DELETE FROM registro WHERE IDventa='"+id+"'";

		java.sql.Statement stm = con.createStatement();

		stm.executeUpdate(query);
		
		eliminarProductosVenta(id);

		con.close();
			
	}
	
	private void eliminarProductosVenta(String id) throws SQLException {
		
		crearConexion();			
		
		String query = "DELETE FROM ventas WHERE IDventas='"+id+"'";

		java.sql.Statement stm = con.createStatement();

		stm.executeUpdate(query);

		con.close();
	}
	
	public String[] fechasVentas() throws ClassNotFoundException, SQLException {
			
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
		
		String query = "SELECT DISTINCT fecha FROM registro WHERE pedidoCerrado = 1";
		
		java.sql.Statement st;
		
		ArrayList<String> fechas1 = new ArrayList<String>();

		try {
			
			st = conexion.createStatement();
			
			ResultSet result = st.executeQuery(query);
			
			while (result.next()) {
			
				fechas1.add(result.getString("fecha"));				
				
			}					
		}
		
		catch (Exception e) {			
			e.printStackTrace();		
		}		
	
		String fechas [] = fechas1.toArray(new String [fechas1.size()]);
					
		return fechas;
				
		}
	
	public String[] ventasPorFecha(String fecha) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
		
		String query = "SELECT DISTINCT cliente FROM registro WHERE ganancia='"+fecha+"' AND pedidoCerrado = 1";
		
		java.sql.Statement st;
		
		ArrayList<String> ventas1 = new ArrayList<String>();

		try {
			
			st = conexion.createStatement();
			
			ResultSet result = st.executeQuery(query);
			
			while (result.next()) {
			
				ventas1.add(result.getString("cliente"));							
			}					
		}
		
		catch (Exception e) {			
			e.printStackTrace();		
		}		
	
		String ventas [] = ventas1.toArray(new String [ventas1.size()]);
					
		return ventas;
						
	}
	
	public static ArrayList<String> traerProductosPorID(String ID) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
		
		String sql = "SELECT ventas.producto FROM ventas "
				   + "INNER JOIN registro on registro.IDventa = ventas.IDventas "
				   + "WHERE registro.IDventa = "+ID+"";
		
		java.sql.Statement st;	
		
		
		ArrayList<String> productos = new ArrayList<>();
		
		try {
			
			st = conexion.createStatement();
			
			ResultSet result = st.executeQuery(sql);
			
			while (result.next()) {
					
				productos.add(result.getString("producto"));		
				
			}						
		}
			
		catch (Exception e) {			
			e.printStackTrace();		
		}
		
		return productos;								
	}
	
	public String[] nombreClientes() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
		
		String sql = "SELECT DISTINCT nombre FROM clientes";
		
		java.sql.Statement st;	
				
		ArrayList<String> clientesAL = new ArrayList<>();
		
		try {
			
			st = conexion.createStatement();
			
			ResultSet result = st.executeQuery(sql);
			
			while (result.next()) {
					
				clientesAL.add(result.getString("nombre"));		
				
			}						
		}
			
		catch (Exception e) {			
			e.printStackTrace();		
		}
		
		String clientes [] = clientesAL.toArray(new String [clientesAL.size()]);
		
		return clientes;	
		
	}
	
	public void cerrarPedido(String ID) throws SQLException {
		
		crearConexion();	
		
		int uno = 1;
		
		String query = "UPDATE registro SET pedidoCerrado = '"+uno+"' WHERE IDventa='"+ID+"'";
				
		java.sql.Statement stm = con.createStatement();
									
		stm.executeUpdate(query);	
		
		con.close();	
		
	}
	
	public void actualizarEstadoPagoPedido(String ID) throws SQLException {
		
		crearConexion();	
		
		int uno = 1;
		
		String query = "UPDATE registro SET estaPago = '"+uno+"' WHERE IDventa='"+ID+"'";
		
		java.sql.Statement stm = con.createStatement();
									
		stm.executeUpdate(query);	
		
		con.close();
	
	}
	
	public void actualizarEstadoEntregadoPedido(String ID) throws SQLException {
		
		crearConexion();	
		
		int uno = 1;
		
		String query = "UPDATE registro SET estaEntregado = '"+uno+"' WHERE IDventa='"+ID+"'";
		
		java.sql.Statement stm = con.createStatement();
									
		stm.executeUpdate(query);	
		
		con.close();
	
	}
	
	public boolean hayPedidosPendientes() throws SQLException, ClassNotFoundException {
		
		boolean hayPedidos = true;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
		
		String sql = "SELECT COUNT(IDventa) as IDventa FROM registro WHERE pedidoCerrado = 0";
			
		java.sql.Statement st;			
				
		String dato = null;
					
		st = conexion.createStatement();
				
		ResultSet result = st.executeQuery(sql);
				
		try {
			while (result.next()) {
				dato = result.getString("IDventa");		
				}
			} catch (SQLException e) {		
				e.printStackTrace();
			}		
		
		if (dato.equals("0")) {
			hayPedidos = false;			
		}
		
		return hayPedidos;
				
	}
	
	public boolean hayVentas(String fecha) throws SQLException, ClassNotFoundException {
		
		boolean hayPedidos = true;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
		
		String sql = "SELECT COUNT(IDventa) as IDventa FROM registro WHERE fecha = '"+fecha+"' AND pedidoCerrado = 1";
			
		java.sql.Statement st;			
				
		String dato = null;
					
		st = conexion.createStatement();
				
		ResultSet result = st.executeQuery(sql);
				
		try {
			while (result.next()) {
				dato = result.getString("IDventa");		
				}
			} catch (SQLException e) {		
				e.printStackTrace();
			}		
		
		if (dato.equals("0")) {
			hayPedidos = false;			
		}
		
		return hayPedidos;
				
	}
	
	

}

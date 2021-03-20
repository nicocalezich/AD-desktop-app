package backend;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConexionContrasenia extends Conexion {
	
	public String traerContra() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
		
		String sql = "SELECT * FROM userdata";
		
		java.sql.Statement st;	
		
		String dato = null;
		
		try {
			
			st = conexion.createStatement();
			
			ResultSet result = st.executeQuery(sql);
			
			while (result.next()) {
			
				dato = result.getString(1);				
					
			}					
		}
		
		catch (Exception e) {			
			e.printStackTrace();		
		}		
					
		return dato;
	}
	
	public void actualizarContra(String nuevaContra) throws ClassNotFoundException, SQLException {
		
		crearConexion();
		
		String query = "UPDATE userdata SET password = '"+nuevaContra+"'";
			
		java.sql.Statement stm = con.createStatement();
							
		stm.executeUpdate(query);
		
		con.close();
		
	}

}

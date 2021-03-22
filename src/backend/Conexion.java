package backend;

import java.sql.DriverManager;
import javax.swing.JOptionPane;


public class Conexion {
	
	protected String clase = "com.mysql.jdbc.Driver";
	protected String conexion = "jdbc:mysql://localhost:3306/admanagments";
	protected java.sql.Connection con;
	
	protected void crearConexion() {
		
		try {	
			
			Class.forName(clase);		
			con = DriverManager.getConnection(conexion,"root","");
		}
		
		catch (Exception e2) {
			JOptionPane.showMessageDialog(null,"Error al conectar a la base de datos","Error",JOptionPane.ERROR_MESSAGE);
			e2.printStackTrace();
		}		
	}
	
				
}


	
	
			


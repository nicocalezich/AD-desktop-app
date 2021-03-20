package backend;

import java.sql.SQLException;

public class Logeo {
	
	public boolean validarContra(String pass) throws ClassNotFoundException, SQLException{
		
		ConexionContrasenia conexion = new ConexionContrasenia();
				
		boolean contraseniaCorrecta = false;		
		
			if (pass.equals(conexion.traerContra())) {
				
				contraseniaCorrecta = true;			
			}
								
			return contraseniaCorrecta;	
	}
		
}

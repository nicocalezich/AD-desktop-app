package backend;

import java.sql.SQLException;

public class CambioContrasenia {
	
	private ConexionContrasenia conexion = new ConexionContrasenia();
	
	public boolean contraActualValida(String contraActual) throws ClassNotFoundException, SQLException {
			
		return contraActual.equals(conexion.traerContra());
			
	}
	
	public boolean nuevaContraValida(String nuevaContra) {
		
		return ((nuevaContra.length() > 5) && (!nuevaContra.isEmpty()) && (!nuevaContra.equals(null)));	
		
	}
	
	public boolean nuevasContrasCoinciden(String nuevaContra, String confirNuevaContra) {
		
		return nuevaContra.equals(confirNuevaContra);
		
	}
	
	public void actualizarContra(String nuevaContra) throws ClassNotFoundException, SQLException {
		
		conexion.actualizarContra(nuevaContra);
		
	}
	

}

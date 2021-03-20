package backend;

import java.sql.SQLException;

import frontend.Calculo;
import frontend.CambioContra;
import frontend.Clientes;
import frontend.Proveedores;
import frontend.RegistroVentas;
import frontend.Stock;
import frontend.Ventas;

public class MenuBack {
	
	public Stock abrirStock(){
		
		Stock stock = null;				
		
		try {	
			
			stock = new Stock();	
			
		}		
		catch (ClassNotFoundException | SQLException e1) {		
			
			e1.printStackTrace();
		}
		
		return stock;		
	}
	
	public Calculo abrirCalculadora() {
		
		Calculo calculo = new Calculo();	
		
		return calculo;		
	}
	
	public Clientes abrirClientes() {
		
		Clientes clientes = null;
		
		try {
			
			clientes = new Clientes();
			
		}
		catch (ClassNotFoundException | SQLException e1) {
			
			e1.printStackTrace();
		}
		
		return clientes;
	}
	
	public Proveedores abrirProveedores() {
		
		Proveedores proveedores = null;
		
		try {
			
			proveedores = new Proveedores();
			
		} 
		catch (ClassNotFoundException | SQLException e1) {
		
			e1.printStackTrace();
		}
		
		return proveedores;
		
	}
	
	public void abrirCambioContra() {
		
		CambioContra cambioDeContra = new CambioContra();	
		
		cambioDeContra.setVisible(true);	
		
	}
	
	public void abrirNuevaVenta() throws ClassNotFoundException, SQLException {
		
		Ventas ventas = new Ventas();
		
		ventas.setVisible(true);
		
	}
	
	public RegistroVentas abrirRegistroVentas() throws ClassNotFoundException, SQLException {
		
		RegistroVentas registro = new RegistroVentas();
		
		return registro;
	}
	

	
		

}

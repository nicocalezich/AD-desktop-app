package backend;

import java.sql.SQLException;

import frontend.Stock;

public class Venta {
	
	private ConexionStock conexionStock;
	private ConexionVentas conexionVentas;
	private Stock lista;
			
	public Venta() throws ClassNotFoundException, SQLException {
		
		conexionStock = new ConexionStock();	
		conexionVentas = new ConexionVentas();
		lista = new Stock();
	}

	public void nuevaVenta(String id, double cantidadVendida, String producto) throws Exception {
				
		guardarVenta(producto, cantidadVendida);																								
											
		modificarStock(cantidadVendida, lista.cantidadProducto(id), id);	
				
	}
	
	public boolean hayStockDisponible(double cantActual, double cantVendida) {	
		
		return cantActual >= cantVendida;		
	}
	
	private void guardarVenta(String producto, double cantidadVendida) throws SQLException, ClassNotFoundException {
		
		conexionVentas.guardarVenta(producto, cantidadVendida);		
	}
	
	private void modificarStock(double cantidadVendida, double cantidadActual, String id) throws ClassNotFoundException, SQLException {
		
		double dif = cantidadActual - cantidadVendida;				
		
		if (dif != 0) {
			conexionStock.restarStock(id, dif);
		}	
		
		else {			 
			conexionStock.eliminarItemStock(id);			
		 }		
	}
	
	public void registrar(String fecha, double ganancia, String nombre, int estaPago, int estaEntregado) throws SQLException {
		
		int pedidoCerrado = 0;
		
		if (estaPago == 1 && estaEntregado == 1) {
			pedidoCerrado = 1;
		}		
						
		conexionVentas.registrar(fecha, ganancia, nombre, estaPago, estaEntregado, pedidoCerrado);
		
	}
		
}

package backend;

public class CalculoBack {
		
	private double StringToDouble(String n) {
		double x=Double.parseDouble(n);
		return x;
	}
	
	private double gananciaNeta(double porcentaje, double costo) {
		
	return (porcentaje*costo)/100;

	}
	
	private String doubleToString(double n) {
		
		return String.valueOf(n);	
	}
	
	public String calcularGananciaNeta (String costoStr, String porcentajeStr) {
					
		double costo = StringToDouble(costoStr);
		double porcentaje = StringToDouble(porcentajeStr);				
		double gananciaNeta = gananciaNeta(porcentaje,costo);				  
		String g=doubleToString(gananciaNeta);  
			
		return g;
	}
	
	public String calcularPrecioVenta(String costoStr, String porcentajeStr) {
		
		double costo = StringToDouble(costoStr);
		double porcentaje = StringToDouble(porcentajeStr);
		
		//efectua operaciones				
		double vende = gananciaNeta(porcentaje,costo)+costo;	
		//pasa todo a string	
		String v = doubleToString(vende);  
		
		return v;

		
	}
	
}

package frontend;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

public class ProductoNuevo extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField inputProducto;
	private JTextField inputPrecio;
	private JTextField inputCantidad;

	/**
	 * Create the panel.
	 */
	public ProductoNuevo() {
		
		setBackground(Color.LIGHT_GRAY);
		setLayout(null);
		setBounds(0, 0, 1082, 498);	
		
		inputProducto = new JTextField();
		inputProducto.setColumns(10);
		inputProducto.setBounds(83, 76, 166, 20);
		add(inputProducto);
		
		JLabel label = new JLabel("Producto");
		label.setBounds(83, 51, 63, 14);
		add(label);
		
		JLabel label_1 = new JLabel("Tipo");
		label_1.setBounds(83, 112, 63, 14);
		add(label_1);
		
		@SuppressWarnings("rawtypes")
		JComboBox cmbTipo = new JComboBox<>();
		cmbTipo.setBounds(83, 140, 166, 20);
		add(cmbTipo);
		
		inputPrecio = new JTextField();
		inputPrecio.setColumns(10);
		inputPrecio.setBounds(83, 211, 166, 20);
		add(inputPrecio);
		
		JLabel label_2 = new JLabel("Precio por");
		label_2.setBounds(83, 186, 63, 14);
		add(label_2);
		
		@SuppressWarnings("rawtypes")
		JComboBox cmbUnidad = new JComboBox();
		cmbUnidad.setBounds(141, 183, 78, 20);
		add(cmbUnidad);
		
		inputCantidad = new JTextField();
		inputCantidad.setColumns(10);
		inputCantidad.setBounds(83, 288, 166, 20);
		add(inputCantidad);
		
		JLabel label_3 = new JLabel("Cantidad disponible");
		label_3.setBounds(83, 263, 124, 14);
		add(label_3);
		
		JButton btnAgregar = new JButton("Agregar");
		btnAgregar.setBounds(83, 358, 185, 23);
		add(btnAgregar);

	}
}

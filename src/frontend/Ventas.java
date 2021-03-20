package frontend;

import java.awt.BorderLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Color;
import java.awt.Font;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import backend.ConexionCliente;
import backend.ConexionVentas;
import backend.Venta;
import java.awt.ComponentOrientation;
import javax.swing.ListSelectionModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class Ventas extends JDialog {

	
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField txtCantidad;
	private Stock stock;
	private JTable table;
	private Venta venta;
	@SuppressWarnings("rawtypes")
	private JComboBox comboBox;
	private String [] lista;
	private JTextField txtGanancia;
	private ConexionVentas conexion;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbboxClientes;
	private JDateChooser dateChooser;
	private Date date;
	private JButton btnAgregarCliente;
	private ConexionCliente conexionClientes;
	private JCheckBox chckbxNewCheckBox;
	private JCheckBox chckbxNewCheckBox_1;
	
	public static void main(String[] args) {
		try {
			Ventas dialog = new Ventas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Ventas() throws ClassNotFoundException, SQLException {
		setResizable(false);
		setTitle("Nueva venta");
		setBounds(100, 100, 632, 447);
		getContentPane().setLayout(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setLocationRelativeTo(null);
		stock = new Stock();
		lista = stock.productos();
		conexion = new ConexionVentas();
		conexionClientes = new ConexionCliente();
		
		getContentPane().add(contentPanel, BorderLayout.CENTER);
			
			dateChooser = new JDateChooser();
			dateChooser.setDate(date);
			dateChooser.setBounds(73, 277, 127, 20);
			contentPanel.add(dateChooser);
		
			JScrollPane scrollPane = new JScrollPane((Component) null);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
			scrollPane.setBackground(Color.BLACK);
			scrollPane.setBounds(279, 37, 307, 303);
			contentPanel.add(scrollPane);
			
				table = new JTable() {
					
					private static final long serialVersionUID = 1L;

					public boolean isCellEditable(int filas, int columnas) {
						return false;
					}
				};
				
				table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				table.setSelectionBackground(Color.LIGHT_GRAY);
				table.setRowHeight(30);
				table.setForeground(Color.WHITE);
				table.setFont(new Font("Tahoma", Font.PLAIN, 16));
				table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
				table.setBorder(new LineBorder(Color.WHITE));
				table.setBackground(Color.DARK_GRAY);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
				scrollPane.setViewportView(table);
				
				JTableHeader head = table.getTableHeader();
				head.setBackground(Color.black);
				head.setForeground(Color.white);
				head.setFont(new Font ("Corbel",Font.PLAIN,18));
				
				DefaultTableModel model = new DefaultTableModel();
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				
				model.addColumn("ID");
				model.addColumn("Producto");
				model.addColumn("Cantidad");
				
				table.setModel(model);
				table.getColumnModel().getColumn(0).setPreferredWidth(32);
				table.getColumnModel().getColumn(1).setPreferredWidth(190);
				table.getColumnModel().getColumn(2).setPreferredWidth(85);
				
				centerRenderer.setHorizontalAlignment( JLabel.CENTER );
				table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); 
				table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); 
				table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); 
				
							
				comboBox = new JComboBox();		
				comboBox.setModel(new DefaultComboBoxModel(lista));
				comboBox.setBounds(53, 46, 181, 20);
				contentPanel.add(comboBox);	
			
			txtCantidad = new JTextField();
			txtCantidad.setColumns(10);
			txtCantidad.setBounds(53, 91, 183, 20);
			contentPanel.add(txtCantidad);
		
			JButton add = new JButton("");
			add.setFocusable(false);
			add.setToolTipText("Agregar");
			add.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent arg0) {
					
					add.setIcon(new ImageIcon(Ventas.class.getResource("/stock/sign-add-iconBIG.png")));
					
				}
			});
			add.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent arg0) {
				
					try {
						venta = new Venta();
					} catch (ClassNotFoundException | SQLException e) {						
						e.printStackTrace();
					}
																								
					String dato[] = new String [3];						
					String item = comboBox.getSelectedItem().toString();
					String id = stock.idProducto(item);
					String cantVendidaTest = txtCantidad.getText();
					String cantVendida = cantVendidaTest.replace(',','.');
					double cantActual = stock.cantidadProducto(id);	
					
					if (!cantVendida.isEmpty() && esNumerico(cantVendida)) {
																														
							if (venta.hayStockDisponible(cantActual, Double.parseDouble(cantVendida)))	{
																						
								dato[0] = id;
								dato[1] = item;
								dato[2] = cantVendida;
																	
								model.addRow(dato);	
								actualizarLista(item,true);
													
							}
							
							else {
								JOptionPane.showMessageDialog(null, "Cantidad vendida mayor al stock","Error",JOptionPane.ERROR_MESSAGE);
								}						
						}
						
						else {
							JOptionPane.showMessageDialog(null, "Cantidad invalida","Error",JOptionPane.ERROR_MESSAGE);
							}					
					
					
					

					txtCantidad.setText("");						
				}
			});
			
			add.setIcon(new ImageIcon(Ventas.class.getResource("/stock/sign-add-icon.png")));
			add.setOpaque(false);
			add.setForeground(Color.WHITE);
			add.setFont(new Font("Tahoma", Font.BOLD, 20));
			add.setBorder(null);
			add.setBackground(Color.BLACK);
			add.setBounds(63, 122, 71, 61);
			contentPanel.add(add);				
		
			JButton delete = new JButton("");
			delete.setFocusable(false);
			delete.setToolTipText("Eliminar");
			delete.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					
					delete.setIcon(new ImageIcon(Ventas.class.getResource("/stock/deletetestBIG.png")));
					
				}
			});
			delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					int row = table.getSelectedRow();	
										
					if(row >= 0) {	
						
						String item = (String) table.getValueAt(row,1);
						actualizarListaDelete(item);
						model.removeRow(row);																	
							
						}
					
					else {
						JOptionPane.showMessageDialog(null, "Debe seleccionar un registro","Error",JOptionPane.ERROR_MESSAGE);	
					}
				}
			});
			delete.setIcon(new ImageIcon(Ventas.class.getResource("/stock/deletetest.png")));
			delete.setOpaque(false);
			delete.setForeground(Color.WHITE);
			delete.setFont(new Font("Tahoma", Font.BOLD, 20));
			delete.setBorder(null);
			delete.setBackground(Color.BLACK);
			delete.setBounds(153, 122, 71, 61);
			contentPanel.add(delete);
			
			JButton btnConfirmar = new JButton("Confirmar venta");
			btnConfirmar.setFocusable(false);
			btnConfirmar.setForeground(Color.BLACK);
			btnConfirmar.setBackground(Color.WHITE);
			btnConfirmar.setBorder(new LineBorder(null));
			btnConfirmar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0){
										
					int filas = table.getRowCount();						
																																																	
					if (filas > 0) {		
												                						
						String gananciaTotal = txtGanancia.getText().replace(',','.');											
						
						if (!gananciaTotal.isEmpty() && esNumerico(gananciaTotal)) {
										
																														
							double gananciaTotDBL = Double.parseDouble(gananciaTotal);	
																												
							int dia = dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH);
							int mes = dateChooser.getCalendar().get(Calendar.MARCH)+1;
							int ano = dateChooser.getCalendar().get(Calendar.YEAR);
							
							int estaPago = booleanToInt(chckbxNewCheckBox.isSelected());
							int estaEntregado = booleanToInt(chckbxNewCheckBox_1.isSelected());		
							
							String estado = estadoDelPedido(chckbxNewCheckBox.isSelected(),chckbxNewCheckBox_1.isSelected());
																																										
							String fecha = (dia+"/"+mes+"/"+ano);
							String cliente = cmbboxClientes.getSelectedItem().toString();
						
							try {
								venta.registrar(fecha, gananciaTotDBL, cliente, estaPago, estaEntregado);
							} catch (SQLException e1) {
								
								e1.printStackTrace();
							}							
																																							
							for (int i = 0; i < filas; i++) {
								
								String id = (String) table.getValueAt(i,0);
								String producto = (String) table.getValueAt(i,1);
								String cantidad = (String) table.getValueAt(i,2);															
								double cantidadDBL = Double.parseDouble(cantidad);													
								
								try {	
															
									venta.nuevaVenta(id, cantidadDBL, producto);								
									
								} catch (Exception e) {
	
									JOptionPane.showMessageDialog(null,"No se pudo realizar la venta","Error",JOptionPane.ERROR_MESSAGE);	
								}							
							}
						
								JOptionPane.showMessageDialog(null,"Venta realizada. Estado del pedido: "+estado,"Nueva venta",JOptionPane.INFORMATION_MESSAGE);						
								setVisible(false);												
						}
					
					else {
						JOptionPane.showMessageDialog(null,"Los campos estan incompletos o tienen errores","Error",JOptionPane.ERROR_MESSAGE);
						}										
						
					}
					else {
						JOptionPane.showMessageDialog(null,"No hay productos seleccionados","Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			btnConfirmar.setBounds(432, 368, 150, 23);
			contentPanel.add(btnConfirmar);
			
			txtGanancia = new JTextField();
			txtGanancia.setBounds(73, 230, 127, 20);
			contentPanel.add(txtGanancia);
			txtGanancia.setColumns(10);
			
			JLabel lblNewLabel = new JLabel("Total cobrado");
			lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setBounds(73, 215, 114, 14);
			contentPanel.add(lblNewLabel);
			
			JLabel lblCliente = new JLabel("Cliente");
			lblCliente.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
			lblCliente.setHorizontalAlignment(SwingConstants.LEFT);
			lblCliente.setForeground(Color.WHITE);
			lblCliente.setBounds(73, 308, 46, 14);
			contentPanel.add(lblCliente);
			
			cmbboxClientes = new JComboBox();
			cmbboxClientes.setModel(new DefaultComboBoxModel(traerNomClientes()));
			cmbboxClientes.setBounds(73, 322, 127, 20);
			contentPanel.add(cmbboxClientes);
			
			JLabel lblFecha = new JLabel("Fecha");
			lblFecha.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
			lblFecha.setHorizontalAlignment(SwingConstants.LEFT);
			lblFecha.setForeground(Color.WHITE);
			lblFecha.setBounds(73, 261, 46, 14);
			contentPanel.add(lblFecha);
			
			JLabel lblDetallesDeLa = new JLabel("Detalles de la venta");
			lblDetallesDeLa.setHorizontalAlignment(SwingConstants.CENTER);
			lblDetallesDeLa.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			lblDetallesDeLa.setForeground(Color.WHITE);
			lblDetallesDeLa.setBounds(51, 194, 183, 14);
			contentPanel.add(lblDetallesDeLa);
			
			JLabel lblProductosVendidos = new JLabel("Producto");
			lblProductosVendidos.setHorizontalAlignment(SwingConstants.LEFT);
			lblProductosVendidos.setForeground(Color.WHITE);
			lblProductosVendidos.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
			lblProductosVendidos.setBounds(53, 26, 127, 14);
			contentPanel.add(lblProductosVendidos);
			
			JLabel lblCantidad = new JLabel("Cantidad");
			lblCantidad.setHorizontalAlignment(SwingConstants.LEFT);
			lblCantidad.setForeground(Color.WHITE);
			lblCantidad.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
			lblCantidad.setBounds(53, 72, 127, 14);
			contentPanel.add(lblCantidad);
			
			JLabel lblSuClienteNo = new JLabel("Su cliente no esta en la lista?");
			lblSuClienteNo.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 11));
			lblSuClienteNo.setHorizontalAlignment(SwingConstants.CENTER);
			lblSuClienteNo.setForeground(Color.WHITE);
			lblSuClienteNo.setBounds(53, 355, 181, 14);
			contentPanel.add(lblSuClienteNo);
			
			btnAgregarCliente = new JButton("Agregar cliente");
			btnAgregarCliente.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent arg0) {
					
					btnAgregarCliente.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
					
				}
			});
			btnAgregarCliente.setOpaque(false);
			btnAgregarCliente.setFocusable(false);
			btnAgregarCliente.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String nombre = ingresoDatos("*Ingrese nombre");
					if (nombre.equals(null)) {}
					String telefono = ingresoDatos("Ingrese telefono");
					if (telefono.equals(null)) {}
					String correo = ingresoDatos("Ingrese correo");
					if (correo.equals(null)) {}
					String direccion = ingresoDatos("*Ingrese direccion");
					if (direccion.equals(null)) {}
					String instagram = ingresoDatos("Ingrese IG");
											
					if (!(nombre.equals(""))) {
						
						try {
							conexionClientes.agregarCliente(nombre, telefono, correo, direccion, instagram);
							cmbboxClientes.setModel(new DefaultComboBoxModel(traerNomClientes()));
						} catch (SQLException e1) {						
							e1.printStackTrace();
							} catch (ClassNotFoundException e) {
							e.printStackTrace();
						}								
					}				
					
					else {					
						JOptionPane.showMessageDialog(null, "Debe indicar el nombre del cliente","Error",JOptionPane.ERROR_MESSAGE);						
				}
					
					btnAgregarCliente.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
				}
			});
			btnAgregarCliente.setForeground(Color.WHITE);
			btnAgregarCliente.setFocusable(false);
			btnAgregarCliente.setBorder(null);
			btnAgregarCliente.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
			btnAgregarCliente.setBackground(Color.WHITE);
			btnAgregarCliente.setBounds(76, 369, 127, 20);
			contentPanel.add(btnAgregarCliente);
			
			chckbxNewCheckBox = new JCheckBox("Esta pago");
			chckbxNewCheckBox.setForeground(Color.WHITE);
			chckbxNewCheckBox.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 12));
			chckbxNewCheckBox.setFocusable(false);
			chckbxNewCheckBox.setOpaque(false);
			chckbxNewCheckBox.setBounds(279, 350, 96, 23);
			contentPanel.add(chckbxNewCheckBox);
			
			chckbxNewCheckBox_1 = new JCheckBox("Esta entregado");
			chckbxNewCheckBox_1.setForeground(Color.WHITE);
			chckbxNewCheckBox_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 12));
			chckbxNewCheckBox_1.setOpaque(false);
			chckbxNewCheckBox_1.setFocusable(false);
			chckbxNewCheckBox_1.setBounds(279, 371, 127, 23);
			contentPanel.add(chckbxNewCheckBox_1);
			
			JLabel lblwallpaper = new JLabel("");
			lblwallpaper.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					
					add.setIcon(new ImageIcon(Ventas.class.getResource("/stock/sign-add-icon.png")));
					delete.setIcon(new ImageIcon(Ventas.class.getResource("/stock/deletetest.png")));
					btnAgregarCliente.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
				}
			});
			lblwallpaper.setForeground(Color.BLACK);
			lblwallpaper.setBackground(Color.WHITE);
			lblwallpaper.setIcon(new ImageIcon(Ventas.class.getResource("/images2/frame.jpg")));
			lblwallpaper.setBounds(-14, 0, 640, 418);
			contentPanel.add(lblwallpaper);
					
	}
	
	private static boolean esNumerico(String cadena){
		
		boolean esNumero;
		
		try {
			Double.parseDouble(cadena);
			esNumero = true;
		} catch (NumberFormatException nfe){
			esNumero = false;
		}
		
		return esNumero;
	}
	

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void actualizarLista(String producto, boolean esAdd) {					
						
		ArrayList<String> list = new ArrayList<String> (Arrays.asList(this.lista));
		
		if (!list.isEmpty()) {	
			
			int indx = list.indexOf(producto);
			
			list.remove(indx);
				
		for (int i = 0; i < list.size(); i++) {
			
			boolean encontrado = false;
			int j = 0;
			
			while (j < table.getRowCount() && !encontrado) {
				
				if (table.getValueAt(j,1).equals(list.get(i))) {
					encontrado = true;
					list.remove(j);					
				 }
				
				else {
					j++;
				}
			}	
		 }					
	  }
		this.lista = list.toArray(new String [0]);
		comboBox.setModel(new DefaultComboBoxModel(this.lista));	
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void actualizarListaDelete(String producto) {					
		
		ArrayList<String> list = new ArrayList<String> (Arrays.asList(this.lista));
		
		list.add(producto);				
		this.lista = list.toArray(new String [0]);
		comboBox.setModel(new DefaultComboBoxModel(this.lista));	
	}
	
	private String[] traerNomClientes() throws ClassNotFoundException, SQLException {
		return conexion.nombreClientes();
	}
	
	public String ingresoDatos(String pregunta) {
		
		String nombre = JOptionPane.showInputDialog(null,pregunta,"Nuevo cliente", JOptionPane.PLAIN_MESSAGE);
		
		return nombre;
		
	}
	
	private int booleanToInt(boolean booleano) {		
		//1 = true, 0 = false
		int booleanValue = 0;
		
		if (booleano) {
			booleanValue = 1;
		}
					
		return booleanValue;
		
	}
	
	private String estadoDelPedido(boolean pagado, boolean entregado) {
		
		String estado = null;
		
		if (pagado && entregado) {
			estado = "CERRADO";
		}
		
		else {
			estado = "ABIERTO";
		}
		
		return estado;
	
	}
}

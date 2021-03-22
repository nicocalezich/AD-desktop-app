package frontend;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import java.awt.ComponentOrientation;
import javax.swing.ScrollPaneConstants;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import backend.ConexionStock;
import backend.TextPrompt;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JSeparator;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class Stock extends JPanel {

	private static final long serialVersionUID = 1L;	
	private JTable table;
	private ConexionStock conexion;
	private JTextField busqueda;
	private JButton btnAgregar;


	public Stock() throws ClassNotFoundException, SQLException {
		
		setBackground(Color.BLACK);
		setLayout(null);
		setBounds(0, 0, 1082, 498);		
		
		conexion = new ConexionStock();		
		table = new JTable() {
			
			private static final long serialVersionUID = 1L; 
			@Override
			public boolean isCellEditable(int filas, int columnas) {
				if (columnas==1 && filas==1) {
					return true;
				}
				else return false;
			}
		};
		table.setOpaque(false);
		table.setSelectionBackground(Color.LIGHT_GRAY);
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setRowHeight(30);
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBackground(Color.DARK_GRAY);
		table.setForeground(Color.WHITE);
		table.setBounds(148, 65, 650, 338);
		add(table);
		
		JTableHeader head = table.getTableHeader();
		head.setBackground(Color.black);
		head.setForeground(Color.white);
		head.setFont(new Font ("Corbel",Font.PLAIN,20));
										
		btnAgregar = new JButton("");
		btnAgregar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				btnAgregar.setIcon(new ImageIcon(Stock.class.getResource("/stock/sign-add-iconBIG.png")));				
				
			}
		});
		btnAgregar.setToolTipText("Agregar stock");
		btnAgregar.setOpaque(false);
		btnAgregar.setFocusable(false);
		btnAgregar.setIcon(new ImageIcon(Stock.class.getResource("/stock/sign-add-icon.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				
				String nuevoProducto = (String) JOptionPane.showInputDialog(null, "Producto","Nuevo producto", JOptionPane.PLAIN_MESSAGE);
				if (nuevoProducto.equals(null)) {}
				String cantidad = (String) JOptionPane.showInputDialog(null, "Cantidad disponible de "+nuevoProducto,"Nuevo producto", JOptionPane.PLAIN_MESSAGE);
				if (cantidad.equals(null)) {}
				String precio = (String) JOptionPane.showInputDialog(null, "Precio de "+nuevoProducto,"Nuevo producto", JOptionPane.PLAIN_MESSAGE);
				if (precio.equals(null)) {}
				
				try {
					conexion.agregarItemStock(nuevoProducto, Double.valueOf(cantidad), Double.valueOf(precio));
					llamar();
				} catch (NumberFormatException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
											
				btnAgregar.setIcon(new ImageIcon(Stock.class.getResource("/stock/sign-add-icon.png")));
			}
			
			
		});
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		btnAgregar.setBorder(null);
		btnAgregar.setBackground(Color.BLACK);
		btnAgregar.setBounds(373, 11, 70, 75);
		add(btnAgregar);
		
		JButton button_1 = new JButton("");
		button_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				button_1.setIcon(new ImageIcon(Stock.class.getResource("/stock/deletetestBIG.png")));
				
			}
		});
		button_1.setToolTipText("Eliminar stock");
		button_1.setFocusable(false);
		button_1.setOpaque(false);
		button_1.setIcon(new ImageIcon(Stock.class.getResource("/stock/deletetest.png")));
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//toma el valor de la fila seleccionada, de no serlo, se advertira al usuario	
				int row = table.getSelectedRow();	
				
				if(row >= 0) {		
					
					int respuesta=JOptionPane.showConfirmDialog(null,"Desea eliminar este registro?","Eliminar",JOptionPane.PLAIN_MESSAGE);
					
						if (respuesta == 0) {				
							
							int num=table.getSelectedRow();					
							String producto = (String) table.getValueAt(num,0);	
							
							try {
								conexion.eliminarItemStock(producto);
								}							 
							catch (SQLException e1) {						
								JOptionPane.showMessageDialog(null, "No se pudo conectar con la base de datos","Error",JOptionPane.ERROR_MESSAGE);	
								}		
							
							try {
								llamar();
								}
							catch (ClassNotFoundException | SQLException e1) {
								e1.printStackTrace();
								}				
						}
					}		
				else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un registro","Error",JOptionPane.ERROR_MESSAGE);				
					 }
				
				button_1.setIcon(new ImageIcon(Stock.class.getResource("/stock/deletetest.png")));
			}
		});
		button_1.setForeground(Color.WHITE);
		button_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		button_1.setBorder(null);
		button_1.setBackground(Color.BLACK);
		button_1.setBounds(515, 11, 70, 75);
		add(button_1);
		
		JScrollPane scrollPane = new JScrollPane(table);		
		scrollPane.setBorder(null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setBounds(10, 97, 1062, 395);
		scrollPane.setOpaque(false);
		add(scrollPane);
		
		JButton button_2 = new JButton("");
		button_2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				button_2.setIcon(new ImageIcon(Stock.class.getResource("/stock/75476-2019-02-08-edit-icon-png-small-11563142463qiwrzqx0e1BIG.png")));
			}
		});
		button_2.setToolTipText("Editar stock");
		button_2.setFocusable(false);
		button_2.setOpaque(false);
		button_2.setIcon(new ImageIcon(Stock.class.getResource("/stock/75476-2019-02-08-edit-icon-png-small-11563142463qiwrzqx0e1.png")));
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table.getSelectedRow();
				
				if(row >= 0)  {		
									
					String[] opciones = {"Producto","Cantidad","Precio"};
									
					int respuesta = JOptionPane.showOptionDialog(null,"Seleccione el campo que desea editar","Editar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, null);
									
					int num = table.getSelectedRow();	
					
					String id = (String) table.getValueAt(num,0);										
					
					if (respuesta == 0) {
																	
						String nuevoProducto = (String) JOptionPane.showInputDialog(null, "Edite el nombre del producto","Editar", JOptionPane.PLAIN_MESSAGE,null,null,table.getValueAt(row, 1));
						
						if (!nuevoProducto.isEmpty()) {														
						
						try {						
							conexion.actualizarItemStock(id,nuevoProducto);
							llamar();							
							} 
						
						catch (ClassNotFoundException | SQLException e1) {			
							e1.printStackTrace();
							}
											
						}
					}
											
					else if (respuesta == 1) {												
															
						String nuevaCantidadTest = (String) JOptionPane.showInputDialog(null, "Edite el nombre del producto","Editar", JOptionPane.PLAIN_MESSAGE,null,null,table.getValueAt(row, 2));
						String nuevaCantidad = nuevaCantidadTest.replace(',','.');
						
						if (!nuevaCantidad.isEmpty()) {
																				
						try {
							conexion.actualizaraCantidadStock(id, Double.valueOf(nuevaCantidad));		
							llamar();
							} 
						
						catch (SQLException  | ClassNotFoundException e1) {						
							e1.printStackTrace();
							} 											
						}
					}
					
					else if (respuesta == 2) {												
						
						String nuevoPrecio = (String) JOptionPane.showInputDialog(null, "Edite el nombre del producto","Editar", JOptionPane.PLAIN_MESSAGE,null,null,table.getValueAt(row, 3));
						nuevoPrecio = nuevoPrecio.replace(',','.');
						
						if (!nuevoPrecio.isEmpty()) {
																				
						try {
							conexion.actualizaraPrecioStock(id, Double.valueOf(nuevoPrecio));		
							llamar();
							} 
						
						catch (SQLException  | ClassNotFoundException e1) {						
							e1.printStackTrace();
							} 											
						}
					}
					
				}
				
				else {
					JOptionPane.showMessageDialog(null,"Debe seleccionar un registro","Error",JOptionPane.ERROR_MESSAGE);
					}
				button_2.setIcon(new ImageIcon(Stock.class.getResource("/stock/75476-2019-02-08-edit-icon-png-small-11563142463qiwrzqx0e1.png")));
			}
		});
		
		button_2.setForeground(Color.WHITE);
		button_2.setBorder(null);
		button_2.setBackground(Color.BLACK);
		button_2.setBounds(650, 11, 70, 75);
		add(button_2);
		
		
		busqueda = new JTextField();
		new TextPrompt(" Buscar...",busqueda);
		busqueda.setFont(new Font("Tahoma", Font.PLAIN, 14));
		busqueda.setOpaque(false);
		busqueda.setBackground(null);
		busqueda.setSelectionColor(Color.GRAY);
		busqueda.setForeground(Color.WHITE);
		busqueda.setBorder(null);
		busqueda.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
				
				buscar();	
				
				if (table.getRowCount() == 0) {
					
					try {
						llamar();
					} catch (ClassNotFoundException | SQLException e1) {
						
						e1.printStackTrace();
					}
					
					JOptionPane.showMessageDialog(null, "No se encontraron coincidencias","Error",JOptionPane.ERROR_MESSAGE);						
					
					}				
				}								
			}
		});
		
		JButton btnCancelarBusqueda = new JButton("");
		btnCancelarBusqueda.setFocusable(false);
		btnCancelarBusqueda.setBorder(null);
		btnCancelarBusqueda.setBackground(Color.BLACK);
		btnCancelarBusqueda.setOpaque(false);
		btnCancelarBusqueda.setIcon(new ImageIcon(Stock.class.getResource("/loging/wrongPass.png")));
		btnCancelarBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					llamar();
				} catch (ClassNotFoundException | SQLException e1) {
					
					e1.printStackTrace();
				}
				
				busqueda.setText("");
			}
		});
		btnCancelarBusqueda.setBounds(173, 71, 25, 23);
		add(btnCancelarBusqueda);
		
		busqueda.setBounds(10, 68, 163, 23);
		add(busqueda);
		busqueda.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 90, 163, 4);
		add(separator);
		
		JLabel wallpaper = new JLabel("");
		wallpaper.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				btnAgregar.setIcon(new ImageIcon(Stock.class.getResource("/stock/sign-add-icon.png")));
				button_1.setIcon(new ImageIcon(Stock.class.getResource("/stock/deletetest.png")));
				button_2.setIcon(new ImageIcon(Stock.class.getResource("/stock/75476-2019-02-08-edit-icon-png-small-11563142463qiwrzqx0e1.png")));
			}
		});
		wallpaper.setBackground(Color.BLACK);
		wallpaper.setBorder(null);
		wallpaper.setIcon(new ImageIcon(Stock.class.getResource("/imagenes/32041.jpg")));
		wallpaper.setBounds(-17, 0, 1099, 498);
		add(wallpaper);
		
		llamar();
		

	}
	
	public void llamar() throws ClassNotFoundException, SQLException {
	
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
		
		String sql = "SELECT * FROM stock";
		
		java.sql.Statement st;
		
		DefaultTableModel model = new DefaultTableModel();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			
		model.addColumn("ID");
		model.addColumn("Producto");
		model.addColumn("Cantidad");
		model.addColumn("Precio Kg/Unidad");

		table.setModel(model);
			
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(500);
		table.getColumnModel().getColumn(2).setPreferredWidth(260);
		table.getColumnModel().getColumn(3).setPreferredWidth(260);
	
			
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); 
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); 
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); 
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); 

		
		String[] dato =  new String [4];
		
		try {
			
			st = conexion.createStatement();
			
			ResultSet result = st.executeQuery(sql);
			
			while (result.next()) {
				
				dato[0]=result.getString(1);
				dato[1]=result.getString(2);
				dato[2]=result.getString(3);
				dato[3]="$"+result.getString(4);
				model.addRow(dato);			
			}		
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");		
		}
		
		ajustarTamanioTable(table.getRowCount());
			
	}

	
	private void ajustarTamanioTable(int rows){		
		
		if (rows > 17) {
			table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(1).setPreferredWidth(569);
			table.getColumnModel().getColumn(2).setPreferredWidth(130);			
		}
		
	
		
	}
	
	private void buscar() {
		
		String buscado = busqueda.getText();								
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e2) {
		
			e2.printStackTrace();
		}
		
		java.sql.Connection conexion1 = null;
		
		try {
			
			conexion1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
		} catch (SQLException e2) {
			
			e2.printStackTrace();
		}
		
		String sql = "SELECT * FROM `stock` WHERE `producto` LIKE '%"+buscado+"%'";
		
		java.sql.Statement st;
		
		DefaultTableModel model = new DefaultTableModel();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			
		model.addColumn("ID");
		model.addColumn("Producto");
		model.addColumn("Cantidad");
		model.addColumn("Precio Kg/Unidad");

		table.setModel(model);
			
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(500);
		table.getColumnModel().getColumn(2).setPreferredWidth(260);
		table.getColumnModel().getColumn(3).setPreferredWidth(260);

			
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); 
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); 
		table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); 
		table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); 

		
		String[] dato =  new String [4];
		
		try {
			
			st = conexion1.createStatement();
			
			ResultSet result = st.executeQuery(sql);
			
			while (result.next()) {
			
				dato[0]=result.getString(1);
				dato[1]=result.getString(2);
				dato[2]=result.getString(3);
				dato[3]="$"+result.getString(4);
				model.addRow(dato);			
			}		
			
		} catch (Exception e1) {
			JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");		
		}		
		
	}

	public String[] productos() {
		
		int filas = table.getRowCount();		
		String[] productos = new String[filas];
		
		for (int i = 0; i < productos.length; i++) {
			productos[i] = (String) table.getValueAt(i,1);
		}		
		
		return productos;				
	}

	public double cantidadProducto(String id) {
		
		String cantidadStr = "";	
		boolean encontrado = false;
		int i = 0;
		
		while (i<table.getRowCount() && !encontrado) {					
			
			if(id.equals(table.getValueAt(i,0))) {
				cantidadStr = (String) table.getValueAt(i,2);
				encontrado = true;
				}
			
			else {
				i++;
			}							
		}
		
		return Double.parseDouble(cantidadStr);
	}
	
	public String idProducto(String producto) {
		
		String id = "";
		boolean encontrado = false;
		int i = 0;
		
		while (i<table.getRowCount() && !encontrado) {					
			
			if(producto.equals(table.getValueAt(i,1))) {
				id = (String) table.getValueAt(i,0);
				encontrado = true;
				
				}
			
			else {
				i++;
			}				
		}
		
		return id;
		
	}
	
	public boolean productoEstaRepetido(String producto) {
		
		boolean estaRepetido = false;	
		int i = 0;
		int rows = table.getRowCount();
		
		while (i<rows && !estaRepetido) {	
			
			if(producto.equals(table.getValueAt(i,1))) {
				estaRepetido = true;
				}			
			else {
				i++;
			}
			
		}		
		return estaRepetido;	
	}
	
}


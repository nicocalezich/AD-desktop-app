package frontend;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import backend.ConexionProveedor;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class Proveedores extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private ConexionProveedor conexion;

	public Proveedores() throws ClassNotFoundException, SQLException {
		
		setBackground(Color.BLACK);
		setLayout(null);
		setBounds(0, 0, 1083, 502);
		conexion = new ConexionProveedor();
		
		 table = new JTable() {
				
				private static final long serialVersionUID = 1L;
				@Override
				public boolean isCellEditable(int filas, int columnas) {
					if (columnas==1&&filas==1) {
						return true;
					}
					else return false;
				}
			};
			
			table.setRowHeight(30);
		 	table.setSelectionBackground(Color.LIGHT_GRAY);
		 	table.setFont(new Font("Tahoma", Font.PLAIN, 18));
			table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setBorder(new LineBorder(Color.WHITE));
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
					
					btnAgregar.setIcon(new ImageIcon(Proveedores.class.getResource("/stock/sign-add-iconBIG.png")));
					
				}
			});
			btnAgregar.setOpaque(false);
			btnAgregar.setToolTipText("Agregar proveedor");
			btnAgregar.setFocusable(false);
			btnAgregar.setIcon(new ImageIcon(Proveedores.class.getResource("/stock/sign-add-icon.png")));
			btnAgregar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					agregarProveedor();
				}
			});
			btnAgregar.setForeground(Color.WHITE);
			btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnAgregar.setBorder(null);
			btnAgregar.setBackground(Color.BLACK);
			btnAgregar.setBounds(373, 11, 70, 75);
			add(btnAgregar);
			
			btnEliminar = new JButton("");
			btnEliminar.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					
					btnEliminar.setIcon(new ImageIcon(Proveedores.class.getResource("/stock/deletetestBIG.png")));
					
				}
			});
			btnEliminar.setOpaque(false);
			btnEliminar.setToolTipText("Eliminar proveedor");
			btnEliminar.setIcon(new ImageIcon(Proveedores.class.getResource("/stock/deletetest.png")));
			btnEliminar.setFocusable(false);
			btnEliminar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					eliminarProveedor(table.getSelectedRow());
			}
				
			});
			btnEliminar.setForeground(Color.WHITE);
			btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 20));
			btnEliminar.setBorder(null);
			btnEliminar.setBackground(Color.BLACK);
			btnEliminar.setBounds(515, 11, 70, 75);
			add(btnEliminar);
			
			JScrollPane scrollPane = new JScrollPane(table);
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 13));			
			scrollPane.setBackground(Color.DARK_GRAY);
			scrollPane.setBounds(10, 97, 1063, 394);
			add(scrollPane);		
			
			//EDITAR PROVEEDOR
			btnEditar = new JButton("");
			btnEditar.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					
					btnEditar.setIcon(new ImageIcon(Proveedores.class.getResource("/stock/75476-2019-02-08-edit-icon-png-small-11563142463qiwrzqx0e1BIG.png")));
					
				}
			});
			btnEditar.setOpaque(false);
			btnEditar.setToolTipText("Editar proveedor");
			btnEditar.setFocusable(false);
			btnEditar.setIcon(new ImageIcon(Proveedores.class.getResource("/stock/75476-2019-02-08-edit-icon-png-small-11563142463qiwrzqx0e1.png")));
			btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					
			editarProveedor(table.getSelectedRow());

		}		
				
	});
			btnEditar.setForeground(Color.WHITE);
			btnEditar.setBorder(null);
			btnEditar.setBackground(Color.BLACK);
			btnEditar.setBounds(650, 11, 70, 75);
			add(btnEditar);
			
			JLabel lblNewLabel = new JLabel("");
			lblNewLabel.addMouseMotionListener(new MouseMotionAdapter() {
				@Override
				public void mouseMoved(MouseEvent e) {
					
					btnAgregar.setIcon(new ImageIcon(Proveedores.class.getResource("/stock/sign-add-icon.png")));
					btnEliminar.setIcon(new ImageIcon(Proveedores.class.getResource("/stock/deletetest.png")));
					btnEditar.setIcon(new ImageIcon(Proveedores.class.getResource("/stock/75476-2019-02-08-edit-icon-png-small-11563142463qiwrzqx0e1.png")));

					
				}
			});
			lblNewLabel.setIcon(new ImageIcon(Proveedores.class.getResource("/images2/frame.jpg")));
			lblNewLabel.setBounds(-13, 0, 1099, 498);
			add(lblNewLabel);
			llamar();
			
		}
		
		private void llamar() throws ClassNotFoundException, SQLException {
			
			Class.forName("com.mysql.jdbc.Driver");
			
			java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
			
			String sql = "SELECT * FROM proveedores";
			
			java.sql.Statement st;
			
			DefaultTableModel model = new DefaultTableModel();
			DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
			
			model.addColumn("ID");
			model.addColumn("Nombre");
			model.addColumn("Productos");
			model.addColumn("Telefono");
			model.addColumn("Direccion");
			model.addColumn("Correo");

			table.setModel(model);
					
			table.getColumnModel().getColumn(0).setPreferredWidth(55);
			table.getColumnModel().getColumn(1).setPreferredWidth(210);
			table.getColumnModel().getColumn(2).setPreferredWidth(150);
			table.getColumnModel().getColumn(3).setPreferredWidth(180);
			table.getColumnModel().getColumn(4).setPreferredWidth(250);
			table.getColumnModel().getColumn(5).setPreferredWidth(215);
			
			centerRenderer.setHorizontalAlignment( JLabel.CENTER );
			table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); 
			table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); 
			table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); 
			table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); 
			table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); 
			table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer); 
			
			String[] dato =  new String [6];
			
			try {
				
				st = conexion.createStatement();
				
				ResultSet result = st.executeQuery(sql);
				
				while (result.next()) {
				
					dato[0]=result.getString(1);
					dato[1]=result.getString(2);
					dato[2]=result.getString(3);
					dato[3]=result.getString(4);
					dato[4]=result.getString(5);
					dato[5]=result.getString(6);
					model.addRow(dato);			
				}		
				
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");		
			}
			
			ajustarTamanioTable(table.getRowCount());
	
	}
		
		private void agregarProveedor() {
			
			String nombre = JOptionPane.showInputDialog(null, "*Ingrese nombre", "Nuevo proveedor", JOptionPane.PLAIN_MESSAGE);
			if(nombre.equals(null)) {}
			String productos =	JOptionPane.showInputDialog(null, "Ingrese productos", "Nuevo proveedor", JOptionPane.PLAIN_MESSAGE);
			if(productos.equals(null)) {}
			String telefono = JOptionPane.showInputDialog(null, "Ingrese telefono", "Nuevo proveedor", JOptionPane.PLAIN_MESSAGE);
			if(telefono.equals(null)) {}
			String direccion = JOptionPane.showInputDialog(null, "*Ingrese direccion", "Nuevo proveedor", JOptionPane.PLAIN_MESSAGE);
			if(direccion.equals(null)) {}
			String correo = JOptionPane.showInputDialog(null, "Ingrese correo", "Nuevo proveedor", JOptionPane.PLAIN_MESSAGE);
											
			if (!(nombre.equals("") || direccion.equals(""))) {
				
				try {
					conexion.agregarProveedor(nombre, productos, telefono, direccion, correo);
					llamar();
				} catch (SQLException | ClassNotFoundException e1) {
					e1.printStackTrace();
				}				
			}
			
			else {																					
				JOptionPane.showMessageDialog(null, "Nombre y direccion son obligatorios","Error",JOptionPane.ERROR_MESSAGE);
				}
			
			btnAgregar.setIcon(new ImageIcon(Proveedores.class.getResource("/stock/sign-add-icon.png")));	
		}
		
		private void eliminarProveedor(int row) {
								
			if(row >= 0) {
							
			int respuesta = JOptionPane.showConfirmDialog(null,"Desea eliminar este registro?","Eliminar",JOptionPane.PLAIN_MESSAGE);
			
			if (respuesta == 0) {
				
				String ID = (String) table.getValueAt(row,0);
				
				try {
								
					conexion.eliminarProveedor(ID);				
					llamar();
				}
			
				catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");
					}			
				}						
			
			else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un registro","Error",JOptionPane.ERROR_MESSAGE);								
			}
			
			btnEliminar.setIcon(new ImageIcon(Proveedores.class.getResource("/stock/deletetest.png")));
			}	
		}
		
		private void editarProveedor(int row) {
			
			if(row >= 0)  {		
				
				String[] opciones = {"Nombre","Productos","Telefono","Direccion","Correo"};
				
				String ID = (String) table.getValueAt(row, 0);
											
				int respuesta = JOptionPane.showOptionDialog(null,"Seleccione el campo que desea editar...","Editar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, null);
					
					switch (respuesta) {
					
					case 0: try {					
								String nuevoNombre = JOptionPane.showInputDialog(null, "Edite el nombre del proveedor", "Editar", JOptionPane.PLAIN_MESSAGE);					
								if (!nuevoNombre.isEmpty()) {
									conexion.editarProveedor(ID,"nombre",nuevoNombre);																		
									llamar();	
									}	
								}
							catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");
								}																							
							break;
				
					case 1:  try {					
								String nuevoProducto = JOptionPane.showInputDialog(null, "Edite el producto del proveedor", "Editar", JOptionPane.PLAIN_MESSAGE);					
								if (!nuevoProducto.isEmpty()) {
									conexion.editarProveedor(ID,"productos",nuevoProducto);																		
									llamar();	
									}	
								}
							catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");
							}																							
							break;
						
					case 2: try {					
								String nuevoTelefono = JOptionPane.showInputDialog(null, "Edite el telefono del proveedor", "Editar", JOptionPane.PLAIN_MESSAGE);					
								if (!nuevoTelefono.isEmpty()) {
									conexion.editarProveedor(ID,"telefono",nuevoTelefono);																		
									llamar();	
									}	
							}
							catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");
							}																							
							break;
					
					case 3: try {					
								String nuevaDireccion = JOptionPane.showInputDialog(null, "Edite la direccion del proveedor", "Editar", JOptionPane.PLAIN_MESSAGE);					
								if (!nuevaDireccion.isEmpty()) {
									conexion.editarProveedor(ID,"direccion",nuevaDireccion);																		
									llamar();	
									}	
								}
							catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");
							}																							
							break;
					
					case 4:	try {					
								String nuevoCorreo = JOptionPane.showInputDialog(null, "Edite el correo del proveedor", "Editar", JOptionPane.PLAIN_MESSAGE);					
								if (!nuevoCorreo.isEmpty()) {
									conexion.editarProveedor(ID,"correo",nuevoCorreo);																		
									llamar();	
									}	
							}
							catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");
							}																							
							break;					
					}
										
			}	
			
			else {
				JOptionPane.showMessageDialog(null, "Debe seleccionar un registro","Error",JOptionPane.ERROR_MESSAGE);				
			}
			
			btnEditar.setIcon(new ImageIcon(Proveedores.class.getResource("/stock/75476-2019-02-08-edit-icon-png-small-11563142463qiwrzqx0e1.png")));	
		}
		
		private void ajustarTamanioTable(int rows) {
			
			if (rows > 17) {
				
				table.getColumnModel().getColumn(0).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setPreferredWidth(100);
				table.getColumnModel().getColumn(2).setPreferredWidth(100);
				table.getColumnModel().getColumn(3).setPreferredWidth(140);
				table.getColumnModel().getColumn(4).setPreferredWidth(160);
				table.getColumnModel().getColumn(5).setPreferredWidth(189);
				
			}
		}

}

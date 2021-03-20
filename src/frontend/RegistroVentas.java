package frontend;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Component;
import java.awt.Font;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import backend.ConexionVentas;
import java.awt.ComponentOrientation;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import com.toedter.calendar.JDateChooser;

public class RegistroVentas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTable table1;
	private ConexionVentas conexion;
	private JLabel lblNewLabel_1;
	private JLabel lblIdDeVenta;
	private JButton button;
	private JButton btnCerrarPedido;
	private JButton btnCambiarEstado;
	

	public RegistroVentas() throws ClassNotFoundException, SQLException {
		
		setBackground(Color.BLACK);
		setLayout(null);
		setBounds(0, 0, 1087, 502);
		
		conexion = new ConexionVentas();	
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane.setBackground(Color.BLACK);
		scrollPane.setBounds(641, 82, 421, 169);
		scrollPane.setVisible(true);
		
		JScrollPane scrollPane_1 = new JScrollPane((Component) null);		
		scrollPane_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
									
				button.setIcon(new ImageIcon(RegistroVentas.class.getResource("/stock/deletetest.png")));
			}
		});
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		scrollPane_1.setBackground(Color.BLACK);
		scrollPane_1.setBounds(28, 81, 603, 356);
		scrollPane_1.setVisible(true);
		
		btnCambiarEstado = new JButton("  Cambiar estado");
		btnCambiarEstado.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				btnCambiarEstado.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
			}
		});
		btnCambiarEstado.setVisible(false);
		btnCambiarEstado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//cerrarPedido(row);
				int row = table1.getSelectedRow();
				
				if (row >= 0) {
				
				String[] opciones = {"Fue pago","Fue entregado","Ambos"};
				String[] opciones1 = {"Fue entregado"};
				String[] opciones2 = {"Fue pago"};
				
				String ID = (String) table1.getValueAt(row,0);
				String estaPago = (String) table1.getValueAt(row,3);
				String estaEntregado = (String) table1.getValueAt(row,4);
				
				if (estaPago.equals("No") && estaEntregado.equals("No")) {
					
					int respuesta = JOptionPane.showOptionDialog(null,"Actualizar estado del pedido","Ventas", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, null);
					
					switch(respuesta) {
						
					case 0:	try {
							conexion.actualizarEstadoPagoPedido(ID);
							verPendientes();
							} catch (SQLException | ClassNotFoundException e) {
							e.printStackTrace();
							} 
							break;		
							
					case 1: try {
							conexion.actualizarEstadoEntregadoPedido(ID);
							verPendientes();
							} catch (SQLException | ClassNotFoundException e) {
								e.printStackTrace();
							} 
							break;
							
					case 2: try {
							conexion.actualizarEstadoPagoPedido(ID);
							conexion.actualizarEstadoEntregadoPedido(ID);
							conexion.cerrarPedido(ID);
							verPendientes();
							} catch (SQLException | ClassNotFoundException e) {
							e.printStackTrace();
							} 
							break;								
					}			
				}
				
				else if (estaPago.equals("Si") && estaEntregado.equals("No")) {
					
					int respuesta = JOptionPane.showOptionDialog(null,"Actualizar estado del pedido","Ventas", JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.INFORMATION_MESSAGE, null, opciones1, null);
					
					if (respuesta == 0) {
						
						try {
							conexion.actualizarEstadoEntregadoPedido(ID);
							conexion.cerrarPedido(ID);
							verPendientes();
							} catch (SQLException | ClassNotFoundException e) {
							e.printStackTrace();
							} 						
					}				
				}
				
				else if (estaPago.equals("No") && estaEntregado.equals("Si")) {
					
					int respuesta = JOptionPane.showOptionDialog(null,"Actualizar estado del pedido","Ventas", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones2, null);
					
					if (respuesta == 0) {
						
						try {
							conexion.actualizarEstadoPagoPedido(ID);
							conexion.cerrarPedido(ID);
							verPendientes();
							} catch (SQLException | ClassNotFoundException e) {
							e.printStackTrace();
							} 						
					}			
				}								
			}
				
				else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
				}
				
			}
		});
		btnCerrarPedido = new JButton("  Cerrar pedido");
		btnCerrarPedido.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btnCerrarPedido.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
			}
		});
		btnCerrarPedido.setHorizontalAlignment(SwingConstants.LEFT);
		btnCerrarPedido.setOpaque(false);
		btnCerrarPedido.setFocusable(false);
		btnCerrarPedido.setBackground(null);
		btnCerrarPedido.setBorder(null);
		btnCerrarPedido.setVisible(false);
		btnCerrarPedido.setForeground(Color.WHITE);
		btnCerrarPedido.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
		btnCerrarPedido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int row = table1.getSelectedRow();
				
				if (row >= 0) {
													
				String ID = (String) table1.getValueAt(row, 0);
				
				try {
					
					conexion.actualizarEstadoPagoPedido(ID);
					conexion.actualizarEstadoEntregadoPedido(ID);
					conexion.cerrarPedido(ID);
					verPendientes();
					
				} catch (SQLException e2) {					
					e2.printStackTrace();
					} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Pedido cerrado con exito");
				
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
				}
				
			}
		});
		btnCerrarPedido.setBounds(446, 448, 126, 23);
		add(btnCerrarPedido);
		btnCambiarEstado.setOpaque(false);
		btnCambiarEstado.setHorizontalAlignment(SwingConstants.LEFT);
		btnCambiarEstado.setForeground(Color.WHITE);
		btnCambiarEstado.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
		btnCambiarEstado.setFocusable(false);
		btnCambiarEstado.setBorder(null);
		btnCambiarEstado.setBackground((Color) null);
		btnCambiarEstado.setBounds(310, 448, 126, 23);
		add(btnCambiarEstado);
		add(scrollPane_1);
		
		table1 = new JTable() {
			
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int filas, int columnas) {
				return false;
			}
		};
		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int row = table1.getSelectedRow();
						
					
					String ID = (String) table1.getValueAt(row, 0);
					
					try {
						mostrarFiltrado(ID);
					} catch (ClassNotFoundException | SQLException e2) {					
						e2.printStackTrace();
						}												
				
			}
		});
		
		table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table1.setSelectionBackground(Color.LIGHT_GRAY);
		table1.setRowHeight(30);
		table1.setForeground(Color.WHITE);
		table1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table1.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table1.setBorder(new LineBorder(Color.WHITE));
		table1.setBackground(Color.DARK_GRAY);
		table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane_1.setViewportView(table1);
		add(scrollPane);
		
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
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setBorder(new LineBorder(Color.WHITE));
		table.setBackground(Color.DARK_GRAY);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		scrollPane.setViewportView(table);
		
		DefaultTableModel model1 = new DefaultTableModel();
		DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
		
		model1.addColumn("Cliente");
				
		centerRenderer1.setHorizontalAlignment( JLabel.CENTER );
		
		JTableHeader head = table.getTableHeader();
		head.setBackground(Color.black);
		head.setForeground(Color.white);
		head.setFont(new Font ("Corbel",Font.PLAIN,20));
		
		JTableHeader head1 = table1.getTableHeader();
		head1.setBackground(Color.black);
		head1.setForeground(Color.white);
		head1.setFont(new Font ("Corbel",Font.PLAIN,15));
		
		JButton btnVerTodas = new JButton("  Ver todos");
		btnVerTodas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				
				btnVerTodas.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
				
			}
		});
		btnVerTodas.setHorizontalAlignment(SwingConstants.LEFT);
		btnVerTodas.setBorder(null);
		btnVerTodas.setForeground(Color.WHITE);
		btnVerTodas.setFocusable(false);
		btnVerTodas.setOpaque(false);
		btnVerTodas.setBackground(null);
		btnVerTodas.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
		btnVerTodas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
											
				try {
					btnCerrarPedido.setVisible(false);
					btnCambiarEstado.setVisible(false);
					lblIdDeVenta.setText("Todas las ventas");
					verTodasVentas();
				} catch (ClassNotFoundException | SQLException e1) {					
					e1.printStackTrace();
				}
													
			}
		});
		btnVerTodas.setBounds(38, 448, 126, 23);
		add(btnVerTodas);
		
		JLabel lblNewLabel = new JLabel("Filtrar por fecha");
		lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(16, 23, 138, 23);
		add(lblNewLabel);
		
		lblIdDeVenta = new JLabel("Ventas");
		lblIdDeVenta.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 18));
		lblIdDeVenta.setHorizontalAlignment(SwingConstants.LEFT);
		lblIdDeVenta.setForeground(Color.WHITE);
		lblIdDeVenta.setBounds(246, 47, 215, 29);
		add(lblIdDeVenta);
		
		JLabel lblDetalleDeLa = new JLabel("Detalle de la venta");
		lblDetalleDeLa.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 18));
		lblDetalleDeLa.setHorizontalAlignment(SwingConstants.CENTER);
		lblDetalleDeLa.setForeground(Color.WHITE);
		lblDetalleDeLa.setBounds(746, 58, 210, 23);
		add(lblDetalleDeLa);
		
		button = new JButton("");
		button.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				
				button.setIcon(new ImageIcon(RegistroVentas.class.getResource("/stock/deletetestBIG.png")));
				
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int row = table1.getSelectedRow();
				
				if (row >= 0) {
					
					String ID = (String) table1.getValueAt(row, 0);
					
					int respuesta=JOptionPane.showConfirmDialog(null,"Desea eliminar venta con ID "+ID+"?","Eliminar",JOptionPane.PLAIN_MESSAGE);
					
					if (respuesta == 0) {	
						
						try {
							conexion.eliminarVenta(ID);
							verTodasVentas();
						} catch (SQLException | ClassNotFoundException e) {
							JOptionPane.showMessageDialog(null, "No se pudo eliminar la venta");
						}					
					}													
				}
				
				else {
					JOptionPane.showMessageDialog(null, "Debe seleccionar un registro");
				}
				
				
			}
		});
		button.setIcon(new ImageIcon(RegistroVentas.class.getResource("/stock/deletetest.png")));
		button.setOpaque(false);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.BOLD, 20));
		button.setFocusable(false);
		button.setBorder(null);
		button.setBackground(Color.BLACK);
		button.setBounds(630, 380, 62, 57);
		add(button);
		
		JButton btnNewButton = new JButton("  Ver pendientes");
		btnNewButton.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				
				btnNewButton.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
				
			}
		});
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setOpaque(false);
		btnNewButton.setBackground(null);
		btnNewButton.setFocusable(false);
		btnNewButton.setBorder(null);
		btnNewButton.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
							
					try {
						
						if (conexion.hayPedidosPendientes()) {
							verPendientes();
							btnCambiarEstado.setVisible(true);
							btnCerrarPedido.setVisible(true);
							lblIdDeVenta.setText("Pendientes");	
							}
						else {
							JOptionPane.showMessageDialog(null, "No tiene pedidos pendientes","Ventas", JOptionPane.INFORMATION_MESSAGE);
						}
						
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
					}				
				} 	
		})
		;
		btnNewButton.setBounds(174, 448, 126, 23);
		add(btnNewButton);		
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(28, 47, 126, 23);
		add(dateChooser);
		
		JButton btnNewButton_1 = new JButton("Ver");
		btnNewButton_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				btnNewButton_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 17));
			}
		});
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setFocusable(false);
		btnNewButton_1.setOpaque(false);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(null);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int dia = dateChooser.getCalendar().get(Calendar.DAY_OF_MONTH);
				int mes = dateChooser.getCalendar().get(Calendar.MARCH)+1;
				int ano = dateChooser.getCalendar().get(Calendar.YEAR);
				
				String fecha = (dia+"/"+mes+"/"+ano);
									
					try {
						
						if (conexion.hayVentas(fecha)) {
							
							btnCerrarPedido.setVisible(false);
							btnCambiarEstado.setVisible(false);
							lblIdDeVenta.setVisible(true);
							lblIdDeVenta.setText("Ventas "+fecha);
							ventasPorFecha(fecha);
						}
						
						else {							
							JOptionPane.showMessageDialog(null, "No se han registrado ventas el "+fecha,"Ventas", JOptionPane.INFORMATION_MESSAGE);
						}
												
								
					} catch (ClassNotFoundException | SQLException e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");
					}											
			}
		});
		btnNewButton_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		btnNewButton_1.setBounds(158, 47, 55, 23);
		add(btnNewButton_1);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				button.setIcon(new ImageIcon(RegistroVentas.class.getResource("/stock/deletetest.png")));
				btnVerTodas.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
				btnNewButton.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
				btnCambiarEstado.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
				btnCerrarPedido.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 13));
				btnNewButton_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
			}
		});
		lblNewLabel_1.setIcon(new ImageIcon(RegistroVentas.class.getResource("/images2/frame.jpg")));
		lblNewLabel_1.setBounds(-13, 0, 1100, 502);
		add(lblNewLabel_1);
						
	}
	
	public void verTodasVentas() throws ClassNotFoundException, SQLException {		
		//11
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
		
		String sql = "SELECT * FROM registro";
		
		java.sql.Statement st;
		
		DefaultTableModel model = new DefaultTableModel();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		
		model.addColumn("IDventa");	
		model.addColumn("Fecha");	
		model.addColumn("Cobrado");
		model.addColumn("Cliente");
		model.addColumn("Estado");

		table1.setModel(model);
		
		table1.getColumnModel().getColumn(0).setPreferredWidth(68);
		table1.getColumnModel().getColumn(1).setPreferredWidth(100);
		table1.getColumnModel().getColumn(2).setPreferredWidth(100);
		table1.getColumnModel().getColumn(3).setPreferredWidth(218);
		table1.getColumnModel().getColumn(4).setPreferredWidth(117);
				
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); 
		table1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); 
		table1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); 
		table1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); 
		table1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); 

		
		String[] dato =  new String [5];
				
		st = conexion.createStatement();
			
		ResultSet result = st.executeQuery(sql);
			
		while (result.next()) {
			
			dato[0]=result.getString(1);
			dato[1]=result.getString(2);
			dato[2]="$"+result.getString(3);
			dato[3]=result.getString(4);
			
			if (result.getString(7).equals("1")) {
				dato[4]="Cerrado";
			}
			else {
				dato[4]="Abierto";
			}
			
			model.addRow(dato);		
				
		}
		
		chequearTamanioTableTodas(table1.getRowCount());
		
					
	}
	
	public void ventasPorFecha(String fecha) throws SQLException, ClassNotFoundException
	{
				
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
					
	    String sql = "SELECT * FROM registro WHERE fecha = '"+fecha+"' AND pedidoCerrado = 1";
							
		java.sql.Statement st;
		
		DefaultTableModel model1 = new DefaultTableModel();
		DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
		
		model1.addColumn("ID");
		model1.addColumn("Cliente");
		model1.addColumn("Ganancia");

		table1.setModel(model1);
		
		table1.getColumnModel().getColumn(0).setPreferredWidth(83);
		table1.getColumnModel().getColumn(1).setPreferredWidth(350);
		table1.getColumnModel().getColumn(2).setPreferredWidth(170);
				
		centerRenderer1.setHorizontalAlignment( JLabel.CENTER );
		table1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer1); 
		table1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer1); 
		table1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer1); 
	
		String[] dato =  new String [3];
				
		st = conexion.createStatement();
			
		ResultSet result = st.executeQuery(sql);
			
		try {
			while (result.next()) {
				
				dato[0] = result.getString(1);
				dato[1] = result.getString(4);
				dato[2] = "$"+result.getString(3);
				model1.addRow(dato);		
					
			}
		} catch (SQLException e) {
		
			e.printStackTrace();
		}	
			
	}
	
	
	public void mostrarFiltrado(String IDventa) throws ClassNotFoundException, SQLException {
		
		String sql = null;
		
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
					
	    sql = "SELECT * FROM ventas WHERE IDventas ='"+IDventa+"'";
							
		java.sql.Statement st;
		
		DefaultTableModel model = new DefaultTableModel();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		
		model.addColumn("Producto");
		model.addColumn("Cantidad");

		table.setModel(model);		
		table.getColumnModel().getColumn(0).setPreferredWidth(271);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);

		
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); 
		table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); 

		
		String[] dato =  new String [2];
				
		st = conexion.createStatement();
			
		ResultSet result = st.executeQuery(sql);
			
		while (result.next()) {
			
			dato[0]=result.getString(2);
			dato[1]=result.getString(3);
			model.addRow(dato);		
				
		}
		
		chequearTamanioTableFiltradas(table.getRowCount());
	}
	
	
	public String gananciaPorVenta(String fecha, String opcion) throws ClassNotFoundException, SQLException {
		
		String ganancia = null;			
		
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
					
	    String sql = "SELECT ganancia FROM registro WHERE cliente='"+opcion+"' AND fecha='"+fecha+"'";
							
		java.sql.Statement st;
						
		st = conexion.createStatement();
			
		ResultSet result = st.executeQuery(sql);
			
		while (result.next()) {
			
			ganancia = result.getString("ganancia");			
				
		}
		
		return ganancia;
		
	}
	
	public void verPendientes() throws SQLException, ClassNotFoundException{
		
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
		
		String sql = "SELECT * FROM registro WHERE pedidoCerrado = 0";
		
		java.sql.Statement st;
		
		DefaultTableModel model = new DefaultTableModel();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		
		model.addColumn("IDventa");	
		model.addColumn("Cobrado");
		model.addColumn("Cliente");
		model.addColumn("Esta pago");
		model.addColumn("Esta entregado");

		table1.setModel(model);
		
		table1.getColumnModel().getColumn(0).setPreferredWidth(68);
		table1.getColumnModel().getColumn(1).setPreferredWidth(100);
		table1.getColumnModel().getColumn(2).setPreferredWidth(220);
		table1.getColumnModel().getColumn(3).setPreferredWidth(100);
		table1.getColumnModel().getColumn(4).setPreferredWidth(112);
				
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table1.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); 
		table1.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); 
		table1.getColumnModel().getColumn(2).setCellRenderer(centerRenderer); 
		table1.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); 
		table1.getColumnModel().getColumn(4).setCellRenderer(centerRenderer); 

		
		String[] dato =  new String [5];
				
		st = conexion.createStatement();
			
		ResultSet result = st.executeQuery(sql);
			
		while (result.next()) {
			
			dato[0]=result.getString(1);
			dato[1]="$"+result.getString(3);
			dato[2]=result.getString(4);
			
			if (result.getString(5).equals("0")) {
				dato[3]="No";
			}
			else {
				dato[3]="Si";
			}
			
			if (result.getString(6).equals("0")) {
				dato[4]="No";
			}
			else {
				dato[4]="Si";
			}
			
			model.addRow(dato);		
				
		}
		
		chequearTamanioTablePendientes(table1.getRowCount());
	}
		
	private void chequearTamanioTableTodas(int rows) {
			
		if (rows > 11) {
				
			table1.getColumnModel().getColumn(0).setPreferredWidth(68);
			table1.getColumnModel().getColumn(1).setPreferredWidth(100);
			table1.getColumnModel().getColumn(2).setPreferredWidth(100);
			table1.getColumnModel().getColumn(3).setPreferredWidth(217);
			table1.getColumnModel().getColumn(4).setPreferredWidth(100);
			
			}
		}
			
	private void chequearTamanioTablePendientes(int rows) {
				
		if (rows > 11) {
					
			table1.getColumnModel().getColumn(0).setPreferredWidth(68);
			table1.getColumnModel().getColumn(1).setPreferredWidth(100);
			table1.getColumnModel().getColumn(2).setPreferredWidth(220);
			table1.getColumnModel().getColumn(3).setPreferredWidth(100);
			table1.getColumnModel().getColumn(4).setPreferredWidth(100);
				
				}
			}
				
	private void chequearTamanioTableFiltradas(int rows) {
					
		if (rows > 4) {
						
			table.getColumnModel().getColumn(0).setPreferredWidth(271);
			table.getColumnModel().getColumn(1).setPreferredWidth(132);
					}		
	}
	
}

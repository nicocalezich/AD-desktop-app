package frontend;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import backend.ConexionCliente;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;

public class Clientes extends JPanel {
	
	private JTable table;
	private ConexionCliente conexion;
	private JButton btnAgregar;
	private JButton buttonEliminar;
	private JButton btnEditar;

	private static final long serialVersionUID = 1L;
	
	public Clientes() throws ClassNotFoundException, SQLException {
		
		setBackground(Color.BLACK);		
		setLayout(null);
		setBounds(0, 0, 1093, 502);
		
		conexion = new ConexionCliente();
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
		
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setSelectionBackground(Color.LIGHT_GRAY);
		table.setRowHeight(30);		
		table.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);		
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setBorder(new LineBorder(Color.WHITE));
		table.setBackground(Color.DARK_GRAY);
		table.setForeground(Color.WHITE);
		table.setBounds(148, 65, 500, 338);
		add(table);		
		
		JTableHeader head = table.getTableHeader();
		head.setBackground(Color.black);
		head.setForeground(Color.white);
		head.setFont(new Font ("Corbel",Font.PLAIN,20));
		
		
		
		buttonEliminar = new JButton("");
		buttonEliminar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				buttonEliminar.setIcon(new ImageIcon(Clientes.class.getResource("/stock/deletetestBIG.png")));
				
			}
		});
		buttonEliminar.setFocusable(false);
		buttonEliminar.setOpaque(false);
		buttonEliminar.setToolTipText("Eliminar cliente");
		buttonEliminar.setIcon(new ImageIcon(Clientes.class.getResource("/stock/deletetest.png")));
		buttonEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminarCliente(table.getSelectedRow());
			}
		});
		buttonEliminar.setForeground(Color.WHITE);
		buttonEliminar.setFont(new Font("Tahoma", Font.BOLD, 20));
		buttonEliminar.setBorder(null);
		buttonEliminar.setBackground(Color.BLACK);
		buttonEliminar.setBounds(515, 11, 70, 75);
		add(buttonEliminar);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setFont(new Font("Tahoma", Font.PLAIN, 13));
		scrollPane.setBackground(Color.DARK_GRAY);
		scrollPane.setBounds(10, 97, 1077, 394);
		add(scrollPane);
		
		btnEditar = new JButton("");
		btnEditar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {			
				btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/stock/75476-2019-02-08-edit-icon-png-small-11563142463qiwrzqx0e1BIG.png")));				
			}
		});
		btnEditar.setToolTipText("Editar cliente");
		btnEditar.setFocusable(false);
		btnEditar.setOpaque(false);
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/stock/75476-2019-02-08-edit-icon-png-small-11563142463qiwrzqx0e1.png")));
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			editarCliente(table.getSelectedRow());
			
			}
			
		});
		btnEditar.setForeground(Color.WHITE);
		btnEditar.setBorder(null);
		btnEditar.setBackground(Color.BLACK);
		btnEditar.setBounds(650, 11, 70, 75);
		add(btnEditar);
		
	    btnAgregar = new JButton("");
		btnAgregar.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				btnAgregar.setIcon(new ImageIcon(Clientes.class.getResource("/stock/sign-add-iconBIG.png")));
				
			}
		});
		btnAgregar.setFocusable(false);
		btnAgregar.setToolTipText("Agregar cliente");
		btnAgregar.setOpaque(false);
		btnAgregar.setIcon(new ImageIcon(Clientes.class.getResource("/stock/sign-add-icon.png")));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
															
				agregarCliente();
		}});

					
		btnAgregar.setForeground(Color.WHITE);
		btnAgregar.setFont(new Font("Tahoma", Font.BOLD, 20));
		btnAgregar.setBorder(null);
		btnAgregar.setBackground(Color.BLACK);
		btnAgregar.setBounds(373, 11, 70, 75);
		add(btnAgregar);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				btnAgregar.setIcon(new ImageIcon(Clientes.class.getResource("/stock/sign-add-icon.png")));
				buttonEliminar.setIcon(new ImageIcon(Clientes.class.getResource("/stock/deletetest.png")));
				btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/stock/75476-2019-02-08-edit-icon-png-small-11563142463qiwrzqx0e1.png")));
			}
		});
		lblNewLabel.setIcon(new ImageIcon(Clientes.class.getResource("/images2/frame.jpg")));
		lblNewLabel.setBounds(-12, 0, 1099, 498);
		add(lblNewLabel);
		
		llamar();

	}
	
	private void llamar() throws ClassNotFoundException, SQLException {
		
		Class.forName("com.mysql.jdbc.Driver");
		
		java.sql.Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/admanagments","root","");
		
		String sql = "SELECT * FROM clientes";
		
		java.sql.Statement st;
		
		DefaultTableModel model = new DefaultTableModel();
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		
		model.addColumn("ID");
		model.addColumn("Nombre");
		model.addColumn("Telefono");
		model.addColumn("Correo");
		model.addColumn("Direccion");
		model.addColumn("Localidad");

		table.setModel(model);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(40);
		table.getColumnModel().getColumn(1).setPreferredWidth(225);
		table.getColumnModel().getColumn(2).setPreferredWidth(135);
		table.getColumnModel().getColumn(3).setPreferredWidth(240);
		table.getColumnModel().getColumn(4).setPreferredWidth(271);
		table.getColumnModel().getColumn(5).setPreferredWidth(150);
		
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
		
	public String ingresoDatos(String pregunta) {
		
		String nombre = JOptionPane.showInputDialog(null,pregunta,"Nuevo cliente", JOptionPane.PLAIN_MESSAGE);
		
		return nombre;
		
	}
	
	private void agregarCliente(){
		
		String nombre = ingresoDatos("*Ingrese nombre");
		if (nombre.equals(null)) {}
		String telefono = ingresoDatos("Ingrese telefono");
		if (telefono.equals(null)) {}
		String correo = ingresoDatos("Ingrese correo");
		if (correo.equals(null)) {}
		String direccion = ingresoDatos("*Ingrese direccion");
		if (direccion.equals(null)) {}
		String instagram= ingresoDatos("Ingrese Localidad");
								
		if (!(nombre.equals("") || direccion.equals(""))) {
			
			try {
				conexion.agregarCliente(nombre, telefono, correo, direccion, instagram);	
				llamar();
			} catch (SQLException | ClassNotFoundException e1) {						
				e1.printStackTrace();
				}								
		}				
		
		else {					
			JOptionPane.showMessageDialog(null, "Nombre y direccion son obligatorios","Error",JOptionPane.ERROR_MESSAGE);						
	}
		btnAgregar.setIcon(new ImageIcon(Clientes.class.getResource("/stock/sign-add-icon.png")));
		
	}
	
	private void eliminarCliente(int row) {		
	
	if(row >= 0) {
						
		int respuesta = JOptionPane.showConfirmDialog(null,"Desea eliminar este registro?","Eliminar",JOptionPane.PLAIN_MESSAGE);
			
			if (respuesta == 0) {

				String ID = (String) table.getValueAt(row,0);
						
				try {
					conexion.eliminarCliente(ID);
				} catch (ClassNotFoundException | SQLException e1) {									
					e1.printStackTrace();
					}	
				
				try {
					llamar();
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
			}						
		}
		
	else {
		JOptionPane.showMessageDialog(null,"Debe seleccionar un registro","Error",JOptionPane.ERROR_MESSAGE);
	}
	
	buttonEliminar.setIcon(new ImageIcon(Clientes.class.getResource("/stock/deletetest.png")));
		
	}
	
	private void editarCliente(int row) {
		
		if(row >=0 )  {		
								
			String[] opciones = {"Nombre","Telefono","Correo","Direccion","Localidad"};
			
			String ID = (String) table.getValueAt(row,0); 
			 																				
			int respuesta = JOptionPane.showOptionDialog(null,"Seleccione el campo que desea editar...","Editar", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, opciones, null);
								
				switch (respuesta) {
				
				case 0: try {
					
							String nuevoNombre = JOptionPane.showInputDialog(null, "Edite el nombre del cliente", "Editar", JOptionPane.PLAIN_MESSAGE);									

							if (!nuevoNombre.isEmpty()) {
								conexion.editarCliente(ID,"nombre", nuevoNombre);	
								llamar();
								}																																								
							}
																					
							catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");
								}
							break;
					
				case 1: try {
					
							String nuevoTelefono=JOptionPane.showInputDialog(null, "Edite el telefono del cliente", "Editar", JOptionPane.PLAIN_MESSAGE);
							
							if (!nuevoTelefono.isEmpty()) {
								conexion.editarCliente(ID,"telefono", nuevoTelefono);																		
								llamar();
								}													
							}
																					
							catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");
								}
							break;	
					
				case 2: try {
					
							String nuevoCorreo=JOptionPane.showInputDialog(null, "Edite el correo del cliente", "Editar", JOptionPane.PLAIN_MESSAGE);
							
							if (!nuevoCorreo.isEmpty()) {
								conexion.editarCliente(ID,"correo", nuevoCorreo);																		
								llamar();	
								}													
							}
																					
							catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");
								}
							break;
					
				case 3: try {
					
							String nuevaDireccion=JOptionPane.showInputDialog(null, "Edite la direccion del cliente", "Editar", JOptionPane.PLAIN_MESSAGE);
							
							if (!nuevaDireccion.isEmpty()) {
								conexion.editarCliente(ID,"direccion", nuevaDireccion);																		
								llamar();
								}										
							}
																					
							catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");
								}
							break;
					
				case 4: try {
							String nuevaLocalidad = JOptionPane.showInputDialog(null, "Edite el instagram del cliente", "Editar", JOptionPane.PLAIN_MESSAGE);
							
							if (!nuevaLocalidad.isEmpty()) {
								conexion.editarCliente(ID,"localidad", nuevaLocalidad);																		
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
			JOptionPane.showMessageDialog(null,"Debe seleccionar un registro","Error",JOptionPane.ERROR_MESSAGE);	
		}
		
		btnEditar.setIcon(new ImageIcon(Clientes.class.getResource("/stock/75476-2019-02-08-edit-icon-png-small-11563142463qiwrzqx0e1.png")));
		
	}
	
	private void ajustarTamanioTable(int rows) {
		
		if (rows > 17) {
			
			table.getColumnModel().getColumn(0).setPreferredWidth(40);
			table.getColumnModel().getColumn(1).setPreferredWidth(115);
			table.getColumnModel().getColumn(2).setPreferredWidth(125);
			table.getColumnModel().getColumn(3).setPreferredWidth(170);
			table.getColumnModel().getColumn(4).setPreferredWidth(155);
			table.getColumnModel().getColumn(5).setPreferredWidth(134);
			
		}
	}		
}

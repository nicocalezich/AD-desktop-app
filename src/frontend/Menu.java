package frontend;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import backend.MenuBack;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.SystemColor;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JScrollPane scrollPane;
	private MenuBack menu;
	private JButton btnStock;
	private JButton btonClientes;
	private JButton btnProveedores;
	private JButton btnCalculadora;
	private JButton btnVentas;
	private JButton btnNuevaVenta;
	private JButton btnOpciones;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Menu() throws ClassNotFoundException, SQLException{
		setIconImage(Toolkit.getDefaultToolkit().getImage(Menu.class.getResource("/images2/adIcon3.jpg")));		
		
		//caracteristicas frame
		setTitle("Menu");
		setResizable(false);	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1085, 615);		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(85, 107, 47));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);		
		setContentPane(contentPane);
		setLocationRelativeTo(null);
		setBackground(null);
														
		menu = new MenuBack();
		
		//scroll de frames
		scrollPane = new JScrollPane();
		scrollPane.getViewport().setBackground(Color.black);
		scrollPane.setBorder(null);
		scrollPane.setBounds(-3, 50, 1082, 537);
		contentPane.add(scrollPane);
		
		btnStock = new JButton("   Productos");
		btnStock.addMouseMotionListener(new MouseMotionAdapter() {
		
			public void mouseMoved(MouseEvent e) {
							
				btnStock.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));				
				
			}
		});

		btnStock.setBackground(new Color(105, 105, 105));
		btnStock.setBorder(null);
		btnStock.setFocusable(false);
		btnStock.setOpaque(false);		

		btnStock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
						
				marcarOpcionSeleccionada(btnStock.getText());
				scrollPane.setViewportView(menu.abrirStock());		
			}
		});
		
		btnStock.setHorizontalAlignment(SwingConstants.LEFT);
		btnStock.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		btnStock.setForeground(new Color(255, 255, 255));
		btnStock.setBounds(10, 5, 128, 40);
		contentPane.add(btnStock);
		
		btonClientes = new JButton("   Clientes");
		btonClientes.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				btonClientes.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));	
			}
		});
		btonClientes.setBackground(new Color(105, 105, 105));
		btonClientes.setOpaque(false);
		btonClientes.setBorder(null);
		btonClientes.setFocusable(false);
		btonClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				marcarOpcionSeleccionada(btonClientes.getText());
				scrollPane.setViewportView(menu.abrirClientes());	
			}
		});
		btonClientes.setHorizontalAlignment(SwingConstants.LEFT);
		btonClientes.setForeground(Color.WHITE);
		btonClientes.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		btonClientes.setBounds(148, 5, 111, 40);
		contentPane.add(btonClientes);
		
		btnProveedores = new JButton("   Proveedores");
		btnProveedores.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				btnProveedores.setFont(new Font("Yu Gothic Medium", Font.PLAIN,16));	
			}
		});
		btnProveedores.setBackground(new Color(105, 105, 105));
		btnProveedores.setOpaque(false);
		btnProveedores.setBorder(null);
		btnProveedores.setFocusable(false);
		btnProveedores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				marcarOpcionSeleccionada(btnProveedores.getText());
				scrollPane.setViewportView(menu.abrirProveedores());	
			}
		});

		btnProveedores.setHorizontalAlignment(SwingConstants.LEFT);
		btnProveedores.setForeground(Color.WHITE);
		btnProveedores.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		btnProveedores.setBounds(269, 5, 143, 40);
		contentPane.add(btnProveedores);
		
		btnCalculadora = new JButton("   Calculadora");
		btnCalculadora.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				btnCalculadora.setFont(new Font("Yu Gothic Medium", Font.PLAIN,16));	
			}
		});
		btnCalculadora.setBackground(new Color(105, 105, 105));
		btnCalculadora.setBorder(null);
		btnCalculadora.setFocusable(false);
		btnCalculadora.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				marcarOpcionSeleccionada(btnCalculadora.getText());
				scrollPane.setViewportView(menu.abrirCalculadora());
			}
		});
		btnCalculadora.setOpaque(false);
		btnCalculadora.setHorizontalAlignment(SwingConstants.LEFT);
		btnCalculadora.setForeground(Color.WHITE);
		btnCalculadora.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		btnCalculadora.setBounds(422, 5, 128, 40);
		contentPane.add(btnCalculadora);
		
		btnNuevaVenta = new JButton("   Nueva venta   ");
		btnNuevaVenta.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				
				btnNuevaVenta.setFont(new Font("Yu Gothic Medium", Font.PLAIN,16));	
			}
		});
		btnNuevaVenta.setBackground(new Color(105, 105, 105));
		btnNuevaVenta.setBorder(null);
		btnNuevaVenta.setFocusable(false);
		btnNuevaVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Stock stock = null;
				
				try {
					stock = new Stock();
				} catch (ClassNotFoundException | SQLException e1) {				
					e1.printStackTrace();
				}
				
				if (stock.productos().length > 0) { 
					
					try {
						
						menu.abrirNuevaVenta();
						
					} catch (ClassNotFoundException | SQLException e2) {
						
						e2.printStackTrace();
					}
					
				}
				
				else {
					JOptionPane.showMessageDialog(null,"No tiene stock para generar una venta","Error",JOptionPane.ERROR_MESSAGE);	
				}
			}
		});
		btnNuevaVenta.setOpaque(false);
		btnNuevaVenta.setHorizontalAlignment(SwingConstants.LEFT);
		btnNuevaVenta.setForeground(Color.WHITE);
		btnNuevaVenta.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		btnNuevaVenta.setBounds(765, 5, 143, 40);
		contentPane.add(btnNuevaVenta);
		
		btnVentas = new JButton("   Registro de ventas");
		btnVentas.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {

				btnVentas.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
				
			}
		});
		btnVentas.setBackground(new Color(105, 105, 105));
		btnVentas.setBorder(null);
		btnVentas.setFocusable(false);
		btnVentas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					
					marcarOpcionSeleccionada(btnVentas.getText());
					scrollPane.setViewportView(menu.abrirRegistroVentas());	
											
				}
				
				catch (ClassNotFoundException | SQLException e1) {
					JOptionPane.showMessageDialog(null, "No se pudo abrir el frame");
				}
			}
		});
		btnVentas.setOpaque(false);
		btnVentas.setHorizontalAlignment(SwingConstants.LEFT);
		btnVentas.setForeground(Color.WHITE);
		btnVentas.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		btnVentas.setBounds(572, 5, 170, 40);
		contentPane.add(btnVentas);
					
		JLabel lblNewLabel_1 = new JLabel("");		
		lblNewLabel_1.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btnStock.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
				btonClientes.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
				btnProveedores.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
				btnCalculadora.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
				btnCalculadora.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
				btnVentas.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
				btnNuevaVenta.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
				btnOpciones.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
				
			}
		});
		
		btnOpciones = new JButton("   Opciones");
		btnOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				menu.abrirCambioContra();
			}
		});
		btnOpciones.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				btnOpciones.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 16));
			}
		});
		btnOpciones.setOpaque(false);
		btnOpciones.setHorizontalAlignment(SwingConstants.LEFT);
		btnOpciones.setForeground(Color.WHITE);
		btnOpciones.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 15));
		btnOpciones.setFocusable(false);
		btnOpciones.setBorder(null);
		btnOpciones.setBackground(SystemColor.controlDkShadow);
		btnOpciones.setBounds(926, 5, 143, 40);
		contentPane.add(btnOpciones);
		lblNewLabel_1.setIcon(new ImageIcon(Menu.class.getResource("/images2/test2.png")));
		lblNewLabel_1.setOpaque(true);
		lblNewLabel_1.setFont(new Font("Yu Gothic Medium", Font.ITALIC, 33));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBackground(new Color(47, 79, 79));
		lblNewLabel_1.setBounds(-3, 0, 1082, 51);
		contentPane.add(lblNewLabel_1);
			
	}
	
	public void marcarOpcionSeleccionada(String opcion) {
		
		String stock = "   Productos";
		String clientes = "   Clientes";
		String proveedores = "   Proveedores";
		String calculadora = "   Calculadora";
		String ventas = "   Registro de ventas";
		String pedidos = "   Pedidos";
				
		if (opcion.equals(stock)) {
			
			btnStock.setText(stock+" •");
			btonClientes.setText(clientes);
			btnProveedores.setText(proveedores);
			btnCalculadora.setText(calculadora);
			btnVentas.setText(ventas);

		}
		
		else if(opcion.equals(clientes)) {
			
			btnStock.setText(stock);
			btonClientes.setText(clientes+" •");
			btnProveedores.setText(proveedores);
			btnCalculadora.setText(calculadora);
			btnVentas.setText(ventas);
			
		}
		
		else if(opcion.equals(proveedores)) {
			
			btnStock.setText(stock);
			btonClientes.setText(clientes);
			btnProveedores.setText(proveedores+" •");
			btnCalculadora.setText(calculadora);
			btnVentas.setText(ventas);			
					
		}
		
		else if(opcion.equals(calculadora)) {
			
			
			btnStock.setText(stock);
			btonClientes.setText(clientes);
			btnProveedores.setText(proveedores);
			btnCalculadora.setText(calculadora+" •");
			btnVentas.setText(ventas);	
					
		}
		
		else if(opcion.equals(ventas)) {
			
			btnStock.setText(stock);
			btonClientes.setText(clientes);
			btnProveedores.setText(proveedores);
			btnCalculadora.setText(calculadora);
			btnVentas.setText(ventas+" •");
							
		}
		
			
		else if (opcion.equals(pedidos)) {
			
			btnStock.setText(stock);
			btonClientes.setText(clientes);
			btnProveedores.setText(proveedores);
			btnCalculadora.setText(calculadora);
			btnVentas.setText(ventas);
			
		}
			
	}
}
	

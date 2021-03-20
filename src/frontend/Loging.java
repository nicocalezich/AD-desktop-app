package frontend;

import backend.Logeo;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class Loging extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordField;;
	private JLabel wrongPassword;
	private JLabel lblContraIncorrecta;
	
	  public static void main(String[] args) {
	 
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				
				try {
					Loging frame = new Loging();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.setTitle("Loging"); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}	
	
	public Loging() {
		
		//caracteristicas frame
		setResizable(false);
		setType(Type.UTILITY);
		setBackground(Color.BLACK);
		setTitle("Loging");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 952, 492);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setForeground(Color.RED);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);		
		
		//label ad managment
		JLabel lblBienvenido = new JLabel("AD managements");
		lblBienvenido.setForeground(Color.BLACK);
		lblBienvenido.setHorizontalAlignment(SwingConstants.CENTER);
		lblBienvenido.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 30));
		lblBienvenido.setBounds(626, 41, 257, 43);
		contentPane.add(lblBienvenido);
		
		//label icono Contra
		JLabel lblContra = new JLabel("");
		lblContra.setIcon(new ImageIcon(Loging.class.getResource("/loging/lock.png")));
		lblContra.setForeground(new Color(255, 255, 255));
		lblContra.setHorizontalAlignment(SwingConstants.CENTER);
		lblContra.setBounds(599, 193, 45, 57);
		contentPane.add(lblContra);
		
		//label wrongPassword
		wrongPassword = new JLabel("");
		wrongPassword.setVisible(false);
		wrongPassword.setIcon(new ImageIcon(Loging.class.getResource("/loging/wrongPass.png")));
		wrongPassword.setBounds(867, 193, 45, 57);	
		contentPane.add(wrongPassword);
					
		passwordField = new JPasswordField();
		passwordField.setOpaque(false);		
		passwordField.setBorder(null);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 13));
		passwordField.setBackground(Color.BLACK);
		passwordField.setBounds(656, 211, 212, 28);
		contentPane.add(passwordField);
		
		//btn sing in
		JButton btnIngresar = new JButton("Sign in");	
		getRootPane().setDefaultButton(btnIngresar);
		btnIngresar.setBackground(Color.LIGHT_GRAY);
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnIngresar.setForeground(Color.BLACK);
		btnIngresar.setMnemonic(KeyEvent.VK_ENTER);			
		btnIngresar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {													
					entrar();								
			}
		});
		
		btnIngresar.setBounds(626, 261, 243, 28);
		contentPane.add(btnIngresar);
		
		
		//label welcome
		JLabel welcomeLabel = new JLabel("Welcome");
		welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
		welcomeLabel.setFont(new Font("Sitka Banner", Font.ITALIC, 20));
		welcomeLabel.setForeground(Color.BLACK);
		welcomeLabel.setBounds(711, 95, 83, 14);
		contentPane.add(welcomeLabel);
		
		//separador input
		JSeparator separator = new JSeparator();
		separator.setBackground(Color.BLACK);
		separator.setBounds(655, 234, 211, 2);
		contentPane.add(separator);
		
		//wallpaper frutos
		JLabel adwallpaper = new JLabel("");
		adwallpaper.setIcon(new ImageIcon(Loging.class.getResource("/loging/logingwallpaper.png")));
		adwallpaper.setBounds(0, 0, 539, 463);
		contentPane.add(adwallpaper);
		
		lblContraIncorrecta = new JLabel("Contrase\u00F1a incorrecta");
		lblContraIncorrecta.setVisible(false);
		lblContraIncorrecta.setHorizontalAlignment(SwingConstants.CENTER);
		lblContraIncorrecta.setForeground(Color.RED);
		lblContraIncorrecta.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
		lblContraIncorrecta.setBounds(678, 237, 162, 14);
		contentPane.add(lblContraIncorrecta);
		
		//wallpaper loging
		JLabel fondo = new JLabel("");
		fondo.setIcon(new ImageIcon(Loging.class.getResource("/loging/logingwallpaper2.png")));
		fondo.setBounds(539, 0, 407, 463);
		contentPane.add(fondo);				
		
	}
	
	private void entrar(){
		
		char[] passChar = passwordField.getPassword();
		String pass = new String(passChar);								
		Logeo log = new Logeo();
																	
			try {
				
				if (log.validarContra(pass)) {
					
					dispose();					
					Menu menu = new Menu();					
					menu.setVisible(true);	
				}
											
				else {	
					
					lblContraIncorrecta.setVisible(true);
					wrongPassword.setVisible(true);
					passwordField.setText("");														
				}
			}
			
			catch (ClassNotFoundException | SQLException e1) {
				
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos");
				
			}	
	}
}

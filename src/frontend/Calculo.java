package frontend;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import backend.CalculoBack;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class Calculo extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField txtCosto;
	private JTextField txtPorcentaje;
	private JTextField txtVende;
	private JTextField txtNeta;
	private JLabel lblValoresInvalidos;
	private JButton button;
	private CalculoBack calculo;

	public Calculo() {
			
		setBackground(Color.BLACK);
		setLayout(null);
		setBounds(0, 0, 1102, 500);
		calculo = new CalculoBack();
		txtCosto = new JTextField();
		txtCosto.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtCosto.setOpaque(false);
		txtCosto.setBorder(null);
		txtCosto.setForeground(Color.WHITE);
		txtCosto.setColumns(10);
		txtCosto.setBackground(Color.BLACK);
		txtCosto.setBounds(532, 119, 162, 20);
		add(txtCosto);
		
		JLabel label = new JLabel("Costo $");
		label.setVerticalAlignment(SwingConstants.TOP);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 20));
		label.setBounds(435, 114, 95, 25);
		add(label);
		
		JLabel label_1 = new JLabel("Ganancia esperada %");
		label_1.setVerticalAlignment(SwingConstants.TOP);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.WHITE);
		label_1.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 20));
		label_1.setBounds(323, 175, 205, 36);
		add(label_1);
		
		txtPorcentaje = new JTextField();
		txtPorcentaje.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtPorcentaje.setOpaque(false);
		txtPorcentaje.setBorder(null);
		txtPorcentaje.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				
				if (arg0.getKeyCode() == KeyEvent.VK_ENTER) {
					
				calcular();
			}
			}
		});
		txtPorcentaje.setForeground(Color.WHITE);
		txtPorcentaje.setColumns(10);
		txtPorcentaje.setBackground(Color.BLACK);
		txtPorcentaje.setBounds(532, 180, 162, 20);
		add(txtPorcentaje);
		
		JLabel lblVendeA = new JLabel("Vende a");
		lblVendeA.setForeground(Color.WHITE);
		lblVendeA.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 20));
		lblVendeA.setBounds(430, 337, 115, 20);
		add(lblVendeA);
		
		JLabel lblGananciaNeta = new JLabel("Ganancia neta");
		lblGananciaNeta.setForeground(Color.WHITE);
		lblGananciaNeta.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 20));
		lblGananciaNeta.setBounds(380, 395, 155, 18);
		add(lblGananciaNeta);
		
		txtVende = new JTextField();
		txtVende.setEditable(false);
		txtVende.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtVende.setOpaque(false);
		txtVende.setBorder(null);
		txtVende.setForeground(Color.WHITE);
		txtVende.setColumns(10);
		txtVende.setBackground(Color.BLACK);
		txtVende.setBounds(538, 333, 162, 20);
		add(txtVende);
		
		txtNeta = new JTextField();
		txtNeta.setEditable(false);
		txtNeta.setFont(new Font("Tahoma", Font.PLAIN, 18));
		txtNeta.setOpaque(false);
		txtNeta.setBorder(null);
		txtNeta.setForeground(Color.WHITE);
		txtNeta.setColumns(10);
		txtNeta.setBackground(Color.BLACK);
		txtNeta.setBounds(538, 393, 162, 20);
		add(txtNeta);

		button = new JButton("Calcular");
		button.setOpaque(false);
		button.setFocusable(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				calcular();
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Yu Gothic Medium", Font.BOLD, 11));
		button.setBackground(Color.BLACK);
		button.setBounds(546, 255, 148, 28);
		add(button);
		
		JLabel label_5 = new JLabel("*Calcula a cuanto vender en base al % deseado del costo.*");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		label_5.setBackground(Color.BLACK);
		label_5.setBounds(258, 648, 293, 14);
		add(label_5);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(532, 138, 162, 2);
		add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(532, 199, 162, 2);
		add(separator_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setBounds(538, 352, 162, 2);
		add(separator_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setBounds(538, 412, 162, 2);
		add(separator_3);
		
		lblValoresInvalidos = new JLabel("Valores invalidos");
		lblValoresInvalidos.setVisible(false);
		lblValoresInvalidos.setVerticalAlignment(SwingConstants.TOP);
		lblValoresInvalidos.setHorizontalAlignment(SwingConstants.CENTER);
		lblValoresInvalidos.setForeground(Color.RED);
		lblValoresInvalidos.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 12));
		lblValoresInvalidos.setBounds(532, 283, 181, 28);
		add(lblValoresInvalidos);
		
		JLabel lblWallpaper = new JLabel("New label");
		lblWallpaper.setIcon(new ImageIcon(Calculo.class.getResource("/images2/frame.jpg")));
		lblWallpaper.setBounds(0, 0, 1135, 521);
		add(lblWallpaper);
	

	}
	
	private void calcular() {
		
		String porcentaje = txtPorcentaje.getText().replace(',','.');
		String costo = txtCosto.getText().replace(',','.');
				
		if (!costo.equals("") && !porcentaje.equals("") && camposValidos(costo,porcentaje)){
			
			lblValoresInvalidos.setVisible(false);
	
			txtVende.setText("$ "+calculo.calcularPrecioVenta(costo, porcentaje));
			txtNeta.setText("$ "+calculo.calcularGananciaNeta(costo, porcentaje));
								
			}
			
			else {
				lblValoresInvalidos.setVisible(true);
			}
	}
	
	private boolean camposValidos(String costo, String porcentaje) {
		
		return (esNumerico(costo) && esNumerico(porcentaje));
		
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
		

}

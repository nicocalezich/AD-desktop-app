package frontend;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import backend.CambioContrasenia;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class CambioContra extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();

	public static void main(String[] args) {
		try {
			CambioContra dialog = new CambioContra();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public CambioContra() {
		
		setTitle("Cambiar contraseña");
		setResizable(false);
		setBounds(100, 100, 439, 245);
		setLocationRelativeTo(null);	
		contentPanel.setLayout(null);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.LIGHT_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				
				JPasswordField campoContraAcual = new JPasswordField();
				campoContraAcual.setForeground(Color.WHITE);
				campoContraAcual.setOpaque(false);
				campoContraAcual.setBorder(null);
				campoContraAcual.setFont(new Font("Tahoma", Font.PLAIN, 13));
				campoContraAcual.setBackground(Color.WHITE);
				campoContraAcual.setBounds(185, 27, 179, 21);
				contentPanel.add(campoContraAcual);
				
				JLabel lblNewLabel = new JLabel("Contrase\u00F1a actual:");
				lblNewLabel.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 12));
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel.setBounds(63, 31, 126, 21);
				contentPanel.add(lblNewLabel);
				
				JLabel lblNuevaContrasea = new JLabel("Nueva contrase\u00F1a:");
				lblNuevaContrasea.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 12));
				lblNuevaContrasea.setForeground(Color.WHITE);
				lblNuevaContrasea.setBounds(63, 79, 109, 21);
				contentPanel.add(lblNuevaContrasea);
				
				JLabel lblConfirmar = new JLabel("Confirmar nueva contrase\u00F1a:");
				lblConfirmar.setFont(new Font("Yu Gothic Medium", Font.PLAIN, 12));
				lblConfirmar.setForeground(Color.WHITE);
				lblConfirmar.setBounds(11, 122, 169, 21);
				contentPanel.add(lblConfirmar);
				
				
				JPasswordField campoContraNueva = new JPasswordField();
				campoContraNueva.setForeground(Color.WHITE);
				campoContraNueva.setBorder(null);
				campoContraNueva.setOpaque(false);
				campoContraNueva.setFont(new Font("Tahoma", Font.PLAIN, 13));
				campoContraNueva.setBackground(Color.WHITE);
				campoContraNueva.setBounds(185, 77, 179, 21);
				contentPanel.add(campoContraNueva);
				
				JPasswordField campoConfirmacion = new JPasswordField();
				campoConfirmacion.setForeground(Color.WHITE);
				campoConfirmacion.setBorder(null);
				campoConfirmacion.setOpaque(false);
				campoConfirmacion.setFont(new Font("Tahoma", Font.PLAIN, 13));
				campoConfirmacion.setBackground(Color.WHITE);
				campoConfirmacion.setBounds(185, 118, 179, 21);
				contentPanel.add(campoConfirmacion);
				
				JLabel lblContraIncorrecta = new JLabel("");
				lblContraIncorrecta.setVisible(false);				
				lblContraIncorrecta.setIcon(new ImageIcon(CambioContra.class.getResource("/loging/wrongPass.png")));
				lblContraIncorrecta.setBounds(374, 23, 32, 32);
				contentPanel.add(lblContraIncorrecta);
				
				JLabel lbl2contraIncorrecta = new JLabel("Contrase\u00F1a actual incorrecta");
				lbl2contraIncorrecta.setVisible(false);
				lbl2contraIncorrecta.setForeground(Color.RED);
				lbl2contraIncorrecta.setFont(new Font("Tahoma", Font.BOLD, 11));
				lbl2contraIncorrecta.setBounds(193, 52, 169, 14);
				contentPanel.add(lbl2contraIncorrecta);
				
				JSeparator separator = new JSeparator();
				separator.setForeground(Color.WHITE);
				separator.setBounds(185, 139, 179, 2);
				contentPanel.add(separator);
				
				JSeparator separator_1 = new JSeparator();
				separator_1.setForeground(Color.WHITE);
				separator_1.setBounds(185, 98, 179, 2);
				contentPanel.add(separator_1);
				
				JSeparator separator_2 = new JSeparator();
				separator_2.setForeground(Color.WHITE);
				separator_2.setBounds(185, 48, 179, 2);
				contentPanel.add(separator_2);
				
				JLabel lblNewLabel_1 = new JLabel("");
				lblNewLabel_1.setIcon(new ImageIcon(CambioContra.class.getResource("/images2/frame.jpg")));
				lblNewLabel_1.setBounds(0, 0, 433, 183);
				contentPanel.add(lblNewLabel_1);
							
				//boton aceptar			
				JButton okButton = new JButton("Aceptar");
				okButton.setOpaque(true);
				okButton.addActionListener(new ActionListener() {
					
					public void actionPerformed(ActionEvent e) {
						
						CambioContrasenia update = new CambioContrasenia();
						
						char[] pass1 = campoContraAcual.getPassword();
						String contra1 = new String(pass1);	
						
						char[] pass2 = campoContraNueva.getPassword();
						String contra2 = new String(pass2);
						
						char[] pass3 = campoConfirmacion.getPassword();
						String contra3 = new String(pass3);
						
						try {
							
							if (update.contraActualValida(contra1)) {
								
								if (update.nuevaContraValida(contra2)) {
									
									if (update.nuevasContrasCoinciden(contra2, contra3)) {
										
										update.actualizarContra(contra2);
										
										JOptionPane.showMessageDialog(null, "Contraseña cambiada con exito");
										setVisible(false);
										
									}
									
									else {
										JOptionPane.showMessageDialog(null, "Las contraseñas deben coincidir");
									}
									
								}
								
								else {
									JOptionPane.showMessageDialog(null, "La nueva contraseña no es valida");
								}
								
							}
							
							else {
								
								lblContraIncorrecta.setVisible(true);
								lbl2contraIncorrecta.setVisible(true);								
								campoContraAcual.setText("");
							}
																		
							
						} catch (ClassNotFoundException | SQLException e1) {
							
							JOptionPane.showMessageDialog(null, "No se ha podido actualizar la contraseña");
							
						}														
						
					}
				});
				okButton.setActionCommand("Aceptar");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setOpaque(false);
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
			
	
		
	}
}

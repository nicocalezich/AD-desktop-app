package backend;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class TestClass {

	public static void main(String[] args) {
		
	
        Properties propiedad = new Properties();
        propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth","true");
        

        
        Session sesion = Session.getDefaultInstance(propiedad);
        String correoEnvia = "ncalezich@gmail.com";
        String contrasena = "fullplanche99";
        String receptor = "ncalezich@gmail.com";
        String asunto = "TEST";
        String mensaje= "mensaje de prueba";
        
        MimeMessage mail = new MimeMessage(sesion);
        
        try {
        	
            mail.setFrom(new InternetAddress (correoEnvia));
            mail.addRecipient(Message.RecipientType.TO, new InternetAddress (receptor));
            mail.setSubject(asunto);
            mail.setText(mensaje);
            
            Transport transportar = sesion.getTransport("smtp");
            transportar.connect(correoEnvia,contrasena);
            transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));          
            transportar.close();
            
            System.out.println("funciona");
            
            
        } catch (AddressException ex) {
        	System.out.println("no funciona");
        } catch (MessagingException ex) {
        	System.out.println("no funciona");
        }
	
     
        /*
		
		Properties propiedad = new Properties();
		
	  propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
        propiedad.setProperty("mail.smtp.starttls.enable", "true");
        propiedad.setProperty("mail.smtp.port", "587");
        propiedad.setProperty("mail.smtp.auth","true");
		
		Session sesion = Session.getDefaultInstance(propiedad);
		
		String correoEnvia = "ncalezich@gmail.com";
		String contrasenia = "fullplanche99";
		String destinatario = "ncalezich@gmail.com";
		String asunto = "TEST";
		String mensaje = "mensaje de prueba";
		
		MimeMessage mail =  new MimeMessage(sesion);
		
		
		try {
			mail.setFrom(new InternetAddress(correoEnvia));
			mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			mail.setSubject(asunto);
			mail.setText(mensaje);
			
			Transport transporte = sesion.getTransport("smtp");
			transporte.connect(correoEnvia,contrasenia);
			transporte.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
			transporte.close();
						
			
		} catch (MessagingException e) {
			e.printStackTrace();
			System.out.println("no funciona");
		}
		
		 */

	}
	

	
}

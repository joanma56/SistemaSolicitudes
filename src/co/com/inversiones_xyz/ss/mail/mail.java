package co.com.inversiones_xyz.ss.mail;

import java.io.IOException;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
// [END simple_includes]

// [START multipart_includes]
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import javax.activation.DataHandler;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
// [END multipart_includes]

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class mail {
	
	private static String fromMail="armj.36@gmail.com";
	
	public static void enviarCorreo(String correoDestino, String mensaje, Integer radicado) 
			throws MessagingException, UnsupportedEncodingException{
		  // [START simple_example]
		System.out.println(correoDestino);
	    Properties props = new Properties();
	    Session session = Session.getInstance(props);//(props, null);

	    try {
	      Message msg = new MimeMessage(session);
	      msg.setFrom(new InternetAddress(fromMail, "Inversiones_xyz"));
	      msg.addRecipient(Message.RecipientType.TO,
	                       new InternetAddress(correoDestino, "Mr. User"));
	      msg.setSubject("Respuesta a su solicitud con radicado #"+radicado.toString());
	      msg.setText(mensaje);
	      Transport.send(msg);
	    } catch (AddressException e) {
	      	throw new AddressException(e.getMessage());
	    } catch (MessagingException e) {
	    	throw new MessagingException(e.getMessage());
	    } catch (UnsupportedEncodingException e) {
	    	throw new UnsupportedEncodingException(e.getMessage());
	    }
	    // [END simple_example]
	}
	
	

}

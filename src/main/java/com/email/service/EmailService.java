package com.email.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Service;

@Service
public class EmailService {

	public boolean sendEmail(String to, String subject, String message) {
		
		boolean f = false;
		String from="123maliamr@gmail.com";
		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();
		System.out.println(properties);

		// setting important properties

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// step1 to get session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("123maliamar@gmail.com", "txruyhqhsxsdhsoj");
			}

		});

		session.setDebug(true);

		// step2. compose the message [text,multimedia]

		MimeMessage messageObj = new MimeMessage(session);

		try {
			// from mail
			messageObj.setFrom(from);
			// mail recevier
			messageObj.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// adding subject to message
			messageObj.setSubject(subject);
			// adding text message
			messageObj.setText(message);

			// step3. send messge using transport class
			Transport.send(messageObj);
			System.out.println("sent success.....");
			f= true;
		} catch (MessagingException e) {

			e.printStackTrace();
		}
		return f;

	}

}

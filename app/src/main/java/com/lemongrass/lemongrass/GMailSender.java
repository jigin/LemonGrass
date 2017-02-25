package com.lemongrass.lemongrass;

import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class GMailSender extends javax.mail.Authenticator {   
    private String mailhost = "smtp.gmail.com";   
    private String user;   
    private String password;   
    private Session session;
 
    static {   
        Security.addProvider(new JSSEProvider());   
    }  
 
    public GMailSender(String user, String password) {   
        this.user = user;   
        this.password = password;   
 
       /* Properties props = new Properties();*/   
/*        props.setProperty("mail.smtp.port", "465");
        props.setProperty("mail.smtp.socketFactory.port", "465");
        props.setProperty("mail.smtp.host", "smtp.gmail.com");
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.auth", "true");  */
 
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getDefaultInstance(props, this);
    }   
 
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(user, password);
    }   
 
    public synchronized void sendMail(String subject, String body, String sender, String recipients) throws Exception {   
        try{
        MimeMessage message = new MimeMessage(session);
        DataHandler handler = new DataHandler(new ByteArrayDataSource(body.getBytes(), "text/plain"));
        message.setSender(new InternetAddress(sender));
        message.setSubject(subject);   
        message.setDataHandler(handler);   
        if (recipients.indexOf(',') > 0)   
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
        else 
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));

       /* Transport transport = session.getTransport("smtps");
    	transport.connect ("smtp.gmail.com", 587, user, password);*/
        Transport.send(message);
                	
        
            Log.e(" Sendin Email","TRUE");

        
        }catch(Exception e){
         Log.e("Error Sendin Email", e.toString());
        }
    }   
 
    public class ByteArrayDataSource implements DataSource {
        private byte[] data;   
        private String type;   
 
        public ByteArrayDataSource(byte[] data, String type) {   
            super();   
            this.data = data;   
            this.type = type;   
        }   
 
        public ByteArrayDataSource(byte[] data) {   
            super();   
            this.data = data;   
        }   
 
        public void setType(String type) {   
            this.type = type;   
        }   
 
        public String getContentType() {   
            if (type == null)   
                return "application/octet-stream";   
            else 
                return type;   
        }   
 
        public InputStream getInputStream() throws IOException {   
            return new ByteArrayInputStream(data);   
        }   
 
        public String getName() {   
            return "ByteArrayDataSource";   
        }   
 
        public OutputStream getOutputStream() throws IOException {   
            throw new IOException("Not Supported");   
        }   
    }   
}
package lk.ijse.salongeetha.util;

import lk.ijse.salongeetha.db.DBConnection;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class SendMail {
    public static boolean Authentication(String to, String text) {
        final String user = "myprojectisuru@gmail.com";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.transport.protocl", "smtp");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("myprojectisuru@gmail.com", "eslpgcdqzelypxgh");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("Authentication");
            message.setText(text);
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
    public static void sendPDF() throws SQLException, ClassNotFoundException {
        String to=null;
        PreparedStatement preparedStatement = DBConnection.getDBConnection().getConnection()
                .prepareStatement("SELECT Email FROM Employee WHERE Job_title = 'Admin'");
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
             to = String.valueOf(resultSet.getObject(1));
        }

        try {
            final String user = "myprojectisuru@gmail.com";

            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "465");
            props.put("mail.smtp.ssl.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.transport.protocl", "smtp");
            Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("myprojectisuru@gmail.com", "eslpgcdqzelypxgh");
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
            message.setSubject("Message Aleart");


            MimeBodyPart messageBodyPart2 = new MimeBodyPart();
//            messageBodyPart2.setContent(message,);
//            String filename = "CustomerReport.pdf";//change accordingly
//            DataSource source = new FileDataSource(filename);
//            messageBodyPart2.setDataHandler(new DataHandler(source));
//            messageBodyPart2.setFileName(filename);
            //Create Multipart object and add MimeBodyPart objects to this object
            Multipart multipart = new MimeMultipart();
//            multipart.addBodyPart(messageBodyPart1);
            multipart.addBodyPart(messageBodyPart2);

            MimeBodyPart attachment=new MimeBodyPart();
            attachment.attachFile(new File("C:\\Users\\ACER\\Desktop\\Salon Geetha\\SalonGeetha\\src\\lk\\ijse\\salongeetha\\report\\CustomerReport.pdf"));
            multipart.addBodyPart(attachment);
            //Set the multiplart object to the message object
            message.setContent(multipart );

            //Send message
            Transport.send(message);
            System.out.println("message sent....");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
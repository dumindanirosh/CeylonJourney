package com.duminda.ceylonjourney.util;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * This class is a utility which sends email to a given email address.
 * @author Duminda
 */
public class MailUtil {

    private static String senderEmail = "";
    private static String senderEmailPassword = "";
    private static String senderHost = "smtp.gmail.com";
   private static String hostAddress = "smtp.gmail.com";
    private static String hostPort = "465";

    public MailUtil() {
    }

    public void sendEmail(String reciptentEmail, String subject, String text) {

        Properties props = new Properties();
        props.put("mail.smtp.user", senderEmail);
        props.put("mail.smtp.host", hostAddress);
        props.put("mail.smtp.port", hostPort);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
//props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.socketFactory.port", hostPort);
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");


        try {
            Authenticator authenticator = new SMTPAuthenticator();
            Session session = Session.getInstance(props, authenticator);
//session.setDebug(true);

            MimeMessage mimeMessage = new MimeMessage(session);
            mimeMessage.setText(text);
            mimeMessage.setSubject(subject);
            mimeMessage.setFrom(new InternetAddress(senderEmail));
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(reciptentEmail));
            Transport.send(mimeMessage);

        } catch (Exception mex) {
            mex.printStackTrace();
        }

    }

//    public static void main(String[] args) {
//
//        MailUtil mailUtil = new MailUtil();
//
//        mailUtil.sendEmail(reciptentEmail, subject, text);
//
//
//    }

    private class SMTPAuthenticator extends javax.mail.Authenticator {

        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(senderEmail, senderEmailPassword);
        }
    }
}

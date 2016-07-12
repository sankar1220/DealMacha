package com.dealmacha.mail;

import java.io.InputStream;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

public class JavaMailSenderImpl implements JavaMailSender {
    private static final Logger LOGGER = Logger.getLogger(JavaMailSenderImpl.class);
    @Autowired
    private JavaMailSender mailSender;

    private Properties mailProperties;
    @Autowired
    private SimpleMailMessage templateMailMessage;

    @Override
    public MimeMessage createMimeMessage() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public MimeMessage createMimeMessage(final InputStream contentStream) throws MailException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void send(final MimeMessage mimeMessage) throws MailException {
        // TODO Auto-generated method stub
    }

    @Override
    public void send(final MimeMessage[] mimeMessages) throws MailException {
        // TODO Auto-generated method stub
    }

    @Override
    public void send(final MimeMessagePreparator mimeMessagePreparator) throws MailException {
        // TODO Auto-generated method stub
    }

    @Override
    public void send(final MimeMessagePreparator[] mimeMessagePreparators) throws MailException {
        // TODO Auto-generated method stub

    }

    @Override
    public void send(final SimpleMailMessage simpleMessage) throws MailException {
        LOGGER.info("mail sender is" + simpleMessage);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(simpleMessage.getTo());
        mailMessage.setReplyTo(simpleMessage.getReplyTo());
        mailMessage.setFrom(simpleMessage.getFrom());
        mailMessage.setSubject(simpleMessage.getSubject());
        mailMessage.setText(simpleMessage.getText());
        mailSender.send(mailMessage);
    }

    @Override
    public void send(final SimpleMailMessage[] simpleMessages) throws MailException {
        // TODO Auto-generated method stub

    }

    public void sendMail(final String to, final String from, final String replyTo, final String subject, final String plainText,
            final String htmlText) {

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setFrom(from);
            helper.setTo(to);
            helper.setReplyTo(replyTo);
            helper.setSubject(subject);
            helper.setText(plainText, htmlText);

            FileSystemResource file = new FileSystemResource("attachment.jpg");
            helper.addAttachment(file.getFilename(), file);

            mailSender.send(mimeMessage);
        }
        catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public void setHost(final String host) {

    }

    public void setMailProperties(final Properties mailProperties) {
        this.mailProperties = mailProperties;
    }

    @Autowired
    public void setMailSender(final JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setPassword(final String password) {
        // TODO Auto-generated method stub

    }

    public void setPort(final int port) {
        // TODO Auto-generated method stub

    }

    public void setProtocol(final Object protocol) {

    }

    public void setSimpleMailMessage(final SimpleMailMessage templateMailMessage) {
        this.templateMailMessage = templateMailMessage;
    }

    public void setUsername(final String username) {
        // TODO Auto-generated method stub

    }
}

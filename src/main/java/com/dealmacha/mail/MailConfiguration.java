package com.dealmacha.mail;

import java.util.Properties;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.dealmacha.mail.MailProperties.Smtp;

@Configuration
@ConfigurationProperties(locations = "classpath:mail.properties", prefix = "mail")
public class MailConfiguration {

    private boolean auth;
    private boolean starttlsEnable;
    @NotBlank
    private String host;
    private int port;
    private String from;
    private String username;
    private String password;
    private String protocol;
    @NotNull
    private Smtp smtp;

  /*  @Bean
    public ClassLoaderTemplateResolver emailTemplateResolver() {
        ClassLoaderTemplateResolver emailTemplateResolver = new ClassLoaderTemplateResolver();
        emailTemplateResolver.setPrefix("/mail/");
        emailTemplateResolver.setSuffix(".html");
        emailTemplateResolver.setTemplateMode("HTML5");
        emailTemplateResolver.setCharacterEncoding("UTF-8");
        emailTemplateResolver.setOrder(1);

        return emailTemplateResolver;
    }*/

    public String getFrom() {
        return from;
    }

    public String getHost() {
        return host;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    public String getProtocol() {
        return protocol;
    }

    public Smtp getSmtp() {
        return smtp;
    }

    public String getUsername() {
        return username;
    }

    public boolean isAuth() {
        return auth;
    }

    public boolean isStarttlsEnable() {
        return starttlsEnable;
    }

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        Properties mailProperties = new Properties();
        mailProperties.put("mail.smtp.auth", auth);
        mailProperties.put("mail.smtp.starttls.enable", starttlsEnable);
        mailSender.setJavaMailProperties(mailProperties);
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setProtocol(protocol);
        mailSender.setUsername(username);
        mailSender.setPassword(password);
        return mailSender;
    }

    public void setAuth(final boolean auth) {
        this.auth = auth;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setPort(final int port) {
        this.port = port;
    }

    public void setProtocol(final String protocol) {
        this.protocol = protocol;
    }

    public void setSmtp(final Smtp smtp) {
        this.smtp = smtp;
    }

    public void setStarttlsEnable(final boolean starttlsEnable) {
        this.starttlsEnable = starttlsEnable;
    }

    public void setUsername(final String username) {
        this.username = username;
    }
}

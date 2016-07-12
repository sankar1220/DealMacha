package com.dealmacha.mail;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(locations = "classpath:mail.properties", ignoreUnknownFields = false, prefix = "mail")
public class MailProperties {

    @NotBlank
    private String host;

    private int port;
    private String from;
    private String username;
    private String password;

    @NotNull
    private Smtp smtp;

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

    public Smtp getSmtp() {
        return smtp;
    }

    public String getUsername() {
        return username;
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

    public void setSmtp(final Smtp smtp) {
        this.smtp = smtp;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public static class Smtp {

        private boolean auth;
        private boolean starttlsEnable;

        public boolean isAuth() {
            return auth;
        }

        public boolean isStarttlsEnable() {
            return starttlsEnable;
        }

        public void setAuth(final boolean auth) {
            this.auth = auth;
        }

        public void setStarttlsEnable(final boolean starttlsEnable) {
            this.starttlsEnable = starttlsEnable;
        }

    }

}

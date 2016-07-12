package com.dealmacha.mail;

import com.dealmacha.domain.Users;

import java.util.Date;

public class Mail {

    private String mailFrom;

    private String mailTo;

    private String mailCc;

    private String mailBcc;

    private String mailSubject;

    private String mailContent;

    private String templateName;

    private String contentType;
    private String charSet;
    private String htmlText;

    private Users users;

    public Mail() {
        contentType = "text/html";
        charSet = "UTF-8";
    }

    public String getCharSet() {
        return charSet;
    }

    public String getContentType() {
        return contentType;
    }

    public String getHtmlText() {
        return htmlText;
    }

    public String getMailBcc() {
        return mailBcc;
    }

    public String getMailCc() {
        return mailCc;
    }

    public String getMailContent() {
        return mailContent;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public Date getMailSendDate() {
        return new Date();
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public String getMailTo() {
        return mailTo;
    }

    public String getTemplateName() {
        return templateName;
    }

    public Users getUsers() {
        return users;
    }

    public void setCharSet(final String charSet) {
        this.charSet = charSet;
    }

    public void setContentType(final String contentType) {
        this.contentType = contentType;
    }

    public void setHtmlText(final String htmlText) {
        this.htmlText = htmlText;
    }

    public void setMailBcc(final String mailBcc) {
        this.mailBcc = mailBcc;
    }

    public void setMailCc(final String mailCc) {
        this.mailCc = mailCc;
    }

    public void setMailContent(final String mailContent) {
        this.mailContent = mailContent;
    }

    public void setMailFrom(final String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public void setMailSubject(final String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public void setMailTo(final String mailTo) {
        this.mailTo = mailTo;
    }

    public void setTemplateName(final String templateName) {
        this.templateName = templateName;
    }

    public void setUsers(final Users users) {
        this.users = users;
    }

    @Override
    public String toString() {
        StringBuilder lBuilder = new StringBuilder();
        lBuilder.append("Mail From:- ").append(getMailFrom());
        lBuilder.append("Mail To:- ").append(getMailTo());
        lBuilder.append("Mail Cc:- ").append(getMailCc());
        lBuilder.append("Mail Bcc:- ").append(getMailBcc());
        lBuilder.append("Mail Subject:- ").append(getMailSubject());
        lBuilder.append("Mail Send Date:- ").append(getMailSendDate());
        lBuilder.append("Mail Content:- ").append(getMailContent());
        lBuilder.append("Mail CharSet:- ").append(getCharSet());
        lBuilder.append("Users :- ").append(getUsers());
        return lBuilder.toString();
    }

}

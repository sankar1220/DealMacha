/**
 *
 */
package com.dealmacha.service;

import com.dealmacha.domain.Users;
import com.dealmacha.mail.Mail;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

/**
 * @author varma
 *
 */
@Component
@ImportResource("classpath:spring-thymeleaf.xml")
@PropertySource("classpath:mail.properties")
@PropertySource("classpath:application.properties")
public class MailService implements IMailService {

    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private TemplateEngine templateEngine;

    @Value("${mail.from}")
    private String mailFrom;
    @Value("${mail.username}")
    private String mailUserName;
    @Value("${url}")
    private String url;

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IMailService#sendUserActivationMail(com.meat.mail.Mail, com.meat.domain.Users)
     */
    @Override
    public void sendUserActivationMail(final Mail mail, final Users users) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getMailFrom());
        message.setTo(mail.getMailTo());
        message.setSubject(mail.getMailSubject());
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(final MimeMessage mimeMessage) throws Exception {
                Context context = new Context();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
                context.setVariable("userName", users.getUserName());
                message.setTo(mail.getMailTo());
                message.setSubject(mail.getMailSubject());
                message.setFrom(mail.getMailFrom());
                String html = templateEngine.process("email-templates/accountactivationsuccess", context);
                message.setText(html, true);

            }

        };
        //Send email using the autowired mailSender
        mailSender.send(preparator);
    }

    /**
     * {@inheritDoc}
     *
     * @see com.meat.service.IMailService#sendUserActivationMail(com.meat.mail.Mail, com.meat.domain.Users)
     */
  /* @Override
    public void sendUserRegistraionMail(final Mail mail, final Users users) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getMailFrom());
        message.setTo(mail.getMailTo());
        message.setSubject(mail.getMailSubject());
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(final MimeMessage mimeMessage) throws Exception {
                Context context = new Context();
                MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
                context.setVariable("userName", users.getUserName());
                String activationurl = url + "/useractivation/" + users.getId();
                context.setVariable("activationurl", activationurl);
                message.setTo(mail.getMailTo());
                message.setSubject(mail.getMailSubject());
                message.setFrom(mail.getMailFrom());
                String html = templateEngine.process("email-templates/accountactivation", context);
                message.setText(html, true);
            }
        };
        //Send email using the autowired mailSender
        mailSender.send(preparator);
    }*/

}

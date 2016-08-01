/**
 *
 */
package com.dealmacha.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
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

import com.dealmacha.domain.Users;
import com.dealmacha.mail.EmailSenderBySendGridWebApi;
import com.dealmacha.mail.Mail;

/**
 * @author varma
 *
 */
@Component
@ImportResource("classpath:spring-thymeleaf.xml")
@PropertySource("classpath:mail.properties")
@PropertySource("classpath:application.properties")
public class MailService implements IMailService {
/*
	@Autowired
	private JavaMailSender mailSender;*/
	@Autowired
	private TemplateEngine templateEngine;

	@Value("${mail.from}")
	private String mailFrom;
	@Value("${mail.username}")
	private String mailUserName;
	@Value("${url}")
	private String url;

	@Autowired
	private EmailSenderBySendGridWebApi emailSenderBySendGridWebapi;
	/**
	 * {@inheritDoc}
	 *
	 * @see com.meat.service.IMailService#sendUserActivationMail(com.meat.mail.Mail,
	 *      com.meat.domain.Users)
	 */
	@Override
	public void sendUserActivationMail(final Mail mail, final Users users) {
		/*
		 * SimpleMailMessage message = new SimpleMailMessage();
		 * message.setFrom(mail.getMailFrom()); message.setTo(mail.getMailTo());
		 * message.setSubject(mail.getMailSubject()); MimeMessagePreparator
		 * preparator = new MimeMessagePreparator() {
		 * 
		 * @Override public void prepare(final MimeMessage mimeMessage) throws
		 * Exception { Context context = new Context(); MimeMessageHelper
		 * message = new MimeMessageHelper(mimeMessage, "UTF-8");
		 * context.setVariable("userName", users.getUserName());
		 * message.setTo(mail.getMailTo());
		 * message.setSubject(mail.getMailSubject());
		 * message.setFrom(mail.getMailFrom()); String html =
		 * templateEngine.process("email-templates/accountactivationsuccess",
		 * context); message.setText(html, true);
		 * 
		 * }
		 * 
		 * }; //Send email using the autowired mailSender
		 * mailSender.send(preparator);
		 */
		String url = "https://api.sendgrid.com/api/mail.send.json";
		Context context = new Context();
		context.setVariable("userName", users.getUserName());
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(url);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("api_user", "arthvedi"));
		urlParameters.add(new BasicNameValuePair("api_key", "ArthV3d!"));
		urlParameters.add(new BasicNameValuePair("to", mail.getMailTo()));
		urlParameters.add(new BasicNameValuePair("toname", "Sankar"));
		urlParameters.add(new BasicNameValuePair("subject", mail.getMailSubject()));

		urlParameters.add(new BasicNameValuePair("from", mailFrom));

		String html = templateEngine.process("email-templates/accountactivationsuccess", context);
		urlParameters.add(new BasicNameValuePair("html", html));
		try {

			post.setEntity(new UrlEncodedFormEntity(urlParameters));
			HttpResponse response = client.execute(post);

			BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}
		} catch (Exception e) {

			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * {@inheritDoc}
	 *
	 * @see com.meat.service.IMailService#sendUserActivationMail(com.meat.mail.Mail,
	 *      com.meat.domain.Users)
	 */
	@Override
	public void sendUserRegistraionMail(final Mail mail, final Users users) {
		
		mail.setMailTo(users.getEmailId());
		mail.setMailSubject(mail.getMailSubject());
		// Preparing html template
		
		Context context = new Context();
		context.setVariable("userName", users.getUserName());
		String activationurl = url + "/useractivation/" + users.getId();
		context.setVariable("activationurl", activationurl);
		String html = templateEngine.process("email-templates/accountactivationsuccess", context);
		mail.setHtmlText(html);
		emailSenderBySendGridWebapi.sendingEmailTemplateWithoutAttachment(mail);
	}

	@Override
	public void sendUserPasswordResetMail(Mail mail, Users users) {
		/*SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(mail.getMailFrom());
		message.setTo(mail.getMailTo());
		message.setSubject(mail.getMailSubject());
		MimeMessagePreparator preparator = new MimeMessagePreparator() {
			@Override
			public void prepare(final MimeMessage mimeMessage) throws Exception {
				Context context = new Context();
				MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "UTF-8");
				context.setVariable("userName", users.getUserName());
				String activationurl = url + "/resetpassword?id=" + users.getId();
				context.setVariable("activationurl", activationurl);
				message.setTo(mail.getMailTo());
				message.setSubject(mail.getMailSubject());
				message.setFrom(mail.getMailFrom());
				String html = templateEngine.process("email-templates/recoverpassword", context);
				message.setText(html, true);

			}

		};
		// Send email using the autowired mailSender
		mailSender.send(preparator);
	}*/
	}
}

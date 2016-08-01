package com.dealmacha.mail;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:mail.properties")
@PropertySource("classpath:application.properties")
public class EmailSenderBySendGridWebApi {


	@Value("${mail.from}")
	private String mailFrom;
	@Value("${mail.username}")
	private String mailUserName;
	@Value("${mail.password}")
	private String password;
	
	@Value("${url}")
	private String url;
	
	String sendgridUrl = "https://api.sendgrid.com/api/mail.send.json";
	public void sendingEmailTemplateWithoutAttachment(final Mail mail){
		
		HttpClient client = HttpClientBuilder.create().build();
		HttpPost post = new HttpPost(sendgridUrl);
		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("api_user", mailUserName));
		urlParameters.add(new BasicNameValuePair("api_key", password));
		urlParameters.add(new BasicNameValuePair("to", mail.getMailTo()));
/*		urlParameters.add(new BasicNameValuePair("toname", "Sankar"));*/
		urlParameters.add(new BasicNameValuePair("subject", mail.getMailSubject()));
		urlParameters.add(new BasicNameValuePair("from", mailFrom));
		urlParameters.add(new BasicNameValuePair("html", mail.getHtmlText()));
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


}

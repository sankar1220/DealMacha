package com.dealmacha;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.stereotype.Component;

import com.dealmacha.api.keys.ApiKeys;

@Component
public class QueryService implements ApiKeys {
    private HttpClient httpClient = HttpClientBuilder.create().build();

    public HttpResponse sendHttpRequestAndResponse(final String url) {

        HttpResponse response = null;

        

        try {
        	
            HttpGet getRequest = new HttpGet(url);
            getRequest.addHeader("accept", "application/json");
            response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("get Error");
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());

            }

        }
        catch (ClientProtocolException e) {

            e.printStackTrace();
        }
        catch (IOException e) {

            e.printStackTrace();
        }

        return response;
    }

    public HttpResponse sendHttpRequestAndResponseByMerchantSpecificHeaders(final String url, final String merchant) {

        HttpResponse response = null;

        HttpGet getRequest = new HttpGet(url);

        switch (merchant) {
            case "FLIPKART":
                getRequest.addHeader("Fk-Affiliate-Id", FLIPKARTAFFILIATEID);
                getRequest.addHeader("Fk-Affiliate-Token", FLIPKARTAFFILIATETOKEN);
                break;
        }
        try {
            response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("get Error");
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());

            }

        }
        catch (ClientProtocolException e) {

            e.printStackTrace();
        }
        catch (IOException e) {

            e.printStackTrace();
        }

        return response;
    }

    public HttpResponse sendHttpRequestAndResponseByOffersSpecificHeaders(final String url) {

        HttpResponse response = null;

        HttpGet getRequest = new HttpGet(url);

        getRequest.addHeader("Payoom-Affiliate-Id", NETWORK_ID);
        getRequest.addHeader("Payoom-Affiliate-Token", API_KEY);

        try {
            response = httpClient.execute(getRequest);

            if (response.getStatusLine().getStatusCode() != 200) {
                System.out.println("get Error");
                throw new RuntimeException("Failed : HTTP error code : " + response.getStatusLine().getStatusCode());

            }

        }
        catch (ClientProtocolException e) {

            e.printStackTrace();
        }
        catch (IOException e) {

            e.printStackTrace();
        }

        return response;
    }

}

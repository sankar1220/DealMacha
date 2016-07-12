package com.dealmacha.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
    private RedirectStrategy redirect = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationFailure(final HttpServletRequest request, final HttpServletResponse response,
            final AuthenticationException exception) throws IOException, ServletException {
        // TODO Auto-generated method stub
        System.out.println("dsdasdasdasdasdasdasdasdasdasdasdasdas" + request.getParameter("channel"));
        if (request.getParameter("channel") != null) {
            //response.sendError(401);
            response.setStatus(401);
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Headers",
                    "Cache-Control, Pragma, Origin, Authorization, Content-Type,Accept, X-Requested-With");
            response.addHeader("Access-Control-Allow-Methods", "GET, PUT, OPTIONS,POST, X-XSRF-TOKEN, DELETE");
        }
        else {
            response.setStatus(200);
            super.onAuthenticationFailure(request, response, exception);
        }
        /*if(request.getParameter("channel")=="mobile"){
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }*/
        /*		else{*/
        //super.onAuthenticationFailure(request, response, exception);
        /*}*/

    }

}

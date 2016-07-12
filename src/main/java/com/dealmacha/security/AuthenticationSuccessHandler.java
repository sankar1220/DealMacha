/**
 *
 */

package com.dealmacha.security;

import com.dealmacha.domain.Users;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

/**
 * @author varma
 *
 */

@Component
public class AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    /**
     * @param authentication
     * @return
     */
    private String determineTargetUrl(final Authentication authentication) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onAuthenticationSuccess(final HttpServletRequest request, final HttpServletResponse response,
            final Authentication authentication) throws IOException, ServletException {
        // super.onAuthenticationSuccess(request, response, authentication);
        RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
        //Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
        //CustomUserDetails userDetails = (CustomUserDetails) authenticate.getPrincipal();
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // clearAuthenticationAttributes(request);
        HttpSession session = request.getSession();

        session.setAttribute("userId", user.getId());
        session.setAttribute("userName", user.getUserName());
        session.setAttribute("userType", user.getUserType());
        /* Cookie cookie = new Cookie("sessionId", session.getId());
        response.addCookie(cookie);*/
        String targetUrl = "/customer/Dashboard";

        if (request.getParameter("channel") != null) {

            response.setStatus(200);

            /*     HttpSession session1 = request.getSession(false);*/
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Headers",
                    "Cache-Control, Pragma, Origin, Authorization, Content-Type,Accept, X-Requested-With");
            response.addHeader("Access-Control-Allow-Methods", "GET, PUT, OPTIONS, POST, X-XSRF-TOKEN, DELETE");
            String userObject = user.getId() + ":::" + user.getUserName();
            response.getWriter().write(userObject);

            HttpSession session1 = request.getSession(true);
            if (session1 == null) {
                return;

            }
            System.out.println(response.getHeaderNames());
            System.out.println(response.getHeader("Set-Cookie"));
            /*  session1.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);*/
            //  redirectStrategy.sendRedirect(request, response, targetUrl);
        }
        else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }

}

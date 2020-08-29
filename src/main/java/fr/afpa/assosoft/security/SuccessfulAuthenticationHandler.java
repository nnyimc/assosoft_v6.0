package fr.afpa.assosoft.security;

import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SuccessfulAuthenticationHandler implements AuthenticationSuccessHandler {
    protected Log logger = LogFactory.getLog(this.getClass());
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain, Authentication authentication
    ) throws IOException, ServletException {
        handle(request,response,authentication);
        clearAuthenticationAttributeRequest(request);
    }


    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {
        handle(request, response, authentication);
    }

    private void clearAuthenticationAttributeRequest(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (null == session) {
            return;
        }
        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    private void handle(HttpServletRequest request,
                        HttpServletResponse response,
                        Authentication authentication) {
        String targetUrl = definirTargetUrl(authentication);

        try {
            if (response.isCommitted()) {
                logger.debug(
                        "Redirection vers: "
                                + targetUrl + " impossible car la réponse de la servlet n'est plus disponible."
                );
                return; // Permet de stopper l'execution au sein d'une condition
            }

            redirectStrategy.sendRedirect(request, response, targetUrl);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private String definirTargetUrl(final Authentication authentication) {
        Map<String, String> roleToDashboard = new HashMap<>();
        roleToDashboard.put("Administrateur plateforme", "/dashboard.html");
        roleToDashboard.put("Administrateur association", "/dashboard_2.html");
        roleToDashboard.put("Adhérent", "/dashboard_3.html");

        final Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authority = grantedAuthority.getAuthority();
            if(roleToDashboard.containsKey(authority)) {
                return roleToDashboard.get(authority);
            }
        }

       return ("/403.html");
    }
}

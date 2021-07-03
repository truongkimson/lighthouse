package com.nus.lighthouse.security;

import com.nus.lighthouse.domain.User;
import com.nus.lighthouse.repo.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    private final RedirectStrategy redirectStrategy;
    private final Map<String, String> redirectUrlMap;
    private final UserRepository userRepository;

    public CustomAuthenticationSuccessHandler(UserRepository userRepository) {
        this.redirectStrategy = new DefaultRedirectStrategy();
        this.redirectUrlMap = new HashMap<>();
        redirectUrlMap.put("ROLE_STU", "/student/home");
        redirectUrlMap.put("ROLE_LEC", "/lecturer/home");
        redirectUrlMap.put("ROLE_ADM", "/admin/home");
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        handleRedirect(request, response, authentication);
    }

    protected void handleRedirect(HttpServletRequest request, HttpServletResponse response,
                                  Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication);

        if (response.isCommitted()) {
            System.out.println("Unable to redirect");
            return;
        }
        setCurrentUserAttributes(request, authentication);
        clearAuthenticationAttributes(request);

        redirectStrategy.sendRedirect(request, response, targetUrl);
    }

    protected void clearAuthenticationAttributes(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session == null)
            return;

        session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
    }

    protected String determineTargetUrl(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            String authorityName = authority.getAuthority();
            if (redirectUrlMap.containsKey(authorityName))
                return redirectUrlMap.get(authorityName);
        }

        return "/";
    }

    protected void setCurrentUserAttributes(HttpServletRequest request, Authentication authentication) {
        HttpSession session = request.getSession(true);
        AppUserDetails userDetails = (AppUserDetails)authentication.getPrincipal();
        User user = userRepository.findById(userDetails.getUserId()).orElse(null);

        session.setAttribute("currentUser", user);
        assert user != null;
        session.setAttribute("userName", user.getFullName());
    }
}

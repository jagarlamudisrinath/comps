package org.comps.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.comps.model.User;
import org.comps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Component
public class AppAuthSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired private ObjectMapper objectMapper;
    @Autowired private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        User user = userService.findById(userDetails.getUsername());
        Map<String, Object> responseMap = Map.of("msg", "User logged-in successfully", "userId", userDetails.getUsername(),
                "user", user);
        response.getWriter().println(objectMapper.writeValueAsString(responseMap));
    }
}

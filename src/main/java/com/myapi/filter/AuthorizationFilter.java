package com.myapi.filter;



import com.myapi.entity.UserSessionPk;
import com.myapi.repository.UserRepository;
import com.myapi.repository.UserSessionRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Order(1)
@AllArgsConstructor
@NoArgsConstructor
@Component
public class AuthorizationFilter implements Filter {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserSessionRepository userSessionRepository;
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods",
                "GET,POST,OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers",
                "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");
        if(!request.getRequestURL().toString().contains("/api/")){
            filterChain.doFilter(request,response);
        }
        String sessionid= request.getHeader("Authorization");
        if(sessionid!=null){
            String email=sessionid.split("_")[0];
            String sessionID=sessionid.split("_")[1];
            System.out.println(sessionID);
            UserSessionPk userSessionPk=new UserSessionPk(email,sessionid);
            boolean userexist=userSessionRepository.existsByUserSessionPk(userSessionPk);
            if(userexist)
                filterChain.doFilter(request,response);
            else response.setStatus(406);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}

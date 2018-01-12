/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.mvc.security;

import PA165.language_school_manager.DTO.PersonDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author Matúš
 */
@WebFilter(urlPatterns = {"/lecture/new", "/lecture/create", "/lecture/edit/*", "/lecture/delete/*", "/course/new", "/course/create", "/course/delete/*",
        "/lecturer/new", "/lecturer/create", "/lecturer/edit/*", "/lecturer/delete/*"
})
public class ProtectFilter implements Filter {

    private final static Logger log = LoggerFactory.getLogger(ProtectFilter.class);

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        PersonDTO person = (PersonDTO) request.getSession().getAttribute("admin");
        
        if (person == null){
            log.debug("Person not authorized!");
            response.sendRedirect(request.getHeader("Referer"));
            return;
        }        
        
        fc.doFilter(request , response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}

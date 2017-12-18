/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.mvc.security;

import PA165.language_school_manager.DTO.PersonAuthenticateDTO;
import PA165.language_school_manager.DTO.PersonDTO;
import PA165.language_school_manager.Entities.Person;
import PA165.language_school_manager.Facade.PersonFacade;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.DatatypeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 *
 * @author Matúš
 */
@WebFilter(urlPatterns = {"/lecture/new", "/lecture/delete/*", "/course/new", "/course/create", "/course/delete/*"})
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

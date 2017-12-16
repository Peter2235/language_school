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
@WebFilter(urlPatterns = {"/lecture/*"})
public class ProtectFilter implements Filter {

    private final static Logger log = LoggerFactory.getLogger(ProtectFilter.class);

    @Override
    public void init(FilterConfig fc) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        
        String auth = request.getHeader("Authorization");
        if (auth == null){
            response401(response);
            return;
        }
        String[] creds = parseAuthHeader(auth);
        String login = creds[0];
        String passw = creds[1];
        
        PersonFacade personFacade = WebApplicationContextUtils.getWebApplicationContext(req.getServletContext()).getBean(PersonFacade.class);
        PersonDTO matchingP = personFacade.findPersonByUserName(login);
        if(matchingP == null){
            log.warn("no user with username {}", login);
            response401(response);
            return;
        }
        PersonAuthenticateDTO personAuthenticateDto = new PersonAuthenticateDTO();
        personAuthenticateDto.setPersonId(matchingP.getId());
        personAuthenticateDto.setPassword(passw);
        if (!personFacade.isAdmin(matchingP)){
            log.warn("person not admin {}", matchingP);
            response401(response);
            return;
        }
        if (!personFacade.authenticate(personAuthenticateDto)){
            log.warn("wrong credentials: username={} password={}", creds[0], creds[1]);
            response401(response);
            return;
        }
        request.setAttribute("authenticatedUser", matchingP);
        fc.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private String[] parseAuthHeader(String auth) {
        return new String(DatatypeConverter.parseBase64Binary(auth.split(" ")[1])).split(":", 2);
    }

    private void response401(HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setHeader("WWW-Authenticate", "Basic realm=\"type email and password\"");
        response.getWriter().println("<html><body><h1>401 Unauthorized</h1> Go away ...</body></html>");
    }   
}

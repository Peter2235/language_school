
import PA165.language_school_manager.Entities.Person;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Matúš
 */
public class Tools {
    
    public static String redirectNonAdmin(HttpServletRequest request, UriComponentsBuilder builder, RedirectAttributes redirectAttributes) {
        Person user = (Person) request.getSession().getAttribute("user");
        if(user == null || !user.isAdmin()){
            redirectAttributes.addFlashAttribute("alert_warning", "You are not authorized!");
            return "redirect:" + builder.path("/").build().toUriString();
        }
        return null;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.DTO;

import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Matúš
 */
@Getter
@Setter
@EqualsAndHashCode
public class PersonCreateDTO {
    
    @NotNull
    private String userName;
    
    private String firstName;
    
    @NotNull
    private String lastName;
    
    @Override
    public String toString() {
        return "PersonCreateDTO{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

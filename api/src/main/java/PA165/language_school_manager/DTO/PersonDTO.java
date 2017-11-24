/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.DTO;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Matúš
 */
@Getter
@Setter
public class PersonDTO {

    private Long id;
    private String userName;
    private String lastName;

    private PersonDTO() {

    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        return result;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonDTO other = (PersonDTO) obj;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        return true;
    }
    
    @Override
    public String toString(){
        return "PersonDTO{" + 
                "id=" + id +
                "userName=" + userName +
                ", fullName='" + lastName + '\'' +
                '}';
    }
}


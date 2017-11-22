/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.DTO;

/**
 *
 * @author MatĂşĹˇ
 */
public class PersonDTO {

    private Long id;
    private String fullName;

    private PersonDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName(){
        return fullName;
    }
    
    public void setFullName(String fullName){
       this.fullName = fullName;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
    
    @Override
    public String toString(){
        return "PersonDTO{" + 
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}


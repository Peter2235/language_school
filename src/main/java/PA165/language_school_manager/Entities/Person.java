/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.Entities;

import javax.persistence.*;
import javax.validation.constraints.*;
/**
 *
 * @author Matúš Sedlák
 */
@Entity
public class Person{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private long id;
    
    private String firstName;
    private String middleName;
    
    @NotNull
    @Column(nullable=false)
    private String lastNamel;
    
}

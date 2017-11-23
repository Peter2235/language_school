/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.service;

import java.util.Collection;
import java.util.List;

/**
 *
 * @author Matúš
 */
public interface BeanMappingService {
    /**
     * 
     * @param <T>
     * @param objects
     * @param mapToClass
     * @return 
     */
    public  <T> List<T> mapTo(Collection<?> objects, Class<T> mapToClass);
    
    /**
     * 
     * @param <T>
     * @param u
     * @param mapToClass
     * @return 
     */
    public  <T> T mapTo(Object u, Class<T> mapToClass);
}

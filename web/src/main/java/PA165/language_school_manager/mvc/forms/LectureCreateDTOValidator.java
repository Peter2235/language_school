/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.mvc.forms;

import PA165.language_school_manager.DTO.LectureCreateDTO;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Matúš
 */
public class LectureCreateDTOValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return LectureCreateDTO.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        LectureCreateDTO lectureCreateDTO = (LectureCreateDTO) o;
        /*if (lectureCreateDTO.getTime() == null) {
            return;
        }
        if (lectureCreateDTO.getLecturer() == null) {
            return;
        }
        if (lectureCreateDTO.getCourse() == null){
            return;
        }
        if (lectureCreateDTO.getTopic() == null) {
            errors.reject("LectureCreateDTOValidator");
        }*/
    }
}

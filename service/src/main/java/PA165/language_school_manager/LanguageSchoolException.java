package PA165.language_school_manager;

import org.springframework.dao.DataAccessException;

/**
 * @author Viktor Slany
 */

public class LanguageSchoolException extends DataAccessException {

    public LanguageSchoolException(String msg) {
        super(msg);
    }

    public LanguageSchoolException(String msg, Throwable cause) {
        super(msg, cause);
    }
}

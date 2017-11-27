/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.service;

import PA165.language_school_manager.Dao.LecturerDAO;
import PA165.language_school_manager.config.ServiceConfiguration;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 *
 * @author Matúš Sedlák
 */
@ContextConfiguration(classes=ServiceConfiguration.class)
public class LecturerServiceTest extends AbstractTransactionalTestNGSpringContextTests{
    @Mock
    private LecturerDAO lecturerDao;
}

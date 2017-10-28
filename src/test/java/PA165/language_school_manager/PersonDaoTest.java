/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Matúš
 */
//@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
//@TestExecutionListeners(TransactionalTestExecutionListener.class)
@Transactional
public class PersonDaoTest extends AbstractTestNGSpringContextTests {
    
}

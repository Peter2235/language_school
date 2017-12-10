/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.facade;

import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.DTO.LecturerCreateDTO;
import PA165.language_school_manager.DTO.LecturerDTO;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.config.ServiceConfiguration;
import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.service.LecturerService;
import PA165_language_school_manager.Facade.LecturerFacade;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 *
 * @author Matúš
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class LecturerFacadeTest {

    @InjectMocks
    private LecturerFacade lecturerFacade = new LecturerFacadeImpl();

    @Inject
    @Spy
    private BeanMappingService mapper;

    @Mock
    private LecturerService lecturerService;

    private LecturerCreateDTO lecturerCreateDto;
    private LecturerDTO lecturerDto;
    private LectureDTO lectureDto;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        lecturerCreateDto = new LecturerCreateDTO();
        lecturerCreateDto.setFirstName("Adam");
        lecturerCreateDto.setLastName("Adamovic");
        lecturerCreateDto.setUserName("adam123");

        lecturerDto = new LecturerDTO();
        lecturerDto.setFirstName("Peter");
        lecturerDto.setLastName("Petrovic");
        lecturerDto.setUserName("peter123");

        lectureDto = new LectureDTO();
        lectureDto.setTopic("englishlesson");
        lecturerFacade.assignNewLecture(lecturerDto.getId(), lectureDto);
        lecturerFacade.addLanguage(lecturerDto.getId(), Language.ENGLISH);
    }

    @Test
    public void findLecturerByFirstNameTest() {
        List<LecturerDTO> lecturerDTO = lecturerFacade.findLecturerByFirstName(lecturerCreateDto.getFirstName());
        assertThat(lecturerDTO.get(0))
                .isEqualTo("Adam");
        assertThat(lecturerDTO)
                .isNotNull();
    }

    @Test
    public void findLecturerByLastNameTest() {
        List<LecturerDTO> lecturers = lecturerFacade.findLecturerByLastName("Petrovic");
        assertThat(lecturers)
                .isNotEmpty();
        assertThat(lecturers)
                .contains(lecturerDto);
    }

    @Test
    public void findLecturerByLectureTest() {
        LecturerDTO lecturer = lecturerFacade.findLecturerByLecture(lectureDto);
        assertThat(lecturer)
                .isNotNull();
        assertThat(lecturer.getLectures())
                .isNotEmpty()
                .contains(lectureDto);
    }

    @Test
    public void findLecturersByLanguage(){
        List<LecturerDTO> lecturers = lecturerFacade.findLecturersByLanguage(Language.ENGLISH);
        assertThat(lecturers)
                .isNotNull()
                .hasSize(1);
        assertThat(lecturers.get(0).getFirstName())
                .isEqualTo("Peter");
    }
}

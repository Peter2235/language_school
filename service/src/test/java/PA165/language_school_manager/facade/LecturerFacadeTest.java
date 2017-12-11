/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.facade;

import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.DTO.LecturerCreateDTO;
import PA165.language_school_manager.DTO.LecturerDTO;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.config.ServiceConfiguration;
import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.service.LecturerService;
import PA165_language_school_manager.Facade.LecturerFacade;
import java.util.ArrayList;
import java.util.HashSet;
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
import java.util.Set;
import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.service.spi.ServiceException;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.verify;
import org.mockito.Spy;

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

    private Lecturer lecturer;
    private List<Lecturer> lecturerList = new ArrayList<>();

    @Before
    public void setUp() throws ServiceException {
        MockitoAnnotations.initMocks(this);

        lecturer = new Lecturer();
        lecturer.setId(1l);
        lecturer.setFirstName("Adam");
        lecturer.setLastName("Adamovic");
        lecturer.setUserName("adam123");
        lecturer.setNativeSpeaker(false);

        lecturerDto = new LecturerDTO();
        lecturerDto.setFirstName("Adam");
        lecturerDto.setLastName("Adamovic");
        lecturerDto.setUserName("adam123");
        lecturerCreateDto = new LecturerCreateDTO();
        lecturerCreateDto.setFirstName("Adam");
        lecturerCreateDto.setLastName("Adamovic");
        lecturerCreateDto.setUserName("adam123");
        Set<Language> languages = new HashSet<>(lecturerCreateDto.getLanguages());
        languages.add(Language.ENGLISH);
        lecturerCreateDto.setLanguages(languages);

        lectureDto = new LectureDTO();
        lectureDto.setTopic("englishlesson");
        lectureDto.setLecturer(lecturerDto);

        lecturerList.add(lecturer);

        when(lecturerService.findLecturerById(1l)).thenReturn(lecturer);
        when(lecturerService.findLecturerByFirstName("Adam")).thenReturn(lecturerList);
        when(lecturerService.findLecturerByLastName("Adamovic")).thenReturn(lecturerList);
        when(lecturerService.findLecturersByLanguage(Language.ENGLISH)).thenReturn(lecturerList);
    }

    /*@Test
    public void createLecturerTest() {
        lecturerFacade.createLecturer(lecturerCreateDto);
        verify(lecturerService).createLecturer(lecturer);
    }
    
    @Test
    public void updateLecturer() {
        lecturerFacade.createLecturer(lecturerCreateDto);
        verify(lecturerService).updateLecturer(lecturer);
    }
    
    @Test
    public void deleteLecturerTest() {
        lecturerFacade.createLecturer(lecturerCreateDto);
        verify(lecturerService).deleteLecturer(lecturer.getId());
    }*/

    @Test
    public void findLecturerByFirstNameTest() {
        List<LecturerDTO> lecturerDTO = lecturerFacade.findLecturerByFirstName(lecturerCreateDto.getFirstName());
        assertThat(lecturerDTO.get(0).getFirstName())
                .isEqualTo("Adam");
        assertThat(lecturerDTO)
                .isNotNull();
    }

    @Test
    public void findLecturerByLastNameTest() {
        List<LecturerDTO> lecturers = lecturerFacade.findLecturerByLastName(lecturerCreateDto.getLastName());
        assertThat(lecturers)
                .isNotEmpty();
        assertThat(lecturers)
                .contains(lecturerDto);
    }

    /*@Test
    public void findLecturerByLectureTest() {
        LecturerDTO foundLecturer = lecturerFacade.findLecturerByLecture(lectureDto);
        assertThat(foundLecturer)
                .isNotNull();
        assertThat(foundLecturer.getLectures())
                .isNotEmpty()
                .contains(lectureDto);
    }*/
    @Test
    public void findLecturersByLanguage() {
        List<LecturerDTO> lecturers
                = lecturerFacade.findLecturersByLanguage(lecturerCreateDto.getLanguages().stream().findFirst().get());
        assertThat(lecturers)
                .isNotNull()
                .hasSize(1);
        assertThat(lecturers.get(0).getFirstName())
                .isEqualTo("Adam");
    }

}

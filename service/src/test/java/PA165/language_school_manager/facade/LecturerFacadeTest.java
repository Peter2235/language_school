/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PA165.language_school_manager.facade;

import PA165.language_school_manager.DTO.CourseDTO;
import PA165.language_school_manager.DTO.LectureDTO;
import PA165.language_school_manager.DTO.LecturerCreateDTO;
import PA165.language_school_manager.DTO.LecturerDTO;
import PA165.language_school_manager.Entities.Lecture;
import PA165.language_school_manager.Entities.Lecturer;
import PA165.language_school_manager.Enums.Language;
import PA165.language_school_manager.config.ServiceConfiguration;
import PA165.language_school_manager.service.BeanMappingService;
import PA165.language_school_manager.service.LecturerService;
import PA165_language_school_manager.Facade.LecturerFacade;
import java.time.LocalDateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.hibernate.service.spi.ServiceException;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

        lecturerCreateDto = new LecturerCreateDTO();
        lecturerCreateDto.setFirstName("Adam");
        lecturerCreateDto.setLastName("Adamovic");
        lecturerCreateDto.setUserName("adam123");

        lecturerDto = new LecturerDTO();
        lecturerDto.setId(1L);
        lecturerDto.setFirstName("Adam");
        lecturerDto.setLastName("Adamovic");
        lecturerDto.setUserName("adam123");

        lecturer = new Lecturer();
        lecturer.setId(1L);
        lecturer.setFirstName("Adam");
        lecturer.setLastName("Adamovic");
        lecturer.setUserName("adam123");

        lecturerList.add(lecturer);

        lectureDto = new LectureDTO();
        lectureDto.setTopic("englishlesson");
        lectureDto.setTime(LocalDateTime.MIN);
        lectureDto.setCourse(new CourseDTO());
        lectureDto.setLecturer(lecturerDto);
        
        lecturerDto.addLecture(lectureDto);

        when(lecturerService.findLecturerByFirstName("Adam")).thenReturn(lecturerList);
        when(lecturerService.findLecturersByLanguage(Language.ENGLISH)).thenReturn(lecturerList);
        when(lecturerService.findLecturerByLastName("Adamovic")).thenReturn(lecturerList);
        when(lecturerService.findLecturerByLecture(mapper.mapTo(lectureDto, Lecture.class))).thenReturn(lecturer);
        //when(lecturerService.assignNewLecture(lecturerDto.getId(), mapper.mapTo(lectureDto , Lecture.class)));
    }

    //TODO
    /*
    @Test
    public void createLecturerTest(){
        lecturerFacade.createLecturer(lecturerCreateDto);
        verify(lecturerService).createLecturer(lecturer);
    }*/
    
    @Test
    public void deleteLecturerTest(){
        lecturerFacade.deleteLecturer(lecturerDto.getId());
        verify(lecturerService).deleteLecturer(lecturerDto.getId());
    }
    
    @Test
    public void findLecturerByFirstNameTest() {
        List<LecturerDTO> lecturerDTO = lecturerFacade.findLecturerByFirstName(lecturerCreateDto.getFirstName());
        assertThat(lecturerDTO).isNotEmpty().contains(lecturerDto);
    }

    @Test
    public void findLecturerByLastNameTest() {
        List<LecturerDTO> lecturers = lecturerFacade.findLecturerByLastName("Adamovic");
        assertThat(lecturers)
                .isNotEmpty();
        assertThat(lecturers)
                .contains(lecturerDto);
    }

    //TODO
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
    public void findLecturersByLanguage(){
        List<LecturerDTO> lecturers = lecturerFacade.findLecturersByLanguage(Language.ENGLISH);
        assertThat(lecturers)
                .isNotNull()
                .hasSize(1);
        assertThat(lecturers.get(0).getFirstName())
                .isEqualTo("Adam");
    }
}

package com.javalord.niit_web_service.niit_web_service.service;


import com.javalord.niit_web_service.niit_web_service.model.entity.Student;
import com.javalord.niit_web_service.niit_web_service.model.request.CreateStudent;
import com.javalord.niit_web_service.niit_web_service.repository.StudentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class StudentServiceTests {

    @Mock
    private StudentService studentService;
    @Mock
    private StudentRepository studentRepository;

   @BeforeAll
    public  static void openMocks() {
        MockitoAnnotations.openMocks(StudentServiceTests.class);
    }

    @Test
    public void testCreateStudent() {
        CreateStudent student = new CreateStudent();
        student.setEmail("test@test.com");
        student.setPassword("test");

        Student createdStudent = new Student();
        createdStudent.setEmail("test@test.com");
        createdStudent.setPassword("test");
        createdStudent.setStudentId("rtyuio");

        Mockito.when(studentRepository.save(createdStudent)).thenReturn(createdStudent);
    }
}

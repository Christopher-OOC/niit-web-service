package com.javalord.niit_web_service.niit_web_service.repository;

import com.javalord.niit_web_service.niit_web_service.model.entity.Student;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
@Rollback(value = false)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudentRepositoryTests {

    String studentId;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeAll
    public void setUp() {
        studentId = Instant.now().toString();
    }

    @Test
    @Order(1)
    void testCreateStudent_Success() {
        System.out.println("Student ID: " + studentId);

        Student student = new Student();
        student.setEmail("test@test.com");
        student.setPassword("test");
        student.setStudentId(studentId);

        Student savedStudent = studentRepository.save(student);

        assertNotNull(savedStudent);
        assertEquals(savedStudent.getEmail(), "test@test.com");
    }

    @Test
    @Order(2)
    void testGetStudentById_Success() {
        System.out.println("Student ID: " + studentId);

        Student student = studentRepository.findByStudentId(studentId);

        assertNotNull(student);
    }

    @Test
    @Order(2)
    void testGetStudentById_Failed() {
        Student student = studentRepository.findByStudentId("ERTYUIOIUYT");

        assertNull(student);
    }
}

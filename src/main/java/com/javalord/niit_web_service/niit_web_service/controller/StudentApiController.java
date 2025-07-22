package com.javalord.niit_web_service.niit_web_service.controller;

import com.javalord.niit_web_service.niit_web_service.model.entity.Student;
import com.javalord.niit_web_service.niit_web_service.model.request.CreateStudent;
import com.javalord.niit_web_service.niit_web_service.service.StudentService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/students")
public class StudentApiController {
    private static final Logger LOGGER = LoggerFactory.getLogger(StudentApiController.class);
    private StudentService studentService;

    public StudentApiController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody @Valid CreateStudent newStudent) {
        Student student = studentService.createStudent(newStudent);

        return ResponseEntity.ok(student);
    }

    @GetMapping(value = "/{studentId}")
    public ResponseEntity<?> findStudentById(@PathVariable(value = "studentId") int id) {
        Student student = studentService.findStudentById(id);

        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(student);
    }

    @GetMapping
    public ResponseEntity<?>  findAllStudents() {
        List<Student> students = studentService.findAllStudents();

        return ResponseEntity.ok(students);
    }

    @PostMapping(value = "/{studentId}/courses")
    public String enrollForACourse() {

        return "Course enrolled!";
    }
}


package com.javalord.niit_web_service.niit_web_service.service;

import com.javalord.niit_web_service.niit_web_service.model.entity.Student;
import com.javalord.niit_web_service.niit_web_service.model.request.CreateStudent;
import com.javalord.niit_web_service.niit_web_service.repository.StudentRepository;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.time.Instant;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(CreateStudent newStudent) {
        Student student = new Student();
        student.setStudentId(Instant.now().toString());
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setEmail(newStudent.getEmail());
        student.setPassword(newStudent.getPassword());

        return studentRepository.save(student);
    }

    public Student findStudentById(int id) {
        return studentRepository.findById(id);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }
}

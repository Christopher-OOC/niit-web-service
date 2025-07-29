package com.javalord.niit_web_service.niit_web_service.service;

import com.javalord.niit_web_service.niit_web_service.model.entity.Course;
import com.javalord.niit_web_service.niit_web_service.model.entity.Student;
import com.javalord.niit_web_service.niit_web_service.model.exception.ErrorMessages;
import com.javalord.niit_web_service.niit_web_service.model.exception.NoSuchResourceException;
import com.javalord.niit_web_service.niit_web_service.model.request.CreateStudent;
import com.javalord.niit_web_service.niit_web_service.repository.CourseRepository;
import com.javalord.niit_web_service.niit_web_service.repository.StudentRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;
    private CourseRepository courseRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public StudentService(StudentRepository studentRepository,
                          CourseRepository courseRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder
    ) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Student createStudent(CreateStudent newStudent) {
        Student student = new Student();
        student.setStudentId(Instant.now().toString());
        student.setFirstName(newStudent.getFirstName());
        student.setLastName(newStudent.getLastName());
        student.setEmail(newStudent.getEmail());
        student.setPassword(bCryptPasswordEncoder.encode(newStudent.getPassword()));

        return studentRepository.save(student);
    }

    public Student findStudentById(int id) {
        return studentRepository.findById(id);
    }

    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    public Student enrollForACourse(String studentId, int courseId) {
        Student student = studentRepository.findByStudentId(studentId);
        Course course = courseRepository.findById(courseId);
        checkStudent(student);

        if (course == null) {
            throw new NoSuchResourceException(ErrorMessages.NO_SUCH_COURSE);
        }

        student.getCurrentCourses().add(course);

        return studentRepository.save(student);
    }

    public List<Course> findAllEnrolledCourses(String studentId) {
        Student student = studentRepository.findByStudentId(studentId);
        checkStudent(student);

        return student.getCurrentCourses();
    }

    private static void checkStudent(Student student) {
        if (student == null) {
            throw new NoSuchResourceException(ErrorMessages.NO_SUCH_STUDENT);
        }
    }
}

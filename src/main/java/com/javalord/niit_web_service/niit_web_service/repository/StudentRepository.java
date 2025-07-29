package com.javalord.niit_web_service.niit_web_service.repository;

import com.javalord.niit_web_service.niit_web_service.model.entity.Course;
import com.javalord.niit_web_service.niit_web_service.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findById(int id);

    Student findByStudentId(String studentId);

    Student findByEmail(String email);

    List<Student> findByCurrentCoursesContaining(Course course);

}

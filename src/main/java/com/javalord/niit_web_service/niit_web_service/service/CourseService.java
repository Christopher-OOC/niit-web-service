package com.javalord.niit_web_service.niit_web_service.service;

import com.javalord.niit_web_service.niit_web_service.model.entity.Course;
import com.javalord.niit_web_service.niit_web_service.model.exception.ErrorMessages;
import com.javalord.niit_web_service.niit_web_service.model.exception.NoSuchResourceException;
import com.javalord.niit_web_service.niit_web_service.model.request.CreateCourse;
import com.javalord.niit_web_service.niit_web_service.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(CreateCourse request) {
        Course newCourse = new Course();
        newCourse.setName(request.getName());
        newCourse.setCode(request.getCode());

        return courseRepository.save(newCourse);
    }

    public Course updateCourse(int id, CreateCourse request) {
        Optional<Course> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NoSuchResourceException(ErrorMessages.NO_SUCH_COURSE);
        }
        Course course = optional.get();
        course.setName(request.getName());
        course.setCode(request.getCode());

        return courseRepository.save(course);
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAll();
    }

    public Course findCourseById(int id) {
        Optional<Course> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new NoSuchResourceException(ErrorMessages.NO_SUCH_COURSE);
        }
        return optional.get();
    }
}

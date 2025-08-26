package com.javalord.niit_web_service.niit_web_service.controller;

import com.javalord.niit_web_service.niit_web_service.model.entity.Course;
import com.javalord.niit_web_service.niit_web_service.model.request.CreateCourse;
import com.javalord.niit_web_service.niit_web_service.service.CourseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/courses")
public class CourseApiController {

    private CourseService courseService;

    public CourseApiController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Secured("ROLE_ADMIN, ROLE_SUPER_ADMIN")
    @PreAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostAuthorize("hasAnyRole('ADMIN', 'SUPER_ADMIN')")
    @PostMapping
    public ResponseEntity<?> createCourse(@RequestBody  CreateCourse request) {
        Course course = courseService.createCourse(request);

        return ResponseEntity.ok(course);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateCourse(@PathVariable(value = "id") int id, @RequestBody CreateCourse request) {
        Course course = courseService.updateCourse(id, request);

        return ResponseEntity.ok(course);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findCourseById(@PathVariable(value = "id") int id) {
        Course course = courseService.findCourseById(id);

        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<?> findAllCourses() {
        List<Course> courses = courseService.findAllCourses();

        return ResponseEntity.ok(courses);
    }
}

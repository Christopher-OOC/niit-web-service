package com.javalord.niit_web_service.niit_web_service.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.javalord.niit_web_service.niit_web_service.model.entity.Student;
import com.javalord.niit_web_service.niit_web_service.model.request.CreateStudent;
import com.javalord.niit_web_service.niit_web_service.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentApiController.class)
@AutoConfigureMockMvc(addFilters = false)
public class StudentApiControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Test
    void testCreateStudent_Success() throws Exception {
        String url = "/api/v1/students";

        CreateStudent student = new CreateStudent();
        student.setEmail("test@test.com");
        student.setPassword("testtests");
        student.setFirstName("RETYUIO");
        student.setLastName("ERTYUIOPPOKIJHG");

        Student createdStudent = new Student();
        createdStudent.setEmail("test@test.com");
        createdStudent.setPassword("testtests");
        createdStudent.setFirstName("RETYUIO");
        createdStudent.setLastName("ERTYUIOPPOKIJHG");

        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(student);

        Mockito.when(studentService.createStudent(Mockito.any())).thenReturn(createdStudent);

        mockMvc.perform(
                post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content)
        )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email", is("test@test.com")))
                .andDo(print());
    }
}

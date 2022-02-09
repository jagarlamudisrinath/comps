package org.comps.controller;

import org.comps.ChatengineApplication;
import org.comps.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {ChatengineApplication.class})
@Sql(scripts = {"classpath:test-data/005-students-classes.sql"}, config = @SqlConfig(errorMode = SqlConfig.ErrorMode.CONTINUE_ON_ERROR))
public class ClassStudentControllerTest {
    @Autowired TestRestTemplate restTemplate;

    @Test
    public void uploadStudents() {
        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("file", new org.springframework.core.io.ClassPathResource("test-data/005-class-students.csv"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, headers);

        ResponseEntity<String> response = restTemplate.withBasicAuth("5srinath", "password").exchange("/class-students/upload", HttpMethod.POST, entity, String.class, "");

        // Expect Ok
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);

        ResponseEntity<List<User>> userRespone = restTemplate.withBasicAuth("5srinath", "password")
                .exchange("/class-students?classId=5-CS", HttpMethod.GET, entity, new ParameterizedTypeReference<List<User>>() {});
        Assertions.assertEquals(9, userRespone.getBody().size());
    }

    @Test
    public void deleteStudent() {
        ResponseEntity<Void> deleteResponse = restTemplate.withBasicAuth("5srinath", "password")
                .exchange("/class-students?classId=5-CS2&studentId=5srinath", HttpMethod.DELETE, null, Void.class);

        Assertions.assertEquals(deleteResponse.getStatusCode(), HttpStatus.OK);

        ResponseEntity<List<User>> userRespone = restTemplate.withBasicAuth("5srinath", "password")
                .exchange("/class-students?classId=5-CS2", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        Assertions.assertEquals(1, userRespone.getBody().size());
    }
}

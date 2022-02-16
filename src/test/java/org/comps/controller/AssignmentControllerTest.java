package org.comps.controller;

import org.comps.ChatengineApplication;
import org.comps.model.Assignment;
import org.comps.model.Group;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.util.LinkedMultiValueMap;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {ChatengineApplication.class})
@Sql(scripts = {"classpath:test-data/006-assignments.sql"}, config = @SqlConfig(errorMode = SqlConfig.ErrorMode.CONTINUE_ON_ERROR))
public class AssignmentControllerTest {
    @Autowired TestRestTemplate restTemplate;

    @Test
    public void createAssignment() {
        Assignment assignment = new Assignment();
        assignment.setClassId("6-2022-CS001");
        assignment.setCreatedBy("6mark");
        assignment.setTitle("What is Computers?");
        assignment.setNoOfGroups(5);
        assignment.setId("6-2022-CS001-001");

        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("file", new org.springframework.core.io.ClassPathResource("test-data/006-assignment-questions.txt"));
        parameters.add("assignment", assignment);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, headers);

        ResponseEntity<Assignment> assignmentResponseEntity = restTemplate.withBasicAuth("6mark", "password")
                .postForEntity("/assignments", entity, Assignment.class);
        Assertions.assertEquals(HttpStatus.OK, assignmentResponseEntity.getStatusCode());

        ResponseEntity<List<Group>> groupResponseEntity = restTemplate.withBasicAuth("6mark", "password")
                .exchange("/groups?assignmentId=6-2022-CS001-001", HttpMethod.GET, null, new ParameterizedTypeReference<List<Group>>(){});
        Assertions.assertEquals(5, groupResponseEntity.getBody().size());
    }

    @Test
    public void getAssignmentsByClassId() {
        ResponseEntity<List<Assignment>> listResponseEntity = restTemplate.withBasicAuth("6srinath", "password")
                .exchange("/assignments?classId=6-2022-CS001", HttpMethod.GET, null, new ParameterizedTypeReference<List<Assignment>>() {});
        Assertions.assertTrue(listResponseEntity.getBody().size() > 0);
    }

    @Test
    public void getAssignmentFile() {
        ResponseEntity<Resource> resourceResponseEntity = restTemplate.withBasicAuth("6srinath", "password")
                .exchange("/assignments/6-2022-CS001-002/file", HttpMethod.GET, null, Resource.class);
        Assertions.assertTrue(resourceResponseEntity.getBody().getFilename().equals("6-2022-CS001-002-006-assignment-questions.txt"));
    }
}

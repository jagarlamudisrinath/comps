package org.comps.controller;

import org.comps.ChatengineApplication;
import org.comps.model.Assignment;
import org.comps.model.Class;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {ChatengineApplication.class})
@Sql(scripts = {"classpath:test-data/006-assignments.sql"}, config = @SqlConfig(errorMode = SqlConfig.ErrorMode.CONTINUE_ON_ERROR))
public class AssignmentControllerTest {
    @Autowired TestRestTemplate restTemplate;

    @Test
    public void createAssignment() {
        Assignment assignment = new Assignment();
        assignment.setClassId("2022-CS001");
        assignment.setCreatedBy("6mark");
        assignment.setTitle("What is Computers?");
        assignment.setNoOfGroups(5);
        assignment.setId("2022-CS001-001");
        ResponseEntity<Assignment> assignmentResponseEntity = restTemplate.withBasicAuth("6mark", "password")
                .postForEntity("/assignments", assignment, Assignment.class);
        Assertions.assertEquals(HttpStatus.OK, assignmentResponseEntity.getStatusCode());
    }

    @Test
    public void getAssignmentsByClassId() {
        ResponseEntity<List<Assignment>> listResponseEntity = restTemplate.withBasicAuth("6srinath", "password")
                .exchange("/assignments?classId=2022-CS001", HttpMethod.GET, null, new ParameterizedTypeReference<List<Assignment>>() {});
        Assertions.assertTrue(listResponseEntity.getBody().size() > 0);
    }
}

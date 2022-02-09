package org.comps.controller;

import org.comps.ChatengineApplication;
import org.comps.model.GroupStudent;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {ChatengineApplication.class})
@Sql(scripts = {"classpath:test-data/008-group-students.sql"}, config = @SqlConfig(errorMode = SqlConfig.ErrorMode.FAIL_ON_ERROR))
public class GroupStudentControllerTest {
    @Autowired private TestRestTemplate restTemplate;

    @Test
    public void assignGroupStudent() {
        GroupStudent groupStudent1 = new GroupStudent("2", "srinath");
        GroupStudent groupStudent2 = new GroupStudent("2", "mahesh");
        GroupStudent groupStudent3 = new GroupStudent("2", "suresh");
        GroupStudent groupStudent4 = new GroupStudent("3", "naresh");
        GroupStudent groupStudent5 = new GroupStudent("3", "vijay");
        GroupStudent groupStudent6 = new GroupStudent("3", "sahil");
        GroupStudent groupStudent7 = new GroupStudent("4", "raja");
        GroupStudent groupStudent8 = new GroupStudent("4", "ganesh");
        GroupStudent groupStudent9 = new GroupStudent("4", "harish");

        HttpEntity<List<GroupStudent>> entity = new HttpEntity<>(List.of(groupStudent1,
                groupStudent2, groupStudent3,
                groupStudent4, groupStudent5, groupStudent6,
                groupStudent7, groupStudent8, groupStudent9), new HttpHeaders());

        ResponseEntity<Map<String, String>> response = restTemplate.withBasicAuth("srinath", "password")
                .exchange("/group-students", HttpMethod.POST, entity, new ParameterizedTypeReference<Map<String, String>>() {});

        // Expect Ok
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}

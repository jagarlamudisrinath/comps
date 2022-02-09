package org.comps.controller;

import org.comps.ChatengineApplication;
import org.comps.model.Group;
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
@Sql(scripts = {"classpath:test-data/007-groups.sql"}, config = @SqlConfig(errorMode = SqlConfig.ErrorMode.CONTINUE_ON_ERROR))
public class GroupControllerTest {
    @Autowired TestRestTemplate restTemplate;

    @Test
    public void createGroup() {
        Group group = new Group();
        group.setGroupId("g1");
        group.setAssignmentId("7-CS-7-AS");
        group.setActive(true);
        group.setAnswer("test");
        ResponseEntity<Group> groupResponseEntity = restTemplate.withBasicAuth("7mark", "password")
                .postForEntity("/groups", group, Group.class);
        Assertions.assertEquals(HttpStatus.OK, groupResponseEntity.getStatusCode());
    }

    @Test
    public void getGroupsByAssignmentId() {
        ResponseEntity<List<Group>> listResponseEntity = restTemplate.withBasicAuth("7srinath", "password")
                .exchange("/groups?assignmentId=7-CS-7-AS", HttpMethod.GET, null, new ParameterizedTypeReference<List<Group>>() {});
        Assertions.assertTrue(listResponseEntity.getBody().size() > 0);
    }
}

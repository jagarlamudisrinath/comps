package org.comps.controller;

import org.comps.ChatengineApplication;
import org.comps.model.GroupStudent;
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

import java.util.List;
import java.util.Map;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {ChatengineApplication.class})
@Sql(scripts = {"classpath:test-data/008-group-students.sql"}, config = @SqlConfig(errorMode = SqlConfig.ErrorMode.CONTINUE_ON_ERROR))
public class GroupStudentControllerTest {
    @Autowired private TestRestTemplate restTemplate;

    @Test
    public void assignGroupStudent() {
        GroupStudent groupStudent1 = new GroupStudent("8-CS-8-AS2-g1", "8srinath");
        GroupStudent groupStudent2 = new GroupStudent("8-CS-8-AS2-g1", "8mahesh");
        GroupStudent groupStudent3 = new GroupStudent("8-CS-8-AS2-g1", "8suresh");
        GroupStudent groupStudent4 = new GroupStudent("8-CS-8-AS2-g2", "8naresh");
        GroupStudent groupStudent5 = new GroupStudent("8-CS-8-AS2-g2", "8vijay");
        GroupStudent groupStudent6 = new GroupStudent("8-CS-8-AS2-g2", "8sahil");
        GroupStudent groupStudent7 = new GroupStudent("8-CS-8-AS2-g3", "8raja");
        GroupStudent groupStudent8 = new GroupStudent("8-CS-8-AS2-g3", "8ganesh");
        GroupStudent groupStudent9 = new GroupStudent("8-CS-8-AS2-g3", "8harish");

        HttpEntity<List<GroupStudent>> entity = new HttpEntity<>(List.of(groupStudent1,
                groupStudent2, groupStudent3,
                groupStudent4, groupStudent5, groupStudent6,
                groupStudent7, groupStudent8, groupStudent9), new HttpHeaders());

        ResponseEntity<Map<String, String>> response = restTemplate.withBasicAuth("8srinath", "password")
                .exchange("/group-students", HttpMethod.POST, entity, new ParameterizedTypeReference<Map<String, String>>() {});

        // Expect Ok
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void deleteUserInGroups() {
        GroupStudent groupStudent = new GroupStudent();
        groupStudent.setGroupId("8-CS-8-AS-g3");
        groupStudent.setStudentId("8mahesh");
        HttpEntity<List<GroupStudent>> entity = new HttpEntity<>(List.of(groupStudent), new HttpHeaders());
        ResponseEntity<Void> deleteResponse = restTemplate.withBasicAuth("8srinath", "password")
                .exchange("/group-students", HttpMethod.DELETE, entity, new ParameterizedTypeReference<Void>() {});
        Assertions.assertEquals(HttpStatus.OK, deleteResponse.getStatusCode());
        ResponseEntity<List<User>> usersResponse = restTemplate.withBasicAuth("8srinath", "password")
                .exchange("/group-students?groupId=8-CS-8-AS-g3", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        Assertions.assertEquals(0, usersResponse.getBody().size());
    }

    @Test
    public void findUsersNotInAnyGroup() {
        ResponseEntity<List<User>> usersResponse = restTemplate.withBasicAuth("8srinath", "password")
                .exchange("/group-students?classId=8-CS&assignmentId=8-CS-8-AS", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        Assertions.assertEquals(4, usersResponse.getBody().size());
    }

    @Test
    public void findUsersInGroup() {
        ResponseEntity<List<User>> usersResponse = restTemplate.withBasicAuth("8srinath", "password")
                .exchange("/group-students?groupId=8-CS-8-AS-g1", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        Assertions.assertEquals(1, usersResponse.getBody().size());
    }
}

package org.comps.controller;

import org.comps.ChatengineApplication;
import org.comps.model.Class;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {ChatengineApplication.class})
@Sql(scripts = {"classpath:test-data/004-classes.sql"}, config = @SqlConfig(errorMode = SqlConfig.ErrorMode.CONTINUE_ON_ERROR))
public class ClassControllerTest {
    @Autowired TestRestTemplate restTemplate;

    @Test
    public void createClass() {
        Class class1 = new Class();
        class1.setTitle("Computer Science for First Year");
        class1.setGaId("4raja");
        class1.setProfId("4steve");
        class1.setId("2022-CS001");
        ResponseEntity<Class> classResponseEntity = restTemplate.withBasicAuth("4steve", "password")
                .postForEntity("/classes", class1, Class.class);
        Assertions.assertTrue(classResponseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void getClassesByGaId() {
        ResponseEntity<List<Class>> listResponseEntity = restTemplate.withBasicAuth("4srinath", "password")
                .exchange("/classes?gaId=4srinath", HttpMethod.GET, null, new ParameterizedTypeReference<List<Class>>() {});
        Assertions.assertEquals(3, listResponseEntity.getBody().size());
    }

    @Test
    public void getClassesByProfId() {
        ResponseEntity<List<Class>> listResponseEntity = restTemplate.withBasicAuth("4srinath", "password")
                .exchange("/classes?profId=4mark", HttpMethod.GET, null, new ParameterizedTypeReference<List<Class>>() {});
        Assertions.assertEquals(2, listResponseEntity.getBody().size());
    }

    @Test
    public void getAllClasses() {
        ResponseEntity<List<Class>> listResponseEntity = restTemplate.withBasicAuth("4srinath", "password")
                .exchange("/classes", HttpMethod.GET, null, new ParameterizedTypeReference<List<Class>>() {});
        Assertions.assertEquals(4, listResponseEntity.getBody().size());
    }
}

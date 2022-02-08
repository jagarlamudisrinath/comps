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
import org.testcontainers.shaded.com.fasterxml.jackson.core.type.TypeReference;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {ChatengineApplication.class})
@Sql(scripts = {"classpath:db/postgres/dml.sql"}, config = @SqlConfig(errorMode = SqlConfig.ErrorMode.CONTINUE_ON_ERROR))
public class ClassControllerTest {
    @Autowired TestRestTemplate restTemplate;

    @Test
    public void createClass() {
        Class class1 = new Class();
        class1.setTitle("Computer Science for First Year");
        class1.setGaId("srinath");
        class1.setProfId("mark");
        class1.setId("2022-CS001");
        ResponseEntity<Class> classResponseEntity = restTemplate.withBasicAuth("srinath", "password")
                .postForEntity("/classes", class1, Class.class);
        Assertions.assertTrue(classResponseEntity.getStatusCode().is2xxSuccessful());
    }

    @Test
    public void getClassesByGaId() {
        Class class1 = new Class();
        class1.setTitle("Computer Science for First Year");
        class1.setGaId("srinath");
        class1.setProfId("mark");
        class1.setId("2022-CS001");
        ResponseEntity<Class> classResponseEntity = restTemplate.withBasicAuth("srinath", "password")
                .postForEntity("/classes", class1, Class.class);
        ResponseEntity<List<Class>> listResponseEntity = restTemplate.withBasicAuth("srinath", "password")
                .exchange("/classes?gaId=srinath", HttpMethod.GET, null, new ParameterizedTypeReference<List<Class>>() {
                });
        Assertions.assertEquals(1, listResponseEntity.getBody().size());
    }

    @Test
    public void getClassesByProfId() {
        Class class1 = new Class();
        class1.setTitle("Computer Science for First Year");
        class1.setGaId("srinath");
        class1.setProfId("mark");
        class1.setId("2022-CS001");
        ResponseEntity<Class> classResponseEntity = restTemplate.withBasicAuth("srinath", "password")
                .postForEntity("/classes", class1, Class.class);
        ResponseEntity<List<Class>> listResponseEntity = restTemplate.withBasicAuth("srinath", "password")
                .exchange("/classes?profId=mark", HttpMethod.GET, null, new ParameterizedTypeReference<List<Class>>() {
                });
        Assertions.assertEquals(1, listResponseEntity.getBody().size());
    }
}

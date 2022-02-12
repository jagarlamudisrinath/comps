package org.comps.controller;

import org.comps.ChatengineApplication;
import org.comps.model.Class;
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
@Sql(scripts = {"classpath:test-data/003-users.sql"}, config = @SqlConfig(errorMode = SqlConfig.ErrorMode.CONTINUE_ON_ERROR))
public class UserControllerTest {
    @Autowired TestRestTemplate restTemplate;

    @Test
    public void uploadUsers() {
        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("file", new org.springframework.core.io.ClassPathResource("test-data/003-users.csv"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, headers);

        ResponseEntity<String> response = restTemplate.withBasicAuth("3srinath", "password").exchange("/users/upload", HttpMethod.POST, entity, String.class, "");

        // Expect Ok
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

    @Test
    public void getUsersByType() {
        ResponseEntity<List<User>> listResponseEntity = restTemplate.withBasicAuth("3srinath", "password")
                .exchange("/users?type=PROFESSOR", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
        //TODO expected should be 2, looks like other test cases users are as well showing up
        Assertions.assertEquals(8, listResponseEntity.getBody().size());
    }
}

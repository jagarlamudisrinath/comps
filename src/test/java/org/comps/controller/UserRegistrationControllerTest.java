package org.comps.controller;

import org.comps.ChatengineApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.LinkedMultiValueMap;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {ChatengineApplication.class})
@Sql(scripts = {"classpath:db/postgres/dml.sql"})
public class UserRegistrationControllerTest {
    @Autowired TestRestTemplate restTemplate;

    @Test
    public void uploadUsers() {
        LinkedMultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("file", new org.springframework.core.io.ClassPathResource("users.csv"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<LinkedMultiValueMap<String, Object>> entity = new HttpEntity<>(parameters, headers);

        ResponseEntity<String> response = restTemplate.withBasicAuth("srinath", "password").exchange("/users/upload", HttpMethod.POST, entity, String.class, "");

        // Expect Ok
        Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}

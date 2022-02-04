package org.comps.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.comps.model.User;
import org.comps.model.UserType;
import org.comps.service.UserService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

@RestController
public class UserRegistrationController implements InitializingBean {
    @Autowired private UserService userService;
    private PasswordEncoder passwordEncoder;

    @Override
    public void afterPropertiesSet() throws Exception {
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @PostMapping(value="/users/upload")
    public Map<String, String> processUpload(@RequestParam MultipartFile file) throws IOException {
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
        CSVParser csvParser = CSVFormat.DEFAULT.builder().setHeader("id", "first_name", "last_name", "email", "type").build().parse(fileReader);
        List<CSVRecord> records = csvParser.getRecords();
        String errorMsg = "";
        int i = 0;
        for(CSVRecord record : records) {
            try {
                i++;
                String id = record.get("id");
                String first_name = record.get("first_name");
                String last_name = record.get("last_name");
                String email = record.get("email");
                String type = record.get("type");
                User user = new User();
                user.setEmail(email);
                user.setFirstName(first_name);
                user.setLastName(last_name);
                user.setId(id);
                user.setPassword(passwordEncoder.encode("password"));
                user.setType(UserType.valueOf(type));
                boolean existsById = userService.existsById(id);
                if(existsById) {
                    user.setNew(false);
                } else {
                    user.setNew(true);
                }
                userService.save(user);
            } catch (Exception e) {
                errorMsg += "Correct line no: " + i + ", error: " + e.getMessage();
            }
        }
        if(!errorMsg.isEmpty()) {
            return Map.of("error", errorMsg, "msg", "Please correct records in the error message and reupload");
        } else {
            return Map.of("msg", "Successfully uploaded users in the file");
        }
    }
}

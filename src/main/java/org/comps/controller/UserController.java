package org.comps.controller;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.comps.model.User;
import org.comps.model.UserType;
import org.comps.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

@RestController
public class UserController implements InitializingBean {
    @Autowired private UserService userService;
    private PasswordEncoder passwordEncoder;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @PostMapping(value="/users/upload")
    public Map<String, String> processUpload(@RequestParam MultipartFile file) throws IOException {
        logger.info("Received file [{}] for upload users", file.getName());
        BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8));
        CSVParser csvParser = CSVFormat.DEFAULT.builder().setCommentMarker('#').setHeader("id", "first_name", "last_name", "email", "type").build().parse(fileReader);
        List<CSVRecord> records = csvParser.getRecords();
        StringBuilder errorMsg = new StringBuilder();
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
                user.setNew(!existsById);
                userService.save(user);
            } catch (Exception e) {
                errorMsg.append("Correct line no: ").append(i).append(", error: ").append(e.getMessage()).append("\n");
                logger.error("Unable to process line no: {} from file : {}", i, file.getName(), e);
            }
        }
        if(errorMsg.length() > 0) {
            return Map.of("error", errorMsg.toString(), "msg", "Please correct records in the error message and reupload");
        } else {
            return Map.of("msg", "Successfully uploaded users in the file");
        }
    }

    @GetMapping(value = "users/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(value = "users")
    public List<User> getUsersByType(@RequestParam UserType type) {
        return userService.findUserByType(type);
    }

    @GetMapping(value = "users", params = "userId")
    public User getUsersByID(@RequestParam String userId) {
        return userService.findUserById(userId);
    }

}

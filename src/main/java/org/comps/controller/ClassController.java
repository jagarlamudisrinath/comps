package org.comps.controller;

import org.comps.errors.AppExceptions;
import org.comps.model.Class;
import org.comps.model.User;
import org.comps.service.ClassService;
import org.comps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
public class ClassController {
    @Autowired private ClassService classService;
    @Autowired private UserService userService;

    @PostMapping("/classes")
    public Class createClass(@RequestBody Class class1) {
        Objects.requireNonNull(class1, "Class object cannot be null");
        User gaUser = userService.findById(class1.getGaId());
        if(gaUser == null) {
            throw AppExceptions.resourceNotFound(String.format("No User found with GA Id: %s", class1.getGaId()));
        }

        User prof = userService.findById(class1.getProfId());
        if(prof == null) {
            throw AppExceptions.resourceNotFound(String.format("No User found with GA Id: %s", class1.getProfId()));
        }

        if(!StringUtils.hasText(class1.getId())) {
            class1.setId(UUID.randomUUID().toString());
            class1.setNew(true);
        } else {
            boolean existsById = classService.existsById(class1.getId());
            class1.setNew(!existsById);
        }
        classService.save(class1);
        return class1;
    }

    @GetMapping(value = "/classes", params = "gaId")
    public List<Class> findClassByGaId(@RequestParam String gaId) {
        return classService.findAllByGaId(gaId);
    }

    @GetMapping(value = "/classes", params = "profId")
    public List<Class> findClassByProfId(@RequestParam String profId) {
        return classService.findAllByProfId(profId);
    }
}

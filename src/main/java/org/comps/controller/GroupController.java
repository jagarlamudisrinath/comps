package org.comps.controller;

import org.comps.errors.AppExceptions;
import org.comps.model.Assignment;
import org.comps.model.Group;
import org.comps.service.AssignmentService;
import org.comps.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class GroupController {
    @Autowired private AssignmentService assignmentService;
    @Autowired private GroupService groupService;

    @PostMapping("/groups")
    public Group createGroup(@RequestBody Group group) {
        Assignment assignment = assignmentService.findById(group.getAssignmentId());
        if(assignment == null) {
            throw AppExceptions.resourceNotFound(String.format("No Assignment found with Id: %s", group.getAssignmentId()));
        }

        if(StringUtils.hasText(group.getId())) {
            Group persistedGroup = groupService.findById(group.getId());
            if(persistedGroup == null) {
                group.setNew(true);
            } else {
                group.setNew(false);
                if(!persistedGroup.getAssignmentId().equals(group.getAssignmentId())) {
                    throw AppExceptions.resourceNotFound("Cannot update Assignment of a group");
                }
            }
        } else {
            group.setNew(true);
            group.setId(UUID.randomUUID().toString());
        }

        groupService.save(group);
        return group;
    }
}

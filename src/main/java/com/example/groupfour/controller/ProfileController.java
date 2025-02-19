package com.example.groupfour.controller;

import com.example.groupfour.dto.ServiceResult;
import com.example.groupfour.entity.Profile;
import com.example.groupfour.service.ProfileService;
import com.example.groupfour.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/api")
public class ProfileController {
    private ProfileService profileService;
    private UserService userService;

    @Autowired
    public ProfileController(ProfileService profileService, UserService userService) {
        this.profileService = profileService;
        this.userService = userService;
    }

    @GetMapping("/profiles")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllProfiles() {

        List<Profile> profiles = profileService.getAllProfiles();
        return ResponseEntity.ok(new ServiceResult(HttpStatus.OK.value(), "Get list of profile successfully!", profiles));

    }
}

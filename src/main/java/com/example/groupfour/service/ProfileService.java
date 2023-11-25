package com.example.groupfour.service;

import com.example.groupfour.entity.Profile;

import java.util.List;

public interface ProfileService {
    Profile createProfile(Profile profile);

    List<Profile> getAllProfiles();
}

package com.example.groupfour.service;

import com.example.groupfour.entity.Role;
import com.example.groupfour.ultilities.ERole;

import java.util.Optional;

public interface RoleService {
    Optional<Role> findByName(ERole name);
}

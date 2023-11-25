package com.example.groupfour.dto;

import com.example.groupfour.entity.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdate {
    private String username;
    private String email;
    private String password;
    private Profile profile;
}
package com.example.groupfour.service;

import com.example.groupfour.entity.Intake;

import java.util.List;
import java.util.Optional;

public interface IntakeService {
    public Intake findByCode(String code);
    public Optional<Intake> findById(Long id);
    public List<Intake> findAll();

}

package com.example.groupfour.dto;

import com.example.groupfour.entity.Exam;
import lombok.Data;

import java.util.List;

@Data
public class ExamDto {
    private Exam exam;
    private List<ExamQuestionPoint> examQuestionPoints;
}

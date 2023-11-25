package com.example.groupfour.dto;

import com.example.groupfour.entity.Exam;
import com.example.groupfour.entity.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamQuestionList {
    private Exam exam;
    private List<Question> questions;
    private int remainingTime;
}

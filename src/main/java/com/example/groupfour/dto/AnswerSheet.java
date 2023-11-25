package com.example.groupfour.dto;

import com.example.groupfour.entity.Choice;
import com.example.groupfour.entity.Exam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AnswerSheet {
    private Long questionId;
    private List<Choice> choices;
    private Integer point;
}
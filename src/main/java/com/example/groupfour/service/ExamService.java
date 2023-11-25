package com.example.groupfour.service;

import com.example.groupfour.dto.AnswerSheet;
import com.example.groupfour.dto.ChoiceList;
import com.example.groupfour.dto.ExamQuestionPoint;
import com.example.groupfour.entity.Exam;
import com.example.groupfour.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ExamService {

    Exam saveExam(Exam exam);

    Page<Exam> findAll(Pageable pageable);

    void cancelExam(Long id);

    List<Exam> getAll();

    Optional<Exam> getExamById(Long id);

    Page<Exam> findAllByCreatedBy_Username(Pageable pageable, String username);

    List<ChoiceList> getChoiceList(List<AnswerSheet> userChoices, List<ExamQuestionPoint> examQuestionPoints);
}

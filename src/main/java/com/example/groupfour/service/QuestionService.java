package com.example.groupfour.service;

import com.example.groupfour.dto.AnswerSheet;
import com.example.groupfour.dto.ExamQuestionPoint;
import com.example.groupfour.entity.Course;
import com.example.groupfour.entity.Part;
import com.example.groupfour.entity.Question;
import com.example.groupfour.entity.QuestionType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface QuestionService {
    Optional<Question> getQuestionById(Long id);

    List<Question> getQuestionByPart(Part part);

    List<Question> getQuestionByQuestionType(QuestionType questionType);

    List<Question> getQuestionPointList(List<ExamQuestionPoint> examQuestionPoints);

    List<AnswerSheet> convertFromQuestionList(List<Question> questionList);

    List<Question> getQuestionList();

    Page<Question> findQuestionsByPart(Pageable pageable, Part part);

    Page<Question> findQuestionsByPartAndDeletedFalse(Pageable pageable, Part part);
    Page<Question> findQuestionsByPart_IdAndCreatedBy_UsernameAndDeletedFalse(Pageable pageable, Long partId, String username);

    Page<Question> findAllQuestions(Pageable pageable);

    String findQuestionTextById(Long questionId);

    Page<Question> findQuestionsByPart_IdAndCreatedBy_Username(Pageable pageable, Long partId, String username);

    Page<Question> findQuestionsByCreatedBy_Username(Pageable pageable, String username);

    void save(Question question);

    void update(Question question);

    void delete(Long id);

}

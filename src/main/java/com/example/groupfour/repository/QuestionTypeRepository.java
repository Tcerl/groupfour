package com.example.groupfour.repository;

import com.example.groupfour.entity.QuestionType;
import com.example.groupfour.ultilities.EQTypeCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuestionTypeRepository extends JpaRepository<QuestionType, Long> {
    boolean existsById(Long id);

    Optional<QuestionType> findAllByTypeCode(EQTypeCode typeCode);
}

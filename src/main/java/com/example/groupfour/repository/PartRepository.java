package com.example.groupfour.repository;

import com.example.groupfour.entity.Course;
import com.example.groupfour.entity.Part;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {
    Page<Part> findAllByCourseId(Long courseId, Pageable pageable);

    List<Part> findAllByCourse(Course course);

}


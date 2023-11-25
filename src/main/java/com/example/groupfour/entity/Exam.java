package com.example.groupfour.entity;

import com.example.groupfour.audit.Auditable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "exam")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exam extends Auditable<Long> implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "canceled")
    private boolean canceled = false;

    @ManyToOne()
    @JoinColumn(name = "intake_id")
    private Intake intake;

    @ManyToOne()
    @JoinColumn(name = "part_id")
    private Part part;

    @Column(name = "title")
    private String title;

    @Column(name = "shuffle", columnDefinition = "TINYINT")
    @Basic
    private boolean isShuffle;

    @Column(name = "duration_exam")
    private int durationExam;

    @Column(name = "begin_exam")
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss a")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date beginExam;

    @Column(name = "finish_exam")
    @DateTimeFormat(pattern = "yyyy/MM/dd hh:mm:ss a")
//    @Temporal(TemporalType.TIMESTAMP)
    private Date finishExam;

    @Transient
    private boolean locked;

    @Column(name="question_data", columnDefinition = "text")
    private String questionData;

}

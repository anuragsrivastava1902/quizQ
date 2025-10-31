package com.example.quizQ;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "qz_ques")
public class QzQues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String question;
    @Column(name = "option_a") private String optionA;
    @Column(name = "option_b") private String optionB;
    @Column(name = "option_c") private String optionC;
    @Column(name = "option_d") private String optionD;
    @Column(name = "correct_option") private String correctOption;
    private String explanation;
    private String topic;

    // getters and setters
}


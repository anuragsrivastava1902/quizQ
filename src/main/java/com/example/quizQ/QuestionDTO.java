package com.example.quizQ;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionDTO {
    private Integer id; // Add the id field to match the entity
    private String question; // This should be a question field, not from the DB
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String correctOption;
    private String explanation;
    private String topic;

//    // Constructor that matches the arguments you will pass
//    public QuestionDTO(Integer id, String question, String optionA, String optionB,
//                       String optionC, String optionD, String correctOption, String explanation, String topic) {
//        this.id = id;
//        this.question = question;  // assuming you want to treat topic as the "question" in the DTO
//        this.optionA = optionA;
//        this.optionB = optionB;
//        this.optionC = optionC;
//        this.optionD = optionD;
//        this.correctOption = correctOption;
//        this.explanation = explanation;
//        this.topic = topic;
//    }
}

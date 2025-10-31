package com.example.quizQ;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class QzQuesController {

    @Autowired
    private QzQuesService service;

    @PostMapping("/questions/upload")
    public ResponseEntity<String> uploadCsv(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("Please upload a CSV file!");
        }

        try {
            service.saveCsvData(file);
            return ResponseEntity.ok("CSV uploaded and data saved successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error processing file: " + e.getMessage());
        }
    }

    // Endpoint to get all questions
    @GetMapping("/questions")
    public ResponseEntity<List<QuestionDTO>> getAllQuestions() {
        List<QuestionDTO> questions = service.getAllQuestions();
        return ResponseEntity.ok(questions);
    }

    // Endpoint to fetch distinct topics
    @GetMapping("/topics")
    public List<String> getTopics() {
        return service.getDistinctTopics(); // Fetch topics from service
    }

    // New endpoint to fetch questions by topic
    @GetMapping("/questions/by-topic")
    public ResponseEntity<List<QuestionDTO>> getQuestionsByTopic(@RequestParam(value = "topic", required = false) String topic) {
        List<QuestionDTO> questions;

        if (topic != null && !topic.isEmpty()) {
            // If topic is provided, fetch questions by topic
            questions = service.getQuestionsByTopic(topic);
        } else {
            // If topic is not provided, fetch all questions
            questions = service.getAllQuestions();
        }

        return ResponseEntity.ok(questions);
    }
}


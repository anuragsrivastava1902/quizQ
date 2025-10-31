package com.example.quizQ;

import com.opencsv.exceptions.CsvValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;

import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class QzQuesService {

    @Autowired
    private QzQuesRepository repository;

    public void saveCsvData(MultipartFile file) throws IOException {
        try (CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()))) {
            List<QzQues> questions = new ArrayList<>();

            String[] data;
            csvReader.readNext(); // Skip header

            while ((data = csvReader.readNext()) != null) {
                System.out.println("File content: " + Arrays.toString(data));  // Log the CSV file content
                if (data.length < 8) {
                    System.out.println("Skipping invalid line: " + Arrays.toString(data));
                    continue;
                }

                QzQues q = new QzQues();
                q.setQuestion(data[0].trim());
                q.setOptionA(data[1].trim());
                q.setOptionB(data[2].trim());
                q.setOptionC(data[3].trim());
                q.setOptionD(data[4].trim());
                q.setCorrectOption(data[5].trim());
                q.setExplanation(data[6].trim());
                q.setTopic(data[7].trim());
                questions.add(q);
            }

            repository.saveAll(questions);
        } catch (CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<QuestionDTO> getAllQuestions() {
        List<QzQues> allQuestions = repository.findAll();
        List<QuestionDTO> questionDTOs = new ArrayList<>();

        for (QzQues question : allQuestions) {
            QuestionDTO dto = new QuestionDTO(
                    question.getId(),
                    question.getQuestion(),
                    question.getOptionA(),
                    question.getOptionB(),
                    question.getOptionC(),
                    question.getOptionD(),
                    question.getCorrectOption(),
                    question.getExplanation(),
                    question.getTopic()
            );
            questionDTOs.add(dto);
        }

        return questionDTOs;
    }

    // Method to get distinct topics
    public List<String> getDistinctTopics() {
        return repository.findDistinctTopics();
    }

    // Method to get questions by topic
    public List<QuestionDTO> getQuestionsByTopic(String topic) {
        // Call the repository method
        List<QzQues> allQzQues = repository.findByTopic(topic);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (QzQues question : allQzQues) {
            QuestionDTO questionDTO = new QuestionDTO(
                    question.getId(),
                    question.getQuestion(),
                    question.getOptionA(),
                    question.getOptionB(),
                    question.getOptionC(),
                    question.getOptionD(),
                    question.getCorrectOption(),
                    question.getExplanation(),
                    question.getTopic()
            );
            questionDTOS.add(questionDTO);
        }
        return questionDTOS;
    }
}

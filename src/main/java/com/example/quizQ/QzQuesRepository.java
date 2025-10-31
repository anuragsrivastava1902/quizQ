package com.example.quizQ;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QzQuesRepository extends JpaRepository<QzQues, Integer> {

    // Custom query to get distinct topics
    @Query("SELECT DISTINCT q.topic FROM QzQues q WHERE q.topic IS NOT NULL")
    List<String> findDistinctTopics();

    // Modify the query to map the question field correctly
    List<QzQues> findByTopic(String topic);

}

package com.blog.question.services;

import com.blog.question.repositories.QuestionRepository;
import com.blog.question.entities.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionService {
    @Autowired
    QuestionRepository repository;
    public Question create(Question question) {
        return repository.save(question);
    }
    public List<Question> createAll(List<Question> question) {
        return repository.saveAll(question);
    }
    public List<Question> getAll() {
        return repository.findAll();
    }
}

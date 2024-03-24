package com.blog.question.controllers;

import com.blog.question.entities.Question;
import com.blog.question.services.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    @GetMapping
    public ResponseEntity<?> getQuestion() {
        return ResponseEntity.ok(questionService.getAll());
    }
    @PostMapping
    public ResponseEntity<?> createQuestion(@RequestBody Question question) {
        return ResponseEntity.ok(questionService.create(question));
    }
    @PostMapping("/list")
    public ResponseEntity<?> createListQuestion(@RequestBody List<Question> question) {
        return ResponseEntity.ok(questionService.createAll(question));
    }
}

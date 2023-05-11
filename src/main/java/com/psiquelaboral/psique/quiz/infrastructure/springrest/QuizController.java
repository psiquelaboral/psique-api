package com.psiquelaboral.psique.quiz.infrastructure.springrest;

import com.psiquelaboral.psique.quiz.application.IQuizService;
import com.psiquelaboral.psique.quiz.domain.model.Quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class QuizController {

    private final IQuizService quizService;

    @GetMapping("/quiz/all")
    public ResponseEntity<List<Quiz>> listAll() {
        var quizzes = this.quizService.listAll();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/quiz/{id}")
    public ResponseEntity<Quiz> getById(@PathVariable String id) {
        Quiz quiz = this.quizService.getById(id);
        return ResponseEntity.ok(quiz);
    }

    @PostMapping("/quiz")
    public ResponseEntity<Quiz> create(@RequestBody Quiz quiz) {
        this.quizService.create(quiz);
        return ResponseEntity.ok(quiz);
    }

}

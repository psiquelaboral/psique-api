package com.psiquelaboral.psique.quiz.infrastructure.springrest;

import com.psiquelaboral.psique.quiz.application.IQuizService;
import com.psiquelaboral.psique.quiz.domain.model.Quiz;
import com.psiquelaboral.psique.shared.infrastructure.openapi.DocumentedRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequiredArgsConstructor
@DocumentedRestController
public class QuizController {

    private final IQuizService quizService;

    @GetMapping("/quiz/all")
    public ResponseEntity<List<Quiz>> listAll() {
        var quizzes = this.quizService.listAll();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/quiz/all/resume")
    public ResponseEntity<List<Quiz>> listAllResume() {
        var quizzes = this.quizService.listAllResumed();
        return ResponseEntity.ok(quizzes);
    }

    @GetMapping("/quiz/employee/{id}")
    public ResponseEntity<Quiz> getById(@PathVariable String id) {
        Quiz quiz = this.quizService.getById(id);
        if (quiz == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found: " + id);
        }
        return ResponseEntity.ok(quiz);
    }

    @PostMapping("/quiz")
    public ResponseEntity<Quiz> create(@RequestBody Quiz quiz) {
        this.quizService.create(quiz);
        return ResponseEntity.ok(quiz);
    }

}

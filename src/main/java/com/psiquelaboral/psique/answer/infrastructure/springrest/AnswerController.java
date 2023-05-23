package com.psiquelaboral.psique.answer.infrastructure.springrest;

import com.psiquelaboral.psique.answer.application.IAnswerService;
import com.psiquelaboral.psique.answer.domain.model.Answer;
import com.psiquelaboral.psique.answer.domain.model.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequiredArgsConstructor
public class AnswerController {

    private final IAnswerService<String> answerService;

    @PostMapping("/answer")
    public ResponseEntity<Answer> initializeAnswer(@RequestBody Answer answer) {
        Answer currentAnswer = this.answerService.retrieveAnswer(answer.getQuizId(), answer.getEmployeeId());
        if (currentAnswer != null) {
            return new ResponseEntity<>(currentAnswer, HttpStatus.OK);
        }

        this.answerService.initializeAnswer(answer);
        return new ResponseEntity<>(answer, HttpStatus.CREATED);
    }

    @PutMapping("/answer/{quizId}")
    public ResponseEntity<Response> registryAnswer(@PathVariable String quizId,
                                                   @RequestBody Response response) {
        //validate quiz exist
        this.answerService.registryAnswer(quizId, response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/answer")
    public ResponseEntity<Answer> registryAnswer(@RequestParam String quizId,
                                                 @RequestParam String employeeId) {
        Answer answer = this.answerService.retrieveAnswer(quizId, employeeId);
        if (answer == null) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Answers not found with quizId and employeeId provided"
            );
        }
        return new ResponseEntity<>(answer, HttpStatus.OK);
    }

}

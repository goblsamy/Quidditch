package hu.progmasters.finalexam.quidditch.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ValidationError>> handleValidationException(MethodArgumentNotValidException exception) {
        List<ValidationError> validationErrors = exception.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
                .collect(Collectors.toList());
        validationErrors.forEach(validationError -> {
            log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ClubNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleClubNotFoundException(ClubNotFoundException exception) {
        ValidationError validationError = new ValidationError("Id",
                "Cannot find club with Id: " + exception.getClubId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoFreePlaceOnPositionException.class)
    public ResponseEntity<List<ValidationError>> handleNoFreePlaceOnPositionException(NoFreePlaceOnPositionException exception) {
        ValidationError validationError = new ValidationError("playerType",
                "No free place on position: " + exception.getPlayerType() + " in team id: " + exception.getClubId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CurrentIdAndGivenMatchesException.class)
    public ResponseEntity<List<ValidationError>> handleCurrentIdAndGivenMatchesException(CurrentIdAndGivenMatchesException exception) {
        ValidationError validationError = new ValidationError("Id",
                "Current club id and given is the same: " + exception.getId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PlayerNotFoundByIdException.class)
    public ResponseEntity<List<ValidationError>> handlePlayerNotFoundByIdException(PlayerNotFoundByIdException exception) {
        ValidationError validationError = new ValidationError("Id",
                "Player not found by Id " + exception.getId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NoPlayersInThisClubException.class)
    public ResponseEntity<List<ValidationError>> handleNoPlayersInThisClubException(NoPlayersInThisClubException exception) {
        ValidationError validationError = new ValidationError("Id",
                "There are no players in this club: " + exception.getClubId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CoachNotFoundException.class)
    public ResponseEntity<List<ValidationError>> handleCoachNotFoundException(CoachNotFoundException exception) {
        ValidationError validationError = new ValidationError("Id",
                "Coach not found by Id " + exception.getCoachId());
        log.error("Error in validation: " + validationError.getField() + ": " + validationError.getErrorMessage());
        return new ResponseEntity<>(List.of(validationError), HttpStatus.BAD_REQUEST);
    }


}

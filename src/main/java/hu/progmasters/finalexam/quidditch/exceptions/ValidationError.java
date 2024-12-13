package hu.progmasters.finalexam.quidditch.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationError {

    private String field;
    private String errorMessage;
}

package hu.progmasters.finalexam.quidditch.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubCreateCommand {

    @NotNull(message = "Cannot be null!")
    @NotBlank(message = "Cannot be blank!")
    @NotEmpty(message = "Cannot be empty")
    private String name;
    private int wins;
}

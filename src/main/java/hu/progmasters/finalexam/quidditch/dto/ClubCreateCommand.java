package hu.progmasters.finalexam.quidditch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubCreateCommand {

    @NotNull(message = "Name cannot be null!")
    @NotBlank(message = "Name cannot be blank!")
    @NotEmpty(message = "Name cannot be empty!")
    private String name;

    @NotNull(message = "Wins cannot be null!")
    @Min(value = 0, message = "Wins cannot be lower than 0!")
    private Integer wins;
}

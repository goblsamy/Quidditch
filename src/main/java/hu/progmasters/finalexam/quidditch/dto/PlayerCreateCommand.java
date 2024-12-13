package hu.progmasters.finalexam.quidditch.dto;

import hu.progmasters.finalexam.quidditch.domain.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCreateCommand {

    @NotNull(message = "Cannot be null!")
    @NotBlank(message = "Cannot be blank!")
    @NotEmpty(message = "Cannot be empty")
    private String name;

    @NotNull(message = "Cannot be null!")
    @Past(message = "The date must be in the past")
    private LocalDate joined;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Cannot be null!")
    private PlayerType playerType;

    private int wins;

    @NotNull(message = "Cannot be null!")
    private int clubId;
}

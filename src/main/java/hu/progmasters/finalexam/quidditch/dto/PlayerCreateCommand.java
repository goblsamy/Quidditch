package hu.progmasters.finalexam.quidditch.dto;

import hu.progmasters.finalexam.quidditch.domain.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.sql.Date;
import java.time.LocalDate;

import static java.time.LocalDate.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerCreateCommand {

    private static final LocalDate localDate = LocalDate.of(2023, 11, 25);

    @NotNull(message = "Name cannot be null!")
    @NotBlank(message = "Name cannot be blank!")
    @NotEmpty(message = "Name cannot be empty!")
    private String name;

    @NotNull(message = "Joined cannot be null!")
    //TODO csak múltbeli lehet!
    private LocalDate joined;

    @NotNull(message = "Type cannot be null!")
    private PlayerType playerType;

    @NotNull(message = "Wins cannot be null!")
    @Min(value = 0, message = "Wins cannot be lower than 0!")
    private Integer wins;

    @NotNull(message = "Club Id cannot be null!")
    @Min(value = 1, message = "Club id cannot be lower than 1!")
    private Integer clubId;
}

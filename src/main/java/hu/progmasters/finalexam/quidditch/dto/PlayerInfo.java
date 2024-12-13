package hu.progmasters.finalexam.quidditch.dto;

import hu.progmasters.finalexam.quidditch.domain.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlayerInfo {

    private long id;
    private String name;
    private LocalDate joined;
    private PlayerType playerType;
    private int wins;
    private String clubName;

}

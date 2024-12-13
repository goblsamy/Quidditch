package hu.progmasters.finalexam.quidditch.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubWins {

    private int id;
    private String name;
    private int wins;
    private String coachName;
    private List<PlayerInfo> players;
}

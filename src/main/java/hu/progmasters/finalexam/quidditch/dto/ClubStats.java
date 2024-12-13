package hu.progmasters.finalexam.quidditch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubStats {

    private int clubWins;
    private double playersAvgWins;
    private int biggestPlayerWin;
    private int lowestPlayerWin;


}

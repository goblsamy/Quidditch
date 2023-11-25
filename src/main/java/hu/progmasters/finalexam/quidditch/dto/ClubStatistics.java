package hu.progmasters.finalexam.quidditch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClubStatistics {
    private Integer clubWins;
    private Double playerAverageWins;
    private Integer mostWins;
    private Integer lessWins;

    public ClubStatistics(Integer clubWins, Double playerAverageWins, Integer mostWins, Integer lessWins) {
        this.clubWins = clubWins;
        this.playerAverageWins = playerAverageWins;
        this.mostWins = mostWins;
        this.lessWins = lessWins;
    }
}

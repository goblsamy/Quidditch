package hu.progmasters.finalexam.quidditch.dto;

import hu.progmasters.finalexam.quidditch.domain.Coach;
import hu.progmasters.finalexam.quidditch.domain.Player;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class ClubPlayerInfo {
        private Integer id;
        private String name;
        private Integer wins;
        private String coachName;
        private List<PlayerInfo> players;
    }


package hu.progmasters.finalexam.quidditch.dto;


import hu.progmasters.finalexam.quidditch.domain.Club;
import hu.progmasters.finalexam.quidditch.domain.PlayerType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlayerInfo {

    private Integer id;
    private String name;
    private LocalDate joined;
    private PlayerType playerType;
    private Integer wins;
    private ClubInfo clubInfo;
}

package hu.progmasters.finalexam.quidditch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClubInfo {
    private Integer clubId;
    private Integer wins;
}

package hu.progmasters.finalexam.quidditch.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClubInfo {

    private Long id;
    private String name;
    private int wins;
}
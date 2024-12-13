package hu.progmasters.finalexam.quidditch.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "player")
@Data
@NoArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate joined;

    @Enumerated(EnumType.STRING)
    private PlayerType playerType;

    private int wins;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}

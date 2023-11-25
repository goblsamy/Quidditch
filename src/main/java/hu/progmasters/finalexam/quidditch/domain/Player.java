package hu.progmasters.finalexam.quidditch.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "player")
@NoArgsConstructor
@Data
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "joined")
    private LocalDate localDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "player_type")
    private PlayerType playerType;

    @Column(name = "wins")
    private Integer wins;

    @ManyToOne
    @JoinColumn(name = "club_id")
    private Club club;
}

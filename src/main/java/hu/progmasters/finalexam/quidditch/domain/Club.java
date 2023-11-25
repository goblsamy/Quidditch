package hu.progmasters.finalexam.quidditch.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "club")
@NoArgsConstructor
@Data
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "wins")
    private Integer wins;

    @OneToMany(mappedBy = "player")
    private List<Player> players;

    @OneToOne(mappedBy = "club")
    private Coach coach;
}

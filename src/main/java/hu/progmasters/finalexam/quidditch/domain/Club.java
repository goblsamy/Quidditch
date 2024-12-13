package hu.progmasters.finalexam.quidditch.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "club")
@Data
@NoArgsConstructor
public class Club {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private int wins;

    @OneToMany(mappedBy = "club" )
    private List<Player> players;

    @OneToOne(mappedBy = "club")
    private Coach coach;



}

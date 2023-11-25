package hu.progmasters.finalexam.quidditch.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "coach")
@NoArgsConstructor
@Data
public class Coach {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "deleted")
    private Boolean deleted;

    @OneToOne
    @JoinColumn(name = "club_id", unique = true)
    private Club club;

}

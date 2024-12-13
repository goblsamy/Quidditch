package hu.progmasters.finalexam.quidditch.repository;

import hu.progmasters.finalexam.quidditch.domain.Club;
import hu.progmasters.finalexam.quidditch.dto.ClubWins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {

    @Query("SELECT COUNT(p) > 0 FROM Player p WHERE p.club.id = :clubId AND p.wins > (SELECT c.wins FROM Club c WHERE c.id = :clubId)")
    boolean hasSuperstarPlayer(@Param("clubId") Long clubId);



}

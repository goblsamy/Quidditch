package hu.progmasters.finalexam.quidditch.repository;

import hu.progmasters.finalexam.quidditch.domain.Player;
import hu.progmasters.finalexam.quidditch.domain.PlayerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {


    @Query("SELECT COUNT(p) FROM Player p WHERE p.playerType = :playerType AND p.club.id = :clubId")
    int countByPlayerTypeAndClub(@Param("playerType") PlayerType playerType, @Param("clubId") Long clubId);


    @Query("select p from Player p order by p.joined desc, p.wins asc")
    List<Player> findAllOrderByJoinedDescWinsAsc();



}


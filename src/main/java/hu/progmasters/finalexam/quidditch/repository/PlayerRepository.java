package hu.progmasters.finalexam.quidditch.repository;

import hu.progmasters.finalexam.quidditch.domain.Player;
import hu.progmasters.finalexam.quidditch.domain.PlayerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Query("select case when (count (p) >:maxValue ) then false else true end from Player p" +
            " where p.club.id=:clubId and p.playerType=:playerType")
    boolean isThereFreeSpaceInTeam(PlayerType playerType, Integer clubId, Integer maxValue);

    @Query("select p.joined, p.wins from Player p order by p. desc and order by p")
}

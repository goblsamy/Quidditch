package hu.progmasters.finalexam.quidditch.repository;

import hu.progmasters.finalexam.quidditch.domain.Coach;
import hu.progmasters.finalexam.quidditch.dto.ClubStats;
import hu.progmasters.finalexam.quidditch.dto.ClubWins;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CoachRepository extends JpaRepository<Coach, Long> {


    Optional<Coach> findCoachById(Long id);

    @Query("select new hu.progmasters.finalexam.quidditch.dto.ClubStats(club.wins, avg(players.wins), max(players.wins), min(players.wins)) from Coach coach join coach.club club join club.players players where coach.id=:coachId group by club.wins")
    Optional<ClubStats> getStatistics(Long coachId);

}

package hu.progmasters.finalexam.quidditch.repository;

import hu.progmasters.finalexam.quidditch.domain.Coach;
import hu.progmasters.finalexam.quidditch.dto.ClubStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {

    @Query("select new hu.progmasters.finalexam.quidditch.dto.ClubStatistics(cl.wins, avg(pl.wins), " +
            "max(pl.wins),min(pl.wins)) from Coach c join c.club cl join cl.players pl where c.id=:id")
    ClubStatistics getSumAvgMaxAndMin(Long id);
}

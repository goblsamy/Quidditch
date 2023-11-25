package hu.progmasters.finalexam.quidditch.repository;

import hu.progmasters.finalexam.quidditch.domain.Club;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
}

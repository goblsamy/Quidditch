package hu.progmasters.finalexam.quidditch.repository;

import hu.progmasters.finalexam.quidditch.domain.Coach;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<Coach, Long> {
}

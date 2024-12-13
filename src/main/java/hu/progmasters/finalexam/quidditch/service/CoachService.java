package hu.progmasters.finalexam.quidditch.service;

import hu.progmasters.finalexam.quidditch.domain.Coach;
import hu.progmasters.finalexam.quidditch.dto.ClubStats;
import hu.progmasters.finalexam.quidditch.exceptions.CoachNotFoundException;
import hu.progmasters.finalexam.quidditch.exceptions.NoPlayersInThisClubException;
import hu.progmasters.finalexam.quidditch.repository.CoachRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CoachService {


    private CoachRepository coachRepository;

    private ClubService clubService;

    @Autowired
    public CoachService(CoachRepository coachRepository, ClubService clubService) {
        this.coachRepository = coachRepository;
        this.clubService = clubService;
    }

    public void deleteCoach(long coachId) {
        Coach coach = findById(coachId);
        coach.setDeleted(true);
        coach.setClub(null);
        coachRepository.save(coach);
    }

    public ClubStats clubStats(long coachId) {
        Coach coach = findById(coachId);
        return coachRepository.getStatistics(coach.getId()).orElseThrow(() -> new NoPlayersInThisClubException(coachId));

    }

    public Coach findById(long coachId) {
        return coachRepository.findCoachById(coachId)
                .orElseThrow(() -> new CoachNotFoundException(coachId));
    }
}


package hu.progmasters.finalexam.quidditch.controller;

import hu.progmasters.finalexam.quidditch.dto.ClubStats;
import hu.progmasters.finalexam.quidditch.service.CoachService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/coaches")
@Slf4j
public class CoachController {

    private final CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @DeleteMapping("/{coachId}")
    public ResponseEntity<Void> deleteCoach(@PathVariable long coachId) {
        log.info("http req DELETE /coaches/" + coachId);
        coachService.deleteCoach(coachId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/statistics/{coachId}")
    public ResponseEntity<ClubStats> getClubStatistics(@PathVariable("coachId") long coachId) {
        log.info("http req GET /coaches/" + coachId);
        ClubStats clubStats = coachService.clubStats(coachId);
        return new ResponseEntity<>(clubStats, HttpStatus.OK);

    }
}


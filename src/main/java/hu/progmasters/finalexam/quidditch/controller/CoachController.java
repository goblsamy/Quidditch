package hu.progmasters.finalexam.quidditch.controller;

import hu.progmasters.finalexam.quidditch.dto.ClubStatistics;
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

    private CoachService coachService;

    @Autowired
    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }

    @DeleteMapping("/{coachId}")
    public ResponseEntity<Void> deleteCoachById(@PathVariable("coachId") Integer id) {
        log.info("Http request, DELETE / api/coaches/{coachId}");
        coachService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping("/statistics/{coachId}")
    public ResponseEntity<ClubStatistics> getWinningStatistics(@PathVariable("coachId") Integer id) {
        log.info("Http request, GET / /api/coaches/statistics/{coachId} with variable. " + id);
        ClubStatistics clubStatistics = coachService.getSumAvgMaxAndMin(id.longValue());
        return new ResponseEntity<>(clubStatistics, HttpStatus.OK);
    }

}

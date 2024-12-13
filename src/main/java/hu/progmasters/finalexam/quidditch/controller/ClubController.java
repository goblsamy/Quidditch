
package hu.progmasters.finalexam.quidditch.controller;

import hu.progmasters.finalexam.quidditch.dto.ClubCreateCommand;
import hu.progmasters.finalexam.quidditch.dto.ClubInfo;
import hu.progmasters.finalexam.quidditch.dto.ClubWins;
import hu.progmasters.finalexam.quidditch.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clubs")
@Slf4j
public class ClubController {

    private final ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping
    public ResponseEntity<ClubInfo> saveClub(@Valid @RequestBody ClubCreateCommand command) {
        log.info("http req. post /api/clubs, with body: " + command.toString());
        ClubInfo clubInfo = clubService.saveClub(command);
        return new ResponseEntity<>(clubInfo, HttpStatus.CREATED);
    }

    @PutMapping("/{clubId}")
    public ResponseEntity<ClubWins> updateClub(@PathVariable("clubId") Integer id) {
        log.info("http req. PUT /api/clubs/{clubId} update club with id: " + id);
        ClubWins clubWins = clubService.winMatch(id);
        return new ResponseEntity<>(clubWins, HttpStatus.OK);
    }


    @GetMapping("/superstar/{clubId}")
    public ResponseEntity<String> getSuperstar(@PathVariable("clubId") Integer id) {
        log.info("http req GET /api/clubs/{clubId} superstar with id: " + id);
        String result = clubService.isThereASuperStar(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


}

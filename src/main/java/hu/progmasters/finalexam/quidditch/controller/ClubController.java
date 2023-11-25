package hu.progmasters.finalexam.quidditch.controller;

import hu.progmasters.finalexam.quidditch.dto.ClubCreateCommand;
import hu.progmasters.finalexam.quidditch.dto.ClubInfo;
import hu.progmasters.finalexam.quidditch.service.ClubService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/clubs")
@Slf4j
public class ClubController {

    private ClubService clubService;

    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @PostMapping
    public ResponseEntity<ClubInfo> saveClub(@Valid @RequestBody ClubCreateCommand command) {
        log.info("Http request, POST / /api/clubs, body: " + command.toString());
        ClubInfo clubInfo = clubService.saveClub(command);
        return new ResponseEntity<>(clubInfo, HttpStatus.CREATED);
    }
}

package hu.progmasters.finalexam.quidditch.controller;

import hu.progmasters.finalexam.quidditch.dto.PlayerCreateCommand;
import hu.progmasters.finalexam.quidditch.dto.PlayerInfo;
import hu.progmasters.finalexam.quidditch.service.PlayerService;
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
@RequestMapping("/api/players")
@Slf4j
public class PlayerController {

    private PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @PostMapping
    public ResponseEntity<PlayerInfo> savePlayer(@Valid @RequestBody PlayerCreateCommand command) {
        log.info("Http request, POST / /api/players, body: " + command.toString());
        PlayerInfo playerInfo = playerService.savePlayer(command);
        return new ResponseEntity<PlayerInfo>(playerInfo, HttpStatus.CREATED);
    }
}

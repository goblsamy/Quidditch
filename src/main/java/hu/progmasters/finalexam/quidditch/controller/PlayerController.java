package hu.progmasters.finalexam.quidditch.controller;

import hu.progmasters.finalexam.quidditch.dto.PlayerCreateCommand;
import hu.progmasters.finalexam.quidditch.dto.PlayerInfo;
import hu.progmasters.finalexam.quidditch.service.PlayerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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

    @PutMapping("/{playerId}/club/{clubId}")
    public ResponseEntity<PlayerInfo> playerChangesClub(@PathVariable("playerId") Integer playerId,
                                                        @PathVariable("clubId") Integer clubId) {
        log.info("Http request, PUT / /api/players/{playerId}/club/{clubId} with variable: " + playerId + " and " + clubId);
        PlayerInfo playerInfo = playerService.update(playerId, clubId);
        return new ResponseEntity<>(playerInfo, HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<List<PlayerInfo>> findAll() {
        log.info("Http request, GET / /api/players");
        List<PlayerInfo> playerInfoList = playerService.listPlayers();
        return new ResponseEntity<>(playerInfoList, HttpStatus.OK);
    }
}

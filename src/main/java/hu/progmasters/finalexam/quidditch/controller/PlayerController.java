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

    private final PlayerService playerService;

    @Autowired
    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }


    @PostMapping
    public ResponseEntity<PlayerInfo> savePlayer(@Valid @RequestBody PlayerCreateCommand command) {
        log.info("http req POST /api/players with body: " + command.toString());
        PlayerInfo playerInfo = playerService.savePlayer(command);
        return new ResponseEntity<>(playerInfo, HttpStatus.CREATED);
    }


    @PutMapping("/{playerId}/club/{clubId}")
    public ResponseEntity<PlayerInfo> playerTransfers(@PathVariable Long playerId, @PathVariable Long clubId) {
        log.info("http req PUT /api/players/" + playerId + "/" + clubId);
        PlayerInfo playerInfo = playerService.transferPlayer(playerId, clubId);
        return new ResponseEntity<>(playerInfo, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<PlayerInfo>> listAll() {
        log.info("http req GET /api/players");
        List<PlayerInfo> playerInfoList = playerService.findAll();
        return new ResponseEntity<>(playerInfoList, HttpStatus.OK);

    }
}

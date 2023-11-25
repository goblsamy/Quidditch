package hu.progmasters.finalexam.quidditch.service;

import hu.progmasters.finalexam.quidditch.domain.Club;
import hu.progmasters.finalexam.quidditch.domain.Player;
import hu.progmasters.finalexam.quidditch.dto.PlayerCreateCommand;
import hu.progmasters.finalexam.quidditch.dto.PlayerInfo;
import hu.progmasters.finalexam.quidditch.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@Slf4j
public class PlayerService {


    private PlayerRepository playerRepository;

    private ModelMapper modelMapper;

    private ClubService clubService;


    @Autowired
    public PlayerService(PlayerRepository playerRepository, ModelMapper modelMapper, ClubService clubService) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.clubService = clubService;
    }


    public PlayerInfo savePlayer(PlayerCreateCommand command) {
        Player playerToSave = modelMapper.map(command, Player.class);
        Club clubById = clubService.findClubById(command.getClubId());
        playerToSave.setClub(clubById);
        Player savedPlayer = playerRepository.save(playerToSave);
        PlayerInfo playerInfo = modelMapper.map(savedPlayer, PlayerInfo.class);
        playerInfo.setClubName(savedPlayer.getClub().getName());
        return playerInfo;
    }
}

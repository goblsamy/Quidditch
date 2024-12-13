package hu.progmasters.finalexam.quidditch.service;


import hu.progmasters.finalexam.quidditch.domain.Club;
import hu.progmasters.finalexam.quidditch.domain.Player;
import hu.progmasters.finalexam.quidditch.dto.PlayerCreateCommand;
import hu.progmasters.finalexam.quidditch.dto.PlayerInfo;
import hu.progmasters.finalexam.quidditch.exceptions.CurrentIdAndGivenMatchesException;
import hu.progmasters.finalexam.quidditch.exceptions.NoFreePlaceOnPositionException;
import hu.progmasters.finalexam.quidditch.exceptions.PlayerNotFoundByIdException;
import hu.progmasters.finalexam.quidditch.repository.PlayerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ModelMapper modelMapper;
    private final ClubService clubService;

    @Autowired
    public PlayerService(PlayerRepository playerRepository, ModelMapper modelMapper, ClubService clubService) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.clubService = clubService;
    }

    public PlayerInfo savePlayer(PlayerCreateCommand command) {
        Player player = modelMapper.map(command, Player.class);
        Club clubById = clubService.findClubById(command.getClubId());
        player.setClub(clubById);
        playerRepository.save(player);
        PlayerInfo playerInfoToSave = modelMapper.map(player, PlayerInfo.class);
        playerInfoToSave.setClubName(clubById.getName());
        List<PlayerInfo> playerInfoList = listPlayers();
        List<PlayerInfo> collect = playerInfoList.stream().filter(playerInfo1 -> playerInfo1.getPlayerType()
                        .equals(playerInfoToSave.getPlayerType()) && playerInfo1.getClubName()
                        .equals(playerInfoToSave.getClubName()))
                .collect(Collectors.toList());

        if (collect.size() >= playerInfoToSave.getPlayerType().getMaxPlayerFromType()) {
            throw new NoFreePlaceOnPositionException(playerInfoToSave.getPlayerType(), Math.toIntExact(player.getClub().getId()));
        }


        return playerInfoToSave;
    }


    public List<PlayerInfo> listPlayers() {
        return playerRepository.findAll().stream()
                .map(player -> {
                    PlayerInfo playerInfoToSave = modelMapper.map(player, PlayerInfo.class);
                    playerInfoToSave.setClubName(player.getClub().getName());
                    return playerInfoToSave;
                }).collect(Collectors.toList());
    }

    public PlayerInfo transferPlayer(Long playerId, Long clubId) {
        Player player = findPlayerById(playerId);
        Club club = clubService.findClubById(clubId);
        int count = playerRepository.countByPlayerTypeAndClub(player.getPlayerType(), clubId);
        if (count >= player.getPlayerType().getMaxPlayerFromType()) {
            throw new NoFreePlaceOnPositionException(player.getPlayerType(), Math.toIntExact(player.getClub().getId()));
        } else if (clubId.equals(player.getClub().getId())) {
            throw new CurrentIdAndGivenMatchesException(clubId);
        }
        player.setClub(club);
        player.setJoined(LocalDate.now());
        PlayerInfo playerInfo = modelMapper.map(player, PlayerInfo.class);
        playerInfo.setClubName(club.getName());
        return playerInfo;
    }

    public Player findPlayerById(Long playerId) {
        Optional<Player> playerOptional = playerRepository.findById(playerId);
        if (playerOptional.isPresent()) {
            return playerOptional.get();
        } else {
            throw new PlayerNotFoundByIdException(playerId);
        }
    }

    public List<PlayerInfo> findAll() {
        return playerRepository.findAllOrderByJoinedDescWinsAsc().stream()
                .map(player -> {
                    PlayerInfo playerInfoToSave = modelMapper.map(player, PlayerInfo.class);
                    playerInfoToSave.setClubName(player.getClub().getName());
                    return playerInfoToSave;
                }).collect(Collectors.toList());
    }
}

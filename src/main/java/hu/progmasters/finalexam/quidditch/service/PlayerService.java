package hu.progmasters.finalexam.quidditch.service;

import hu.progmasters.finalexam.quidditch.domain.Club;
import hu.progmasters.finalexam.quidditch.domain.Player;
import hu.progmasters.finalexam.quidditch.domain.PlayerType;
import hu.progmasters.finalexam.quidditch.dto.PlayerCreateCommand;
import hu.progmasters.finalexam.quidditch.dto.PlayerInfo;
import hu.progmasters.finalexam.quidditch.exceptionhandling.ClubNotFoundException;
import hu.progmasters.finalexam.quidditch.exceptionhandling.NotEnoughSpaceOnThisPost;
import hu.progmasters.finalexam.quidditch.repository.ClubRepository;
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


    private PlayerRepository playerRepository;

    private ClubRepository clubRepository;

    private ModelMapper modelMapper;


    @Autowired
    public PlayerService(PlayerRepository playerRepository, ModelMapper modelMapper, ClubRepository clubRepository) {
        this.playerRepository = playerRepository;
        this.modelMapper = modelMapper;
        this.clubRepository = clubRepository;

    }

    private Club findClubById(Integer id) {
        Optional<Club> clubOptional = clubRepository.findById(id.longValue());
        if (clubOptional.isEmpty()) {
            //TODO sqaját exception!
            throw new ClubNotFoundException(id);
        }
        return clubOptional.get();
    }

    public PlayerInfo savePlayer(PlayerCreateCommand command) {
        Player playerToSave = modelMapper.map(command, Player.class);
        Club clubById = findClubById(command.getClubId());
        if (!playerRepository.isThereFreeSpaceInTeam(command.getPlayerType(),
                command.getClubId(), command.getPlayerType().getMaxPlayerFromType())) {
            playerToSave.setClub(clubById);
            Player savedPlayer = playerRepository.save(playerToSave);
            PlayerInfo playerInfo = modelMapper.map(savedPlayer, PlayerInfo.class);
            playerInfo.setClubName(savedPlayer.getClub().getName());
            return playerInfo;
        } else {
            throw new NotEnoughSpaceOnThisPost();

        }
    }


    public Player findPlayerById(Integer id) {
        Optional<Player> playerOptional = playerRepository.findById(id.longValue());
        if (playerOptional.isEmpty()) {
            //TODO Exceptiont írni!
            throw new RuntimeException();
        }
        return playerOptional.get();
    }

    public PlayerInfo update(Integer playerId, Integer clubId) {
        Player player = findPlayerById(playerId);
        Club club = findClubById(clubId);
        if (playerId.equals(clubId)) {
            //TODO Exceptiont írni!
            throw new RuntimeException();
        }
        player.setClub(club);
        player.setJoined(LocalDate.now());
        return modelMapper.map(player, PlayerInfo.class);
    }

    public List<PlayerInfo> listPlayers() {
        return playerRepository.findAll().stream()
                .map(player -> modelMapper.map(player, PlayerInfo.class))
                .collect(Collectors.toList());
    }

}

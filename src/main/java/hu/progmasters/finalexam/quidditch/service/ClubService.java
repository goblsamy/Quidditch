package hu.progmasters.finalexam.quidditch.service;

import hu.progmasters.finalexam.quidditch.domain.Club;
import hu.progmasters.finalexam.quidditch.domain.Player;
import hu.progmasters.finalexam.quidditch.dto.ClubCreateCommand;
import hu.progmasters.finalexam.quidditch.dto.ClubInfo;
import hu.progmasters.finalexam.quidditch.dto.ClubPlayerInfo;
import hu.progmasters.finalexam.quidditch.dto.PlayerInfo;
import hu.progmasters.finalexam.quidditch.repository.ClubRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
public class ClubService {

    private ClubRepository clubRepository;

    private ModelMapper modelMapper;




    @Autowired
    public ClubService(ClubRepository clubRepository, ModelMapper modelMapper) {
        this.clubRepository = clubRepository;
        this.modelMapper = modelMapper;


    }


    public ClubInfo saveClub(ClubCreateCommand command) {
        Club club = modelMapper.map(command, Club.class);
        Club savedClub = clubRepository.save(club);
        return modelMapper.map(savedClub, ClubInfo.class);
    }

    public Club findClubById(Integer clubId) {
        Optional<Club> clubOptional = clubRepository.findById(clubId.longValue());
        if (clubOptional.isEmpty()) {
            //TODO sqaját exception!
            throw new RuntimeException();
        }
        return clubOptional.get();
    }

    public ClubPlayerInfo update(Integer id) {
        Club clubById = findClubById(id);
        clubById.setWins(clubById.getWins() + 1);
        for (Player player : clubById.getPlayers()) {
            player.setWins(player.getWins() + 1);
        }
        ClubPlayerInfo clubPlayerInfo = modelMapper.map(clubById, ClubPlayerInfo.class);
        List<PlayerInfo> playerInfoList = clubById.getPlayers().stream()
                .map(player -> {
                    PlayerInfo playerInfo = new PlayerInfo();
                    playerInfo.setId(player.getId());
                    playerInfo.setWins(player.getWins());
                    playerInfo.setName(player.getName());
                    playerInfo.setJoined(player.getJoined());
                    playerInfo.setPlayerType(player.getPlayerType());
                    playerInfo.setClubName(player.getClub().getName());
                    return playerInfo;
                }).collect(Collectors.toList());
        clubPlayerInfo.setPlayers(playerInfoList);
        return clubPlayerInfo;
    }


    public String getSuperStar(Integer id) {
        Club club = findClubById(id);
        String result = "";
        for (Player player : club.getPlayers()) {
            if (player.getWins() > club.getWins()) {
                result = "Yeah, we have a superstar";
                return result;
            } else {
                result = "We are not a big club";
            }
        }
        return result;
    }
}

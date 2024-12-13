package hu.progmasters.finalexam.quidditch.service;

import hu.progmasters.finalexam.quidditch.domain.Club;
import hu.progmasters.finalexam.quidditch.domain.Player;
import hu.progmasters.finalexam.quidditch.dto.ClubCreateCommand;
import hu.progmasters.finalexam.quidditch.dto.ClubInfo;
import hu.progmasters.finalexam.quidditch.dto.ClubWins;
import hu.progmasters.finalexam.quidditch.dto.PlayerInfo;
import hu.progmasters.finalexam.quidditch.exceptions.ClubNotFoundException;
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

    private final ClubRepository clubRepository;
    private final ModelMapper modelMapper;


    @Autowired
    public ClubService(ClubRepository clubRepository, ModelMapper modelMapper) {
        this.clubRepository = clubRepository;
        this.modelMapper = modelMapper;

    }

    public ClubInfo saveClub(ClubCreateCommand command) {
        Club club = modelMapper.map(command, Club.class);
        clubRepository.save(club);
        return modelMapper.map(club, ClubInfo.class);
    }

    public Club findClubById(long clubId) {
        Optional<Club> clubOptional = clubRepository.findById(clubId);
        if (clubOptional.isPresent()) {
            return clubOptional.get();
        } else {
            throw new ClubNotFoundException(clubId);
        }
    }

    public ClubWins winMatch(Integer id) {
        Club club = findClubById(id);
        club.setWins(club.getWins() + 1);
        List<Player> players = club.getPlayers();
        players.forEach(player -> player.setWins(player.getWins() + 1));
        List<PlayerInfo> collect = players.stream()
                .map(player -> {
                    PlayerInfo playerInfo = modelMapper.map(player, PlayerInfo.class);
                    playerInfo.setClubName(player.getClub().getName());
                    return playerInfo;
                })
                .collect(Collectors.toList());
        ClubWins clubWins = modelMapper.map(club, ClubWins.class);
        clubWins.setCoachName(club.getCoach().getName());
        clubWins.setPlayers(collect);
        return clubWins;
    }

    public String isThereASuperStar(Integer id) {
        boolean isThereASuperStar = clubRepository.hasSuperstarPlayer(Long.valueOf(id));
        return isThereASuperStar ? "Yes, we have a superstar!" : "We are not a big club";
    }
}

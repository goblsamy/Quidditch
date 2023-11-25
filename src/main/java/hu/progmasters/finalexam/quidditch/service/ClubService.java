package hu.progmasters.finalexam.quidditch.service;

import hu.progmasters.finalexam.quidditch.domain.Club;
import hu.progmasters.finalexam.quidditch.domain.Player;
import hu.progmasters.finalexam.quidditch.dto.ClubCreateCommand;
import hu.progmasters.finalexam.quidditch.dto.ClubInfo;
import hu.progmasters.finalexam.quidditch.repository.ClubRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
}

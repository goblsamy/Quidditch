package hu.progmasters.finalexam.quidditch.service;

import hu.progmasters.finalexam.quidditch.domain.Club;
import hu.progmasters.finalexam.quidditch.domain.Coach;
import hu.progmasters.finalexam.quidditch.domain.Player;
import hu.progmasters.finalexam.quidditch.repository.CoachRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class CoachService {

    private CoachRepository coachRepository;

    private ModelMapper modelMapper;



    @Autowired
    public CoachService(CoachRepository coachRepository, ModelMapper modelMapper) {
        this.coachRepository = coachRepository;
        this.modelMapper = modelMapper;

    }


    public Coach findCoachById(Integer id) {
        Optional<Coach> coachOptional = coachRepository.findById(id.longValue());
        if (coachOptional.isEmpty()) {
            //TODO Exceptiont írni!
            throw new RuntimeException();
        }
        return coachOptional.get();
    }

//    public void delete(Integer id) {
//        Coach coach =
//    }
}

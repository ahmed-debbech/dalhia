package tn.dalhia.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Donation;
import tn.dalhia.entities.Event;
import tn.dalhia.entities.User;
import tn.dalhia.repositories.DonationRepository;
import tn.dalhia.repositories.EventRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.services.IDonationService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DonationService implements IDonationService {

    private final DonationRepository donationRepository;
    private final EventRepository eventRepository;
    private final UserRepository  userRepository;
    @Override
    public List<Donation> getAll() {
        return donationRepository.findAll();
    }


    public Donation add(Donation donation, Long eventId) {
        Event event = eventRepository.findById(eventId).get();
        donation.setEvent(event);
        if(event.getCollectedAmount()>event.getGoalAmount()){
            return null;
        }else{
            event.setCollectedAmount(event.getCollectedAmount()+donation.getAmount());
            eventRepository.save(event);
            donation.setDonationDate(LocalDateTime.now());
            return donationRepository.save(donation);
        }

    }
    public Donation get(Long id) {
        log.info("Fetching donation by id:{}",id);
        return donationRepository.findById(id).orElse(null);
    }
}

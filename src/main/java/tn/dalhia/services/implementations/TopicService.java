package tn.dalhia.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Topic;
import tn.dalhia.entities.TopicClaim;
import tn.dalhia.entities.TopicRate;
import tn.dalhia.entities.enumerations.VoteType;
import java.time.temporal.ChronoUnit;
import tn.dalhia.repositories.TopicRateRepository;
import tn.dalhia.repositories.TopicRepository;
import tn.dalhia.services.ITopicService;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TopicService implements ITopicService {

    @Autowired
    private TopicRepository topicRepository;
    @Autowired
    private TopicRateRepository topicRateRepository;

    @Override
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic add(Topic topic) {
        topic.setBanned(false);
        topic.setDateRemoved(null);
        topic.setScore(0);
        topic.setDatePublished(LocalDateTime.now());
        return topicRepository.save(topic);
    }

    @Override
    public Topic modify(Topic topic, Long id) {
        Topic t1 = this.get(id);
        if(t1 == null){
            return null;
        }
        t1.setTitle(topic.getTitle());
        t1.setText(topic.getText());
        t1.setTag(topic.getTag());
        return topicRepository.save(topic);
    }

    @Override
    public Topic get(Long id) {
        return topicRepository.findById(id).orElse(null);
    }

    @Override
    public boolean delete(Long id) {
        Topic tt = topicRepository.findById(id).orElse(null);
        if(tt != null){
            tt.setDateRemoved(LocalDateTime.now());
            topicRepository.save(tt);
            return true;
        }
        return false;
    }

    @Override
    public boolean bans(Long id, boolean ban) {
        Topic tt = topicRepository.findById(id).orElse(null);
        if(tt != null){
            if(ban){
                tt.setDateRemoved(LocalDateTime.now());
            }
            tt.setBanned(ban);
            topicRepository.save(tt);
            return true;
        }
        return false;
    }

    @Override
    public TopicRate addRate(VoteType voteType, Long id) {
        Topic tt = topicRepository.findById(id).orElse(null);
        if(tt != null){
            TopicRate tr = new TopicRate();
            tr.setDate(LocalDateTime.now());
            tr.setVoteType(voteType);
            if(voteType == VoteType.UPVOTE){
                tt.setScore(tt.getScore()+1);
            }else{
                tt.setScore(tt.getScore()-1);
            }
            tt.getTopicRateList().add(tr);
            topicRepository.save(tt);
            return tr;
        }
        return null;
    }

    @Override
    public boolean RemoveRate(Long id) {
        Topic tt = topicRepository.findById(id).orElse(null);
        if(tt != null){
            //TODO get the user id to be able to find and delete the rate
            //topicRateRepository.deleteById();
            return true;
        }
        return false;
    }

    @Override
    public List<TopicRate> getRate(Long id) {
        Topic tt = topicRepository.findById(id).orElse(null);
        if(tt != null){
            return tt.getTopicRateList();
        }
        return null;
    }

    @Scheduled(cron = "0 0 0/1 * * *")
    //@Scheduled(cron = "0 0 * * * *")
    public void deleteTopicsWithLowInterest(){
        log.info("check topics without interaction");
        List<Topic> topics = topicRepository.findAll();
        for(Topic tp : topics){
            LocalDate ld1 = tp.getDatePublished().toLocalDate();
            LocalDate ld2 = LocalDateTime.now().toLocalDate();
            if(DAYS.between(ld1, ld2) == 2){
                if(tp.getScore() <= 0){
                    tp.setBanned(true);
                    tp.setDateRemoved(LocalDateTime.now());
                    topicRepository.save(tp);
                }
            }
        }
    }
}

package tn.dalhia.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.Topic;
import tn.dalhia.entities.TopicClaim;
import tn.dalhia.repositories.TopicClaimRepository;
import tn.dalhia.services.ITopicClaimService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class TopicClaimService implements ITopicClaimService {

    @Autowired
    private TopicService topicService;
    @Autowired
    private TopicClaimRepository topicClaimRepository;

    @Override
    public List<TopicClaim> getAll(Long id) {
        Topic tt = topicService.get(id);
        if(tt != null){
            return tt.getTopicClaims();
        }
        return null;
    }

    @Override
    public TopicClaim add(Long id, TopicClaim topicClaim) {
        Topic tt = topicService.get(id);
        if(tt != null){
            tt.getTopicClaims().add(topicClaim);
            topicService.add(tt);
            return topicClaim;
        }
        return null;
    }
}

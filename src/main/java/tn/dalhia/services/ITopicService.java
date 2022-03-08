package tn.dalhia.services;

import tn.dalhia.entities.Topic;
import tn.dalhia.entities.TopicClaim;
import tn.dalhia.entities.TopicRate;
import tn.dalhia.entities.enumerations.VoteType;

import java.util.List;

public interface ITopicService {
    List<Topic> getAll();
    Topic add(Topic topic);
    Topic modify(Topic topic, Long id);
    Topic get(Long id);
    boolean delete(Long id);
    boolean bans(Long id, boolean ban);
    TopicRate addRate(VoteType voteType, Long id);
    boolean RemoveRate(Long id);
    List<TopicRate> getRate(Long id);
    void getTopicOfTheDay();
}

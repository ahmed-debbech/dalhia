package tn.dalhia.services;

import tn.dalhia.entities.TopicClaim;

import java.util.List;

public interface ITopicClaimService {
    List<TopicClaim> getAll(Long id);
    TopicClaim add(Long id, TopicClaim topicClaim);
}

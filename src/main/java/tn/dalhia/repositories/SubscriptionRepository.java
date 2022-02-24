package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.dalhia.entities.Subscription;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
	Subscription findBySubscritpionId(String subscriptionId);
}

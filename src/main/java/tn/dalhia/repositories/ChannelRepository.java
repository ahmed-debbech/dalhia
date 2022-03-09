package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Long> {
}

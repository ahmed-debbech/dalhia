package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.OwnedChannels;

@Repository
public interface OwnedChannelRepository extends JpaRepository<OwnedChannels, Long> {
}

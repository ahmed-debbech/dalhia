package tn.dalhia.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.dalhia.entities.Topic;

import java.util.List;

@Repository
public interface TopicRepository extends JpaRepository<Topic, Long> {
    @Query(value = "select * from topic where day(date_published) = day(curdate())", nativeQuery = true)
    List<Topic> getTopicsOfToday();

    @Query(value = "select * from topic where (day(date_published) = day(curdate())) and ((text LIKE %?1%) or (title LIKE %?1%));", nativeQuery = true)
    List<Topic> getMajorTopics(String word);
}

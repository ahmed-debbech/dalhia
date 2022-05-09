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
import tn.dalhia.entities.User;
import tn.dalhia.entities.enumerations.VoteType;
import java.time.temporal.ChronoUnit;
import tn.dalhia.repositories.TopicRateRepository;
import tn.dalhia.repositories.TopicRepository;
import tn.dalhia.services.ITopicService;
import tn.dalhia.shared.tools.UtilsUser;
import tn.dalhia.utils.GeneralUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

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
    @Autowired
    private UtilsUser utilsUser;

    @Override
    public List<Topic> getAll() {
        return topicRepository.findAll();
    }

    @Override
    public Topic add(Topic topic) {
        topic.setBanned(false);
        topic.setDateRemoved(null);
        topic.setScore(0);
        User logg = utilsUser.getLoggedInUser();
        topic.setDatePublished(LocalDateTime.now());
        topic.setUser(logg);
        topic = topicRepository.save(topic);
        System.err.println(topic);
        return topic;
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
            User logg = utilsUser.getLoggedInUser();
            tr.setUser(logg);
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
            User logg = utilsUser.getLoggedInUser();
            System.err.println(logg);
            for(TopicRate tr : tt.getTopicRateList()){
                if(tr.getUser().getId().equals(logg.getId())){
                    tt.getTopicRateList().remove(tr);
                    tt.setScore(tt.getScore() - 1);
                    topicRepository.save(tt);
                }
            }
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

    //@Scheduled(cron = "0 0 0 * * *")
    @Override
    public Topic getTopicOfTheDay(){
        //we get all topics of this day
        List<Topic> topicsOfToday = topicRepository.getTopicsOfToday();
        System.err.println(topicsOfToday.toString());
        //we parse each word and put them in lists each
        List<List<String>> listOfWords = new ArrayList<>();
        for(Topic t : topicsOfToday){
            listOfWords.add(GeneralUtils.listWords(t.getTitle() + " " +t.getText()));
        }
        System.err.println(listOfWords.toString());
        //we apply an algorithm to get the highest occurence of words
        class Ranking implements Comparable{
            String word;
            int occ;

            public Ranking(String w){this.word = w; this.occ = 1;}
            @Override
            public int hashCode() {
                Random r = new Random();
                return r.nextInt(99999) + word.length();
            }

            @Override
            public boolean equals(Object obj) {
                Ranking pp = (Ranking) obj;
                if(this.word.contains(pp.word)){
                    this.occ++;
                   return true;
                }
                return false;
            }

            @Override
            public String toString() {
                return "{ "+this.word+", "+this.occ+" }";
            }

            @Override
            public int compareTo(Object o) {
                Ranking pp = (Ranking) o;
                //System.err.println(pp.word);
                if(pp.word.contains(this.word)){
                    this.occ++;
                    return 0;
                }
                return 1;
            }
        }
        TreeSet<Ranking> set = new TreeSet<>();
        for(List<String> list : listOfWords) {
            for (String w : list) {
                set.add(new Ranking(w));
            }
        }
        System.err.println(set);
        System.err.println("total: " + set.size());
        int max = -1;
        for(Ranking r : set){
            if(max < r.occ){
                max = r.occ;
            }
        }
        System.err.println("most: " + max);
        //the word found is considered TREND 3
        List<String> ww = new ArrayList<String>();
        ww.add(set.pollFirst().word);
        ww.add(set.pollFirst().word);
        ww.add(set.pollFirst().word);

        List<Topic> majorTopics = new ArrayList<>();
        for(int i=0; i<=ww.size()-1; i++) {
            majorTopics.addAll(topicRepository.getMajorTopics(ww.get(i)));
            System.err.println("the word is: " + ww.get(i));
        }
        System.err.println("final result set: " + majorTopics);

        //we get the highest topic ratings with the trend word
        Topic postOfTheDay = majorTopics.get(0);
        for(int i=1; i<= majorTopics.size()-1; i++){
            if(postOfTheDay.getScore() < majorTopics.get(i).getScore()){
                postOfTheDay = majorTopics.get(i);
            }
        }
        System.err.println("THE POST OF THE DAY IS: " + postOfTheDay);
        //we affect that to database
        postOfTheDay.setLastBeTopicOfTheDay(LocalDateTime.now());
        return topicRepository.save(postOfTheDay);
    }
}

package tn.dalhia.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.ForumAd;
import tn.dalhia.repositories.ForumAdsRepository;
import tn.dalhia.services.IForumAdService;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ForumAdService implements IForumAdService {

    @Autowired
    private ForumAdsRepository repository;

    @Override
    public List<ForumAd> getAll() {
        return repository.findAll();
    }

    @Override
    public ForumAd add(ForumAd ad) {
        ad.setActualViews(0);
        ad.setNumClicks(0);
        ad.setStartDate(LocalDateTime.now());
        return repository.save(ad);
    }

    @Override
    public ForumAd modidy(ForumAd ad, Long id) {
        ForumAd ff = repository.findById(id).orElse(null);
        if(ff == null){
            return null;
        }
        ff.setForumAdTarget(ad.getForumAdTarget());
        ff.setEndDate(ad.getEndDate());
        ff.setRedirectUrl(ad.getRedirectUrl());
        ff.setImageUrl(ad.getImageUrl());
        ff.setText(ad.getText());
        ff.setTitle(ad.getTitle());
        ff.setVideoUrl(ad.getVideoUrl());
        ff.setViewChannel(ad.getViewChannel());
        return repository.save(ff);
    }

    @Override
    public boolean delete(Long id) {
        ForumAd ff = repository.findById(id).orElse(null);
        if(ff == null){
            return false;
        }
        repository.delete(ff);
        return true;
    }

    @Override
    public ForumAd getOne(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public void click(Long id) {
        ForumAd ff = repository.findById(id).orElse(null);
        if(ff != null){
            int cnt = ff.getNumClicks();
            cnt++;
            ff.setNumClicks(cnt);
            repository.save(ff);
        }
    }
}

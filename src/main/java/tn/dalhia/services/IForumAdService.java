package tn.dalhia.services;

import tn.dalhia.entities.ForumAd;

import java.util.List;

public interface IForumAdService {
    List<ForumAd> getAll();
    ForumAd add(ForumAd ad);
    ForumAd modidy(ForumAd ad, Long id);
    boolean delete(Long id);
    ForumAd getOne(Long id);
    void click(Long id);
}

package tn.dalhia.services;

import tn.dalhia.entities.OffensiveWord;

import java.util.List;

public interface IOffensiveWordService {
    OffensiveWord add(String word);
    boolean delete(Long id);
    List<OffensiveWord> getAll();
}

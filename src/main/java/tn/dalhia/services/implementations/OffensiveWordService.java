package tn.dalhia.services.implementations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.OffensiveWord;
import tn.dalhia.repositories.OffensiveWordRepository;
import tn.dalhia.services.IOffensiveWordService;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class OffensiveWordService implements IOffensiveWordService {

    @Autowired
    private OffensiveWordRepository repo;

    @Override
    public OffensiveWord add(String word) {
        OffensiveWord ow = new OffensiveWord();
        ow.setWord(word);
        return repo.save(ow);
    }

    @Override
    public boolean delete(Long id) {
        OffensiveWord ow = repo.findById(id).orElse(null);
        if(ow != null){
            repo.delete(ow);
            return true;
        }
        return false;
    }

    @Override
    public List<OffensiveWord> getAll() {
        return repo.findAll();
    }
}

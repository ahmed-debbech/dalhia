package tn.dalhia.services.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.dalhia.entities.HistoryOffer;
import tn.dalhia.entities.Offer;
import tn.dalhia.entities.User;
import tn.dalhia.repositories.HistoryOfferRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.services.IHistoryOfferService;

@Service

public class HistoryOfferService implements IHistoryOfferService {
    @Autowired
    private HistoryOfferRepository historyRepo ;
    @Autowired
    private UserRepository userRepo ;

    @Override
    public HistoryOffer addHistory(HistoryOffer c) {

            return historyRepo.save(c);

    }
    @Override
    public HistoryOffer updateHistory(HistoryOffer c) {

        return historyRepo.save(c);

    }



}

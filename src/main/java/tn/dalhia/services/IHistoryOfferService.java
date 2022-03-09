package tn.dalhia.services;

import tn.dalhia.entities.HistoryOffer;
import tn.dalhia.entities.User;

public interface IHistoryOfferService {
    HistoryOffer addHistory(HistoryOffer c);
    HistoryOffer updateHistory(HistoryOffer c);

}

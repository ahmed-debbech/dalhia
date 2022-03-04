package tn.dalhia.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.dalhia.entities.Offer;
import tn.dalhia.repositories.OfferRepository;
import tn.dalhia.services.IOfferService;

@Service
public class OfferService implements IOfferService {
	
	@Autowired
	private OfferRepository offerRepo ;

	public List<Offer> retrieveAllOffers() {
		return (List<Offer>) offerRepo.findAll();
		
	}

	public Offer addOffer(Offer c) {
		return offerRepo.save(c);
	}

	public void deleteOffer(Long id) {
		offerRepo.deleteById(id);
		
	}

	public Offer updateOffer(Offer c) {
		return offerRepo.save(c);
	}

	public Offer retrieveOffer(Long id) {
		return offerRepo.findById(id).get();
	}

}

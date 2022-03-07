package tn.dalhia.controllers;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tn.dalhia.entities.HistoryOffer;
import tn.dalhia.entities.Offer;
import tn.dalhia.entities.User;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.services.IHistoryOfferService;
import tn.dalhia.services.IOfferService;


@RestController
@RequestMapping("/Offer")

@Slf4j
public class OfferController{

	@Autowired
	IOfferService offerService;

	@Autowired
	IHistoryOfferService historyService;

	@Autowired
	UserRepository userRepo;

	// http://localhost:8089/api/v1/offer/retrieve-all-offers
	@GetMapping("/retrieve-all-offers")
	public List<Offer> getOffers() {
	List<Offer> listOffers = offerService.retrieveAllOffers();
	return listOffers;
	}

	// http://localhost:8089/api/v1/offer/retrieve-offer/8
	@GetMapping("/retrieve-offer/{offer-id}")
	public Offer retrieveOffer(@PathVariable("offer-id") Long id) {
	return offerService.retrieveOffer(id);
	}
	
	@PostMapping("/add-offer/{category-id}")
	public Offer addOffer (@PathVariable("category-id") Long idCategory,@RequestBody Offer c)
	{
		Offer offer =offerService.addOffer(c);
		offerService.affecterOfferACategory(offer.getId(), idCategory);
	return offer ;
	}

	// http://localhost:8089/api/v1/Offer/remove-Offer/{Offer-id}
	@DeleteMapping("/remove-offer/{offer-id}")
	public void removeOffer(@PathVariable("offer-id") Long id) {
	offerService.deleteOffer(id);
	}

	// http://localhost:8089/api/v1/offer/modify-offer
	@PutMapping("/modify-offer")
	public Offer modifyOffer(@RequestBody Offer offer) {
	return offerService.updateOffer(offer);
	}


	// http://localhost:8089/api/v1/offer/retrieve-all-offers
	@GetMapping("/recommandation/{user-id}")
	public List<Offer> getRecommandation(@PathVariable("user-id") Long userid) {
		List<Offer> listOffers = offerService.recommandations(userid);
		return listOffers;
	}

	// http://localhost:8089/api/v1/offer/retrieve-all-offers
	@PostMapping("/searchOffer/{user_id}/{search_text}")
	public List<Offer> searchOffer (@PathVariable("search_text") String search_text,
									@PathVariable("user_id") int userId) {

		List<Offer> listOffers = offerService.searchOffer(search_text);
		User userEntity = userRepo.findById((long) userId).get();

		List<HistoryOffer> listHistory = userEntity.getHistory();


		boolean found = listHistory.stream()
				.anyMatch(p -> p.getName().equals(search_text));
		System.out.println(found);

		if (listHistory.size()==0 || !(found) ){
			Date date = new Date();
			HistoryOffer h = new HistoryOffer(search_text,date,0);
			HistoryOffer history =  historyService.addHistory(h);
			offerService.affecterHistoryAUser(userId,history.getId().intValue());
			System.out.println("add");

		}
		else  {

			Optional<HistoryOffer> matchingObject = listHistory.stream().
					filter(p -> p.getName().equals(search_text)).
					findFirst();
			System.out.println(matchingObject.get().getName());

			int count = matchingObject.get().getNb()+1;
			HistoryOffer historyUpdated = new HistoryOffer(
					matchingObject.get().getId(),
					count,
					matchingObject.get().getName(),
					matchingObject.get().getDate());
			historyService.updateHistory(historyUpdated);

			}
			/*listHistory.forEach(l -> {
				System.out.println("-"+l.getName());
				if (l.getName().equalsIgnoreCase(search_text) ) {
					System.out.println("+"+l.getName());
					int count = l.getNb() + 1;
					HistoryOffer historyUpdated = new HistoryOffer(l.getId(), count, l.getName(),l.getDate());
					historyService.updateHistory(historyUpdated);

				} else {
					Date date = new Date();
					HistoryOffer h = new HistoryOffer(search_text, date, 0);
					HistoryOffer history = historyService.addHistory(h);
					offerService.affecterHistoryAUser(userId, history.getId().intValue());
				}
			});*/


		return listOffers;
	}


}


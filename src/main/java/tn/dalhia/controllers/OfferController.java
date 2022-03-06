package tn.dalhia.controllers;

import java.util.List;

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
import tn.dalhia.entities.Offer;
import tn.dalhia.services.IOfferService;


@RestController
@RequestMapping("/Offer")
@Slf4j
public class OfferController {

	@Autowired
	IOfferService offerService;

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




}


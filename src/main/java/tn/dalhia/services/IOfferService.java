package tn.dalhia.services;

import java.util.List;

import tn.dalhia.entities.Offer;


public interface IOfferService {
	
	List<Offer> retrieveAllOffers();

	Offer addOffer(Offer c);

	void deleteOffer(Long id);

	Offer updateOffer(Offer c);

	
	Offer retrieveOffer(Long id);
/*
	List<Offer> retrieveAllOffersByBirthSQL(Date date1, Date date2);


	List<Offer> retrieveAllOffersByBirthJPQL(Date date1, Date date2);*/

}
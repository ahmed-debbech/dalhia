package tn.dalhia.services;

import java.util.List;

import tn.dalhia.entities.Offer;


public interface IOfferService {
	
	List<Offer> retrieveAllOffers();
	List<Offer> recommandations(Long userid);
	List<Offer> searchOffer(String text);

	Offer addOffer(Offer c);

	void deleteOffer(Long id);

	Offer updateOffer(Offer c);

	
	Offer retrieveOffer(Long id);

    void affecterOfferACategory(Long idOffer, Long idCategory);
	void affecterOfferAHistory(int idUser,int idHistory);
	void affecterHistoryAUser(int idUser, int idHistory);
/*
	List<Offer> retrieveAllOffersByBirthSQL(Date date1, Date date2);


	List<Offer> retrieveAllOffersByBirthJPQL(Date date1, Date date2);*/

}

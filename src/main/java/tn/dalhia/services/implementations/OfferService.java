package tn.dalhia.services.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.dalhia.entities.JobCategory;
import tn.dalhia.entities.Offer;
import tn.dalhia.entities.User;
import tn.dalhia.repositories.JobCategoryRepository;
import tn.dalhia.repositories.OfferRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.services.IOfferService;

@Service
public class OfferService implements IOfferService {
	
	@Autowired
	private OfferRepository offerRepo ;
	@Autowired
	private JobCategoryRepository jobRepo ;
	@Autowired
	private UserRepository userRepo ;



	public List<Offer> retrieveAllOffers() {
		return (List<Offer>) offerRepo.findAll();
		
	}

	@Override
	public List<Offer> recommandations(Long userid) {
		User userEntity = userRepo.findById((long) userid).get();
		List<Offer> Loffers=	offerRepo.findAllOfferBySpecialty(userEntity.getSpeciality().name());
		return Loffers;
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

	@Override
	public void affecterOfferACategory(Long idOffer, Long idCategory) {

			//Le bout Master de cette relation N:1 est departement
			//donc il faut rajouter l'entreprise a departement
			// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
			//Rappel : la classe qui contient mappedBy represente le bout Slave
			//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
			JobCategory JobCategoryEntity = jobRepo.findById((long) idCategory).get();
			Offer offerEntity = offerRepo.findById((long) idOffer).get();
			offerEntity.setJobCategory(JobCategoryEntity);
			offerRepo.save(offerEntity);


	}

}

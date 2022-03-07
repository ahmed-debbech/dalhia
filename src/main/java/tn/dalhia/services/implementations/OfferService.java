package tn.dalhia.services.implementations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.dalhia.entities.*;
import tn.dalhia.repositories.HistoryOfferRepository;
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
	@Autowired
	private HistoryOfferRepository historyRepo ;



	public List<Offer> retrieveAllOffers() {
		return (List<Offer>) offerRepo.findAll();

	}

	@Override
	public List<Offer> recommandations(Long userid) {
		User userEntity = userRepo.findById((long) userid).get();
		List<Offer> Loffers=	offerRepo.findAllOfferBySpecialty(userEntity.getSpeciality().name());
		return Loffers;
	}

	@Override
	public List<Offer> recommandationsHistory(Long userid) {
		User userEntity = userRepo.findById((long) userid).get();
		List<HistoryOffer> LHistory = userEntity.getHistory().stream().sorted( (HistoryOffer h1 , HistoryOffer h2) -> Long.compare(h2.getNb(),h1.getNb())).collect(Collectors.toList());
		System.out.println(LHistory.stream().findFirst().get().getName());
		List<Offer> Loffers= offerRepo.findAllOfferBySpecialty(LHistory.stream().findFirst().get().getName());
		return Loffers;
	}

	@Override
	public List<Offer> searchOffer(String text) {
		String likeExpression = "%"+text+"%";
		LocalDateTime ldt = LocalDateTime.now();
		System.out.println("ldt " +ldt);

		Date date = null ;
		String d = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(ldt);
		System.out.println("ddd " +d);

		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(d);
			System.out.println("yyyy-MM-dd" +date);

			return offerRepo.searchOfferByText(likeExpression,date);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return offerRepo.searchOfferByText(likeExpression,date);

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
	@Override
	public void affecterOfferAHistory(int idUser,int idHistory) {


		HistoryOffer history = historyRepo.findById((long) idHistory).get();
		System.out.println(history.getName());
		User userEntity = userRepo.findById((long) idUser).get();
		System.out.println(userEntity.getId());

		userEntity.getHistory().add(history);


		historyRepo.save(history);

	}

	@Override
	public void affecterHistoryAUser(int idUser, int idHistory) {

		HistoryOffer h = historyRepo.findById((long) idHistory).get();
		User userEntity = userRepo.findById((long) idUser).get();
		userEntity.getHistory().add(h);
		historyRepo.save(h);

	}


}

package tn.dalhia.services.implementations;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import tn.dalhia.entities.Application;
import tn.dalhia.entities.JobCategory;
import tn.dalhia.entities.Offer;
import tn.dalhia.entities.User;
import tn.dalhia.repositories.ApplicationRepository;
import tn.dalhia.repositories.OfferRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.services.IApplicationService;

@Service
public class ApplicationService implements IApplicationService {
	@Autowired
	private ApplicationRepository applicationRepo ;
	@Autowired
	private OfferRepository offerRepo ;
	@Autowired
	private UserRepository userRepo ;

	public List<Application> retrieveAllApplications() {
		return (List<Application>) applicationRepo.findAll();
	}

	@Override
	public List<Application> findAvailableApplications(Long Userid) {
		User userEntity = userRepo.findById((long) Userid).get();
		List<Application> apps =  userEntity.getApplications().stream().filter((Application app) ->{
			return app.isEtat() == true;}).collect(Collectors.toList());
		return  apps ;
	}

	@Override
	public List<Application> findNotAvailableApplications(Long Userid) {
		User userEntity = userRepo.findById((long) Userid).get();
		List<Application> apps =  userEntity.getApplications().stream().filter((Application app) ->{
			return app.isEtat() == false;}).collect(Collectors.toList());
		return  apps ;
	}

	@Override
	public List<Application> AllApplicationSend(Long Userid) {
		User userEntity = userRepo.findById((long) Userid).get();
		List<Application> apps =  userEntity.getApplications().stream()
				.sorted(Comparator.comparing(Application::getDate).reversed())
				.collect(Collectors.toList());
		return apps;
	}

	public Application addApplication(Application c) {

		Application a = new Application(c.getTitle(), new Date(), false, c.getEmail());
		return applicationRepo.save(a);
	}

	public void deleteApplication(Long id) {
		applicationRepo.deleteById(id);

	}

	public Application updateApplication(Application c) {

		return applicationRepo.save(c);
	}

	public Application retrieveApplication(Long id) {
		return applicationRepo.findById(id).get();
	}

	@Override
	public void affecterApplicationAOffer(int idOffer, int idApplication) {

		//Le bout Master de cette relation N:1 est departement
		//donc il faut rajouter l'entreprise a departement
		// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
		//Rappel : la classe qui contient mappedBy represente le bout Slave
		//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
		Application ApplicationEntity = applicationRepo.findById((long) idApplication).get();
		Offer offerEntity = offerRepo.findById((long) idOffer).get();
		ApplicationEntity.setOffer(offerEntity);
		applicationRepo.save(ApplicationEntity);


	}

	@Override
	public void affecterApplicationAUser(int idUser, int idApplication) {
		Application ApplicationEntity = applicationRepo.findById((long) idApplication).get();
		User userEntity = userRepo.findById((long) idUser).get();
		userEntity.getApplications().add(ApplicationEntity);
		applicationRepo.save(ApplicationEntity);
	}



		/*@Autowired
		private JavaMailSender emailSender;

		public void sendSimpleMessage(String to, String subject, String text) {

			SimpleMailMessage message = new SimpleMailMessage();
			message.setFrom("ali.alidg55@gmail.com");
			message.setTo(to);
			message.setSubject(subject);
			message.setText(text);
			emailSender.send(message);

		}*/

		public void ValidCondidat (Long idApplication){
			Application ApplicationEntity = applicationRepo.findById((long) idApplication).get();
			ApplicationEntity.setEtat(true);
			applicationRepo.save(ApplicationEntity);
			//sendSimpleMessage(ApplicationEntity.getEmail(),"validation","vous etes accepter version test");

		}

	public void InValidCondidat (Long idApplication){
		Application ApplicationEntity = applicationRepo.findById((long) idApplication).get();
		ApplicationEntity.setEtat(false);
		applicationRepo.save(ApplicationEntity);
		//sendSimpleMessage(ApplicationEntity.getEmail(),"validation","vous etes accepter version test");

	}

}

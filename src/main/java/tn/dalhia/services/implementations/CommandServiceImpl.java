package tn.dalhia.services.implementations;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import tn.dalhia.entities.Command;
import tn.dalhia.entities.Product;
import tn.dalhia.entities.User;
import tn.dalhia.exceptions.UserServiceException;
import tn.dalhia.repositories.CommandRepository;
import tn.dalhia.repositories.ProductRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.request.CommandRequestModel;
import tn.dalhia.response.ErrorMessages;
import tn.dalhia.services.CommandService;
import tn.dalhia.shared.dto.CommandDto;
import tn.dalhia.shared.tools.UtilsUser;

@Service
public class CommandServiceImpl implements CommandService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CommandRepository commandRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
    UtilsUser utils;
	
	@Override
	@Transactional
	public CommandDto createCommand(CommandRequestModel commandDetails, String id, Authentication authentification) {
		User userEntity = userRepo.findByUserId(id);
		if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		if (commandDetails.getProducts() == null) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FILED.getErrorMessage());
		if(!utils.connectedUser(authentification,userEntity)) throw new UserServiceException(ErrorMessages.SECURITY_ERROR.getErrorMessage());
		
		for (Product product : commandDetails.getProducts()) {
			Product testProduct = productRepo.getById(product.getId());
			if (testProduct == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
			if (testProduct.getQuantity() == 0) throw new UserServiceException(ErrorMessages.QUANTITY_OVER.getErrorMessage());
			testProduct.setQuantity(testProduct.getQuantity() - 1);
			productRepo.save(testProduct);
		}
		
		Command commandEntity = new Command();
		BeanUtils.copyProperties(commandDetails,commandEntity);
		
		// ?? add command question 
		commandEntity.setCommandId(utils.generateCommandId(30));
		commandEntity.setUsers(userEntity);
//		for (Product products : commandDetails.getProducts()) {
//			commandEntity.getProducts().add(products);
//		}
//		if(commandEntity.getProducts() != null) {
//		commandEntity.getProducts().add(ProductEntity);}
//		else{
//			List<Product> lP = new ArrayList<Product>();//nes2el monsieur
//			lP.add(ProductEntity);
//			commandEntity.setProducts(lP);
//		}

		Command storedCommand = commandRepo.save(commandEntity);
		ModelMapper modelMapper = new ModelMapper();
		CommandDto returnValue = modelMapper.map(storedCommand, CommandDto.class);
		return returnValue;
	}

	@Override
	public CommandDto updateCommand(CommandRequestModel commandDetails, String id, Authentication authentification) {
		if(!utils.connectedUser(authentification,null)) throw new UserServiceException(ErrorMessages.SECURITY_ERROR.getErrorMessage());
		Command commandEntity = commandRepo.findByCommandId(id);
		if (commandEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		commandEntity.setCard(commandDetails.getCard());
		commandEntity.setCode(commandDetails.getCode());
		commandEntity.setEmail(commandDetails.getEmail());
		commandEntity.setName(commandDetails.getName());
		
		Command updateCommand = commandRepo.save(commandEntity);
		ModelMapper modelMapper = new ModelMapper();
		CommandDto returnValue = modelMapper.map(updateCommand, CommandDto.class);
		
		return returnValue;
	}

	@Override
	public CommandDto getCommandById(String id, Authentication authentification) {
		ModelMapper modelMapper = new ModelMapper();
		Command command = commandRepo.findByCommandId(id);
		if (command == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		if(!utils.connectedUser(authentification,command.getUsers())) throw new UserServiceException(ErrorMessages.SECURITY_ERROR.getErrorMessage());
		CommandDto returnValue = modelMapper.map(command,CommandDto.class);
		return returnValue;
	}

	@Override
	public void deleteCommand(String id, Authentication authentification) {
		Command command = commandRepo.findByCommandId(id) ;
		if (command == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		if(!utils.connectedUser(authentification,command.getUsers())) throw new UserServiceException(ErrorMessages.SECURITY_ERROR.getErrorMessage());
		commandRepo.delete(command);
	}

	@Override
	public List<CommandDto> getCommandsPagination(int page, int limit, Authentication authentification) {
		List<CommandDto> returnValue = new ArrayList<>();
		if(!utils.connectedUser(authentification,null)) throw new UserServiceException(ErrorMessages.SECURITY_ERROR.getErrorMessage());
		if(page>0) page = page-1;
		
		Pageable pageableRequest = PageRequest.of(page, limit);
		Page<Command>  commandsPage= commandRepo.findAll(pageableRequest);
		
		List<Command> commands = commandsPage.getContent();
		
		for (Command command : commands) {
			
			ModelMapper modelMapper = new ModelMapper();
			CommandDto commandDto = modelMapper.map(command,CommandDto.class);
			 //BeanUtils.copyProperties(command, commandDto);
			 returnValue.add(commandDto);
		}
		return returnValue;
	}

}

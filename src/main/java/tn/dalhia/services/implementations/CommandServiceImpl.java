package tn.dalhia.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import tn.dalhia.entities.Command;
import tn.dalhia.entities.CommandProduct;
import tn.dalhia.entities.Product;
import tn.dalhia.entities.User;
import tn.dalhia.exceptions.UserServiceException;
import tn.dalhia.repositories.CommandProductRepository;
import tn.dalhia.repositories.CommandRepository;
import tn.dalhia.repositories.ProductRepository;
import tn.dalhia.repositories.UserRepository;
import tn.dalhia.request.CommandRequestModel;
import tn.dalhia.request.CommandRequestProducts;
import tn.dalhia.response.CommandProductRest;
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
	CommandProductRepository commandProductRepo;
	
	@Autowired
    UtilsUser utils;
	
	@Override
	@Transactional
	public CommandDto createCommand(CommandRequestModel commandDetails, String id, Authentication authentification) {
		User userEntity = userRepo.findByUserId(id);
		if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		if (commandDetails.getProducts() == null) throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FILED.getErrorMessage());
		if(!utils.connectedUser(authentification,userEntity)) throw new UserServiceException(ErrorMessages.SECURITY_ERROR.getErrorMessage());
		
		for (CommandRequestProducts commandRequestProducts : commandDetails.getProducts()) {
			Product testProduct = productRepo.findByTitle(commandRequestProducts.getTitle());
			if (testProduct == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
			if (testProduct.getQuantity() == 0 || testProduct.getQuantity()-commandRequestProducts.getQuantity()<0) throw new UserServiceException(ErrorMessages.QUANTITY_OVER.getErrorMessage());
		}
		Command commandEntity = new Command();
		
		BeanUtils.copyProperties(commandDetails,commandEntity);
		
		commandEntity.setCommandId(utils.generateCommandId(30));
		commandEntity.setUsers(userEntity);
		


		Command storedCommand = commandRepo.save(commandEntity);
		
		for (CommandRequestProducts commandRequestProducts : commandDetails.getProducts()) {
			CommandProduct commandProductEntity = new CommandProduct();
			Product testProduct = productRepo.findByTitle(commandRequestProducts.getTitle());
			testProduct.setQuantity(testProduct.getQuantity() - commandRequestProducts.getQuantity());
			productRepo.save(testProduct);
			commandProductEntity.setCommands(storedCommand);
			commandProductEntity.setProducts(testProduct);
			commandProductEntity.setQuantityProduct(commandRequestProducts.getQuantity());
			commandProductRepo.save(commandProductEntity);
		}
		
		ModelMapper modelMapper = new ModelMapper();
		CommandDto returnValue = modelMapper.map(storedCommand, CommandDto.class);
		final String ACCOUNT_SID ="AC228e60b8a2ebb67be77e99883a9ce3fa";
	    final String AUTH_TOKEN = "40e87a192b2f6779dbd40fcc49bace35";
		
		  Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	        Message message = Message.creator(
	                new com.twilio.type.PhoneNumber("+21654649865"), //to
	                new com.twilio.type.PhoneNumber("+16812525336"), //from
	                "Thank you for purchasing from us. "
	                + "This is to confirm that your order has been processed, and will reach you in a matter of 3days. We insist on reminding you that you have contributed in the help towards a good cause!")
	            .create();

	        System.out.println(message.getBody());
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
	
	@Override
	public List<CommandProductRest> getCommands( Authentication authentification) {
		List<CommandProductRest> returnValue = new ArrayList<>();
		List<Command> commands = commandRepo.findAll();
		
		for(Command command : commands) {
			CommandProductRest ret = new CommandProductRest();
			ret.setCommandId(command.getCommandId());
			ret.setEmail(command.getEmail());
			ret.setName(command.getName());
			ret.setUserId(command.getUsers().getUserId());
			List<CommandProduct> commandProducts  = command.getQuantities();
			 List<Long> idProducts = new ArrayList<>();
			 List<Integer> quantity= new ArrayList<>();
			 for (CommandProduct commandPro : commandProducts) {
				 idProducts.add(commandPro.getProducts().getId());
				 quantity.add(commandPro.getQuantityProduct());
				 
			 }
			 ret.setIdProducts(idProducts);
			 ret.setQuantity(quantity);
			 returnValue.add(ret);
		}
		return returnValue;
	}

}

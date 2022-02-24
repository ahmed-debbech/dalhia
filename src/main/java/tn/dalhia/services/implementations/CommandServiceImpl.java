package tn.dalhia.services.implementations;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import tn.dalhia.shared.dto.Utils;

@Service
public class CommandServiceImpl implements CommandService{

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	CommandRepository commandRepo;
	
	@Autowired
	ProductRepository productRepo;
	
	@Autowired
	Utils utils;
	
	@Override
	@Transactional
	public CommandDto createCommand(CommandRequestModel commandDetails, String id) {
		User userEntity = userRepo.findByUserId(id);
		Product ProductEntity = productRepo.findByProductId(commandDetails.getProductId());
		if (userEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		if (ProductEntity == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		
		Command commandEntity = new Command();
		BeanUtils.copyProperties(commandDetails,commandEntity);
		commandEntity.setCommandId(utils.generateCommandId(30));
		commandEntity.setUsers(userEntity);
		if(commandEntity.getProducts() != null) {
		commandEntity.getProducts().add(ProductEntity);}
		else{
			List<Product> lP = new ArrayList<Product>();//nes2el monsieur
			lP.add(ProductEntity);
			commandEntity.setProducts(lP);
		}
		
		Command storedCommand = commandRepo.save(commandEntity);
		ModelMapper modelMapper = new ModelMapper();
		CommandDto returnValue = modelMapper.map(storedCommand, CommandDto.class);
		return returnValue;
	}

	@Override
	public CommandDto updateCommand(CommandRequestModel commandDetails, String id) {
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
	public CommandDto getCommandById(String id) {
		ModelMapper modelMapper = new ModelMapper();
		Command command = commandRepo.findByCommandId(id);
		CommandDto returnValue = modelMapper.map(command,CommandDto.class);
		return returnValue;
	}

	@Override
	public void deleteCommand(String id) {
		Command command = commandRepo.findByCommandId(id) ;
		if (command == null) throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		commandRepo.delete(command);
	}

}

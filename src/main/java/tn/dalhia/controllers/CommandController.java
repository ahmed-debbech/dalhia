package tn.dalhia.controllers;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import tn.dalhia.request.CommandRequestModel;
import tn.dalhia.response.CommandRest;
import tn.dalhia.response.OperationStatusModel;
import tn.dalhia.response.RequestOperationName;
import tn.dalhia.response.RequestOperationStatus;
import tn.dalhia.services.CommandService;
import tn.dalhia.shared.dto.CommandDto;

@RestController
@RequestMapping("commands")
@Api(tags ="Gestion des commandes")
public class CommandController {
	
	@Autowired
	CommandService commandService;
	
	@PostMapping("/{id}")
	public CommandRest createCommand (@RequestBody CommandRequestModel commandDetails, @PathVariable String id, Authentication authentification) {

		ModelMapper modelMapper = new ModelMapper();
		CommandDto createCommand = commandService.createCommand(commandDetails,id,authentification);
		CommandRest returnValue = modelMapper.map(createCommand, CommandRest.class);
		return returnValue;
	}
	
	@PutMapping("/{id}")
	public CommandRest updateCommand (@RequestBody CommandRequestModel commandDetails, @PathVariable String id, Authentication authentification) {
		ModelMapper modelMapper = new ModelMapper();
		CommandDto createCommand = commandService.updateCommand(commandDetails,id,authentification);
		CommandRest returnValue = modelMapper.map(createCommand, CommandRest.class);
		return returnValue;
	}
	
	@GetMapping("/{id}")
	public CommandRest getCommand(@PathVariable String id, Authentication authentification) {
		ModelMapper modelMapper = new ModelMapper();
		CommandDto command = commandService.getCommandById(id,authentification);
		CommandRest returnValue = modelMapper.map(command, CommandRest.class);
		
		return returnValue;
	}

	@GetMapping("/get-commands-pagination")
	public List<CommandRest> getCommands(@RequestParam(value="page",defaultValue="0") int page ,
			@RequestParam(value="limit",defaultValue="3") int limit, Authentication authentification) {
		List<CommandRest> returnValue = new ArrayList<>();
		 List<CommandDto> commands = commandService.getCommandsPagination(page,limit,authentification);
		 
		 for(CommandDto commandDto : commands) {
			 CommandRest commandModel = new CommandRest();
			 BeanUtils.copyProperties(commandDto, commandModel);
			 returnValue.add(commandModel);
		 }
		return returnValue;
	}
	
	@DeleteMapping("/{id}")
	public OperationStatusModel deleteCommand(@PathVariable String id, Authentication authentification) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		commandService.deleteCommand(id,authentification);

		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}
}

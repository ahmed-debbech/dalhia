package tn.dalhia.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public CommandRest createCommand (@RequestBody CommandRequestModel commandDetails, @PathVariable String id) {

		ModelMapper modelMapper = new ModelMapper();
		CommandDto createCommand = commandService.createCommand(commandDetails,id);
		CommandRest returnValue = modelMapper.map(createCommand, CommandRest.class);
		return returnValue;
	}
	
	@PutMapping("/{id}")
	public CommandRest updateCommand (@RequestBody CommandRequestModel commandDetails, @PathVariable String id) {
		ModelMapper modelMapper = new ModelMapper();
		CommandDto createCommand = commandService.updateCommand(commandDetails,id);
		CommandRest returnValue = modelMapper.map(createCommand, CommandRest.class);
		return returnValue;
	}
	
	@GetMapping("/{id}")
	public CommandRest getCommand(@PathVariable String id) {
		ModelMapper modelMapper = new ModelMapper();
		CommandDto command = commandService.getCommandById(id);
		CommandRest returnValue = modelMapper.map(command, CommandRest.class);
		
		return returnValue;
	}
	@DeleteMapping("/{id}")
	public OperationStatusModel deleteCommand(@PathVariable String id) {
		OperationStatusModel returnValue = new OperationStatusModel();
		returnValue.setOperationName(RequestOperationName.DELETE.name());
		
		commandService.deleteCommand(id);
		returnValue.setOperationResult(RequestOperationStatus.SUCCESS.name());
		
		return returnValue;
	}
}

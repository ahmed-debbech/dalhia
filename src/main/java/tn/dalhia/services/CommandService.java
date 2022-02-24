package tn.dalhia.services;

import tn.dalhia.request.CommandRequestModel;
import tn.dalhia.shared.dto.CommandDto;

public interface CommandService {

	CommandDto createCommand(CommandRequestModel commandDetails, String id);

	CommandDto updateCommand(CommandRequestModel commandDetails, String id);

	CommandDto getCommandById(String id);

	void deleteCommand(String id);

}

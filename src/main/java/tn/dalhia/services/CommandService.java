package tn.dalhia.services;

import java.util.List;

import org.springframework.security.core.Authentication;
import tn.dalhia.request.CommandRequestModel;
import tn.dalhia.shared.dto.CommandDto;

public interface CommandService {

	CommandDto createCommand(CommandRequestModel commandDetails, String id, Authentication authentification);

	CommandDto updateCommand(CommandRequestModel commandDetails, String id, Authentication authentification);

	CommandDto getCommandById(String id, Authentication authentification);

	void deleteCommand(String id, Authentication authentification);

	List<CommandDto> getCommandsPagination(int page, int limit, Authentication authentification);

}

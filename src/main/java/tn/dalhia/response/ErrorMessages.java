package tn.dalhia.response;

public enum ErrorMessages {

	MISSING_REQUIRED_FILED("Missing required field. Please check documentation for required fields"),
	RECORD_ALREADY_EXISTS("Record already exists"),
	INTERNAL_SERVER_ERROR("Internal server error"),
	NO_RECORD_FOUND("Record with provided id is not found"),
	AUTHENTICTION_FAILED("Authentication failed"),
	COULD_NOT_UPDATE_RECORD("Could not update record"),
	COULD_NOT_DELETE_RECORD("Could not delete record"),
	EMAIL_ADDRESS_NOT_VERIFIED("Email adress could not verified"),
	QUANTITY_OVER("We re sorry , this product is currently out of stock"),
	SECURITY_ERROR("you re not allowed to do that");
	
	
	private String errorMessage;
	

	private ErrorMessages(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}

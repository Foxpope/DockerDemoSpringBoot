package com.revature.errorhandling;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * 
 * This class is designed to represent information about an HTTP Error.
 * The structure of this class can be serialized into JSON and sent
 * back to the client about what went wrong.
 *
 */
@Data // all getters and setters
public class ApiError {
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy hh:mm:ss")
	private LocalDateTime timestamp;
	
	private int status;
	
	private String error;
	
	private String message;
	private String debugMessage;
	// There might be sub errrors (we'll create a class from this to represent ApiVlaidation Errors)
	List<ApiSubError> subErrors = new ArrayList<>();
	


	public ApiError() {
		super();
		this.timestamp = LocalDateTime.now();
	}

	public void addSubError(ApiSubError error) {
		this.subErrors.add(error);
		
	}

	public ApiError(HttpStatus status) {
		super();
		this.status = status.value(); // convert the HttpStatus to an integer by capturing the value
		this.error = status.getReasonPhrase();
	}
	
	public ApiError(HttpStatus status, Throwable ex) {
		
		this(status); // this is constructor chaining -- we are doing everythign that the above constructor is doing with this aparam
		this.message = "No message available";
		this.debugMessage = ex.getLocalizedMessage(); // now we don't have to keep looking at our console to figure out why things went wrong!
	}
	
	public ApiError(HttpStatus status, String message, Throwable ex) {
		
		this(status, ex); // trigger the above constructor
		this.message = message;
		
	}
}

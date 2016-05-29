package tvz.nppjj.paris.model.exception;

/*
 * Custom exception for easier controller error handling and logging purposes
 *
 * @author jkovacek
 *
 */
@SuppressWarnings("serial")
public class ParisException extends RuntimeException {

	public ParisException(String message) {
		super(message);
	}

	public ParisException(String message, Throwable cause) {
		super(message, cause);
	}

}

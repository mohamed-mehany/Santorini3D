package eg.edu.guc.santorini.exceptions;

@SuppressWarnings("serial")
public class InvalidMoveException extends Exception {
	public InvalidMoveException() {
	}

	public InvalidMoveException(String m) {
		super(m);
	}

}

package gr.unipi.gym;

public class UIException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UIException() {
		super();
	}

	public UIException(String message) {
		super(message);
	}

	public UIException(Throwable cause) {
		super(cause);
	}

	public UIException(String message, Throwable cause) {
		super(message, cause);
	}
}

package nl.novi.backend.les12.exception;

public class DuplicateException extends RuntimeException {

    //
    private static final long serialVersionUID = 1L;
    public DuplicateException() {
        super();
    }
    
    public DuplicateException(String message) {
        super(message);
    }
}

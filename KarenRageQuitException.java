// CAT: I designed this custom exception class extending Exception to model unique domain-specific termination events.
public class KarenRageQuitException extends Exception {
    public KarenRageQuitException(String message) {
        super(message);
    }
}

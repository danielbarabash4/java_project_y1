// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

public class NotProfDocException extends CollageException {
    public NotProfDocException(String msg) {
        super(msg);
    }

    public NotProfDocException() {
        super("Lecturer doesn't meet the criterion");
    }
}

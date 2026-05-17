// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

public class ComExistException extends CollageException {
    public ComExistException(String msg) {
        super(msg);
    }

    public ComExistException() {
        super("Committee already exist");
    }
}

// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

public class ComNotExistException extends CollageException {
    public ComNotExistException(String msg) {
        super(msg);
    }

    public ComNotExistException() {
        super("Committee does not exist");
    }
}

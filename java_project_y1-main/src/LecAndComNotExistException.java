// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

public class LecAndComNotExistException extends CollageException {
    public LecAndComNotExistException(String msg) {
        super(msg);
    }

    public LecAndComNotExistException() {
        super("Committee and lecturer does not exist");
    }
}

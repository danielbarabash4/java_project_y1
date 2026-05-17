// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

public class LecNotExistException extends CollageException {
    public LecNotExistException(String msg) {
        super(msg);
    }

    public LecNotExistException() {
        super("Lecturer does not exist");
    }
}

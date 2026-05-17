// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

public class DepAndLecNotExistException extends CollageException {
    public DepAndLecNotExistException(String msg) {
        super(msg);
    }

    public DepAndLecNotExistException() {
        super("department and Lecturer Does not exist");
    }
}

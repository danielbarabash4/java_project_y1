public class DoesntExistException extends CollageException {
    public DoesntExistException(String msg) {
        super(msg);
    }

    public DoesntExistException() {
        super("Lecturer does not exist");
    }
}

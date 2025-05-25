public class ComExistException extends CollageException {
    public ComExistException(String msg) {
        super(msg);
    }

    public ComExistException() {
        super("Committee already exist");
    }
}

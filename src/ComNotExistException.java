public class ComNotExistException extends CollageException {
    public ComNotExistException(String msg) {
        super(msg);
    }

    public ComNotExistException() {
        super("Committee does not exist");
    }
}

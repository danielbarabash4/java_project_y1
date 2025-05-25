public class LecAndComNotExistException extends CollageException {
    public LecAndComNotExistException(String msg) {
        super(msg);
    }

    public LecAndComNotExistException() {
        super("Committee and lecturer does not exist");
    }
}

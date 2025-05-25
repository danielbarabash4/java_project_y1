public class LecNotExistException extends CollageException {
    public LecNotExistException(String msg) {
        super(msg);
    }

    public LecNotExistException() {
        super("Lecturer does not exist");
    }
}

public class DepAndLecNotExistException extends CollageException {
    public DepAndLecNotExistException(String msg) {
        super(msg);
    }

    public DepAndLecNotExistException() {
        super("department and Lecturer Does not exist");
    }
}

public class NotProfDocException extends CollageException {
    public NotProfDocException(String msg) {
        super(msg);
    }

    public NotProfDocException() {
        super("Lecturer doesn't meet the criterion");
    }
}

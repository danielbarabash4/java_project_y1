public class AlreadyMemberException extends CollageException {
    public AlreadyMemberException(String msg) {
        super(msg);
    }

    public AlreadyMemberException() {
        super("the lecturer is already a member");
    }
}

// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

public class AlreadyMemberException extends CollageException {
    public AlreadyMemberException(String msg) {
        super(msg);
    }

    public AlreadyMemberException() {
        super("the lecturer is already a member");
    }
}

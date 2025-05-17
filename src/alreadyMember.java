public class alreadyMember extends CollageException {
    public alreadyMember(String msg) {
        super(msg);
    }

    public alreadyMember() {
        super("the lecturer is already a member");
    }
}

public class HeadOfException extends CollageException {
    public HeadOfException(String msg) {
        super(msg);
    }

    public HeadOfException(){
        super("This lecturer is already the head of the committee");
    }
}

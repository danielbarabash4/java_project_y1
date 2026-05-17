// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

public class HeadOfException extends CollageException {
    public HeadOfException(String msg) {
        super(msg);
    }

    public HeadOfException(){
        super("This lecturer is already the head of the committee");
    }
}

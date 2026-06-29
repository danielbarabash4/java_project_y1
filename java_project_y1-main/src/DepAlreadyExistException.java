// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

public class DepAlreadyExistException extends CollageException{
    public DepAlreadyExistException(String msg) {
        super(msg);
    }

    public DepAlreadyExistException(){
        super("Department already exist");
    }
}

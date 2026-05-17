// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

public class DepNotExistException extends CollageException{
    public DepNotExistException(String msg) {
        super(msg);
    }

    public DepNotExistException(){
        super("Department Does not exist");
    }
}

public class DepAlreadyExistException extends CollageException{
    public DepAlreadyExistException(String msg) {
        super(msg);
    }

    public DepAlreadyExistException(){
        super("Department already exist");
    }
}

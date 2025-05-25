public class DepNotExistException extends CollageException{
    public DepNotExistException(String msg) {
        super(msg);
    }

    public DepNotExistException(){
        super("Department Does not exist");
    }
}

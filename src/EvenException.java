public class EvenException extends CollageException{
    public EvenException(String msg){
        super(msg);
    }
    public EvenException(){
        super("Even amount");
    }
}

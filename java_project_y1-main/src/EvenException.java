// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

public class EvenException extends CollageException{
    public EvenException(String msg){
        super(msg);
    }
    public EvenException(){
        super("Even amount");
    }
}

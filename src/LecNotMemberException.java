// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

public class LecNotMemberException extends CollageException{
    public LecNotMemberException(String msg) {
        super(msg);
    }

    public LecNotMemberException(){
        super("Lecturer is not a member of the committee");
    }

}


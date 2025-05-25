public class LecNotMemberException extends CollageException{
    public LecNotMemberException(String msg) {
        super(msg);
    }

    public LecNotMemberException(){
        super("Lecturer is not a member of the committee");
    }

}


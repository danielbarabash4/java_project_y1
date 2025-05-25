import java.util.Arrays;
import java.util.Objects;

public class Committee implements Cloneable {
    private String CommitteeName;
    private Lecturer[] committeeMembers;
    private Lecturer headOfCommittee;
    private int lecSize = 0; //logical variables

    public Committee(String committeeName, Lecturer headOfCommittee) {
        setCommitteeName(committeeName);
        this.committeeMembers = new Lecturer[1];
        setHeadOfCommittee(headOfCommittee);
        this.lecSize = 0;
    }


        public Object clone()throws CloneNotSupportedException {
            Committee cloned = (Committee) super.clone();
            cloned.CommitteeName = this.CommitteeName + "-new";
            cloned.headOfCommittee = this.headOfCommittee;
            cloned.committeeMembers = new Lecturer[this.lecSize];
            for (int i = 0; i < this.lecSize; i++) {
                cloned.committeeMembers[i] = this.committeeMembers[i];
            }
            return cloned;
    }


    public int getLecSize() {
        return lecSize;
    }

    public void setLecSize(int lecSize) {
        this.lecSize = lecSize;
    }

    public void removeLecFromMembers(Lecturer lec){
        for (int i=0; i <committeeMembers.length;i++){
            if (committeeMembers[i]!= null && committeeMembers[i].equals(lec)){
                committeeMembers[i] = null;
                shiftLecMembers(i);
                break;
            }
        }
    }

    public void shiftLecMembers(int lecIndex){
        for(int i = lecIndex; i<committeeMembers.length;i++){
            if(i==committeeMembers.length-1){
                committeeMembers[i]=null;
            }
            else {
                committeeMembers[i] = committeeMembers[i + 1];
            }
        }
        lecSize--;
    }

    public void setCommitteeName(String committeeName) {
        CommitteeName = committeeName;
    }

    public void setCommitteeMembers(Lecturer[] committeeMembers) {
        this.committeeMembers = committeeMembers;
    }

    public void setHeadOfCommittee(Lecturer headOfCommittee) {
        this.headOfCommittee = headOfCommittee;
    }

    public String getCommitteeName() {
        return CommitteeName;
    }

    public Lecturer[] getCommitteeMembers() {
        return committeeMembers;
    }

    public Lecturer getHeadOfCommittee() {
        return headOfCommittee;
    }

    public String getCommittees() {
        String res = "";
        for (int i = 0; i < committeeMembers.length; i++) {
            if (committeeMembers[i] != null) {
                res += committeeMembers[i].getName() + " ";
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "|Committee:" +
                "name= " + CommitteeName +
                ", members= " + getCommittees() +
                ", head of committee= " + headOfCommittee.getName()+"|";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Committee committee = (Committee) o;
        return Objects.equals(CommitteeName, committee.CommitteeName) && Objects.deepEquals(committeeMembers, committee.committeeMembers) && Objects.equals(headOfCommittee, committee.headOfCommittee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CommitteeName, Arrays.hashCode(committeeMembers), headOfCommittee);
    }
}

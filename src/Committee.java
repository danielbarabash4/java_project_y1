import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Committee<T> implements Cloneable, Serializable {
    private String CommitteeName;
    private ArrayList<T> committeeMembers;
    private Lecturer headOfCommittee;
    private Class<T> type;
    private int lecSize = 0; //logical variables

    public Committee(String committeeName, Lecturer headOfCommittee,Class<T>type) {
        setCommitteeName(committeeName);
        this.committeeMembers= new ArrayList<T>();
        setHeadOfCommittee(headOfCommittee);
        this.lecSize = 0;
        this.type=type;

    }



    public Object clone()throws CloneNotSupportedException {
            Committee<T> cloned = (Committee<T>) super.clone();
            cloned.CommitteeName = this.CommitteeName + "-new";
            cloned.headOfCommittee = this.headOfCommittee;
            cloned.committeeMembers=new ArrayList<>();
            cloned.committeeMembers.addAll(committeeMembers);
//            cloned.committeeMembers = new Lecturer[this.lecSize];
//            for (int i = 0; i < this.lecSize; i++) {
//                cloned.committeeMembers[i] = this.committeeMembers[i];
//            }
            return cloned;
    }


    public int getLecSize() {
        return lecSize;
    }

    public void setLecSize(int lecSize) {
        this.lecSize = lecSize;
    }

    public void removeLecFromMembers(Lecturer lec){
            committeeMembers.remove(lec);

//        for (int i=0; i <committeeMembers.length;i++){
//            if (committeeMembers[i]!= null && committeeMembers[i].equals(lec)){
//                committeeMembers[i] = null;
//                shiftLecMembers(i);
//                break;
//            }
//        }
    }

//    public void shiftLecMembers(int lecIndex){
//        for(int i = lecIndex; i<committeeMembers.length;i++){
//            if(i==committeeMembers.length-1){
//                committeeMembers[i]=null;
//            }
//            else {
//                committeeMembers[i] = committeeMembers[i + 1];
//            }
//        }
//        lecSize--;
//    }

    public void setCommitteeName(String committeeName) {
        CommitteeName = committeeName;
    }

    public void setCommitteeMembers(ArrayList<T> committeeMembers) {
        this.committeeMembers = committeeMembers;
    }

    public void setHeadOfCommittee(Lecturer headOfCommittee) {
        this.headOfCommittee = headOfCommittee;
    }

    public String getCommitteeName() {
        return CommitteeName;
    }

    public ArrayList<T>  getCommitteeMembers() {
        return committeeMembers;
    }

    public Lecturer getHeadOfCommittee() {
        return headOfCommittee;
    }

    public String getCommittees() {
        String res = "";
        for (int i = 0; i < committeeMembers.size(); i++) {
            if (committeeMembers.get(i) != null) {
                res += ((Lecturer) committeeMembers.get(i)).getName() + " ";
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
        Committee<?> committee = (Committee<?>) o;
        return lecSize == committee.lecSize && Objects.equals(CommitteeName, committee.CommitteeName) && Objects.equals(committeeMembers, committee.committeeMembers) && Objects.equals(headOfCommittee, committee.headOfCommittee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CommitteeName, committeeMembers, headOfCommittee, lecSize);
    }

    public void addLecturer(T lecturer) {
        T temp;
        if(lecturer.getClass()==type ) {
            committeeMembers.add(lecturer);
        }
    }
}

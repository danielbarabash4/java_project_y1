// Idan Amrani : 322205808
// Daniel Barbash : 322272683
// Daniel Liser : 325100196

import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Committee<T> implements Cloneable, Serializable {
    private String CommitteeName;
    private HashSet<T> committeeMembers;
    private Lecturer headOfCommittee;
    private Class<T> type;
    private int lecSize = 0;

    public Committee(String committeeName, Lecturer headOfCommittee, Class<T> type) {
        setCommitteeName(committeeName);
        this.committeeMembers = new HashSet<T>();
        setHeadOfCommittee(headOfCommittee);
        this.lecSize = 0;
        this.type = type;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        Committee<T> cloned = (Committee<T>) super.clone();
        cloned.CommitteeName = this.CommitteeName + "-new";
        cloned.headOfCommittee = this.headOfCommittee;
        cloned.committeeMembers = new HashSet<>();
        cloned.committeeMembers.addAll(committeeMembers);
        return cloned;
    }

    public Class<T> getType() {
        return type;
    }

    public int getLecSize() {
        return committeeMembers.size();
    }

    public void removeLecFromMembers(Lecturer lec) {
        committeeMembers.remove(lec);
    }

    public void setCommitteeName(String committeeName) {
        CommitteeName = committeeName;
    }

    public void setCommitteeMembers(HashSet<T> committeeMembers) {
        this.committeeMembers = committeeMembers;
    }

    public void setHeadOfCommittee(Lecturer headOfCommittee) {
        this.headOfCommittee = headOfCommittee;
    }

    public String getCommitteeName() {
        return CommitteeName;
    }

    public HashSet<T> getCommitteeMembers() {
        return committeeMembers;
    }

    public String getCommittees() {
        String res = "";
        Iterator<T> it = committeeMembers.iterator();
        while (it.hasNext()) {
            T member = it.next();
            if (member != null) {
                res += ((Lecturer) member).getName() + " ";
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "|Committee:" +
                "name= " + CommitteeName +
                ", members= " + getCommittees() +
                ", head of committee= " + headOfCommittee.getName() + "|";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Committee<?> committee = (Committee<?>) o;
        return lecSize == committee.lecSize &&
                Objects.equals(CommitteeName, committee.CommitteeName) &&
                Objects.equals(committeeMembers, committee.committeeMembers) &&
                Objects.equals(headOfCommittee, committee.headOfCommittee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(CommitteeName, committeeMembers, headOfCommittee, lecSize);
    }

    public void addLecturer(T lecturer) {
        if (lecturer.getClass() == type) {
            committeeMembers.add(lecturer);
        }
    }

    public Lecturer getHeadOfCommittee() {
        return headOfCommittee;
    }

}
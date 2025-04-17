import java.util.Arrays;
import java.util.Objects;

public class Committee {
    String CommitteeName;
    Lecturer[] committeeMembers;
    Lecturer headOfCommittee;

    public Committee(String committeeName, Lecturer headOfCommittee) {
        setCommitteeName(committeeName);
        this.committeeMembers = new Lecturer[1];
        setHeadOfCommittee(headOfCommittee);
    }

    public void removeLecFromMembers(Lecturer removeLec) {
        for (int i = 0; i < committeeMembers.length; i++) {
            if (committeeMembers[i] != null && committeeMembers[i].equals(removeLec))
                committeeMembers[i] = null;
        }
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
    public String getMembers() {
        String res = "";
        for (int i = 0; i < committeeMembers.length; i++) {
            if (committeeMembers[i] != null) {
                res += committeeMembers[i].getName() + " ";
            }
        }
        return res;
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
        return "Committee{" +
                "CommitteeName='" + CommitteeName + '\'' +
                ", committeeMembers=" + getCommittees() +
                ", headOfCommittee=" + headOfCommittee.getName() +
                '}';
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

public class Committee {
    String CommitteeName;
    Lecturer[] committeeMembers;
    Lecturer headOfCommittee;

    public Committee(String committeeName, Lecturer[] committeeMembers, Lecturer headOfCommittee) {
        setCommitteeName(committeeName);
        setCommitteeMembers(committeeMembers);
        setHeadOfCommittee(headOfCommittee);
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
    //update for liser

}

public class Committee {
    String CommitteeName;
    lecturer[] committeeMembers;
    lecturer headOfCommittee;

    public Committee(String committeeName, lecturer[] committeeMembers, lecturer headOfCommittee) {
        setCommitteeName(committeeName);
        setCommitteeMembers(committeeMembers);
        setHeadOfCommittee(headOfCommittee);
    }

    public void setCommitteeName(String committeeName) {
        CommitteeName = committeeName;
    }

    public void setCommitteeMembers(lecturer[] committeeMembers) {
        this.committeeMembers = committeeMembers;
    }

    public void setHeadOfCommittee(lecturer headOfCommittee) {
        this.headOfCommittee = headOfCommittee;
    }

    public String getCommitteeName() {
        return CommitteeName;
    }

    public lecturer[] getCommitteeMembers() {
        return committeeMembers;
    }

    public lecturer getHeadOfCommittee() {
        return headOfCommittee;
    }
}

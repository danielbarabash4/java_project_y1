public class Professor extends Doctor {
    private String academy;

    public Professor(String name, String id, Degree degree, String degreeName, double salary, Department department,int articleSize ,String academy) {
        super(name, id, degree, degreeName, salary, department,articleSize);
        setAcademy(academy);
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }
}

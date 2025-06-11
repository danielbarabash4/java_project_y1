import java.util.ArrayList;
import java.util.Objects;

public class Professor extends Doctor {
    private String academy;

    public Professor(String name, String id, Degree degree, String degreeName, double salary, Department department, int articleSize , String academy, ArrayList<String> artArray) {
        super(name, id, degree, degreeName, salary, department,articleSize,artArray);
        setAcademy(academy);
    }

    public String getAcademy() {
        return academy;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nacademy=" + academy;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Professor professor = (Professor) o;
        return Objects.equals(academy, professor.academy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), academy);
    }
}

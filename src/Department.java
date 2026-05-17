import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;

public class Department implements Serializable {
    private String DepartmentName;
    private int studentsNum;
    private HashSet<Lecturer> lecturers;
    private int lecSize = 0;

    public Department(String departmentName, int studentsNum) {
        setDepartmentName(departmentName);
        setStudentsNum(studentsNum);
        this.lecturers = new HashSet<>();
        this.lecSize = 0;
    }

    public int getLecSize() {
        return lecSize;
    }

    public void setLecSize(int lecSize) {
        this.lecSize = lecSize;
    }

    private boolean lecInDep(Lecturer lecturer) {
        return lecturers.contains(lecturer);
    }

    public void removeLec(Lecturer lec) {
        lecturers.remove(lec);
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public void setStudentsNum(int studentsNum) {
        this.studentsNum = studentsNum;
    }

    public void setLecturers(HashSet<Lecturer> lecturers) {
        this.lecturers = lecturers;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public HashSet<Lecturer> getLecturers() {
        return lecturers;
    }

    @Override
    public String toString() {
        String lecStr = "";
        Iterator<Lecturer> it = lecturers.iterator();
        while (it.hasNext()) {
            lecStr += it.next().getName() + " ";
        }
        return "Department name:= '" + DepartmentName +
                ", Number of students= " + studentsNum +
                ", lecturers: =" + lecStr;
    }
}
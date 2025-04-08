public class Department {
    private String DepartmentName;
    private int studentsNum;
    private Lecturer[] lecturers;

    public Department(String departmentName, int studentsNum, Lecturer[] lecturers) {
        setDepartmentName(departmentName);
        setStudentsNum(studentsNum);
        setLecturers(lecturers);
    }

    public void setDepartmentName(String departmentName) {
        DepartmentName = departmentName;
    }

    public void setStudentsNum(int studentsNum) {
        this.studentsNum = studentsNum;
    }

    public void setLecturers(Lecturer[] lecturers) {
        this.lecturers = lecturers;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public int getStudentsNum() {
        return studentsNum;
    }

    public Lecturer[] getLecturers() {
        return lecturers;
    }

}

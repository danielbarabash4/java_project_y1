public class Department {
    private String DepartmentName;
    private int studentsNum;
    private lecturer[] lecturers;

    public Department(String departmentName, int studentsNum, lecturer[] lecturers) {
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

    public void setLecturers(lecturer[] lecturers) {
        this.lecturers = lecturers;
    }

    public String getDepartmentName() {
        return DepartmentName;
    }

    public int getStudentsNum() {
        return studentsNum;
    }

    public lecturer[] getLecturers() {
        return lecturers;
    }

}

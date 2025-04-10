import java.util.Arrays;

public class Department {
    private String DepartmentName;
    private int studentsNum;
    private Lecturer[] lecturers;

    public Department(String departmentName, int studentsNum, Lecturer[] lecturers) {
        setDepartmentName(departmentName);
        setStudentsNum(studentsNum);
        setLecturers(lecturers);
    }
    public void AddNewLecturer(Lecturer lecturer){
        if(isFull(lecturers)){
            lecturers=extendArr();
        }
        lecturerAdd(lecturer);

    }

    private Lecturer[] extendArr() {
        Lecturer []newArr=new Lecturer[lecturers.length*2];
        for (int i = 0; i < lecturers.length; i++) {
            if(lecturers[i]!=null){
                newArr[i]=lecturers[i];
            }
        }
        return newArr;
    }

    private void lecturerAdd(Lecturer lecturer) {
        for (int i = 0; i < lecturers.length; i++) {
            if(lecturers[i]==null)
                lecturers[i]=lecturer;
                break;

        }
    }

    private boolean isFull(Lecturer[] lecturers) {
        for (int i = 0; i < lecturers.length; i++) {
            if(lecturers[i]==null){
                return true;
            }
        }
        return false;
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

    @Override
    public String toString() {
        return "Department{" +
                "Department name:= '" + DepartmentName + '\'' +
                ", Number of students= " + studentsNum +
                ", lecturers: =" + Arrays.toString(lecturers) +
                '}';
    }
}

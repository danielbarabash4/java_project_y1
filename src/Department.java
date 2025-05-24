import java.util.Arrays;

public class Department {
    private String DepartmentName;
    private int studentsNum;
    private Lecturer[] lecturers;
    private int lecSize = 0; //logical variable

    public Department(String departmentName, int studentsNum) {
        setDepartmentName(departmentName);
        setStudentsNum(studentsNum);
        this.lecturers=new Lecturer[1];
        this.lecSize = 0;
    }

    public int getLecSize() {
        return lecSize;
    }

    public void setLecSize(int lecSize) {
        this.lecSize = lecSize;
    }

    private boolean lecInDep(Lecturer lecturer) {
        for (int i = 0; i <lecturers.length ; i++) {
            if(lecturers[i]!=null && lecturers[i].equals(lecturer)){
                return true;
            }
        }
        return false;
    }

    public void removeLec(Lecturer lec){
        if(lecInDep(lec)){
            for (int i = 0; i <lecturers.length ; i++) {
                if(lecturers[i]!=null && lecturers[i]==lec){
                    lecturers[i]=null;
                    shiftLec(i);
                    break;
                }
            }
        }
    }

    public void shiftLec(int lecIndex){
        for(int i = lecIndex; i <lecturers.length;i++){
            if(i==lecturers.length-1){
                lecturers[i]=null;
            }
            else {
                lecturers[i] = lecturers[i + 1];
            }
        }
        lecSize--;
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

    public Lecturer[] getLecturers() {
        return lecturers;
    }

    @Override
    public String toString() {
        return "Department name:= '" + DepartmentName +
                ", Number of students= " + studentsNum +
                ", lecturers: =" + Arrays.toString(lecturers);
    }
}

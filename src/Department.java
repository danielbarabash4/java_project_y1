import java.util.Arrays;

public class Department {
    private String DepartmentName;
    private int studentsNum;
    private Lecturer[] lecturers;

    public Department(String departmentName, int studentsNum) {
        setDepartmentName(departmentName);
        setStudentsNum(studentsNum);
        this.lecturers=new Lecturer[1];
    }
    public void AddNewLecturer(Lecturer lecturer){
        if(!lecInDep(lecturer)) {
            if (isFull(lecturers)) {
                lecturers = extendArr();
            }
            lecturerAdd(lecturer);
        }
        else{
            System.out.println("Lecturer is already a member of the department 111");
        }
    }

    private boolean lecInDep(Lecturer lecturer) {
        for (int i = 0; i <lecturers.length ; i++) {
            if(lecturers[i]!=null && lecturers[i].equals(lecturer)){
                return true;
            }
        }
        return false;
    }

    public Lecturer[] extendArr() {
        Lecturer []newArr=new Lecturer[lecturers.length*2];
        for (int i = 0; i < lecturers.length; i++) {
            if(lecturers[i]!=null){
                newArr[i]=lecturers[i];
            }
        }
        return newArr;
    }

    public void lecturerAdd(Lecturer lecturer) {
        for (int i = 0; i < lecturers.length; i++) {
            if(lecturers[i]==null) {
                lecturers[i] = lecturer;
                break;
            }
        }
    }
    public void removeLec(Lecturer lec){
        if(lecInDep(lec)){
            for (int i = 0; i <lecturers.length ; i++) {
                if(lecturers[i]!=null && lecturers[i]==lec){
                    lecturers[i]=null;
                }
            }
        }
    }

    public boolean isFull(Lecturer[] lecturers) {
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
    public String getNames() {
        String res = "";
        for (int i = 0; i < lecturers.length; i++) {
            if (lecturers[i] != null) {
                res += lecturers[i].getName() + " ";
            }
        }
        return res;
    }

    @Override
    public String toString() {
        return "Department name:= '" + DepartmentName +
                ", Number of students= " + studentsNum +
                ", lecturers: =" + Arrays.toString(lecturers);
    }
}
